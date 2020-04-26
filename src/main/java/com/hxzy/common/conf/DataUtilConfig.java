package com.hxzy.common.conf;

import com.hxzy.common.util.DataUtil;
import com.hxzy.common.vo.DataTypeVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台通用字典数据
 */
@Configuration
public class DataUtilConfig {

    /**
     * 一个类，交给Spring管理
     * @return
     */
    @Bean
    public DataUtil getDataUtil(){
        DataUtil  dt=new DataUtil();

        //集合
        List<DataTypeVO>  arr=new ArrayList<DataTypeVO>();

        DataTypeVO type01=new DataTypeVO();
        type01.setTypeId(1);
        type01.setText("作业标签");
        arr.add(type01);

        DataTypeVO type02=new DataTypeVO();
        type02.setTypeId(2);
        type02.setText("作业内容");
        arr.add(type02);

        DataTypeVO type03=new DataTypeVO();
        type03.setTypeId(3);
        type03.setText("作业类型");
        arr.add(type03);

        DataTypeVO type05=new DataTypeVO();
        type05.setTypeId(5);
        type05.setText("学历");
        arr.add(type05);

        //放到DataUtil中去
        dt.setDataTypeVOList(arr);

        return dt;
    }

}
