package com.hxzy.controller.portal;

import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.service.api.HomeJobTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 给前端页面提供的数据
 */
@RequestMapping(value = "/api")
@Controller
public class HomeController {

    @Autowired
    private HomeJobTableService homeJobTableService;


    /**
     * 学生作品推荐8笔接口
     * @return
     */
    @GetMapping(value = "/student/assignments")
    @ResponseBody
    public ResponseMessage apiStudentAssignments(){
        return this.homeJobTableService.search();
    }


}
