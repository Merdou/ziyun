package com.hxzy.service;

import com.hxzy.entity.Follow;

public interface FollowService {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(Follow record);

    /**
     * 查询我的编号查询有多少粉丝
     * @param studentId
     * @return
     */
    int searchFollowMeFans(int studentId);
}
