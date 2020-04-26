package com.hxzy.common.vo;

import lombok.Data;

/**
 * 自定义类型
 */
@Data
public class DataTypeVO {
    /**
     * 显示的文字,必须叫text因为treeview.js规定了
     */
    private String text;
    /**
     * 自定义类型编号
     */
    private Integer typeId;


}
