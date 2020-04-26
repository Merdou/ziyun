package com.hxzy.mapper;

import com.hxzy.entity.JobtableViews;

/**
 * 作品浏览和点赞
 */
public interface JobtableViewsMapper {

    int deleteByPrimaryKey(Integer jobTableId);

    int insert(JobtableViews record);

    JobtableViews selectByPrimaryKey(Integer jobTableId);

    int updateByPrimaryKeySelective(JobtableViews record);

    int updateByPrimaryKey(JobtableViews record);
}