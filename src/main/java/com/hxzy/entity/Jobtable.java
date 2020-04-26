package com.hxzy.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * jobtable
 * @author 
 */
@Data
public class Jobtable implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 学员编号
     */
    private Integer studentId;

    /**
     * 学生所在班级 对象
     */
    private Classes  classes;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学员头像
     */
    private String portrait;

    /**
     * 老师编号
     */
    private Integer teacherId;

    /**
     * 作品名称50个字
     */
    private String title;

    /**
     * 作品描述200个字
     */
    private String description;

    /**
     * 标签查询 sys_data中types=1
     */
    private Integer label;
    private String labelName;

    /**
     * 内容查询 sys_data中types=2
     */
    private Integer content;
    private String contentName;

    /**
     * 内容查询 sys_data中types=3
     */
    private Integer types;
    private String typesName;

    /**
     * 作品封面图地址
     */
    private String cover;

    /**
     * 作品提交时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date submitTime;

    /**
     * 状态:1 未批改   2：已批改
     */
    private Integer state;
    /**
     * 教师名称
     */
    private  String teacherName;
    /**
     * 教师点评类
     */
    private HomeworkComments homeworkComments;
    /**
     * 作品多图
     */
    private List<JobtableDetail> detailsList;

    private static final long serialVersionUID = 1L;


}