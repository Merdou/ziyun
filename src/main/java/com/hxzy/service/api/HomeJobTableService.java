package com.hxzy.service.api;

import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.vo.home.DataHomeVO;

import java.util.List;

/**
 * 给前端的service
 */
public interface HomeJobTableService {

    /**
     * 返回给前端首页的数据(vue)
     * @return
     */
    ResponseMessage search();

    /**
     * 根据id查询作品详情
     * @param id
     * @return
     */
    ResponseMessage searchById(int id);
}
