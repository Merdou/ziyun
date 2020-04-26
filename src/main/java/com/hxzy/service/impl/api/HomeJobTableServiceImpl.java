package com.hxzy.service.impl.api;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Data;
import com.hxzy.entity.Jobtable;
import com.hxzy.entity.JobtableViews;
import com.hxzy.service.*;
import com.hxzy.service.api.HomeJobTableService;
import com.hxzy.vo.home.DataHomeVO;
import com.hxzy.vo.home.JobtableVO;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Log4j2
@Transactional
@Service
public class HomeJobTableServiceImpl implements HomeJobTableService {

    private static final Object _sigle=new Object();

    @Autowired
    private DataService dataService;

    @Autowired
    private JobtableService jobtableService;

    @Autowired
    private JobtableViewsService jobtableViewsService;

    @Autowired
    private GuestcommentService guestcommentService;

    @Autowired
    private FollowService followService;

    //redis
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //存放分类键的key
    private static final String DATA_REDIS_KEY="data:category";
    //存放每种分类的前8笔数据
    private static final String DATA_REDIS_TOP8_KEY="data:category:top8:";

    //存放 某个作品点赞，查看，
    private static final String DETAIL_OTHER_REDIS_KEY="work:detail:other:";
    //存放某个学生的 所有的关注量，粉丝量,总的作品数
    private static final String FANS_FLOOW_REDIS_KEY="student:FANSFLOOW:";

    //评论表，粉丝表(二期)


