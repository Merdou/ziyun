package com.hxzy.vo.home;

import com.hxzy.entity.Jobtable;
import lombok.Getter;
import lombok.Setter;

/**
 * 首页查询分类作品详情自定义对象
 */
@Getter
@Setter
public class JobtableVO  extends Jobtable{

    /**
     * 作品的查看总次数
     */
    private Integer visits;

    /**
     * 作品的点赞总次数
     */
    private Integer likes;

    /**
     * 作品的总评论数量
     */
    private Integer commentsCount;

    /**
     * 学生的总的粉丝数
     */
    private Integer fansCount;

    /**
     * 学生的总的作品数量
     */
    private Integer productCount;
}
