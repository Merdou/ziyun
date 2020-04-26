package com.hxzy.mapper;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.entity.Teacher;

import java.util.List;

public interface TeacherMapper {

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    /**
     * 查询
     * @param pageSearch
     * @return
     */
    List<Teacher> search(PageSearch pageSearch);

    /**
     * 加载所有未离职的老师
     * @return
     */
    List<Teacher> searchAll();

    /**
     * searchTeacher
     * @param classesId
     * @return
     */
    List<Teacher> searchTeacher(Integer classesId);

    Teacher login(Teacher teacher);
}