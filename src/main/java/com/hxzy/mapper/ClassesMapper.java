package com.hxzy.mapper;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.entity.Classes;
import com.hxzy.vo.TeachClassesSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ClassesMapper {

    int insert(Classes record);

    Classes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    /**
     * 分页查询
     * @param search
     * @return
     */
    List<Classes> search(PageSearch search);

    /**
     * 根据专业查询班级信息
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
     * 根据班级编号删除teacher_classes所有数据
     * @param classesId
     * @return
     */
    int deleteByClassesId(int classesId);

    /**
     * 向teacher_classes批量新增信息
     * @param classesId 班级编号
     * @param teacherArr 老师编号
     * @return
     */
    int insertBatchTeacherClasses(@Param("classesId") int classesId,@Param("teacherArr") List<Integer> teacherArr);

    /**
     * 查询该登录的教师所带的班级
     * @param search
     * @return
     */
    List<Classes> searchTeachClasses(TeachClassesSearch search);
}