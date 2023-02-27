package com.sohu.handler;

import com.oppo.annotation.InterfaceLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Aspect
@Order(0)
public class AspectTestB {
    @Pointcut("@annotation(com.oppo.annotation.InterfaceLog)")
    public void testAnnotationPointCut() {
    }

    @Before("testAnnotationPointCut()")
    public void before000(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        InterfaceLog annotation = method.getAnnotation(InterfaceLog.class);   // 获取指定注解实例
        System.out.println("打印：" + annotation.type() + " 前置日志1");   // 获取注解实例的参数
    }
    @After("testAnnotationPointCut()")
    public void after000(JoinPoint point) {
        MethodSignature sign = (MethodSignature) point.getSignature();
        Method method = sign.getMethod();
        InterfaceLog annotation = method.getAnnotation(InterfaceLog.class);  // 获取指定注解实例
        System.out.println("打印自带参数：" + annotation.version() + " 后置日志1");  // 获取注解实例的参数
    }
}
