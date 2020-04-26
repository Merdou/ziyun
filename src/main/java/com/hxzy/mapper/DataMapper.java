package com.hxzy.mapper;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.entity.Data;

import java.util.List;

public interface DataMapper {

    int insert(Data record);

    Data selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Data record);

    int updateByPrimaryKey(Data record);

    /**
     * 根据类型查询数据
     * @param types
     * @return
     */
    List<Data> selectTypes(int types);

    List<Data> search(PageSearch searchVO);

    int existsName(Data data);
}