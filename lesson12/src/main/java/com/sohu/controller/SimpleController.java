package com.sohu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SimpleController extends AbstractController {

    /**
     * http://localhost:8080/lesson12/test/v12?username=oppo
     *
     * @param request r
     * @param response r
     * @return M
     * @throws Exception e
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("SimpleController...");
        System.out.println(request.getParameter("username"));
        ModelAndView mv = new ModelAndView("simpleController");
        mv.addObject("msg", "hello,SimpleController");
        return mv;
    }
}
