package com.hxzy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.util.DataUtil;
import com.hxzy.common.vo.DataTypeVO;
import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Data;
import com.hxzy.service.DataService;
import com.hxzy.vo.DataSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/data")
public class AdminDataController {

    @Autowired
    private DataUtil  dataUtil;

    @Autowired
    private DataService dataService;

    @GetMapping(value = "/search")
    public String search(){
        return "admin/data/list";
    }

    /**
     * 加载边左树形分类
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/tree")
    public ResponseMessage  loadDataType(){
        List<DataTypeVO> arr=this.dataUtil.getDataTypeVOList();

        return ResponseMessage.success("成功",arr);
    }

    @ResponseBody
    @PostMapping(value = "/data")
    public ResponseMessage ajaxData(DataSearchVO searchVO){
       return this.dataService.search(searchVO);
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseMessage save(Data data){

        boolean result=false;
        if(data.getId()==null){
            result=this.dataService.insert(data);
        }else{
            result=this.dataService.updateByPrimaryKeySelective(data);
        }
         ResponseMessage  vo= result? ResponseMessage.success(): ResponseMessage.failed();
        return vo;
    }

    /**
     * 判断值是否被使用
     * @param data
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/exist")
    public JSONObject existsName(Data data){
        boolean result=this.dataService.existsName(data);
        JSONObject json=new JSONObject();
        json.put("valid", result);
        return json;
    }



}
