package com.sohu.listener;

import com.sohu.task.ScanSessionTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

/**
 自定义Session扫描器
 Session是保存在内存中的，如果Session过多，服务器的压力就会非常大。但是呢，Session的默认失效时间是30分钟(30分钟没人用才会失效)，
 这造成Seesion可能会过多（没人用也存在内存中，这不是明显浪费吗？）当然啦，我们可以在web.xml文件中配置Session的生命周期。但是呢，
 这是由服务器来做的，我嫌它的时间不够准确。（有时候我配置了3分钟，它用4分钟才帮我移除掉Session）
 所以，我决定自己用程序手工移除那些长时间没人用的Session。分析要想移除长时间没人用的Session，
 肯定要先拿到全部的Session啦。所以我们使用一个容器来装载站点所有的Session。。
 只要Sesssion一创建了，就把Session添加到容器里边。毫无疑问的，我们需要监听Session了。接着，
 我们要做的就是隔一段时间就去扫描一下全部Session，如果有Session长时间没使用了，我们就把它从内存中移除。隔一段时间去做某事，
 这肯定是定时器的任务呀。定时器应该在服务器一启动的时候，就应该被创建了。因此还需要监听Context最后，
 我们还要考虑到并发的问题，如果有人同时访问站点，那么监听Session创建的方法就会被并发访问了。
 定时器扫描容器的时候，可能是获取不到所有的Session的。这需要我们做同步
 */
public class MyScanSessionListener implements ServletContextListener, HttpSessionListener {
    // 服务器一启动，就应该创建容器。我们使用的是LinkList(涉及到增删)。容器也应该是线程安全的。
    List<HttpSession> httpSessionList = Collections.synchronizedList(new LinkedList<HttpSession>());

    // 定义一把锁（Session添加到容器和扫描容器这两个操作应该同步起来）
    private Object lock = new Object();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Timer timer = new Timer();
        //执行我想要的任务，0秒延时，每10秒执行一次
        timer.schedule(new ScanSessionTask(httpSessionList, lock), 0, 10 * 1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed被销毁啦。");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // 只要Session一创建了，就应该添加到容器中
        synchronized (lock) {
            System.out.println("Session被创建啦");
            HttpSession httpSession = httpSessionEvent.getSession();
            httpSessionList.add(httpSession);
            System.out.println(httpSession.getClass().getName()+"--crate->"+httpSession);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session被销毁啦。");
    }
}
