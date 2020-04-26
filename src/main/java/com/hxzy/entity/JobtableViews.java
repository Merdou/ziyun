package com.hxzy.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 作品浏览次数和点赞次数
 */
@Data
public class JobtableViews implements Serializable {
    /**
     * 作业编号
     */
    private Integer jobTableId;

    /**
     * 访问总次数
     */
    private Integer visits;

    /**
     * 点赞总次数
     */
    private Integer likes;

    private static final long serialVersionUID = 1L;


}