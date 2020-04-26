package com.hxzy.controller.admin;

import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Teacher;
import com.hxzy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = "/login")
    public String login(){
        return "admin/login";
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseMessage loginValidate(Teacher teacher, HttpSession session){
        ResponseMessage rm= this.teacherService.login(teacher);
        if(rm.getCode()==0){
            session.setAttribute("adminInfo",rm.getData());
        }
        return rm;
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("adminInfo");
        return "redirect:/admin/login";
    }
}
