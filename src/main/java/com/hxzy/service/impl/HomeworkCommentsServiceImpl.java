package com.hxzy.service.impl;

import com.hxzy.entity.HomeworkComments;
import com.hxzy.entity.Jobtable;
import com.hxzy.entity.JobtableViews;
import com.hxzy.mapper.HomeworkCommentsMapper;
import com.hxzy.service.HomeworkCommentsService;
import com.hxzy.service.JobtableService;
import com.hxzy.service.JobtableViewsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class HomeworkCommentsServiceImpl implements HomeworkCommentsService{

    @Autowired
    private HomeworkCommentsMapper homeworkCommentsMapper;

    @Autowired
    private JobtableService jobtableService;

    @Autowired
    private JobtableViewsService jobtableViewsService;


    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        return this.homeworkCommentsMapper.deleteByPrimaryKey(id)>0;
    }

    /**
     * 点评作业
     * @param record
     * @return
     */
    @Override
    public boolean insert(HomeworkComments record) {
        boolean result= this.homeworkCommentsMapper.insert(record)>0;
        if(result){
           //新增 点评和浏览次数
            JobtableViews  jobtableViews=new JobtableViews();
            jobtableViews.setJobTableId(record.getJobTableId());
            jobtableViews.setLikes(0);
            jobtableViews.setVisits(0);
            result=this.jobtableViewsService.insert(jobtableViews);

            if(result){
               //更新作业状态
                Jobtable  jobtable=new Jobtable();
                jobtable.setId(record.getJobTableId());
                jobtable.setState(2);

                result=this.jobtableService.updateByPrimaryKeySelective(jobtable);

                if(!result){
                    log.error("点评作业失败:"+record.getJobTableId()+",更新Jobtable状态失败");
                    throw new RuntimeException("点评作业失败:"+record.getJobTableId());
                }
            }else{
                log.error("点评作业失败:"+record.getJobTableId()+",插入jobtable_views表失败");
                throw new RuntimeException("点评作业失败:"+record.getJobTableId());
            }
        }else{
            log.error("点评作业失败:"+record.getJobTableId());
            throw new RuntimeException("点评作业失败:"+record.getJobTableId());
        }
        return true;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public HomeworkComments selectByPrimaryKey(Integer id) {
        return this.homeworkCommentsMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(HomeworkComments record) {
        return this.homeworkCommentsMapper.updateByPrimaryKeySelective(record)>0;
    }


    @Override
    public boolean updateByPrimaryKey(HomeworkComments record) {
        return this.homeworkCommentsMapper.updateByPrimaryKey(record)>0;
    }
}
