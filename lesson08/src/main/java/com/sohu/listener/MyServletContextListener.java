package com.sohu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //被监听的对象 -- ServletContext
        ServletContext servletContext = servletContextEvent.getServletContext();
        //被监听的对象 通用方法
        Object source = servletContextEvent.getSource();
        System.out.println("context 创建了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("context 销毁了");
    }
}
