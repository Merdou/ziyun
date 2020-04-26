package com.hxzy.mapper;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.entity.Student;

import java.util.List;

public interface StudentMapper {

    int insert(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    /**
     * 分页查询
     * @param pageSearch
     * @return
     */
    List<Student> search(PageSearch pageSearch);

    /**
     * 根据手机号来登录
     * @param mobile
     * @return
     */
    Student login(String mobile);

   
}