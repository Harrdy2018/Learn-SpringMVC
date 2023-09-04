package com.sohu;

import com.sohu.service.HelloService;
import org.junit.Test;
import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Example {
    @Test
    public void display1(){
        System.out.println("spi == Service Provider Interface");
    }

    @Test
    public void test1(){
        Iterator<HelloService> providers = Service.providers(HelloService.class);
        while (providers.hasNext()){
            HelloService ser = providers.next();
            ser.sayHello();
        }
    }

    @Test
    public void test2(){
        ServiceLoader<HelloService> load = ServiceLoader.load(HelloService.class);
        Iterator<HelloService> iterator = load.iterator();
        while(iterator.hasNext()) {
            HelloService ser = iterator.next();
            ser.sayHello();
        }
    }
}
