package com.sohu.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRespBean<T> {
    private Integer retCode;

    private T data;

    public BaseRespBean(Integer retCode, T data) {
        this.retCode = retCode;
        this.data = data;
    }

    public BaseRespBean() {
    }
}
