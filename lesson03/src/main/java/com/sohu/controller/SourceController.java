package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import com.oppo.model.NationalLanguage;
import com.oppo.tool.ApplicationContextUtil;
import com.oppo.tool.MessageSourceAwareUtil;
import com.oppo.tool.ServletUtils;
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
import java.util.concurrent.TimeUnit;

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

    /**
     * 主线程和子线程都能拿到Request对象
     * http://localhost:8080/lesson03/v3/testServlet?age=18
     * 可以解决子线程线程中无法获取request对象的问题；但是如果主线程先结束，子线程再去取，会有同样的问题
     * 解决办法：主线程以参数的形式传给子线程
     *
     * @return string
     */
    @RequestMapping(value = "/testServlet", method = RequestMethod.GET)
    @ResponseBody
    public String display4(){
        ServletUtils.setRequestObjectShare(true);
        System.out.println("CurrentThread "+Thread.currentThread().getName() +"URL->"+ ServletUtils.getRequest().getRequestURL());
        System.out.println("params->"+ ServletUtils.getParameter("age"));
        new Thread(()->{
            System.out.println("CurrentThread "+Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
                System.out.println("URL->"+ ServletUtils.getRequest().getRequestURL());
                System.out.println("params->"+ ServletUtils.getParameter("age"));
            } catch (Exception e){
                e.printStackTrace();
            }
        }).start();
        return "OK";
    }
}
