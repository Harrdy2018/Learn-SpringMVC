package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v2")
public class Learn01Controller {
    /**
     * URL http://localhost:8080/lesson02/v2/01/hello
     * SpringMVC提供了@RequestHeader注解，可以将请求头中变量值映射到控制器的参数中。如下例子：将请求头中的变量值token映射到控制器参数token中
     * 如果@RequestHeader绑定的变量，如果在请求头中不存在。Spring会将控制器中的参数初始化为null。
     */
    @RequestMapping(value = "/01/hello", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<String> getAllEmployees2(HttpServletRequest request, HttpServletResponse response, HttpSession session,
        @RequestHeader(value = "Host") String hostX,
        @RequestHeader(value = "User-Agent") String userAgentX){
        return BaseRespBean.success("START "+hostX+" "+userAgentX+" END");
    }
}

