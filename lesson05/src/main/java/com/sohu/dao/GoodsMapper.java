package com.sohu.dao;

import com.sohu.po.GoodsPO;

import java.util.List;

public interface GoodsMapper {
    GoodsPO selectById(Integer id);

    int updateGoods(GoodsPO goodsPO);

    List<GoodsPO> queryAllGoods();
}
