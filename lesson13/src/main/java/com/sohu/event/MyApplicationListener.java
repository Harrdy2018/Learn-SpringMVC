package com.sohu.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 通过注册Spring Bean的方式注册监听器
 * 首先定义一个监听器实现类MyApplicationListener，并监听org.springframework.context.event.ContextRefreshedEvent事件
 */
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println ("MyApplicationListener event:" + event.getSource());
    }
}
