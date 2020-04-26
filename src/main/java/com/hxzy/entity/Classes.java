package com.hxzy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 班级
 * @author 
 */
@Data
public class Classes implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属专业
     */
    private Integer marjorId;

    /**
     * 所属专业的名称(查询用)
     */
    private String majorName;

    /**
     * 开班时间
     */
    //DateTimeFormat  把字符串转换成Date日期格式 (接收用户传过来的参数)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //JSONField把日期转换成指定格式的字符串 (输出给客户端)
    @JSONField(format = "yyyy-MM-dd")
    private Date openingDate;

    /**
     * 结课时间
     */
    //DateTimeFormat  把字符串转换成Date日期格式 (接收用户传过来的参数)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //JSONField把日期转换成指定格式的字符串(输出给客户端)
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    private static final long serialVersionUID = 1L;



    /**
     * 带班老师(让客户端去处理js)
     */
    private List<Teacher> teacherArr;


}