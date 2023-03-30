### 处理器映射器```HandlerMapping``实现类
```txt
处理器映射作用
通过处理器映射，你可以将Web 请求映射到正确的处理器 Controller 上。当接收到请求时，DispactherServlet 将请求交给 HandlerMapping 处理器映射，
让他检查请求并找到一个合适的HandlerExecutionChain，这个HandlerExecutionChain 包含一个能处理该请求的处理器 Controller。
然后，DispactherServlet 执行在HandlerExecutionChain 中的处理器 Controller。
Spring内置了许多处理器映射策略，目前主要由三个实现。SimpleUrlHandlerMapping、``BeanNameUrlHandlerMapping和RequestMappingHandlerMapping。
注意：Spring MVC3.1之前使用DefaultAnnotationHandlerMapping，Spring MVC3.1之后改为RequestMappingHandlerMapping。
```
#### ```SimpleUrlHandlerMapping```
```xml
  <!--
    <entry key="/test/v12" value-ref="simpleController" />
    请求 http://localhost:8080/lesson12/test/v12 对应 容器里面 SimpleController.java的BeanName
    -->
    <bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
        <property name="urlMap">
            <map>
                <entry key="/test/v12" value-ref="simpleController" />
            </map>
        </property>
    </bean>
```
* 对应的controller
```java
package com.sohu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SimpleController extends AbstractController {

    /**
     * http://localhost:8080/lesson12/test/v12?username=oppo
     *
     * @param request r
     * @param response r
     * @return M
     * @throws Exception e
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("SimpleController...");
        System.out.println(request.getParameter("username"));
        ModelAndView mv = new ModelAndView("simpleController");
        mv.addObject("msg", "hello,SimpleController");
        return mv;
    }
}
```
#### ```BeanNameUrlHandlerMapping```
```xml
<!-- BeanNameUrlHandlerMapping -->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
    <!-- http://localhost:8080/lesson12/hello.do对应处理器com.sohu.controller.HelloController类 -->
    <bean id="/hello.do" class="com.sohu.controller.HelloController"/>
```
```java
package com.sohu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController extends AbstractController {

    /**
     * http://localhost:8080/lesson12/hello.do
     *
     * @param request r
     * @param response r
     * @return M
     * @throws Exception e
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("helloController");
        return mv;
    }
}
```
#### ```RequestMappingHandlerMapping```
```txt
只要有 @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET) 注解即可
```