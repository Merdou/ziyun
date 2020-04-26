package com.hxzy.controller.student;

import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Student;
import com.hxzy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/student")
public class StudentLoginController {

    @Autowired
    private StudentService studentService;

    //显示登录界面
    @GetMapping(value = "/login")
    public String login(){
        return "student/login";
    }

    //登录
    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseMessage executeLogin(Student student, HttpSession session){
        ResponseMessage rm=this.studentService.login(student);
        //成功，把登录用户保存到session中
        if(rm.getCode()==0){
           session.setAttribute("studentInfo",rm.getData());
        }

        return rm;
    }

    /**
     * 退出登录(要么回到首页，要么回到登录页)
     * @param session
     */
    @GetMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("studentInfo");
        return "redirect:/student/login";
    }

}
