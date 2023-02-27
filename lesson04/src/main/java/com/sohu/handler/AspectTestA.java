package com.sohu.handler;

import com.oppo.annotation.InterfaceLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

/**
 * @Order(-99)注解作用
 * 控制多个Aspect的执行顺序，越小越先执行, 当然也可以不写这注解,
 * 对于写和不写@order的两个切面, 有@order的优先于无@order的执行; 都有@order时, 越小越执先执行
 */
@Aspect
@Order(-99)
public class AspectTestA {
    /**
     * 可以参考若依的自定义注解。自定义注解一般使用@annotation
     * @Before可以有两种写法, @annotation(形参test)，
     *
     * @Before("@annotation(log)")作用
     * 拦截被InterfaceLog注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)
     *
     * @param point
     * @param log
     * @throws Throwable
     */
    @Before("@annotation(log)")
    public void beforeTest(JoinPoint point, InterfaceLog log) throws Throwable {
        System.out.println("beforeTest:" + log.interfaceName());   // 直接获取注解参数
    }

    @After("@annotation(log)")
    public void afterTest(JoinPoint point, InterfaceLog log) {
        System.out.println("afterTest:" + log.interfaceName());  // 直接获取注解参数
    }

    /**
     * 可以控制方法运行, 同时修改入参和返回值
     *
     * @param pjp
     * @param log 注解里面的log表示aroundTest方法中的log入参
     * @return
     * @throws Throwable
     */
    @Around("@annotation(log)")
    public Object aroundTest(ProceedingJoinPoint pjp, InterfaceLog log) throws Throwable {
        System.out.println("aroundTest:" + log.interfaceName());
        // 获取入参并修改
        Object[] args = pjp.getArgs();
        args[0] = "sohu";
        // 传入修改后的参数, 并继续执行
        Object res = pjp.proceed(args);
        // 修改返回值
        return res.toString();
    }
}
