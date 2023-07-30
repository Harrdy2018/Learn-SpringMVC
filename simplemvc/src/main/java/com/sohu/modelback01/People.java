package com.sohu.modelback01;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component(value = "modelback01_People")
public class People {
    private String name;

    private Integer age;

    public People() {
        System.out.println("modelback01 People constructor no params");
    }
}
