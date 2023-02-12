### lesson04
#### git配置
* git config --global user.name "lukang-local"
* git config --global user.email "1035525823@email.com"
* git config --global --list
* ssh-keygen -t rsa
#### ```AOP```技术思想的实现
```txt
使用框架实现AOP.实现AOP的框架有很多。有名的有两个
1)Spring Spring框架实现AOP思想中的部分功能。Spring框架实现AOP的操作比较繁琐，比重
2)AspectJ 独立的框架，专门是AOP.属于Eclipse
```
#### 通知注解(Advice)切面执行时间
* @Before 前置通知
* @AfterRetunring 后置通知
* @Around 环绕通知
* @AfterThrowing 异常通知
* @After 最终通知 
#### ```AspectJ```对```AOP```的实现
* 切入点表达式
```txt
execution(访问权限 方法返回值 方法申明(参数) 异常类型)

目标方法的方法名(方法签名)
访问权限和异常类型可以省略，各部分之间用空格分开

符号
*   0至多个任意字符
..  用在方法参数中，表示任意多个参数；用在包名后，表示前包及其子包路径
+   用在类名后，表示当前类及其子类；用在接口后，表示当前接口及其实现类

举例
execution(public * *(..))            
指定切入点为: 任意公共方法

execution(* set*(..))                
指定切入点为: 任意一个以set开始的方法

execution(* com.xyz.service.*.*(..))
指定切入点为: 定义在service包里面的任意类的任意方法

execution(* com.xyz.service..*.*(..))
指定切入点为: 定义在service包或者子包里面的任意类的任意方法。
".."出现在类名中时，后面必须跟"*",表示包、子包下的所有类

execution(* *..service.*.*(..))
指定切入点为: 指定所有包下的service子包下所有类(接口)中所有方法为切入点
```

