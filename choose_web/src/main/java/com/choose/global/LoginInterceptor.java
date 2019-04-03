package com.choose.global;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //必须登录才能访问网址
        if (request.getSession().getAttribute("login") == null) {
            request.setAttribute("message","必须登录才能选课哦");
            request.getRequestDispatcher("/user/loginPage").forward(request,response);
            return false;
        } else {
            String servletPath = request.getServletPath();
            Integer type = (Integer) request.getSession().getAttribute("type");
            if (servletPath.contains("admin")) {
                //老师才能进入此页面
                if (type==1) {
                    return true;
                } else {
                    //跳转到无权限访问界面
                    request.setAttribute("message","您没有此权限访问此页面");
                    request.getRequestDispatcher("/user/loginPage").forward(request,response);
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
