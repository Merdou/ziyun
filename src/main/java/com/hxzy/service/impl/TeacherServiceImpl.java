package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxzy.common.util.MD5Util;
import com.hxzy.common.vo.BSTable;
import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Classes;
import com.hxzy.entity.Major;
import com.hxzy.entity.Teacher;
import com.hxzy.mapper.TeacherMapper;
import com.hxzy.service.MajorService;
import com.hxzy.service.TeacherService;
import com.hxzy.vo.TeachClassesSearch;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Log4j2
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private MajorService  majorService;

    @Override
    public boolean insert(Teacher record) {
        return this.teacherMapper.insert(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Teacher selectByPrimaryKey(Integer id) {
        return this.teacherMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Teacher record) {
        return this.teacherMapper.updateByPrimaryKeySelective(record)>0;
    }

    @Override
    public boolean updateByPrimaryKey(Teacher record) {
        return this.teacherMapper.updateByPrimaryKey(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage search(PageSearch pageSearch) {

        PageHelper.offsetPage(pageSearch.getOffset(),pageSearch.getLimit());

        List<Teacher>  data=this.teacherMapper.search(pageSearch);

        Page  pg=(Page) data;
        long total=pg.getTotal();

        //查询专业对应的名称
        List<Major>  arrMajor=this.majorService.searchAll();
        for(Teacher  t : data){
            String know=t.getTeachKnowledge();
            if(StringUtils.isEmpty(know)){
                t.setMajorNames("");
            }else{
                //设定专业的名称
                setMajorNames(t, arrMajor);
            }
        }

        //封装
        BSTable bs=new BSTable();
        bs.setTotal(total);
        bs.setRows(data);

        return ResponseMessage.success("成功", bs);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Teacher> searchAll() {
        //查询所有专业
        List<Major>  arrMajor=this.majorService.searchAll();

        List<Teacher> allTeacher=this.teacherMapper.searchAll();
        for(Teacher  t : allTeacher){
            String know=t.getTeachKnowledge();
            if(StringUtils.isEmpty(know)){
                t.setMajorNames("");
            }else{
                //设定专业的名称
                setMajorNames(t, arrMajor);
            }
        }
        return allTeacher;
    }

    /**
     * 加载班级的认课老师(未离职)
     * @param classesId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Teacher> searchTeacher(Integer classesId) {
        return this.teacherMapper.searchTeacher(classesId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage login(Teacher teacher) {
        ResponseMessage rm=null;

        Teacher dbTeacher=this.teacherMapper.login(teacher);
        if(dbTeacher!=null){
            String md5Pwd= MD5Util.MD5Encode(teacher.getPassword(),dbTeacher.getSalt());
            if(md5Pwd.equals(dbTeacher.getPassword())){
                if(dbTeacher.getState()==3){
                    log.warn(teacher.getMobile()+"已离职不能登录");
                    rm=ResponseMessage.failed(406,"该账户已被锁定!");
                }else{
                    rm=ResponseMessage.success("登录成功",dbTeacher);
                }
            }else{
                log.warn(teacher.getMobile()+"密码错误");
                rm=ResponseMessage.failed(405,"用户名或密码错误");
            }

        }else{
            log.warn(teacher.getMobile()+"登录的手机号不存在");
            rm=ResponseMessage.failed(404,"用户名或密码错误");
        }
        return rm;
    }



    private void setMajorNames(Teacher t, List<Major> arrMajor) {
        //1,2
        String[]  arrs=t.getTeachKnowledge().split(",");
        StringBuffer  str=new StringBuffer();
        for(String s : arrs){
            int id=Integer.parseInt(s);
            String name=arrMajor.stream().filter(p -> p.getId() ==id ).map(Major::getName).findFirst().get();
            str.append(name).append(",");
        }
        //最后删除最后一个 ","
        str.deleteCharAt(str.length()-1);
        t.setMajorNames(str.toString());
    }


}
