package com.hxzy.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * bootstrap-table类
 */
@Getter
@Setter
public class BSTable {
    //总记录数
    private long total;

    //当前页的记录
    private List rows;
}
