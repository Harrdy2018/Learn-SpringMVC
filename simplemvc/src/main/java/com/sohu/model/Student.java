package com.sohu.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String name;

    private String age;

    public Student() {
        System.out.println("no param constructor");
    }

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
        System.out.println("have param constructor");
    }
}
