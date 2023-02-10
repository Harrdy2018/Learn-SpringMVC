package com.sohu.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private Long corpId;
}
