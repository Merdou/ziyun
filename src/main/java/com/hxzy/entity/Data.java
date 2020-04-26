package com.hxzy.entity;

import java.io.Serializable;

/**
 * sys_data
 * @author 
 */

public class Data implements Serializable {
    private Integer id;

    /**
     * 角色编号
     */
    private String name;

    /**
     * 1启用，0禁用
     */
    private Integer state;

    /**
     * 类型:  1、作品标签  2、作品内容，3、作品类型
     */
    private Integer types;

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

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }
}