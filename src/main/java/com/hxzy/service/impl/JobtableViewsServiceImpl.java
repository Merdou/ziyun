package com.hxzy.service.impl;

import com.hxzy.entity.JobtableViews;
import com.hxzy.mapper.JobtableViewsMapper;
import com.hxzy.service.JobtableViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobtableViewsServiceImpl implements JobtableViewsService {

    @Autowired
    private JobtableViewsMapper jobtableViewsMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer jobTableId) {
        return this.jobtableViewsMapper.deleteByPrimaryKey(jobTableId)>0;
    }

    @Override
    public boolean insert(JobtableViews record) {
        return this.jobtableViewsMapper.insert(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public JobtableViews selectByPrimaryKey(Integer jobTableId) {
        return this.jobtableViewsMapper.selectByPrimaryKey(jobTableId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(JobtableViews record) {
        return this.jobtableViewsMapper.updateByPrimaryKeySelective(record)>0;
    }

    @Override
    public boolean updateByPrimaryKey(JobtableViews record) {
        return this.jobtableViewsMapper.updateByPrimaryKey(record)>0;
    }
}
