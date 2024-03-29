package com.sohu.controller;


import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/basetest")
public class EmployeeController {
    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    @ResponseBody
    public String getAllEmployees(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return "Hello World!";
    }

}
