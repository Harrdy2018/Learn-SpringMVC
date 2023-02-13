package com.sohu.handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oppo.bean.BaseRespBean;
import com.oppo.bean.StatusCode;
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
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody // 测试了一下，还真要加这个注解，不然返回的String总是视图的样子
    public String handleException(Exception e) {
        LOGGER.error("exception : {}", e.getClass().getName());
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try {
            str = objectMapper.writeValueAsString(BaseRespBean.failure(StatusCode.PARAM_TYPE_BIND_ERROR));
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
        return str;
    }
}
