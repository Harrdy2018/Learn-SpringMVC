package com.sohu.event;

import org.springframework.context.ApplicationEvent;

public class MyApplicationEvent extends ApplicationEvent {
    public MyApplicationEvent(Object source) {
        super(source);
        System.out.println("MyApplicationEvent created");
    }
}
