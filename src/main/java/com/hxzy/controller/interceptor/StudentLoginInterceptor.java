package com.hxzy.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 学生登录检查拦截器
 */
@Component
public class StudentLoginInterceptor implements HandlerInterceptor{
    //前置增强（执行Controller方法前做的事情）
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session=httpServletRequest.getSession();

        //登录过了
        if(session.getAttribute("studentInfo")!=null){
            return true;
        }
        //回到登录界面(重定向)
        String projectName=httpServletRequest.getContextPath();
        httpServletResponse.sendRedirect(projectName+"/student/login");
        //不放行
        return false;
    }

    //执行完Controller以后做的事情
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //当view被加载完成以后做事情
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
