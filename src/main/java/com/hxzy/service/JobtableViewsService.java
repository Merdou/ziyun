package com.hxzy.service;

import com.hxzy.entity.JobtableViews;

public interface JobtableViewsService {
    boolean deleteByPrimaryKey(Integer jobTableId);

    boolean insert(JobtableViews record);

    JobtableViews selectByPrimaryKey(Integer jobTableId);

    boolean updateByPrimaryKeySelective(JobtableViews record);

    boolean updateByPrimaryKey(JobtableViews record);
}
