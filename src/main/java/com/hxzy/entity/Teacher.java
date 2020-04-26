package com.hxzy.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * teacher
 * @author 
 */
@Data
public class Teacher implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 盐 (不要生成json串)
     */
    @JSONField(serialize = false)
    private String salt;

    /**
     * 登录密码(不要生成json串)
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 所带专业，多个专业用,号隔开
     */
    private String teachKnowledge;

    /**
     * 专业的名称(自定义)
     */
    private String majorNames;

    /**
     * 手机号码
     */
    private String mobile;

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
     * 学历：
     */
    private Integer education;

    /**
     * 学历名称
     */
    private String eduName;

    /**
     * 状态:1正常   2：休假    3：离职
     */
    private Integer state;



}