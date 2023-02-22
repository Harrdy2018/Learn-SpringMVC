package com.sohu.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        // 获得添加到Context域中的name
        String name = servletContextAttributeEvent.getName();
        // 获得添加到Context域中的value
        Object value = servletContextAttributeEvent.getValue();
        System.out.println("["+name+"-->"+value+"]");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        // 获得删除Context域中的name
        String name = servletContextAttributeEvent.getName();
        // 获得删除Context域中的value
        Object value = servletContextAttributeEvent.getValue();
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        // 获得修改前Context域中的name
        String name = servletContextAttributeEvent.getName();
        // 获得修改前Context域中的value
        Object value = servletContextAttributeEvent.getValue();
    }
}
