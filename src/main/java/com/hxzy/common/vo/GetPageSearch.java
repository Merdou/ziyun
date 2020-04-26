package com.hxzy.common.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 前端页面接收get方式分页
 */

public class GetPageSearch {
    //当前是第几页
    private Integer pageNum=1;
    //每页显示10笔
    private int pageSize=10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
