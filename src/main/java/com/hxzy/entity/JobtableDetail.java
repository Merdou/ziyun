package com.hxzy.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * jobtable_detail
 * @author 
 */
@Data
public class JobtableDetail implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 作业提交表编号
     */
    private Integer jobTableId;

    /**
     * 附件提交地址
     */
    private String attachment;

    /**
     * 提交时间
     */
    private Date submitTime;

    private static final long serialVersionUID = 1L;

}