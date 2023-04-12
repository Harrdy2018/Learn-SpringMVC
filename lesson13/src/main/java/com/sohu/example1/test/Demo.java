package com.sohu.example1.test;

import com.sohu.example1.EventMulticaster;
import com.sohu.example1.SimpleEventMulticaster;

/**
 * spring里面的事件类 spring-context
 *
 * org.springframework.context.ApplicationEvent
 * org.springframework.context.ApplicationListener 感兴趣的事件
 * org.springframework.context.event.ApplicationEventMulticaster 事件广播器
 * org.springframework.context.event.SimpleApplicationEventMulticaster 事件广播器的简单实现
 */
public class Demo {
    public static void main(String[] args) {
        EventMulticaster eventPublish = new SimpleEventMulticaster();
        eventPublish.addEventListener(new SendEmailEventListener());

        // 事件
        UserRegisterEvent userRegisterEvent = new UserRegisterEvent("注册", "小明");
        eventPublish.multicastEvent(userRegisterEvent);
    }
}
