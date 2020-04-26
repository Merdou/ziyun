package com.hxzy.service.impl;

import com.hxzy.entity.Follow;
import com.hxzy.mapper.FollowMapper;
import com.hxzy.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FollowServiceImpl  implements FollowService{

    @Autowired
    private FollowMapper followMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        return this.followMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean insert(Follow record) {

        return this.followMapper.insert(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int searchFollowMeFans(int studentId) {
        return this.followMapper.searchFollowMeFans(studentId);
    }
}
