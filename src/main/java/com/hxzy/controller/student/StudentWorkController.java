package com.hxzy.controller.student;

import com.github.pagehelper.PageInfo;
import com.hxzy.entity.Jobtable;
import com.hxzy.entity.Student;
import com.hxzy.service.JobtableService;
import com.hxzy.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 学生作业控制器
 */
@Controller
@RequestMapping(value = "/student/work")
public class StudentWorkController {

    @Autowired
    private JobtableService jobtableService;

    //未批改
    @GetMapping(value = "/notchecked")
    public String weipigai( Integer pageNum, Model model, HttpSession session){
        Student stu=(Student) session.getAttribute("studentInfo");
        //查询条件
        StudentVO  studentVO=new StudentVO();
        studentVO.setStuId(stu.getId());
        if(pageNum!=null) {
            studentVO.setPageNum(pageNum);
        }
        //查询
        PageInfo<Jobtable> pageInfo=this.jobtableService.searchNoChecked(studentVO);
        model.addAttribute("pageInfo",pageInfo);
        return "student/weipigai";
    }


}
