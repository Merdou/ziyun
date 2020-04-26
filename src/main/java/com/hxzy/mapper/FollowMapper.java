package com.hxzy.mapper;

import com.hxzy.entity.Follow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Follow record);

    /**
     * 查询我的编号查询有多少粉丝
     * @param studentId
     * @return
     */
    int searchFollowMeFans(int studentId);
}