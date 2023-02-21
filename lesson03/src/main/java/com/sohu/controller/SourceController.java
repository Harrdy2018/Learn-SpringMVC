package com.sohu.controller;

import com.oppo.tool.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.CustomResourceLoader;
import util.CustomResourceLoaderByConfig;
import util.MessageConvertUtils;

@Controller
@RequestMapping(value = "/v3")
public class SourceController {
    @Autowired
    @Qualifier(value = "messageUtil")
    private MessageConvertUtils messageConvertUtils;

    @Autowired
    private CustomResourceLoader customResourceLoader;

    @Autowired
    private CustomResourceLoaderByConfig customResourceLoaderByConfig;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public String display(){
        messageConvertUtils.readLocaleSpecificMessage();
        return "success";
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
