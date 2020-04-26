package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxzy.entity.Jobtable;
import com.hxzy.entity.JobtableViews;
import com.hxzy.mapper.JobtableMapper;
import com.hxzy.service.GuestcommentService;
import com.hxzy.service.JobtableService;
import com.hxzy.service.JobtableViewsService;
import com.hxzy.vo.ClassesWorkSearchVO;
import com.hxzy.vo.StudentVO;
import com.hxzy.vo.home.JobtableVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
@Transactional
public class JobtableServiceImpl implements JobtableService {

    private  final  static Object _sigle = new Object();

    @Autowired
    private GuestcommentService guestcommentService;
    @Autowired
    private JobtableViewsService jobtableViewsService;
    @Autowired
    private JobtableMapper jobtableMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //作品详情redis前缀
    private static final String DETAIL_KEY="work:detail:";

    private static final String DETAIL_OTHER_REDIS_KEY="work:detail:other:";

    @Override
    public boolean insert(Jobtable record) {
        return this.jobtableMapper.insert(record)>0;
    }

    //新增及批量插入照片
    @Override
    public boolean insertBatchDetail(Jobtable record, List<String> arr) throws RuntimeException {
        boolean result=this.insert(record);  //数据库语句
        if(result){
            int jobId=record.getId();
            result=this.jobtableMapper.insertBatchDetail(jobId,arr)>0;  //数据库语句

            if(!result){
                log.error("新增作品图片失败");
                throw new RuntimeException("新增作品失败");
            }
        }else{
            log.error("新增作品失败");
            log.error(record.toString());
            throw new RuntimeException("新增作品失败");
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Jobtable selectByPrimaryKey(Integer id) {
        return this.jobtableMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Jobtable record) {
        return this.jobtableMapper.updateByPrimaryKeySelective(record)>0;
    }

    @Override
    public boolean updateByPrimaryKey(Jobtable record) {
        return this.jobtableMapper.updateByPrimaryKey(record)>0;
    }

    /**
     * 分页查询未批改
     * @param studentVO
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<Jobtable> searchNoChecked(StudentVO studentVO) {
        //分页
        PageHelper.startPage(studentVO.getPageNum(), studentVO.getPageSize());

        //查询数据库
        List<Jobtable> data=this.jobtableMapper.searchNoChecked(studentVO);

        //强转得到Page
        Page<Jobtable> pg=(Page<Jobtable>) data;

        return  pg.toPageInfo();
    }

    /**
     * 老师未批改某个班的作业
     * @param classesWorkSearchVO
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<Jobtable> searchTeacherNoChecked(ClassesWorkSearchVO classesWorkSearchVO) {
        //分页
        PageHelper.startPage(classesWorkSearchVO.getPageNum(), classesWorkSearchVO.getPageSize());

        //查询未批改的
        classesWorkSearchVO.setState(1);
        //查询
        List<Jobtable> data=this.jobtableMapper.searchTeacherClassesWork(classesWorkSearchVO);

        //强转Page
        Page<Jobtable> pg= (Page<Jobtable>) data;

        return pg.toPageInfo();
    }

    /**
     * 根据类型查询前8笔数据
     * @param id
     * @return
     */
    @Override
    public List<Jobtable> selectTopJobTable(Integer id) {
         return this.jobtableMapper.selectTopJobTable(id);
    }

    /**
     * 根据学生编号查询它的总的作品数量（已点评的）
     * @param studentId
     * @return
     */
    @Override
    public int selectCountByStudent(int studentId) {
        return this.jobtableMapper.selectCountByStudent(studentId);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Jobtable searchById(int id) throws Exception {
        //先读取缓存，不管对错
        Jobtable jobtable=null;
        try {
            String key = DETAIL_KEY+id;
            String redisStr= stringRedisTemplate.opsForValue().get(key);
            //判断缓存中是否有数据
            if(StringUtils.isBlank(redisStr)){
                //同步锁
                synchronized (this.getClass()){
                    redisStr= stringRedisTemplate.opsForValue().get(key);
                    if(StringUtils.isBlank(redisStr)){
                        //没有就先查询
                        jobtable = this.jobtableMapper.searchById(id);
                        String jsonStr = JSONObject.toJSONString(jobtable);
                        //如果jobtable为null表示缓存中没有数据
                        if(jobtable==null){
                            //再放入缓存中
                            log.debug(Thread.currentThread().getId()+"有人在攻击你id:"+id);
                            stringRedisTemplate.opsForValue().set(key,jsonStr,1, TimeUnit.MINUTES);
                        }else{
                            stringRedisTemplate.opsForValue().set(key,jsonStr);
                        }

                    }else{
                        log.debug(Thread.currentThread().getId()+ ",多线程读取锁中的提缓存.......");
                        //如果不为空，就直接使用缓存中的数据（使用JSONObject的parseObject方法制动转换成你想要的类）
                        jobtable =JSONObject.parseObject(redisStr,Jobtable.class);
                    }
                }
                //没有就先查询
                jobtable = this.jobtableMapper.searchById(id);
                String jsonStr = JSONObject.toJSONString(jobtable);
                //再放入缓存中
                stringRedisTemplate.opsForValue().set(key,jsonStr);
            }else{
                log.debug(Thread.currentThread().getId()+ ",多线程读取缓存.......");
                //如果不为空，就直接使用缓存中的数据（使用JSONObject的parseObject方法制动转换成你想要的类）
                jobtable =JSONObject.parseObject(redisStr,Jobtable.class);
            }
        }catch (Exception e){
            log.error("服务器异常"+this.getClass()+"的searchById方法id为"+id+"的查询异常");
            throw new Exception(e.getMessage());//抛出异常
        }
        return jobtable;
    }

    private  void  fillOther(JobtableVO vo){
        String key = DETAIL_OTHER_REDIS_KEY+vo.getId();

        if(this.stringRedisTemplate.hasKey(key)){
            List<Object> resultArr = getJobtableHashOther(key);
            vo.setLikes(Integer.parseInt(resultArr.get(0).toString()));
            vo.setFansCount(Integer.parseInt(resultArr.get(1).toString()));
            vo.setCommentsCount(Integer.parseInt(resultArr.get(3).toString()));
        }else{
            synchronized (_sigle){
                if(this.stringRedisTemplate.hasKey(key)){
                    List<Object> resultArr = getJobtableHashOther(key);
                    vo.setLikes(Integer.parseInt(resultArr.get(0).toString()));
                    vo.setFansCount(Integer.parseInt(resultArr.get(1).toString()));
                    vo.setCommentsCount(Integer.parseInt(resultArr.get(2).toString()));
                }else {
                    JobtableViews views = this.jobtableViewsService.selectByPrimaryKey(vo.getId());
                    vo.setLikes(views.getLikes());
                    vo.setVisits(views.getVisits());
                    int commentCount = this.guestcommentService.selectByCount(vo.getId());
                    vo.setCommentsCount(commentCount);

                    //写入缓存
                    this.stringRedisTemplate.opsForHash().put(key,"likes",views.getLikes());
                    this.stringRedisTemplate.opsForHash().put(key,"visits",views.getVisits());
                    this.stringRedisTemplate.opsForHash().put(key,"comments",commentCount);

                }
            }
        }

        //先查数据库
        JobtableViews views = this.jobtableViewsService.selectByPrimaryKey(vo.getId());

        int conmmentCount = this.guestcommentService.selectByCount(vo.getId());

    }

    private List<Object> getJobtableHashOther(String key) {
        List<Object> arr = new ArrayList<>();
        arr.add("likes");
        arr.add("fans");
        arr.add("comments");
        return this.stringRedisTemplate.opsForHash().multiGet(key, arr);
    }
}
