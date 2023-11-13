package com.sohu.controller;

import com.sohu.model.Student;
import com.sohu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/basetest")
public class EmployeeController {

    /**
     * 如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。即@Resource注解默认按照名称进行匹配,名称可以通过name属性进行指定，
     * 如果没有指定name属性，当注解写在字段上时，默认取字段名，按照名称查找,当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
     *
     * 现在取student02进行查找，发现找不到，按照class查找
     */
    @Resource
    public Student student02;

    /**
     * 现在按照student01作为ID查找，也就是按照名称查找
     */
    @Resource(name = "student01")
    public Student student03;

    /**
     * 现在按照类型查找
     */
    @Resource(type = Student.class)
    public Student student04;

    /**
     * UserService有两个实现类 UserServiceImpl01.java UserServiceImpl02.java
     * 首先根据名称匹配userService找不到
     * 再根据类型匹配发现有两个类，启动报错
     * 如果使用@Resource注解只能加name属性
     */
//    @Resource
//    public UserService userService;

    @Resource(name = "userServiceImpl01")
    public UserService userService;

    /**
     * 注解@Autowired只根据类型，编译直接报错
     * 需要搭配@Qualifier使用
     *
     * 也可以这样
     * 只使用@Autowired 另外在实现类比如UserServiceImpl01.java加上注解@Primary
     *
     * == @Primary注解是Spring框架中用于解决自动装配冲突的注解。当存在多个类型相同的Bean时，
     *    如果没有使用@Qualifier注解或者@Qualifier注解没有指定Bean的名称，Spring框架将无法判断应该使用哪个Bean进行注入。此时，可以使用@Primary注解来指定默认的Bean。
     * == @Primary注解可以用于在多个实现类中指定默认的实现类。当存在多个实现类时，Spring框架将默认选择带有@Primary注解的实现类进行自动装配。
     */
//    @Autowired
//    public UserService userService01;

    @Autowired
    @Qualifier(value = "userServiceImpl01")
    public UserService userService01;

    public EmployeeController() {
        System.out.println("EmployeeController "+this.student02);
    }

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    @ResponseBody
    public String getAllEmployees(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println("end");
        return "Hello World!";
    }

    /**
     * 复杂类型注入 list 通过类型找所有的实现类
     *
     * userServiceList=[com.sohu.service.UserServcieImpl02@7bedc48a, com.sohu.service.UserServiceImpl01@131ef10]
     */
    @Autowired
    public List<UserService> userServiceList;

    @Resource
    public List<UserService> userServiceList01;

    /**
     * 复杂类型注入 map 通过类型找所有的实现类,key是beanId
     *
     * mapServices={userServcieImpl02=com.sohu.service.UserServcieImpl02@d35dea7, userServiceImpl01=com.sohu.service.UserServiceImpl01@7770f470}
     */
    @Autowired
    public Map<String, UserService> mapServices;
}
