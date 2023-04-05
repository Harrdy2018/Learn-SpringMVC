package com.sohu.event;

import org.springframework.context.ApplicationListener;

import java.util.Arrays;

public class MyEventListenerB implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        int[] source = (int[])event.getSource();
        System.out.println("MyEventListenerB source = "+ Arrays.toString(source));
    }
}
