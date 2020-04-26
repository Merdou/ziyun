package com.hxzy.vo;

import com.hxzy.common.vo.GetPageSearch;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询某个老师，某个班的作业
 */
@Getter
@Setter
public class ClassesWorkSearchVO extends GetPageSearch {

    //班级编号
    private Integer classesId;


    //作业批改状态,1未批改，2代表已批改
    private int state=1;

    //哪个老师来批改
    private int teacherId;
}
