package com.hxzy.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 论评表
 * @author 
 */
@Data
public class GuestComment implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 作业表 jobTable(id)
     */
    private Integer jobTableId;

    /**
     * 评论人编号,members(id)
     */
    private Integer sender;

    /**
     * 论评内容 200个字以内
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createDate;

    private static final long serialVersionUID = 1L;
}