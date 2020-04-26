package com.hxzy.controller.portal;

import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.service.api.HomeJobTableService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class DetaileController {
    @Autowired
    HomeJobTableService homeJobTableService;

    @ResponseBody
    @GetMapping(value = "/detail/{id}")
    public ResponseMessage detail(@PathVariable(value = "id") int id){
       return this.homeJobTableService.searchById(id);
    }
}
