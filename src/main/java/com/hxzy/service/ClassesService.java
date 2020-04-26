package com.hxzy.service;

import com.github.pagehelper.PageInfo;
import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Classes;
import com.hxzy.vo.TeachClassesSearch;

import java.util.List;

public interface ClassesService {

    boolean insert(Classes record);

    /**
     * 新增
     * @param record
     * @param teacherArr 带课老师
     * @return
     */
    boolean insertBatch(Classes record,List<Integer> teacherArr) throws RuntimeException;

    Classes selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(Classes record);

    boolean updateByPrimaryKey(Classes record);

    /**
     * 更新
     * @param record
     * @param teacherArr
     * @return
     */
    boolean updateByPrimaryKey(Classes record,List<Integer> teacherArr) throws RuntimeException;

    /**
     * 分页查询
     * @param pageSearch
     * @return
     */
    ResponseMessage search(PageSearch pageSearch);

    /**
     * 根据专业得到班级
     * @param majorId
     * @return
     */
    List<Classes> selectByMajorId(int majorId);

    /**
     * 根据专业查询还未结课的班级
     * @param majorId
     * @return
     */
    List<Classes> selectByNotCloseMajorId(int majorId);


    /**
     * 查询该登录的教师所带的班级
     * @param search
     * @return
     */
    PageInfo<Classes> searchTeachClasses(TeachClassesSearch search);
}
