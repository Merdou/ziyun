package com.hxzy.service;

import com.hxzy.entity.GuestComment;

public interface GuestcommentService {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(GuestComment record);

    /**
     * 得到某个作品评论的总次数
     * @param jobTableId
     * @return
     */
    int selectByCount(int jobTableId);
}