    /**
     * 给首端用的数据
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage search() {
        //从redis中获取 分类
        List<DataHomeVO>  dataList=getData();
       //把每个分类的前8笔数据读取出来,
       for(DataHomeVO  d : dataList){
           //分页查询
           List<Jobtable>  jobTableArr=new ArrayList<>();
           //先判断缓存
           String top8Key=DATA_REDIS_TOP8_KEY+d.getId();
           if(this.stringRedisTemplate.hasKey(top8Key)){
               System.out.println("首页推荐8读的是缓存");
               String jsonArr=this.stringRedisTemplate.opsForValue().get(top8Key);
               jobTableArr=JSONArray.parseArray(jsonArr,Jobtable.class);
           }else{
               synchronized (_sigle){
                   if(this.stringRedisTemplate.hasKey(top8Key)){
                       System.out.println("首页推荐8读的是缓存");
                       String jsonArr=this.stringRedisTemplate.opsForValue().get(top8Key);
                       jobTableArr=JSONArray.parseArray(jsonArr,Jobtable.class);
                   }else{
                       System.out.println("首页推荐8读的是----读数据库----");
                       PageHelper.startPage(1,8);
                       jobTableArr=this.jobtableService.selectTopJobTable(d.getId());
                      //存放到redis中
                       String jsonArr=JSONArray.toJSONString(jobTableArr);
                       //随机一个缓存(1-2秒)
                       Random rd=new Random();
                       int seconds=rd.nextInt(61)+60;
                       this.stringRedisTemplate.opsForValue().set(top8Key,jsonArr,seconds,TimeUnit.SECONDS);
                   }
               }
           }



           //组装数据
           List<JobtableVO>  newJobTableArr=new ArrayList<JobtableVO>();
           //把数据库的结果先放到 jobtableVo中
           for(Jobtable  vo : jobTableArr) {
               //SpringBean工具类对象复制
               JobtableVO newVo = new JobtableVO();
               BeanUtils.copyProperties(vo, newVo);

               //作品的查看总次数 ,作品的点赞总次数,作品的总评论数量 ,学生的总的粉丝数,学生的总的作品数量(时时都在变化，不可能时时查询数据库)  redis的hash操作
               //1、作品的查看总次数 ,作品的点赞总次数
               String redisKey=DETAIL_OTHER_REDIS_KEY+vo.getId();
                 //缓存中有  visits,likes
                if(this.stringRedisTemplate.opsForHash().hasKey(redisKey,"likes")){
                    List<Object> arr= new ArrayList<>();
                    arr.add("visits");
                    arr.add("likes");
                    List<Object> resultArr=this.stringRedisTemplate.opsForHash().multiGet(redisKey,arr);
                    newVo.setVisits( Integer.parseInt(resultArr.get(0).toString()));
                    newVo.setLikes( Integer.parseInt(resultArr.get(1).toString()));
                }else{
                    //不存在缓存,查询数据库
                    synchronized (_sigle){
                        if(this.stringRedisTemplate.opsForHash().hasKey(redisKey,"likes")){
                            List<Object> arr= new ArrayList<>();
                            arr.add("visits");
                            arr.add("likes");
                            List<Object> resultArr=this.stringRedisTemplate.opsForHash().multiGet(redisKey,arr);
                            newVo.setVisits( Integer.parseInt(resultArr.get(0).toString()));
                            newVo.setLikes( Integer.parseInt(resultArr.get(1).toString()));
                        }else{
                            JobtableViews  views=this.jobtableViewsService.selectByPrimaryKey(vo.getId());
                            newVo.setVisits(views.getVisits());
                            newVo.setLikes(views.getLikes());
                            //放入缓存
                            this.stringRedisTemplate.opsForHash().put(redisKey,"visits",newVo.getVisits()+"");
                            this.stringRedisTemplate.opsForHash().put(redisKey,"likes",newVo.getLikes()+"");
                        }
                    }
                }


                //2、作品的总评论数量，先判断缓存
               if(this.stringRedisTemplate.opsForHash().hasKey(redisKey,"comments")){
                    Object o=this.stringRedisTemplate.opsForHash().get(redisKey,"comments");
                    newVo.setCommentsCount(Integer.parseInt(o.toString()));
               }else{
                   synchronized (_sigle){
                       if(this.stringRedisTemplate.opsForHash().hasKey(redisKey,"comments")){
                           Object o=this.stringRedisTemplate.opsForHash().get(redisKey,"comments");
                           newVo.setCommentsCount(Integer.parseInt( o.toString()));
                       }else{
                           int commentCount=this.guestcommentService.selectByCount(vo.getId());
                           newVo.setCommentsCount(commentCount);
                           this.stringRedisTemplate.opsForHash().put(redisKey,"comments",commentCount+"");
                       }
                   }
               }


               //3、学生的总的粉丝数
               String studentKey=FANS_FLOOW_REDIS_KEY+vo.getStudentId();

               if(this.stringRedisTemplate.opsForHash().hasKey(studentKey,"fansCount")){
                   Object o=this.stringRedisTemplate.opsForHash().get(studentKey,"fansCount");
                   newVo.setFansCount(Integer.parseInt( o.toString()));
               }else{
                   synchronized (_sigle){
                       if(this.stringRedisTemplate.opsForHash().hasKey(studentKey,"fansCount")){
                           Object o=this.stringRedisTemplate.opsForHash().get(studentKey,"fansCount");
                           newVo.setFansCount(Integer.parseInt( o.toString()));
                       }else{
                           int fansCount=this.followService.searchFollowMeFans(vo.getStudentId());
                           newVo.setFansCount(fansCount);
                           this.stringRedisTemplate.opsForHash().put(studentKey,"fansCount",fansCount+"");
                       }
                   }
               }

               //4、学生的总的作品数量
               if(this.stringRedisTemplate.opsForHash().hasKey(studentKey,"productCount")){
                   Object o=this.stringRedisTemplate.opsForHash().get(studentKey,"productCount");
                   newVo.setProductCount(Integer.parseInt( o.toString()));
               }else{
                   synchronized (_sigle){
                       if(this.stringRedisTemplate.opsForHash().hasKey(studentKey,"productCount")){
                           Object o=this.stringRedisTemplate.opsForHash().get(studentKey,"productCount");
                           newVo.setProductCount(Integer.parseInt( o.toString()));
                       }else{
                           int productCount=this.jobtableService.selectCountByStudent(vo.getStudentId());
                           newVo.setProductCount(productCount);
                           this.stringRedisTemplate.opsForHash().put(studentKey,"productCount",productCount+"");
                       }
                   }
               }
               //把放到新对象中
               newJobTableArr.add(newVo);
           }

           //把前8笔数据放到发送对象中
           d.setItems(newJobTableArr);
       }

        return ResponseMessage.success("ok", dataList);
    }

    /**
     * 根据id查询作品详情
     * @param id
     * @return
     */
    @Override
    public ResponseMessage searchById(int id) {

        ResponseMessage rm=null;
          if(id<=0){
              log.warn("用户恶意在攻击你,查询作品的编号为:"+id);
              rm=ResponseMessage.failed(500,"查询数据有误!");
          }else{
              Jobtable  jobtable= null;
              try {
                  jobtable = this.jobtableService.searchById(id);
                  if(jobtable==null){
                      rm=ResponseMessage.failed(404,"查询不到数据");
                  }else{

                      //把jobtable转换成自定义的jobtableVo
                      JobtableVO  jobtableVO=new JobtableVO();
                      //Spring工具类
                      BeanUtils.copyProperties(jobtable,jobtableVO);

                      //差 总浏览次数，总的点赞次数，总的评论次数
                      fillOther(jobtableVO);

                      rm=ResponseMessage.success("ok",jobtableVO);
                  }
              } catch (Exception e) {
                  //log.error(e.getMessage());
                  e.printStackTrace();
                 rm=ResponseMessage.failed(500,"查询数据异常!");
              }
          }
        return rm;
    }

