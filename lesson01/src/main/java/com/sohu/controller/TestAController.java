package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import com.oppo.bean.RetCode;
import com.oppo.exception.BusinessException;
import com.sohu.vo.StudentVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @RestController注解有两个目的。首先他是一个类似于@controller和@Service的构造型注解，能够让类被组件扫描功能发现。
 * 但是，与REST最相关在于@RestController会告诉Spring，控制器中所有的处理器方法的返回值都要直接写入响应体中，
 * 而不是将值放到模型中并传递给一个视图以便于渲染。
 * 作为替代方案就是@Controller加上@Response。
 */
@RestController
public class TestAController {
    @RequestMapping(value="/v1/display11.do", method = RequestMethod.GET)
    public BaseRespBean<StudentVO> display11(Integer id, HttpSession session){
        StudentVO studentVO = new StudentVO();
        studentVO.setId(12);
        studentVO.setName("ddsds");
        studentVO.setAge(18);

        try {
            // 模拟业务异常
            if(id == null || id != 12) {
                throw new BusinessException(RetCode.SYSTEM_ERROR.getCode(), "id is error");
            }
        } catch (BusinessException e) {
            return new BaseRespBean<>(e.getCode(), e.getMessage());
        }

        return BaseRespBean.success(studentVO);
    }
}
