package com.sohu.event;

import com.sohu.bo.AppContext;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventExample {
    @Test
    public void display1(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册监听器
        applicationContext.register(MyApplicationListener.class);

        //启动Spring应用上下文
        applicationContext.refresh();

        //关闭Spring应用上下文
        applicationContext.close();
    }

    @Test
    public void display2(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //实例化监听器对象
        MyApplicationListener myApplicationListener = new MyApplicationListener();

        //将监听器对象添加到Spring应用上下文
        applicationContext.addApplicationListener(myApplicationListener);

        //启动Spring应用上下文
        applicationContext.refresh();

        //关闭Spring应用上下文
        applicationContext.close();
    }

    @Test
    public void display3(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //实例化监听器对象
        MyEventListenerA myEventListenerA = new MyEventListenerA();
        MyEventListenerB myEventListenerB = new MyEventListenerB();

        //将监听器对象添加到Spring应用上下文
        applicationContext.addApplicationListener(myEventListenerA);
        applicationContext.addApplicationListener(myEventListenerB);

        //启动Spring应用上下文
        applicationContext.refresh();

        // 发布事件
        int[] array = {1,2,3,4};
        // 发布事件的时候，不仅会被 MyEventListenerA 监听，其他只要监听了 MyApplicationEvent 的监听器 都会收到事件
        applicationContext.publishEvent(new MyApplicationEvent(array));

        //关闭Spring应用上下文
        applicationContext.close();
    }

    @Test
    public void display4(){
        String configLocation = "springmvc.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);

        //实例化监听器对象
        MyEventListenerA myEventListenerA = new MyEventListenerA();
        MyEventListenerB myEventListenerB = new MyEventListenerB();

        //将监听器对象添加到Spring应用上下文
        applicationContext.addApplicationListener(myEventListenerA);
        applicationContext.addApplicationListener(myEventListenerB);

        // 发布事件
        int[] array = {1,2,3,4};
        // 发布事件的时候，不仅会被 MyEventListenerA 监听，其他只要监听了 MyApplicationEvent 的监听器 都会收到事件
        applicationContext.publishEvent(new MyApplicationEvent(array));
    }

    /**
     * 嵌套依赖学习
     */
    @Test
    public void display5(){
        String configLocation = "springmvc.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        for (String temp: applicationContext.getBeanDefinitionNames()) {
            System.out.println(temp);
        }

        System.out.println(applicationContext.getBean("people"));
        System.out.println(applicationContext.getBean("people"));
        System.out.println(applicationContext.getBean("people") == applicationContext.getBean("people"));
        Assert.assertSame(applicationContext.getBean("people"), applicationContext.getBean("people"));
    }

    /**
     * 嵌套依赖学习 @Configuration+@Bean注解
     */
    @Test
    public void display6(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppContext.class);
        for (String temp: applicationContext.getBeanDefinitionNames()) {
            System.out.println(temp);
        }
        System.out.println(applicationContext.getBean("createPeople"));
        Assert.assertSame(applicationContext.getBean("createPeople"), applicationContext.getBean("createPeople"));
    }

    /**
     * 嵌套依赖学习 @Configuration+@Bean注解  注册
     */
    @Test
    public void display7(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AppContext.class);
        for (String temp: applicationContext.getBeanDefinitionNames()) {
            System.out.println(temp);
        }
        applicationContext.refresh();
        System.out.println("refresh >>>>>>>>>>>>>>>>>>>>>");
        for (String temp: applicationContext.getBeanDefinitionNames()) {
            System.out.println(temp);
        }
        System.out.println(applicationContext.getBean("createPeople"));
        Assert.assertSame(applicationContext.getBean("createPeople"), applicationContext.getBean("createPeople"));
    }
}