    /**
     * 查询 总浏览次数，总的点赞次数，总的评论次数
     * @param vo
     */
    private void fillOther(JobtableVO vo){
       String key=DETAIL_OTHER_REDIS_KEY+vo.getId();

       //先判断redis中是否有键，因为查询hash类型
       if(this.stringRedisTemplate.hasKey(key)){
           List<Object> resultArr = getJobtableHashOther(key);
           //likes
           vo.setLikes(Integer.parseInt( resultArr.get(0).toString()));
            //visits
           vo.setVisits(Integer.parseInt( resultArr.get(1).toString()));
           //comments  评论
           vo.setCommentsCount(Integer.parseInt( resultArr.get(2).toString()));
       }else{

           synchronized (_sigle){
               if(this.stringRedisTemplate.hasKey(key)){
                   List<Object> resultArr = getJobtableHashOther(key);
                   //likes
                   vo.setLikes(Integer.parseInt( resultArr.get(0).toString()));
                   //visits
                   vo.setVisits(Integer.parseInt( resultArr.get(1).toString()));
                   //comments  评论
                   vo.setCommentsCount(Integer.parseInt( resultArr.get(2).toString()));
               }else{
                   //先查询数据库
                   //1、jobtable_views 作品浏览次数，点赞次数
                   JobtableViews  views=this.jobtableViewsService.selectByPrimaryKey(vo.getId());
                   //likes
                   vo.setLikes(views.getLikes());
                   //visits
                   vo.setVisits(views.getVisits());

                   //2、总的评论数
                   int commentsCount=this.guestcommentService.selectByCount(vo.getId());
                   vo.setCommentsCount(commentsCount);

                   //写入缓存
                   this.stringRedisTemplate.opsForHash().put(key,"likes",views.getLikes()+"");
                   this.stringRedisTemplate.opsForHash().put(key,"visits",views.getVisits()+"");
                   this.stringRedisTemplate.opsForHash().put(key,"comments",commentsCount+"");
               }
           }

       }

    }

    /**
     * 取得详情中需要的数据
     * @param key
     * @return
     */
    private List<Object> getJobtableHashOther(String key) {
        List<Object> arr=new ArrayList<>();
        arr.add("likes");
        arr.add("visits");
        arr.add("comments");
        return this.stringRedisTemplate.opsForHash().multiGet(key,arr);
    }


    //取分类信息```````````````````````````````````````
    private List<DataHomeVO>  getData(){

        List<Data> dataArr=null;
        try{
            //尝试从redis中取值 .可能出现的错误是redis连接不上
            dataArr=getRedisData();
        }catch(Exception e){
            //有异常，只能去连接数据库
            dataArr=this.dataService.selectTypes(2);
            log.error(e.getMessage());
        }

        //把值转换DataHome
        List<DataHomeVO>   arr=new ArrayList<DataHomeVO>();
        for(Data  d : dataArr){
            DataHomeVO  vo=new DataHomeVO();
            vo.setId(d.getId());
            vo.setName(d.getName());
            arr.add(vo);
        }
        return arr;
    }

    //从redis中取值
    private List<Data> getRedisData(){
        List<Data> dataArr=null;
        //从redis中取值
        String redisValue=this.stringRedisTemplate.opsForValue().get(DATA_REDIS_KEY);
        if(StringUtils.isBlank(redisValue)){
            //加锁
            synchronized (this){
                //加一次读取数据
                redisValue=this.stringRedisTemplate.opsForValue().get(DATA_REDIS_KEY);
                if(StringUtils.isBlank(redisValue)){
                    //没有，才做数据库操作
                    dataArr=this.dataService.selectTypes(2);
                    String jsonArr= JSONArray.toJSONString(dataArr);
                    //12小时过期
                    this.stringRedisTemplate.opsForValue().set(DATA_REDIS_KEY, jsonArr, 12L,TimeUnit.HOURS);
                }else{
                    dataArr= JSONArray.parseArray(redisValue,Data.class);
                }
            }
        }else{
            dataArr= JSONArray.parseArray(redisValue,Data.class);
        }
        return dataArr;
    }

}
