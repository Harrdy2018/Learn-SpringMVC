package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import com.sohu.bo.UserBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v9")
public class LoginOutController {
    private static final String USER_MAP = "user_map";

    /**
     * http://localhost:8080/lesson09/v9/loginout?userName=oppo&password=root
     *
     * @param userName userName
     * @param password password
     * @param request request
     * @return
     */
    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<String> loginout(String userName, String password, HttpServletRequest request){
        UserBO userBO = new UserBO();
        userBO.setUserName(userName);
        userBO.setPassword(password);

        Map<UserBO, HttpSession> userSessionMap = (Map<UserBO, HttpSession>)request.getServletContext().getAttribute(USER_MAP);
        HttpSession httpSession = userSessionMap.get(userBO);
        httpSession.invalidate();
        userSessionMap.remove(userBO);
        return BaseRespBean.success("登出成功");
    }
}

