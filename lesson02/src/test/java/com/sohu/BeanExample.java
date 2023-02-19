package com.sohu;

import com.sohu.bo.ProductBO;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanExample {
    @Test
    public void display1(){
        String config = "springmvc.xml";
        // 下面这条语句执行之后，就会执行bean的构造函数和类里面的init函数
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        ProductBO productBO = (ProductBO)context.getBean("productBO");
        productBO.ppPrint();
        //摧毁productBO实例对象
        ((ClassPathXmlApplicationContext)context).close();
    }

    @Test
    public void display2(){
        String config = "springmvc.xml";
        // 下面这条语句执行之后，就会执行bean的构造函数和类里面的init函数
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        ProductBO productBO = context.getBean("productBO", ProductBO.class);
        productBO.ppPrint();
        //摧毁productBO实例对象
        ((ClassPathXmlApplicationContext)context).close();
    }
}
