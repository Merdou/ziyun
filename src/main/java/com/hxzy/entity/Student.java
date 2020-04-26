package com.hxzy.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * student
 * @author 
 */
@Data
public class Student implements Serializable {
    /**
     * 学号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 所学专业
     */
    private Integer majorId;

    /**
     * 专业名称(自定义)
     */
    private String majorName;

    /**
     * 所在班级
     */
    private Integer classesId;

    /**
     * 班级名称(自定义)
     */
    private String classesName;

    /**
     * 头像图片地址
     */
    private String portrait;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 入学日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date joinDate;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生年月
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 学历 
     */
    private Integer education;

    /**
     * 学历名称(自定义列)
     */
    private String eduName;

    /**
     * 毕业院校
     */
    private String schoolName;

    /**
     * 学校所学专业
     */
    private String collegeMajor;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * qq号
     */
    private String qq;

    /**
     * 家庭住址
     */
    private String homeAddress;

    /**
     * 现住地址
     */
    private String currentAddress;

    /**
     * 状态:1正常   2：休学    3：退学 
     */
    private Integer state;

    /**
     * 登录密码
     */
    @JSONField(serialize = false)
    private String loginPwd;

    /**
     * 盐
     */
    @JSONField(serialize = false)
    private String salt;

    //开班时间
    @JSONField(format = "yyyy-MM-dd")
    private Date openingDate;

    //结课时间
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    private static final long serialVersionUID = 1L;

}