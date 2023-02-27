package com.sohu.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class EmployeeBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String firstName;

    private String lastName;
}
