package com.sohu.controller;

import com.sohu.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/basetest2")
public class StudentController {
    @Resource
    public Student student;

    /**
     * 构造函数初始化的时候，成员bean还没有注入
     */
    public StudentController() {
        System.out.println("StudentController start");
        System.out.println("student= "+this.student);
        System.out.println("StudentController end");
    }

    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    @ResponseBody
    public String getAllStudents(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println("end");
        return "Hello World!";
    }

    /**
     * 使用 @PostConstruct注解有值
     */
    @PostConstruct
    public void init01() {
        System.out.println("PostConstruct init start");
        System.out.println("student= "+this.student);
        System.out.println("PostConstruct init end");
    }

    /**
     * 使用xml配置也有值 <bean id="studentController" class="com.sohu.controller.StudentController" init-method="init02"></bean>
     */
    public void init02() {
        System.out.println("xml conf init02 start");
        System.out.println("student= "+this.student);
        System.out.println("xml conf init02 end");
    }
}
