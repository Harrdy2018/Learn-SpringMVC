package com.sohu.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalePO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 购买商品的id
     */
    private Integer gid;

    /**
     * 购买商品的数量
     */
    private Integer num;
}
