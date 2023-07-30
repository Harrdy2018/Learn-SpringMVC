package com.sohu.modellazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy // 延迟加载，被使用的时候才加载
public class LazyClassB {
    private String name;

    public LazyClassB() {
        System.out.println("LazyClassB no param Controller");
    }
}
