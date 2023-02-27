package com.sohu.handler;

import com.oppo.bean.BaseRespBean;
import com.oppo.bean.RetCode;
import com.oppo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    /**
     * handleException
     *
     * @param e e
     * @return BaseRespBean<Object>
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseRespBean<Object> handleException(Exception e) {
        LOGGER.error("exception : {}", e.getClass().getName());
        return new BaseRespBean<>(RetCode.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    /**
     * handleException
     * @ResponseBody注解必须存在，不然按照视图模块出现
     * 业务出现BusinessException异常会优先进入到此函数捕获，相当于catch
     *
     * @param e e
     * @return BaseRespBean<Object>
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public BaseRespBean<Object> handleException(BusinessException e) {
        LOGGER.error("exception : code={}, errorMessage={}", e.getCode(), e.getMessage());
        LOGGER.info("stack={}", e.getStackTrace());
        return new BaseRespBean<>(e.getCode(), e.getMessage());
    }
}
