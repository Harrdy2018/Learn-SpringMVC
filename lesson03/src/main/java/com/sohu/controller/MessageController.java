package com.sohu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.MessageConvertUtils;

@Controller
@RequestMapping(value = "/v3")
public class MessageController {
    @Autowired
    @Qualifier(value = "messageUtil")
    private MessageConvertUtils messageConvertUtils;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public String display(){
        messageConvertUtils.readLocaleSpecificMessage();
        return "success";
    }
}
