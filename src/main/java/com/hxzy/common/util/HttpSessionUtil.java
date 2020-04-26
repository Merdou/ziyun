package com.hxzy.common.util;

import com.hxzy.entity.Student;
import com.hxzy.entity.Teacher;

import javax.servlet.http.HttpSession;

/**
 * session工具
 */
public class HttpSessionUtil {

    /**
     * 取得session中的学生信息
     * @param session
     * @return
     */
    public static Student getSessionStudent(HttpSession session){
        Student stu=null;
        if(session.getAttribute("studentInfo")!=null){
            stu=(Student) session.getAttribute("studentInfo");
        }
        return stu;
    }

    /**
     * 取得session中的教师信息
     * @param session
     * @return
     */
    public static Teacher getSessionTeacher(HttpSession session){
        Teacher teacher=null;
        if(session.getAttribute("teacherInfo")!=null){
             teacher=(Teacher) session.getAttribute("teacherInfo");
        }
        return teacher;
    }



    /**
     * 取得session后台
     * @param session
     * @return
     */
    public static Teacher getSessionAdmin(HttpSession session){
        Teacher teacher=null;
        if(session.getAttribute("adminInfo")!=null){
            teacher=(Teacher) session.getAttribute("adminInfo");
        }
        return teacher;
    }
}
