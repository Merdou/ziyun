package com.hxzy.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.hxzy.common.util.HttpSessionUtil;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Data;
import com.hxzy.entity.HomeworkComments;
import com.hxzy.entity.Jobtable;
import com.hxzy.entity.Teacher;
import com.hxzy.service.DataService;
import com.hxzy.service.HomeworkCommentsService;
import com.hxzy.service.JobtableService;
import com.hxzy.vo.ClassesWorkSearchVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

/**
 * 老师批改作业控制器
 */
@Log4j2
@Controller
@RequestMapping(value = "/teacher")
public class TeacherWorkController {

    @Autowired
    private DataService dataService;

    @Autowired
    private JobtableService  jobtableService;

    @Autowired
    private HomeworkCommentsService homeworkCommentsService;

    /**
     * 老师未批改作业
     * @param classesWorkSearchVO
     * @param session
     * @param model
     * @return
     */
    @GetMapping(value = "/work/notchecked")
    public String noChecked(ClassesWorkSearchVO classesWorkSearchVO, HttpSession session, Model model){

        //模糊每页显示一笔
        //classesWorkSearchVO.setPageSize(1);

        //得到当前登录的老师
        Teacher  teacher= HttpSessionUtil.getSessionTeacher(session);
        classesWorkSearchVO.setTeacherId(teacher.getId());

        PageInfo<Jobtable> pageInfo=this.jobtableService.searchTeacherNoChecked(classesWorkSearchVO);

        //传递班级id
        model.addAttribute("classesId", classesWorkSearchVO.getClassesId());
        model.addAttribute("pageInfo",pageInfo);

        return "teacher/weipigai";
    }

    //点评作业
    @GetMapping(value = "/dianping/{id}")
    public String dianping(@PathVariable(value = "id") int id,Model model){
        //根据id查询学生的作品信息
       Jobtable  jobtable=this.jobtableService.selectByPrimaryKey(id);
       if(jobtable==null){
           log.error("查询不到作品编号为:"+id+"的数据");
           return "redirect:/teacher/teachClasses";
       }

        List<Data>  commentArr=this.dataService.selectTypes(10);
        model.addAttribute("commentArr",commentArr);

        //学生作品
        model.addAttribute("jobtable",jobtable);

        return "teacher/dianping";
    }

    /**
     * 保存点评数据
     * @param homeworkComments
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/dianping")
    public ResponseMessage  saveDianPing(HomeworkComments  homeworkComments){
          homeworkComments.setCommentsDate(new Date());

         ResponseMessage  rm=null;

          try{
              boolean result=this.homeworkCommentsService.insert(homeworkComments);

              rm=ResponseMessage.success("成功");
          }catch(RuntimeException e){
              rm=ResponseMessage.failed(500,e.getMessage());
          }
        return rm;
    }
}
