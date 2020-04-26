package com.hxzy.mapper;

import com.hxzy.entity.GuestComment;
import org.apache.ibatis.annotations.Mapper;


public interface GuestcommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GuestComment record);

    /**
     * 得到某个作品评论的总次数
     * @param jobTableId
     * @return
     */
    int selectByCount(int jobTableId);

}