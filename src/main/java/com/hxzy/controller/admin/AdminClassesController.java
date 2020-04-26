package com.hxzy.controller.admin;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Classes;
import com.hxzy.entity.Major;
import com.hxzy.entity.Teacher;
import com.hxzy.service.ClassesService;
import com.hxzy.service.MajorService;
import com.hxzy.service.TeacherService;
import com.hxzy.vo.ClassesSearch;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 班级控制器
 */
@Log4j2
@Controller
@RequestMapping(value = "/admin/classes")
public class AdminClassesController {

    @Autowired
    private MajorService majorService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 分页查询
     */
    @ResponseBody
    @PostMapping(value = "/data")
    public ResponseMessage data(ClassesSearch pageSearch){
        return this.classesService.search(pageSearch);
    }

    /**
     * 显示班级页面
     * @return
     */
    @GetMapping(value = "/search")
    public String search(Model  model){
        //加载专业信息
        List<Major> allMajor=this.majorService.searchAll();
        model.addAttribute("allMajor", allMajor);

        //加载所有老师(未离职的)
        List<Teacher> allTeacher=this.teacherService.searchAll();
        model.addAttribute("allTeacher",allTeacher);

        return "admin/classes/list";
    }

    /**
     * 保存操作
     * @param classes
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseMessage save(Classes classes,Integer[] teacherIds){
        ResponseMessage  rm=null;
       //数组[]  转换成  List集合
        List<Integer> teacherArr= Arrays.asList(teacherIds);

        try{
            if(classes.getId()==null || classes.getId()==0){
                //新增
                boolean result=this.classesService.insertBatch(classes,teacherArr);
                if(result){
                    rm=ResponseMessage.success("操作成功");
                }else{
                    rm=ResponseMessage.success("新增数据失败");
                }

            }else{
                //修改
                boolean result=this.classesService.updateByPrimaryKey(classes,teacherArr);
                if(result){
                    rm=ResponseMessage.success("操作成功");
                }else{
                    rm=ResponseMessage.success("修改数据失败");
                }
            }
        }catch(Exception e){
           log.error(e.getMessage());
            e.printStackTrace();
        }
        return rm;
    }


    /**
     * 根据专业编号得到班级信息
     * @param majorId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/major/{id}")
    public ResponseMessage classesInfo(@PathVariable("id") int majorId){
        List<Classes> arr=this.classesService.selectByMajorId(majorId);

        return ResponseMessage.success("ok",arr);
    }

    /**
     * 根据专业编号得到未结课班级信息
     * @param majorId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/notclose/major/{id}")
    public ResponseMessage classesNotCloseInfo(@PathVariable("id") int majorId){
        List<Classes> arr=this.classesService.selectByNotCloseMajorId(majorId);
        return ResponseMessage.success("ok",arr);
    }


}
