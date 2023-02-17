package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import com.oppo.tool.BeanConvertUtils;
import com.sohu.bo.EmployeeBO;
import com.sohu.service.EmployeeService;
import com.sohu.vo.EmployeeVO;
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
@RequestMapping(value = "/v5")
public class EmployeeController {
    @Autowired(required = true)
    EmployeeService employeeService;

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<List<EmployeeVO>> getAllEmployees(HttpServletRequest request, HttpServletResponse response, HttpSession session){

        List<EmployeeBO> employeeBOS = employeeService.getAllEmployees();
        // BO->VO
        List<EmployeeVO> employeeVOS = BeanConvertUtils.convertListTo(employeeBOS, EmployeeVO::new);

        return BaseRespBean.success(employeeVOS);
    }
}
