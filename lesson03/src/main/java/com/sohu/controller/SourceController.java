package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import com.oppo.model.NationalLanguage;
import com.oppo.tool.ApplicationContextUtil;
import com.oppo.tool.MessageSourceAwareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.CustomResourceLoader;
import util.CustomResourceLoaderByConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/v3")
public class SourceController {
    @Autowired
    @Qualifier(value = "messageUtil")
    private MessageSourceAwareUtil messageSourceAwareUtil;

    @Autowired
    private CustomResourceLoader customResourceLoader;

    @Autowired
    private CustomResourceLoaderByConfig customResourceLoaderByConfig;

    /**
     * 国际化测试 http://localhost:8080/lesson03/v3/message?key=123
     *
     * @param request request
     * @param response response
     * @return resp
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<NationalLanguage> display(HttpServletRequest request, HttpServletResponse response){
        String key = request.getParameter("key");
        messageSourceAwareUtil.setCode(key);
        NationalLanguage nationalLanguage = messageSourceAwareUtil.readLocaleSpecificMessage();
        return BaseRespBean.success(nationalLanguage);
    }

    @RequestMapping(value = "/loadfile", method = RequestMethod.GET)
    @ResponseBody
    public String display1(){
        return customResourceLoader.showResourceData();
    }

    @RequestMapping(value = "/loadfileByConfig", method = RequestMethod.GET)
    @ResponseBody
    public String display2(){
        return customResourceLoaderByConfig.showResourceData();
    }

    @RequestMapping(value = "/context", method = RequestMethod.GET)
    @ResponseBody
    public String display3(){
        System.out.println("context ...");
        CustomResourceLoader tempBean = ApplicationContextUtil.getBean("customResourceLoader", CustomResourceLoader.class);
        return tempBean.showResourceData();
    }
}
