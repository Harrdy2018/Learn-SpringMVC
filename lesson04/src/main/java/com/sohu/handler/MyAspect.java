package com.sohu.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;


/**
 * @Aspect 当前类是一个切面类
 */
@Aspect
public class MyAspect {
    /**
     * 定义方法 表示切面的具体功能
     * @Before 前置通知
     * public void 自定义(可以有参数，如果有是JointPoint 也可以没有)
     */
    @Before(value = "execution(public String com.sohu.controller.LearnAOPController.display2(String, Integer))")
    public void myBefore1(){
        System.out.println("@Before 前置通知");
    }

    /**
     * http://localhost:8080/lesson04/v4/say2?name=oppo&age=18
     *
     * @param joinPoint 方法的基本信息
     */
    @Before(value = "execution(* com.sohu.controller.LearnAOPController.display2(..))")
    public void myBefore2(JoinPoint joinPoint){
        System.out.println("@Before 前置通知");
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getSignature().getName()); // 方法名字 display2
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * @AfterReturning 后置通知
     * public void 自定义(方法有参数推荐使用Object字段)
     */
    @AfterReturning(value = "execution(* com.sohu.controller.LearnAOPController.display2(..))", returning="res")
    public void myAfterReturning1(Object res){
        System.out.println(res);
    }


    /**
     * 环绕通知 http://localhost:8080/lesson04/v4/say1?name=oppo&age=18
     * 先执行@Around再执行里面方法，@Before，执行目标方法返回，执行@Around返回结果，可以修改目标返回的结果
     *
     * 方法是public
     * @param proceedingJoinPoint 必须 相当于反射中的Method
     * @return 必须要有 推荐Object
     */
    @Around(value = "execution(* com.sohu.controller.LearnAOPController.display1(..))")
    public Object myAround1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("start execute myAround1");
        // org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint
        System.out.println(proceedingJoinPoint.getClass().getName());
        System.out.println(Arrays.toString(proceedingJoinPoint.getArgs())); // 打印参数
        System.out.println(proceedingJoinPoint.getSignature().getName()); // 打印目标方法名
        Object methodReturn = proceedingJoinPoint.proceed(); // 相当于method.invove()
        System.out.println("methodReturn: "+methodReturn);
        return "end myAround1";
    }
    @Before(value = "execution(* com.sohu.controller.LearnAOPController.display1(..))")
    public void myBefore1(JoinPoint joinPoint) {
        System.out.println("start execute myBefore1.");
    }



    /**
     * 可以是目标方法的监控
     * http://localhost:8080/lesson04/v4/say3?name=oppo&age=18
     *
     * @param ex 从执行目标方法拿到的异常
     */
    @AfterThrowing(value = "execution(* com.sohu.controller.LearnAOPController.display3(..))", throwing = "ex")
    public void myAfterThrowing(Exception ex){
        System.out.println(ex);
    }

    /**
     * 最终通知 总是会被执行 相当于try catch finally里面的finally
     * http://localhost:8080/lesson04/v4/say3?name=oppo&age=18
     *
     */
    @After(value = "execution(* com.sohu.controller.LearnAOPController.display3(..))")
    public void myAfter(){
        System.out.println("@After 最终通知 总是会被执行");
    }


    /**
     * 不是通知注解，定义和管理切入点
     * http://localhost:8080/lesson04/v4/say4?name=oppo&age=18
     */
    @Pointcut(value = "execution(* com.sohu.controller.LearnAOPController.display4(..))")
    public void simple(){
        // 即使有代码都不会被执行
        System.out.println("@Pointcut 不是通知注解，定义和管理切入点");
    }
    @After(value = "simple()")
    public void mySimpleAfter(){
        System.out.println("@After 最终通知 总是会被执行");
    }
    @Before(value = "simple()")
    public void mySimpleBefore(){
        System.out.println("@Before 前置通知");
    }
}
