package com.sohu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v3")
public class Employee2Controller {
    // http://localhost:8080/lesson03/v3/query/?name=oppo&email=1035525823@qq.com
    // RequestParam不支持POST方法
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public String queryEmployee(@RequestParam(name = "name") String firstName,
                                @RequestParam(name = "email") String email){
        System.out.println(firstName);
        System.out.println(email);
        return "success";
    }
}
