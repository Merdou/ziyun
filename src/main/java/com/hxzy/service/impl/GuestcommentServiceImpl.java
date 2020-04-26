package com.hxzy.service.impl;

import com.hxzy.entity.GuestComment;
import com.hxzy.mapper.GuestcommentMapper;
import com.hxzy.service.GuestcommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class GuestcommentServiceImpl implements GuestcommentService {

    @Autowired
    private GuestcommentMapper guestcommentMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        return this.guestcommentMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean insert(GuestComment record) {
        return this.guestcommentMapper.insert(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int selectByCount(int jobTableId) {
        return this.guestcommentMapper.selectByCount(jobTableId);
    }
}
