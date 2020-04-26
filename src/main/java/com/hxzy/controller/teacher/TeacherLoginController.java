package com.hxzy.controller.teacher;

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

/**
 * 老师登录
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherLoginController {

    @Autowired
    private TeacherService teacherService;


    @GetMapping("/login")
    public String login(){
        return "teacher/login";
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseMessage  loginValidator(Teacher teacher, HttpSession session){
        ResponseMessage rm=this.teacherService.login(teacher);
        if(rm.getCode()==0){
            session.setAttribute("teacherInfo", rm.getData());
        }
        return  rm;
    }



}
