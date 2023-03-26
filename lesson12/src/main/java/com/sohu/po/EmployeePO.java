package com.sohu.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmployeePO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String firstName;

    private String lastName;
}
