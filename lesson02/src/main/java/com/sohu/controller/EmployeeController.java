package com.sohu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sohu.model.EmployeeVO;
import com.sohu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v2")
public class EmployeeController {
    @Autowired(required = true)
    EmployeeService employeeService;

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    @ResponseBody
    public String getAllEmployees(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        List<EmployeeVO> employeeVOList = employeeService.getAllEmployees();

        ObjectMapper objectMapper = new ObjectMapper();
        String resJson = null;
        try {
            resJson = objectMapper.writeValueAsString(employeeVOList);
            System.out.println(resJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resJson;
    }
}
