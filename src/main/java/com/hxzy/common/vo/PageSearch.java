package com.hxzy.common.vo;

import lombok.*;

/**
 * 分页查询通用类(接收参数)
 */
@Getter
@Setter
public class PageSearch {
    //排除前几笔
    private int offset;

    /**
     * 取几笔
     */
    private int limit;

}
