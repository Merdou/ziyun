package com.hxzy.controller.admin;

import com.hxzy.common.util.MD5Util;
import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Data;
import com.hxzy.entity.Major;
import com.hxzy.entity.Teacher;
import com.hxzy.mapper.TeacherMapper;
import com.hxzy.service.DataService;
import com.hxzy.service.MajorService;
import com.hxzy.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/teacher")
public class AdminTeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private DataService dataService;


    /**
     * 分页查询
     */
    @ResponseBody
    @GetMapping(value = "/data")
    public ResponseMessage data(PageSearch pageSearch){
        return this.teacherService.search(pageSearch);
    }
    /**
     * 显示教师页面
     * @return
     */
    @GetMapping(value = "/search")
    public String search(Model model){
        //查询专业
        List<Major> arrMajor=this.majorService.searchAll();
        model.addAttribute("arrMajor", arrMajor);

        //查询学历
        List<Data> arrData=this.dataService.selectTypes(5);
        model.addAttribute("arrData", arrData);

        return "admin/teacher/list";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseMessage save(Teacher teacher, int[] teach){
        ResponseMessage  rm=null;
        //teach数组转换成字符串  1,2
        String teachKnowledge=StringUtils.join(teach,',');
        teacher.setTeachKnowledge(teachKnowledge);

        //新增
        if(teacher.getId()==null || teacher.getId()==0){
            //新增密码取手机后6位  位11
            String pwd=teacher.getMobile().substring(5);
            //生成盐
            String salt= MD5Util.randomSalt(5);
            //加密密码
            String md5Pwd=MD5Util.MD5Encode(pwd,salt);
            //对象赋值
            teacher.setSalt(salt);
            teacher.setPassword(md5Pwd);

            boolean result=this.teacherService.insert(teacher);
            if(result){
                rm=ResponseMessage.success("操作成功");
            }else{
                rm=ResponseMessage.failed(500,"新增数据失败!");
            }
        }else{
            //选择性的更新
            boolean result=this.teacherService.updateByPrimaryKeySelective(teacher);
            if(result){
                rm=ResponseMessage.success("操作成功");
            }else{
                rm=ResponseMessage.failed(500,"修改数据失败!");
            }
        }
        return rm;


    }

}
