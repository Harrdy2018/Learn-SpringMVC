## lesson10
* 拦截器的使用
```xml
    <!-- 配置一个全局拦截器，拦截所有请求 -->
    <mvc:interceptors>
        <!-- 公共拦截器可以拦截所有请求，而且可以有多个 -->
        <bean class="com.sohu.handler.LoginHandlerInterceptor"/>
        <!-- 如果有多个拦截器，则按照顺序进行配置 -->
        <mvc:interceptor>
            <!-- /**表示所有URL和子URL路径 -->
            <mvc:mapping path="/v10/query/**" />
            <!-- 特定请求的拦截器只能有一个 -->
            <bean class="com.sohu.handler.MyHandlerInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
```
```txt
# 先走登录 回话属性赋值
http://localhost:8080/lesson10/v10/login
LoginHandlerInterceptor preHandle
/lesson10/v10/login
LoginHandlerInterceptor postHandle
LoginHandlerInterceptor afterCompletion

# 再走业务 检查登录状态+业务
http://localhost:8080/lesson10/v10/query/getAllEmployees
LoginHandlerInterceptor preHandle
/lesson10/v10/query/getAllEmployees
MyHandlerInterceptor preHandle
MyHandlerInterceptor postHandle
LoginHandlerInterceptor postHandle
MyHandlerInterceptor afterCompletion
LoginHandlerInterceptor afterCompletion
```
```txt
这里需要注意的是：如果有多个拦截器，那么配置在 springmvc.xml 中最上面的拦截器，拦截器优先级最高。
```
* 参考文章```https://www.cnblogs.com/likeguang/p/16725652.html```
