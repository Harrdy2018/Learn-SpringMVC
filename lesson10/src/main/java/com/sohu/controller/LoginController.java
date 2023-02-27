package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import com.sohu.vo.EmployeeVO;
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
@RequestMapping(value = "/v10")
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<List<EmployeeVO>> getAllEmployees(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        // 把用户信息保存到session中
        session.setAttribute("username", "username");
        return BaseRespBean.success("ok");
    }
}
