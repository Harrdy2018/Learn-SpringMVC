package com.sohu.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * http://localhost:8080/lesson04/v4/queryEmployee3?name=oppo&age=18
 */
@Aspect
// @Component 可以加，但是要检查有没有加扫描包的配置
public class InterfaceLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceLogAspect.class);

    @Pointcut("execution(public * com.sohu.controller.*.*(..))")
    public void controllerMethod() {}

    @Before(value = "controllerMethod()")
    public void before(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();

        long tid = Thread.currentThread().getId();

        LOGGER.info("===> TID:{} => 准备调用 {} 方法", tid, method.getName());

        if (joinPoint.getArgs().length == 0) {
            return ;
        }

        LOGGER.info("===> TID:{} => 它的参数如下：", tid);
        for (int i = 0; i < joinPoint.getArgs().length; i ++) {
            Object arg = joinPoint.getArgs()[i];
            LOGGER.info("===> TID:{} => 第 {} 个参数是：{}", tid, i + 1, arg.toString());
        }
    }

    @AfterReturning(value = "controllerMethod()", returning = "methodResult")
    public void afterReturning(JoinPoint joinPoint, Object methodResult) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        long tid = Thread.currentThread().getId();
        // 可以考虑对象的toString()方法 但是jackson能自定义序列化方法，比如避免打印不需要的字段
        LOGGER.info("===> TID:{} resp={}", tid, objectMapper.writeValueAsString(methodResult));
    }
}
