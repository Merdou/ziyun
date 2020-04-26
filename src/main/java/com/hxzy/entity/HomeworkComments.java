package com.hxzy.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 作业点评
 * @author 
 */
@Data
public class HomeworkComments implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 作业表 jobTable(id)
     */
    private Integer jobTableId;

    /**
     * 云值分数
     */
    private Integer score;

    /**
     * 星级点评分数总分100分，使用星级插件
     */
    private Integer rating;

    /**
     * 标签点评
     */
    private String tags;

    /**
     * 评语点评
     */
    private String comments;

    /**
     * 点评时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date commentsDate;

    private static final long serialVersionUID = 1L;


}