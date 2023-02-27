package com.sohu.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginHandlerInterceptor preHandle");
        // 获取请求的URI
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        // 1.如果请求的URL是公开地址（无需登录就可以访问的URL），采取放行。
        if (requestURI.indexOf("login") > -1) {
            return true;
        }
        // 2.如果用户session存在，则放行。
        Object username = request.getSession().getAttribute("username");
        if (username != null && !username.equals("")) {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginHandlerInterceptor postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginHandlerInterceptor afterCompletion");
    }
}
