package com.sohu.listener;

import com.sohu.bo.UserBO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * session属性监听器
 * 比如添加属性，每执行一次request.getSession().setAttribute("user", userBO);进来一次
 *
 */
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    private static final String USER_MAP = "user_map";

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        //得到context对象，看看context对象是否有容器装载Session
        ServletContext context = httpSessionBindingEvent.getSession().getServletContext();

        //如果没有，就创建一个呗
        Map<UserBO, HttpSession> userMap = (Map<UserBO, HttpSession>) context.getAttribute(USER_MAP);
        if (userMap == null) {
            userMap = new HashMap<UserBO, HttpSession>();
            context.setAttribute(USER_MAP, userMap);
        }

        // 得到Session属性的值  UserBO(userName=oppo, password=root)
        Object o = httpSessionBindingEvent.getValue();

        //判断属性的内容是否是User对象
        if (o instanceof UserBO) {
            UserBO userBO = (UserBO) o;
            userMap.put(userBO, httpSessionBindingEvent.getSession());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
