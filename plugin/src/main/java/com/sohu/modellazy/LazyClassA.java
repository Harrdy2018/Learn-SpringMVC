package com.sohu.modellazy;

import org.springframework.stereotype.Component;

@Component
public class LazyClassA {
    private String name;

    public LazyClassA() {
        System.out.println("LazyClassA no param Controller");
    }
}
