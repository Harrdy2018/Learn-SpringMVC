package com.sohu.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("MyServletRequestListener requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println(servletRequest);
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        System.out.println(httpServletRequest);
        String url = ((HttpServletRequest)servletRequest).getRequestURL().toString();
        String uri = ((HttpServletRequest)servletRequest).getRequestURI();
        System.out.println("MyServletRequestListener url="+url+"----uri"+uri);
    }
}
