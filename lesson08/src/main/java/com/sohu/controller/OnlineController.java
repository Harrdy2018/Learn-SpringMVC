package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v8")
public class OnlineController {
    @RequestMapping(value = "/query/online", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<Integer> getOnlineNum(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println(session.getServletContext());
        System.out.println(request.getServletContext());
        System.out.println(session.getServletContext().getClass().getName());

        Integer num = (Integer) session.getServletContext().getAttribute("num");
        return BaseRespBean.success(num);
    }
}
