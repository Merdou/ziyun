package com.hxzy.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/student/homework")
public class StudentHomeWorkController {

    //已批改的作业
    @GetMapping(value = "/check")
    public String checkHomeWork(Model model){

        return "student/yipigai";
    }

}
