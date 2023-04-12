package com.sohu.example1.test;

import com.sohu.example1.EventListener;

/**
 * 这个监听器只对用户注册事件感兴趣
 */
public class SendEmailEventListener implements EventListener<UserRegisterEvent> {
    @Override
    public void onEvent(UserRegisterEvent event) {
        System.out.println(String.format("给用户 %s 发送注册成功邮件", event.getUserName()));
    }
}
