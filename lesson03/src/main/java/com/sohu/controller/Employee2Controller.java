package com.sohu.controller;

import com.sohu.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * RequestBody直接以String接收前端传过来的json数据,后台收到的也是json字符串
     *
     * @param jsonString
     * @return
     */
    @RequestMapping(value = "/test0", method = RequestMethod.POST)
    @ResponseBody
    public String queryEmployee(@RequestBody String jsonString){
        System.out.println(jsonString);
        return "success";
    }

    /**
     * RequestBody以对象接收前端传过来的json数据,后台自动装配
     *
     * @param employeeDTO
     * @return
     */
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ResponseBody
    public String queryEmployee(@RequestBody EmployeeDTO employeeDTO){
        System.out.println(employeeDTO);
        return "success";
    }
}
