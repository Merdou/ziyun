package com.hxzy.service;

import com.github.pagehelper.PageInfo;
import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Classes;
import com.hxzy.entity.Teacher;
import com.hxzy.vo.TeachClassesSearch;

import java.util.List;

public interface TeacherService {

    boolean insert(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(Teacher record);

    boolean updateByPrimaryKey(Teacher record);

    /**
     * 分页
     * @param pageSearch
     * @return
     */
    ResponseMessage search(PageSearch pageSearch);

    /**
     * 加载所有未离职老师
     * @return
     */
    List<Teacher> searchAll();

    /**
     * 加载班级的认课老师(未离职)
     * @param classesId
     * @return
     */
    List<Teacher> searchTeacher(Integer classesId);

    ResponseMessage login(Teacher teacher);


}
