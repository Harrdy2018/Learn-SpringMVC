package com.sohu.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsPO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品库存
     */
    private Integer amount;

    /**
     * 商品单价
     */
    private Float price;
}
