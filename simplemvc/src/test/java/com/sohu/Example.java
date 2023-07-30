package com.sohu;

import com.sohu.controller.EmployeeController;
import com.sohu.controller.StudentController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Example {
    @Test
    public void display1(){
        System.out.println("simplemvc");
    }

    // 打印所有bean
    @Test
    public void display2(){
        String configLocation = "springmvc.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        for (String temp: applicationContext.getBeanDefinitionNames()) {
            System.out.println(temp);
        }
    }

    // 在spring容器内通过beanId找到bean默认是单例
    @Test
    public void display3(){
        String configLocation = "springmvc.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        System.out.println(applicationContext.getBean("student01"));
        System.out.println(applicationContext.getBean("student01"));
        System.out.println(applicationContext.getBean("student01") == applicationContext.getBean("student01"));
        Assert.assertSame(applicationContext.getBean("student01"), applicationContext.getBean("student01"));
    }

    /**
     * 通过@Resource注解引用bean
     *
     * 注意 @Resource注解是Java自身的注解,@Autowired注解是Spring的注解.
     * 注意 @Resource注解有两个重要的属性,分别是name和type,如果name属性有值,则使用byName的自动注入策略,将值作为需要注入bean的名字,如果type有值,则使用byType自动注入策略,将值作为需要注入bean的类型.
     *     如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。即@Resource注解默认按照名称进行匹配,名称可以通过name属性进行指定，
     *     如果没有指定name属性，当注解写在字段上时，默认取字段名，按照名称查找,当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
     * 注意 @Autowired注解是spring的注解,此注解只根据type进行注入,不会去匹配name.但是如果只根据type无法辨别注入对象时,就需要配合使用@Qualifier注解或者@Primary注解使用.
     */
    @Test
    public void display4(){
        String configLocation = "springmvc.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        System.out.println(applicationContext.getBean("student01"));
        System.out.println(applicationContext.getBean("student01"));
        System.out.println(applicationContext.getBean("student01") == applicationContext.getBean("student01"));
        Assert.assertSame(applicationContext.getBean("student01"), applicationContext.getBean("student01"));
        System.out.println("*************************************");
        EmployeeController employeeController =(EmployeeController)applicationContext.getBean("employeeController");
        System.out.println(employeeController.student02);
        System.out.println(employeeController.student03);
        System.out.println(employeeController.student04);
    }

    @Test
    public void display5(){
        String configLocation = "springmvc.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        EmployeeController employeeController =(EmployeeController)applicationContext.getBean("employeeController");
        System.out.println(employeeController.userServiceList);
        System.out.println(employeeController.userServiceList01);
        System.out.println(employeeController.mapServices);
    }

    @Test
    public void display6(){
        String configLocation = "springmvc.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        StudentController studentController =(StudentController)applicationContext.getBean("studentController");
        System.out.println(studentController.student);
    }

    // LazyClassB不会加载，只有在引用的时候才加载
    @Test
    public void display7(){
        String configLocation = "springmvc.xml";
        new ClassPathXmlApplicationContext(configLocation);
    }
}
