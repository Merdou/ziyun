package com.hxzy.controller.admin;

import com.hxzy.common.util.MD5Util;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Data;
import com.hxzy.entity.Major;
import com.hxzy.entity.Student;
import com.hxzy.service.DataService;
import com.hxzy.service.MajorService;
import com.hxzy.service.StudentService;
import com.hxzy.vo.StudentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/student")
public class AdminStudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private DataService dataService;

    @GetMapping(value = "/search")
    public String search(Model model){
      //加载所有的专业
        List<Major> arrMajor=this.majorService.searchAll();
        model.addAttribute("arrMajor", arrMajor);

        //加载学历
        List<Data> arrEdu=this.dataService.selectTypes(5);
        model.addAttribute("arrEdu",arrEdu);

        return "/admin/student/list";
    }

    @ResponseBody
    @PostMapping(value = "/data")
    public ResponseMessage data(StudentSearch search){
        return this.studentService.search(search);
    }


    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseMessage save(Student student){
        ResponseMessage  rm=null;
        //新增
        if(student.getId()==null || student.getId()==0){
            //新增密码取手机后6位  位11
            String pwd=student.getMobile().substring(5);
            //生成盐
            String salt= MD5Util.randomSalt(5);
            //加密密码
            String md5Pwd=MD5Util.MD5Encode(pwd,salt);

            student.setSalt(salt);
            student.setLoginPwd(md5Pwd);

            boolean result=this.studentService.insert(student);
            if(result){
                rm=ResponseMessage.success("操作成功");
            }else{
                rm=ResponseMessage.failed(500,"新增数据失败!");
            }
        }else{
            boolean result=this.studentService.updateByPrimaryKeySelective(student);
            if(result){
                rm=ResponseMessage.success("操作成功");
            }else{
                rm=ResponseMessage.failed(500,"修改数据失败!");
            }
        }
        return rm;
    }

}
