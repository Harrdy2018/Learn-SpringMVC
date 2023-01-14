package com.sohu.controller;

import com.sohu.bo.EmployeeBO;
import com.sohu.dto.EmployeeDTO;
import com.sohu.service.EmployeeService;
import com.sohu.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v3")
public class EmployeeController {
    @Autowired(required = true)
    EmployeeService employeeService;

    @Autowired(required = true)
    EmployeeValidator validator;

    /**
     * 请求 http://localhost:8080/lesson03/v3/validate/1
     * 先根据请求参数ID执行findEmployeeById 将数据存进model
     * 执行validateEmployee
     *
     * @param employeeDTO
     * @param result
     * @return
     */
    @RequestMapping(value = "/validate/{employeeId}", method = RequestMethod.GET)
    @ResponseBody
    public String validateEmployee(@ModelAttribute(name = "employeeDTO") EmployeeDTO employeeDTO,
                                BindingResult result){
        validator.validate(employeeDTO, result);
        if(result.hasErrors()){
            System.out.println(result.getClass().getName());
            System.out.println(result);
            return "validate failed";
        }
        return "success";
    }

    /**
     * queryEmployee
     * 请求 http://localhost:8080/lesson03/v3/queryEmployeeById/1
     * 先根据请求参数ID执行findEmployeeById 将数据存进model
     * 后执行 queryEmployee从model里面取数据
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryEmployeeById/{employeeId}", method = RequestMethod.GET)
    @ResponseBody
    public String queryEmployee(Model model){
        System.out.println(model);
        return "success";
    }

    /**
     * ModelAttribute注解用在参数前面
     * 请求 http://localhost:8080/lesson03/v3/queryEmployeeById1/1
     * 先根据请求参数ID执行findEmployeeById 将数据存进model
     * 后执行 queryEmployee1
     * @ModelAttribute("employee") EmployeeBO employeeBO 表示根据属性值employee从model里面取出数据赋值给BO对象
     *
     * @param employeeBO
     * @return
     */
    @RequestMapping(value = "/queryEmployeeById1/{employeeId}", method = RequestMethod.GET)
    @ResponseBody
    public String queryEmployee1(@ModelAttribute("employee") EmployeeBO employeeBO){
        System.out.println(employeeBO);
        return "success";
    }

    /**
     * ModelAttribute注解用在方法上面
     * 最先执行
     *
     * @param id
     * @param model
     */
    @ModelAttribute
    public void findEmployeeById(@PathVariable("employeeId") Integer id, Model model){
        EmployeeBO employeeBO = employeeService.queryEmployeeById(id);
        model.addAttribute("employee", employeeBO);

        // 为validator构造用例
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("first_name");
        employeeDTO.setEmail("     ");
        model.addAttribute("employeeDTO", employeeDTO);
    }
}
