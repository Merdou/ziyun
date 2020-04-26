package com.hxzy.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping(value = "/admin")
@Controller
public class AdminIndexController {

    /**
     * 后台首页
     * @return
     */
    @GetMapping(value = {"/", "/index"})
    public String index(){
        return "admin/index";
    }

    /**
     * default页
     * @return
     */
    @GetMapping(value = "/default")
    public String defaultPage(){

        return "admin/default";
    }

}
