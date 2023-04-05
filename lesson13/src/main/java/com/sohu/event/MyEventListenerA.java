package com.sohu.event;

import org.springframework.context.ApplicationListener;

import java.util.Arrays;

public class MyEventListenerA implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        int[] source = (int[])event.getSource();
        System.out.println("MyEventListenerA source = "+ Arrays.toString(source));
    }
}
