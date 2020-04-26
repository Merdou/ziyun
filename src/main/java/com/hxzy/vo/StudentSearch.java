package com.hxzy.vo;

import com.hxzy.common.vo.PageSearch;
import lombok.Getter;
import lombok.Setter;

/**
 * 学生查询
 */
@Getter
@Setter
public class StudentSearch extends PageSearch {
    //专业编号 0或者null 代表所有专业
    private Integer majorId;

    //班级编号  0或null 代表所有班级
    private Integer classesId;

    private String name;
    /**
     * 学历  0 或者null 代表所有
     */
    private Integer education;

    /**
     * 状态 0 或者null 代表所有
     */
    private Integer state;
}
