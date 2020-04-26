package com.hxzy.service;

import com.hxzy.entity.HomeworkComments;

public interface HomeworkCommentsService {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(HomeworkComments record);

    HomeworkComments selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(HomeworkComments record);

    boolean updateByPrimaryKey(HomeworkComments record);
}
