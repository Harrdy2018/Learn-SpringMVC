package com.sohu.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * ServletRequestAttributeListener的使用
 */
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("增加了" + servletRequestAttributeEvent.getName() + " " + servletRequestAttributeEvent.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("去除了" + servletRequestAttributeEvent.getName() + " " + servletRequestAttributeEvent.getValue() );
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("取代了" + servletRequestAttributeEvent.getName() + " " + servletRequestAttributeEvent.getValue()+"现在的新值是"+servletRequestAttributeEvent.getServletRequest().getAttribute(servletRequestAttributeEvent.getName()));
    }
}
