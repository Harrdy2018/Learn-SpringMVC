package com.sohu.dao;

import com.sohu.po.SalePO;

import java.util.List;

public interface SaleMapper {
    int insertSale(SalePO salePO);

    List<SalePO> queryAllSales();
}
