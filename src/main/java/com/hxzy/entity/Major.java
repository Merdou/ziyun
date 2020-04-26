package com.hxzy.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Major implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    private String name;

    /**
     * 状态:1启用 0禁用
     */
    private Integer state;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}