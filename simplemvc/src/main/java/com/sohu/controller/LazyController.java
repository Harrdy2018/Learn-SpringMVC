package com.sohu.controller;

import com.sohu.modellazy.LazyClassA;
import com.sohu.modellazy.LazyClassB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/lazy")
public class LazyController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println("end");
        return "Hello World!";
    }

    @Resource
    public LazyClassA lazyClassA;

//    @Resource
//    public LazyClassB lazyClassB;
}
