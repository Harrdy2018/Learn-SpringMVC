package com.sohu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sohu.bo.EmployeeBO;
import com.sohu.dto.EmployeeDTO;
import com.sohu.service.EmployeeService;
import com.sohu.validator.EmployeeValidator;
import com.sohu.vo.BaseRespBean;
import com.sohu.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v4")
public class EmployeeController {
    /**
     * RequestBody以对象接收前端传过来的json数据,后台自动装配
     *
     * @param employeeDTO
     * @return
     */
    @RequestMapping(value = "/queryEmployee1", method = RequestMethod.POST)
    @ResponseBody
    public String queryEmployee(@RequestBody EmployeeDTO employeeDTO, HttpServletRequest request){
        System.out.println(employeeDTO);

        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setId(10001212);
        employeeVO.setLastName("oppo");
        employeeVO.setFirstName("sohu");

        BaseRespBean<EmployeeVO> resp = new BaseRespBean<>();
        resp.setRetCode(200);
        resp.setData(employeeVO);

        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try {
            str = objectMapper.writeValueAsString(resp);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    @RequestMapping(value = "/queryEmployee2", method = RequestMethod.POST)
    @ResponseBody
    public String queryEmployee2(@RequestBody EmployeeDTO employeeDTO, HttpServletRequest request){
        System.out.println(employeeDTO);
        BaseRespBean<EmployeeVO> resp = new BaseRespBean<>();
        resp.setRetCode(400);
        resp.setData(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try {
            str = objectMapper.writeValueAsString(resp);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}
