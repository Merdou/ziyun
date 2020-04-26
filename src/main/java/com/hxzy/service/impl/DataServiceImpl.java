package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hxzy.common.vo.BSTable;
import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Data;
import com.hxzy.mapper.DataMapper;
import com.hxzy.service.DataService;
import com.hxzy.vo.DataSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DataServiceImpl implements DataService{

    @Autowired
    private DataMapper dataMapper;

    @Override
    public boolean insert(Data record) {
        return this.dataMapper.insert(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Data selectByPrimaryKey(Integer id) {
        return this.dataMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Data record) {
        return this.dataMapper.updateByPrimaryKeySelective(record)>0;
    }

    @Override
    public boolean updateByPrimaryKey(Data record) {
        return this.dataMapper.updateByPrimaryKey(record)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Data> selectTypes(int types) {
        return this.dataMapper.selectTypes(types);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage search(PageSearch searchVO) {
        PageHelper.offsetPage(searchVO.getOffset(),searchVO.getLimit());
        List<Data> data=this.dataMapper.search(searchVO);
        long total=((Page)data).getTotal();
        //生成BSTable
        BSTable bs=new BSTable();
        bs.setRows(data);
        bs.setTotal(total);
        return ResponseMessage.success("成功",bs);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean existsName(Data data) {
        //true代表可以使用
        boolean  result=true;
        //新增
        if(data.getId()==null || data.getId()==0 ){
            result=this.dataMapper.existsName(data)==0;
        }else{
            //修改
            Data dbDic=this.dataMapper.selectByPrimaryKey(data.getId());
            if(!dbDic.getName().equals( data.getName())){
                result=this.dataMapper.existsName(data)==0;
            }
        }
        return result;
    }
}
