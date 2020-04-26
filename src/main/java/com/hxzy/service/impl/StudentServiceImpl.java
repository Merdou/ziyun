package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.hxzy.common.conf.props.ForgetPwdProperties;
import com.hxzy.common.util.MD5Util;
import com.hxzy.common.util.SMSUtil;
import com.hxzy.common.vo.BSTable;
import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Student;
import com.hxzy.mapper.StudentMapper;
import com.hxzy.service.StudentService;
import com.hxzy.vo.ChangePwdVO;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //忘记密码短信模板属性
    @Autowired
    private ForgetPwdProperties  forgetPwdProperties;

    @Override
    public boolean insert(Student record) {
        return this.studentMapper.insert(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Student selectByPrimaryKey(Integer id) {
        return this.studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Student record) {
        return this.studentMapper.updateByPrimaryKeySelective(record)>0;
    }

    @Override
    public boolean updateByPrimaryKey(Student record) {
        return this.studentMapper.updateByPrimaryKey(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage search(PageSearch pageSearch) {
        PageHelper.offsetPage(pageSearch.getOffset(),pageSearch.getLimit());
        List<Student> data=this.studentMapper.search(pageSearch);
        Page pg=(Page) data;
        long total=pg.getTotal();

        //生成BSTable
        BSTable bs=new BSTable();
        bs.setRows(data);
        bs.setTotal(total);

        return ResponseMessage.success("成功",bs);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage login(Student student) {
         ResponseMessage  rm=null;
        //1、根据手机号查询用户
         Student db=this.studentMapper.login(student.getMobile());
         if(db!=null){
             //2、如果有再根据密码加密 与数据库之前密码做比较
              String md5Pwd= MD5Util.MD5Encode(student.getLoginPwd(),  db.getSalt());
              if(md5Pwd.equals(db.getLoginPwd())){
                  //3、状态 如果退学不能登录
                  if(db.getState()==3){
                      rm=ResponseMessage.failed(406,"该账户已退学，不能登录!");
                      log.warn(student.getMobile()+",该账户已退学，不能登录");
                  }else{
                      rm=ResponseMessage.success("登录成功",db);
                  }
              }else{
                  rm=ResponseMessage.failed(405,"用户名或密码不存在");
                  log.warn(student.getMobile()+",密码错误");
              }
         }else{
             rm=ResponseMessage.failed(404,"用户名或密码不存在");
             log.warn(student.getMobile()+",手机号不存在");
         }

        return rm;
    }

    //找回密码发送验证
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage sendFindPasswordSms(String mobile) {
        ResponseMessage  rm=null;

        //redis里面查询它是否已经发过.查询手机的剩余时间，看有没有, 如果有剩余时间，就代表已经发过了，不允许再发了
        //5分钟过期  至少要等2分钟才能再发送
        //5*60=300-120=180
        //剩余过期时间  300
       long expiredTime=this.stringRedisTemplate.getExpire(this.forgetPwdProperties.getRedisPrefix()+mobile);
       int retryTime=this.forgetPwdProperties.getExpiredMinute()*60-this.forgetPwdProperties.getRetrySeconds();
        //ttl有期时间 150>180
        if(expiredTime>retryTime){
            rm=ResponseMessage.failed(400,mobile+"手机获取短信验证码的频繁太快了吧!");
            log.error(mobile+"手机获取短信验证码的频繁太快了吧!");
            return rm;
        }

        //1、查询手机是否存在
        Student stu=this.studentMapper.login(mobile);
        if(stu==null){
            rm=ResponseMessage.failed(500,"手机号码不存在的");
        }else{

            String templateID = this.forgetPwdProperties.getTemplateId();
            String[] phoneNumbers = {"+86"+mobile};
            int minute=this.forgetPwdProperties.getExpiredMinute(); //5分钟
            //验证码
            String code=SMSUtil.createCode(this.forgetPwdProperties.getCodeLen());
            try {
                SMSUtil.sendSMS(templateID,phoneNumbers,code,minute);

                //写入redis(几分钟后过期)
                stringRedisTemplate.opsForValue().set(this.forgetPwdProperties.getRedisPrefix()+mobile, code ,minute, TimeUnit.MINUTES  );

                rm=ResponseMessage.success("短信发送成功");

                //默认120秒就可以再次发送
                rm.setData(this.forgetPwdProperties.getRetrySeconds());

            } catch (TencentCloudSDKException e) {
                log.error(e.getMessage());
                rm=ResponseMessage.failed(500,"短信发送失败，请稍后再试");
            }
        }
        return rm;
    }

    /**
     * 检查输入短信码与手机是否匹配，以及短信码是否已经过期
     * @param changePwdVO
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage checkSmsCode(ChangePwdVO changePwdVO) {
        ResponseMessage  rm=null;

        //通过redis查询手机对应的code
        String code=this.stringRedisTemplate.opsForValue().get(this.forgetPwdProperties.getRedisPrefix()+changePwdVO.getMobile());
        //redis中找不到验证码
        if(StringUtils.isBlank(code)){
            rm=ResponseMessage.failed(505,"验证码已过期，请重新获取!");
        }else{
            //判断验证码是否正确
            if(changePwdVO.getCode().equals(code)){
                rm=ResponseMessage.success("ok");
            }else{
                rm=ResponseMessage.failed(506,"验证码输入错误,请重新输入!");
            }
        }
        return rm;
    }

    //重置密码
    @Override
    public ResponseMessage updatePwd(ChangePwdVO changePwdVO) {
        ResponseMessage  rm=this.checkSmsCode(changePwdVO);
        //判断code是否过期
        if(rm.getCode()==0){
            //更新密码
            Student  stu=this.studentMapper.login(changePwdVO.getMobile());
            //重新生成md5密码
            String md5Pwd=MD5Util.MD5Encode(changePwdVO.getPassword(),  stu.getSalt());
            //更新
            Student  newStu=new Student();
            newStu.setId(stu.getId());
            newStu.setLoginPwd(md5Pwd);
            //选择性更新
            int count= this.studentMapper.updateByPrimaryKeySelective(newStu);
            if(count>0){
                rm=ResponseMessage.success("更新密码成功");
                //删除redis的key
                this.stringRedisTemplate.delete(this.forgetPwdProperties.getRedisPrefix()+stu.getMobile());
            }else{
                rm=ResponseMessage.failed(500,"更新密码失败");
            }
        }
        return rm;
    }
}
