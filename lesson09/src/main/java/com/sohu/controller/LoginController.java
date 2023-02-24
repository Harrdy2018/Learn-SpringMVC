package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import com.sohu.bo.UserBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v9")
public class LoginController {

    /**
     * http://localhost:8080/lesson09/v9/login?userName=oppo&password=root
     *
     * @param userName userName
     * @param password password
     * @param request request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<String> login(String userName, String password, HttpServletRequest request){
        UserBO userBO = new UserBO();
        userBO.setUserName(userName);
        userBO.setPassword(password);
        request.getSession().setAttribute("user", userBO);
        return BaseRespBean.success("登录成功");
    }
}

