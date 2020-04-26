package com.hxzy.service;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Data;
import com.hxzy.vo.DataSearchVO;

import java.util.List;

public interface DataService {

    boolean insert(Data record);

    Data selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(Data record);

    boolean updateByPrimaryKey(Data record);

    /**
     * 根据类型查询数据
     * @param types
     * @return
     */
    List<Data> selectTypes(int types);

    ResponseMessage search(PageSearch searchVO);

    /**
     * true代表没有人使用
     * @param data
     * @return
     */
    boolean existsName(Data data);
}
