package com.sohu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/v1")
public class HelloWorldController {
    /**
     * 测试返回值类型 String
     * ResponseBody注解控制返回纯字符串
     *
     * @param session
     * @return String
     */
    @ResponseBody
    @RequestMapping(value="/display1.do", method = RequestMethod.GET)
    public String display1(HttpSession session){
        return "Hello World";
    }

    /**
     * 测试返回值类型 String
     * ResponseBody注解控制返回纯字符串
     *
     * @param session
     * @return String
     */
    @ResponseBody
    @RequestMapping(value="/display2.do", method = RequestMethod.POST)
    public String display2(HttpSession session){
        return "Hello World";
    }

    /**
     * 测试返回值类型 ModelAndView
     *
     * @param session
     * @return ModelAndView
     */
    @RequestMapping(value="/display3.do", method = RequestMethod.GET)
    public ModelAndView display3(HttpSession session){
        ModelAndView mv=new ModelAndView();
        mv.addObject("resquestUrl", "/v1/display3.do");
        mv.addObject("msg", "Hello World");
        /*
        指定视图：
        如果没有配置视图解析器 mv.setViewName("/WEB-INF/pages/show.jsp");
        配置了视图解析器 mv.setViewName("show");
        */
        mv.setViewName("show");
        return mv;
    }

    /**
     * 测试返回值类型 String传递视图
     *
     * @param model
     * @return 视图
     */
    @RequestMapping(value="/display4.do", method = RequestMethod.GET)
    public String display4(Model model){
        model.addAttribute("resquestUrl", "/v1/display4.do");
        model.addAttribute("msg", "Hello World");
        return "show";
    }
}
