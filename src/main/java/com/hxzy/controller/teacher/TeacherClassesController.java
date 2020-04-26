package com.hxzy.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.hxzy.common.util.HttpSessionUtil;
import com.hxzy.entity.Classes;
import com.hxzy.entity.Teacher;
import com.hxzy.service.ClassesService;
import com.hxzy.service.TeacherService;
import com.hxzy.vo.TeachClassesSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 老师所带班级的信息
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherClassesController {
    @Autowired
    private ClassesService classesService;
    /**
     * 老师所带的班级
     * @return
     */
    @GetMapping(value = "/teachClasses")
    public String teachClasses(TeachClassesSearch search, HttpSession session, Model model){
        //得到登录教师的信息
        Teacher teacher= HttpSessionUtil.getSessionTeacher(session);
        search.setTeacherId(teacher.getId());

        //查询该登录的教师所带的班级
        PageInfo<Classes>  pageInfo=this.classesService.searchTeachClasses(search);
        model.addAttribute("pageInfo",pageInfo);

        return "teacher/classes";
    }

}
