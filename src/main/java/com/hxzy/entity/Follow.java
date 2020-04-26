package com.hxzy.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 粉丝关注表
 * @author 
 */
@Data
public class Follow implements Serializable {
    /**
     * 关注表
     */
    private Integer id;

    /**
     * 谁关注,学生的编号
     */
    private Integer who;

    /**
     * 关注我,学生的编号
     */
    private Integer me;

    /**
     * 关注时间
     */
    private Date createDate;

    private static final long serialVersionUID = 1L;
}