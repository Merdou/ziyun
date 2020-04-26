package com.hxzy.vo;

import com.hxzy.common.vo.GetPageSearch;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Lock;

/**
 *  学员查询已批改或未批改作业的
 */
@Getter
@Setter
public class StudentVO  extends GetPageSearch{

    private Integer stuId;


}
