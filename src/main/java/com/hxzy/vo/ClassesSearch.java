package com.hxzy.vo;

import com.hxzy.common.vo.PageSearch;
import lombok.Getter;
import lombok.Setter;

/**
 * 班级查询
 */
@Getter
@Setter
public class ClassesSearch extends PageSearch {
    //专业编号
    private Integer marjorId;
    //结课状态
    private Integer state;
}
