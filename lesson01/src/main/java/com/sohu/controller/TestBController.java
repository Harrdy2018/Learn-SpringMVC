package com.sohu.controller;

import com.oppo.annotation.CustomizedHandleException;
import com.oppo.bean.BaseRespBean;
import com.oppo.bean.RetCode;
import com.oppo.exception.BusinessException;
import com.sohu.vo.StudentVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * java异常统一管理实现例子:利用ControllerAdvice实现带注解的异常通知
 */
@Controller
@RequestMapping(value = "/vb")
@CustomizedHandleException
public class TestBController {
    /**
     * http://localhost:8080/lesson01/vb/display1.do?id=12
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value="/display1.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<StudentVO> display1(Integer id, HttpSession session){
        StudentVO studentVO = new StudentVO();
        studentVO.setId(12);
        studentVO.setName("ddsds");
        studentVO.setAge(18);

        // 模拟业务异常
        if(id == null || id != 12) {
            throw new BusinessException(RetCode.SYSTEM_ERROR.getCode(), "id is error");
        }

        return BaseRespBean.success(studentVO);
    }

    /**
     * http://localhost:8080/lesson01/vb/display2.do?id=13
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value="/display2.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<StudentVO> display2(Integer id, HttpSession session){
        StudentVO studentVO = new StudentVO();
        studentVO.setId(13);
        studentVO.setName("ddsds");
        studentVO.setAge(18);

        // 模拟业务异常
        if(id == null || id != 13) {
            throw new BusinessException(RetCode.PARAM_IS_INVALID.getCode(), "id is error");
        }

        return BaseRespBean.success(studentVO);
    }
}
