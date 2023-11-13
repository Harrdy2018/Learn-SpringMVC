package com.sohu.modelback02;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class People {
    private String name;

    private Integer age;

    public People() {
        System.out.println("modelback02 People constructor no params");
    }
}
