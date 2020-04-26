package com.hxzy.vo;

import com.hxzy.common.vo.GetPageSearch;
import com.hxzy.common.vo.PageSearch;
import lombok.Getter;
import lombok.Setter;

/**
 * 老师所带班级查询(前端)
 */
@Getter
@Setter
public class TeachClassesSearch extends GetPageSearch {

    //老师编号
    private int teacherId;

}
