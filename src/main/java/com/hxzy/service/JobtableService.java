package com.hxzy.service;

import com.github.pagehelper.PageInfo;
import com.hxzy.entity.Jobtable;
import com.hxzy.vo.ClassesWorkSearchVO;
import com.hxzy.vo.StudentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobtableService {
    boolean insert(Jobtable record);

    /**
     * 批量插入作品图片 jobtable_detail
     * @param record
     * @param arr
     * @return
     */
    boolean insertBatchDetail(Jobtable record,  List<String> arr) throws RuntimeException;

    Jobtable selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(Jobtable record);

    boolean updateByPrimaryKey(Jobtable record);

    /**
     * 学生自己的未批改作业
     * @param studentVO
     * @return
     */
    PageInfo<Jobtable>  searchNoChecked(StudentVO  studentVO);

    /**
     * 老师未批改某个班的作业
     * @param classesWorkSearchVO
     * @return
     */
    PageInfo<Jobtable> searchTeacherNoChecked(ClassesWorkSearchVO classesWorkSearchVO);

    /**
     * 产品首页分类前8笔数据
     * @param id
     * @return
     */
    List<Jobtable> selectTopJobTable(Integer id);

    /**
     * 根据学生编号查询它的总的作品数量（已点评的）
     * @param studentId
     * @return
     */
    int selectCountByStudent(int studentId);

    Jobtable searchById(int id) throws Exception ;
}
