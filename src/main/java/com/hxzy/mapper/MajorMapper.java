package com.hxzy.mapper;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.entity.Major;

import java.util.List;

public interface MajorMapper {

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(Major record);

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
    int updateByPrimaryKeySelective(Major record);

    /**
     * 根据主键全部更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Major record);

    /**
     * 分页查询
     * @param pageSearch
     * @return
     */
    List<Major> search(PageSearch pageSearch);

    /**
     * 加载所有的数据
     * @return
     */
    List<Major> searchAll();

}