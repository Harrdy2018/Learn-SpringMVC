package com.sohu.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {
    /**
     * 应用场景：登录认证、身份授权
     *
     * @param request request
     * @param response response
     * @param handler handler
     * @return true表示放行
     * @throws Exception Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyHandlerInterceptor preHandle");
        return true;
    }

    /**
     * 进入Handler开始执行，并且在返回ModelAndView之前调用
     * 应用场景：对ModelAndView对象操作，可以把公共模型数据传到前台，可以统一指定视图
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyHandlerInterceptor postHandle");
    }

    /**
     * 执行完Handler之后调用
     * 应用场景：统一异常处理、统一日志处理
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyHandlerInterceptor afterCompletion");
    }
}
