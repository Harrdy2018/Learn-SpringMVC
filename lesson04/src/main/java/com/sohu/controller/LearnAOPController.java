package com.sohu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oppo.annotation.InterfaceLog;
import com.oppo.bean.BaseRespBean;
import com.oppo.bean.RetCode;
import com.oppo.exception.BusinessException;
import com.sohu.dto.EmployeeDTO;
import com.sohu.vo.EmployeeVO;
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
        resp.setCode(RetCode.SUCCESS.getCode());

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

    /**
     * 利用注解实现切入点例子
     * @param name
     * @param age
     * @return
     */
    @InterfaceLog(version = "V2.0",
            type = "req",
            interfaceName = "http://localhost:8080/lesson04/v4/say5?name=oppo&age=18",
            sourceID = "",
            destinationID = "")
    @RequestMapping(value = "/say5", method = RequestMethod.GET)
    @ResponseBody
    public String display5(String name, Integer age){
        System.out.println("execute display5");
        return "Hello "+name;
    }

    /**
     * java异常统一解决方案
     * http://localhost:8080/lesson04/v4/say6?id=12&firstName=abc
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/say6", method = RequestMethod.GET)
    public BaseRespBean<EmployeeVO> display6(Integer id, String firstName){
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setId(id);
        employeeVO.setFirstName(firstName);
        // 模拟业务异常
        if(id == null || id != 12) {
            throw new BusinessException(RetCode.SYSTEM_ERROR.getCode(), "id is error");
        }
        return BaseRespBean.success(employeeVO);
    }
}
