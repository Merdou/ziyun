package com.hxzy.vo.home;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 给首页用的（学员作业）
 */
@Getter
@Setter
public class DataHomeVO {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;


    /**
     * 每个分类下面的产品
     */
    private List<JobtableVO>  items;


}
