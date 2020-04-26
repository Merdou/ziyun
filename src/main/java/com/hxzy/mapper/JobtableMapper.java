package com.hxzy.mapper;

import com.hxzy.entity.Jobtable;
import com.hxzy.vo.ClassesWorkSearchVO;
import com.hxzy.vo.StudentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobtableMapper {

    int insert(Jobtable record);

    /**
     * 批量插入作品图片 jobtable_detail
     * @param jobId
     * @param arr
     * @return
     */
    int insertBatchDetail(@Param("jobId") int jobId,@Param("arr") List<String> arr);

    Jobtable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Jobtable record);

    int updateByPrimaryKey(Jobtable record);

    /**
     * 未批改
     * @param studentVO
     * @return
     */
    List<Jobtable> searchNoChecked(StudentVO studentVO);

    /**
     * 老师对某个班的作业情况
     * @param classesWorkSearchVO
     * @return
     */
    List<Jobtable> searchTeacherClassesWork(ClassesWorkSearchVO classesWorkSearchVO);

    /**
     * 根据类型查询前8笔数据
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

    Jobtable searchById(int id);
}