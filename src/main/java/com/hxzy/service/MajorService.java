package com.hxzy.service;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Major;

import java.util.List;

public interface MajorService {

    /**
     * 新增
     * @param record
     * @return
     */
    boolean insert(Major record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Major selectByPrimaryKey(Integer id);

    /**
     * 根据主键，只更新不为null的列
     * @param record
     * @return
     */
    boolean updateByPrimaryKeySelective(Major record);

    /**
     * 根据主键全部更新
     * @param record
     * @return
     */
    boolean updateByPrimaryKey(Major record);


    /**
     * 分页查询
     * @param pageSearch
     * @return
     */
    ResponseMessage  search(PageSearch pageSearch);

    /**
            * 查询所有的专业
     * @return
             */
    List<Major> searchAll();

}
