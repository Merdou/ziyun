package com.hxzy.mapper;

import com.hxzy.entity.HomeworkComments;
import java.util.List;

public interface HomeworkCommentsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(HomeworkComments record);

    HomeworkComments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomeworkComments record);

    int updateByPrimaryKey(HomeworkComments record);
}