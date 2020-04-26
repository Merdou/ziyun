package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxzy.common.vo.BSTable;
import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Classes;
import com.hxzy.mapper.ClassesMapper;
import com.hxzy.service.ClassesService;
import com.hxzy.vo.TeachClassesSearch;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@Service
@Transactional
public class ClassesServiceImpl implements ClassesService {

    @Autowired

    private ClassesMapper classesMapper;
    @Override
    public boolean insert(Classes record) {
        return this.classesMapper.insert(record)>0;
    }

    @Override
    public boolean insertBatch(Classes record, List<Integer> teacherArr) throws RuntimeException{
        //调用新增方法
        boolean result=this.insert(record);
        if(result){
            //批量插入信息
            result=this.classesMapper.insertBatchTeacherClasses(record.getId(), teacherArr)>0;
            if(!result){
                log.error("新增带级老师信息失败,"+record.toString());
                throw new RuntimeException("新增带级老师信息失败");
            }
        }else{
            log.error("新增班级信息失败,"+record.toString());
            throw new RuntimeException("新增班级信息失败");
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Classes selectByPrimaryKey(Integer id) {
        return this.classesMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Classes record) {
        return this.classesMapper.updateByPrimaryKeySelective(record)>0;
    }

    @Override
    public boolean updateByPrimaryKey(Classes record) {
        return this.classesMapper.updateByPrimaryKey(record)>0;
    }

    @Override
    public boolean updateByPrimaryKey(Classes record, List<Integer> teacherArr) throws RuntimeException{
        //1、修改数据
        boolean result=this.updateByPrimaryKey(record);
        if(result){
            //2、删除teacher_classes
            this.classesMapper.deleteByClassesId(record.getId());

            //3、新增teacher_classes
            result=this.classesMapper.insertBatchTeacherClasses(record.getId(),teacherArr)>0;

            if(!result){
                log.error("更新带班老师信息失败,"+record.toString());
                throw new RuntimeException("更新带班老师信息失败");
            }
        }else{
            log.error("更新班级信息失败,"+record.toString());
            throw new RuntimeException("更新班级信息失败");
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage search(PageSearch pageSearch) {
        PageHelper.offsetPage(pageSearch.getOffset(),pageSearch.getLimit());

        List<Classes>  data=this.classesMapper.search(pageSearch);
        //循环查询所带班老师有哪些


        Page pg=(Page) data;
        long total=pg.getTotal();

        //封装 BSTable
        BSTable bs=new BSTable();
        bs.setTotal(total);
        bs.setRows(data);

        return  ResponseMessage.success("成功",bs);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Classes> selectByMajorId(int majorId) {
        return this.classesMapper.selectByMajorId(majorId);
    }

    /**
     * 查询未结课的班级
     * @param majorId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Classes> selectByNotCloseMajorId(int majorId) {
        return this.classesMapper.selectByNotCloseMajorId(majorId);
    }

    //查询该登录的教师所带的班级
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<Classes> searchTeachClasses(TeachClassesSearch search) {
        //分页
        PageHelper.startPage(search.getPageNum(),search.getPageSize());

        List<Classes> data=this.classesMapper.searchTeachClasses(search);

        Page  pg=(Page) data;

        return pg.toPageInfo();
    }
}
