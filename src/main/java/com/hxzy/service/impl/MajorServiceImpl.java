package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hxzy.common.vo.BSTable;
import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Major;
import com.hxzy.mapper.MajorMapper;
import com.hxzy.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MajorServiceImpl implements MajorService{

    //动态代理
    @Autowired
    private MajorMapper  majorMapper;

    @Override
    public boolean insert(Major record) {
        return this.majorMapper.insert(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Major selectByPrimaryKey(Integer id) {
        return this.majorMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Major record) {
        return this.majorMapper.updateByPrimaryKeySelective(record)>0;
    }

    @Override
    public boolean updateByPrimaryKey(Major record) {
        return this.majorMapper.updateByPrimaryKey(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage search(PageSearch pageSearch) {
        //1、启用分页使用PageHelper插件
        PageHelper.offsetPage(pageSearch.getOffset(),pageSearch.getLimit());

        //2、执行查询操作
        List<Major> data=this.majorMapper.search(pageSearch);

        //3、得到共有多少笔数据
        Page  pg=(Page) data;
        long total=pg.getTotal();

        //4、封装成BSTable这个类中
        BSTable tb=new BSTable();
        tb.setTotal(total);
        tb.setRows(data);

        return  ResponseMessage.success("操作成功",tb);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Major> searchAll() {
        return this.majorMapper.searchAll();
    }
}
