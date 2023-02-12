package com.sohu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oppo.bean.BaseRespBean;
import com.oppo.bean.StatusCode;
import com.sohu.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/v4")
public class LearnAOPController {
    @RequestMapping(value = "/say1", method = RequestMethod.GET)
    @ResponseBody
    public String display1(EmployeeDTO employeeDTO, HttpServletRequest request){
        System.out.println(employeeDTO);
        Object o = "Hello World";
        BaseRespBean<Object> resp = new BaseRespBean<>();
        resp.setData(o);
        resp.setStatusCode(StatusCode.SUCCESS);

        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try {
            str = objectMapper.writeValueAsString(resp);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    @RequestMapping(value = "/say2", method = RequestMethod.GET)
    @ResponseBody
    public String display2(String name, Integer age){
        return "Hello World!";
    }

    @RequestMapping(value = "/say3", method = RequestMethod.GET)
    @ResponseBody
    public String display3(String name, Integer age){
        throw new RuntimeException("my exception", new Throwable("have 0!"));
    }

    @RequestMapping(value = "/say4", method = RequestMethod.GET)
    @ResponseBody
    public String display4(String name, Integer age){
        System.out.println("execute display4");
        return "Hello World!";
    }
}
