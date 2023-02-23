package com.sohu.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器可以用来检测网站的在线人数，统计网站的访问量等等！
 *
 * 我们每使用一个浏览器访问服务器，都会新创建一个Session。那么网站的在线人数就会+1。
 * 使用同一个页面刷新，还是使用的是那个Sesssion，所以网站的在线人数是不会变的。
 *
 */

public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("start sessionCreated");
        //获取得到Context对象，使用Context域对象保存用户在线的个数
        ServletContext context = httpSessionEvent.getSession().getServletContext();

        //直接判断Context对象是否存在这个域，如果存在就人数+1,如果不存在，那么就将属性设置到Context域中
        Integer num = (Integer) context.getAttribute("num");

        if (num == null) {
            context.setAttribute("num", 1);
        } else {
            num++;
            context.setAttribute("num", num);
        }
        System.out.println("end sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("start sessionDestroyed");
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        Integer num = (Integer) httpSessionEvent.getSession().getAttribute("num");
        if (num == null) {
            context.setAttribute("num", 1);
        } else {
            num--;
            context.setAttribute("num", num);
        }
        System.out.println("end sessionDestroyed");
    }
}
