package com.hxzy.controller.admin;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Major;
import com.hxzy.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 专业控制器
 */
@Controller
@RequestMapping(value = "/admin/major")
public class AdminMajorController {

    @Autowired
    private MajorService majorService;

    /**
     * 分页查询
     */
    @ResponseBody
    @GetMapping(value = "/data")
    public ResponseMessage data(PageSearch pageSearch){
        return this.majorService.search(pageSearch);
    }

    /**
     * 显示专业页面
     * @return
     */
    @GetMapping(value = "/search")
    public String search(){
        return "admin/major/list";
    }

    /**
     * 保存操作
     * @param major
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseMessage save(Major major){
       ResponseMessage  rm=null;
       //新增
       if(major.getId()==null || major.getId()==0){
           boolean result=this.majorService.insert(major);
           if(result){
               rm=ResponseMessage.success("操作成功");
           }else{
               rm=ResponseMessage.failed(500,"新增数据失败!");
           }
       }else{
           boolean result=this.majorService.updateByPrimaryKey(major);
           if(result){
               rm=ResponseMessage.success("操作成功");
           }else{
               rm=ResponseMessage.failed(500,"修改数据失败!");
           }
       }
       return rm;
    }

}
