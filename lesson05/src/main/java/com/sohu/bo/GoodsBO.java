package com.sohu.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsBO {
    private Integer id;

    private String name;

    private Integer amount;

    private Float price;
}
