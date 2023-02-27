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
 * ServletRequestAttributeListener的使用
 */
@Controller
@RequestMapping(value = "/v8")
public class TestRequestAttributeListenerController {
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<List<EmployeeVO>> test1(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println("add attribute");
        request.setAttribute("name", "马克-to-win");
        System.out.println("replace attribute");
        request.setAttribute("name", "mark-to-win");
        System.out.println("remove attribute");
        request.removeAttribute("name");
        return BaseRespBean.success("ok");
    }
}
