package com.hxzy.controller.student;

import com.hxzy.common.util.HttpSessionUtil;
import com.hxzy.entity.Data;
import com.hxzy.entity.Jobtable;
import com.hxzy.entity.Student;
import com.hxzy.entity.Teacher;
import com.hxzy.service.DataService;
import com.hxzy.service.JobtableService;
import com.hxzy.service.TeacherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 学员上传个人作品
 */
@Log4j2
@Controller
@RequestMapping(value = "/student")
public class StudentUploadController {

    @Autowired
    private DataService dataService;

    @Autowired
    private JobtableService jobtableService;

    @Autowired
    private TeacherService teacherService;


    //显示个人作品上传
    @GetMapping(value = "/upload")
    public String upload(Model model,HttpSession session){
        //标签   sys_data  types=1
        List<Data> arrLabels=this.dataService.selectTypes(1);
        model.addAttribute("arrLabels",arrLabels);

        //内容  sys_data  types=2
        List<Data> arrContents=this.dataService.selectTypes(2);
        model.addAttribute("arrContents",arrContents);

        //类型   sys_data  types=3
        List<Data> arrTypes=this.dataService.selectTypes(3);
        model.addAttribute("arrTypes",arrTypes);

        //加载该学生班级带课老师
        Student stu= HttpSessionUtil.getSessionStudent(session);
        List<Teacher> arrTeacher=this.teacherService.searchTeacher(stu.getClassesId());
        model.addAttribute("arrTeacher", arrTeacher);

        return "student/upload";
    }

    //文件上传
    @PostMapping(value = "/upload")
    public String save(Jobtable record, String[] attachment, HttpSession session){
        //得到学生信息
        Student stu=(Student) session.getAttribute("studentInfo");
        record.setStudentId(stu.getId());
        record.setSubmitTime(new Date());
        record.setState(1);
        //转List
        List<String> arr=Arrays.asList(attachment);

        try{
            this.jobtableService.insertBatchDetail(record,arr);
            log.info("上传文件成功");
            //成功页面
        }catch(RuntimeException e){
            log.error(e.getMessage());
            //失败页面
        }

        //重定向
        return "redirect:/student/upload";
    }

}
