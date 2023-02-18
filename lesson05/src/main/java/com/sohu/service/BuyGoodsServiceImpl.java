package com.sohu.service;

import com.oppo.bean.RetCode;
import com.oppo.exception.BusinessException;
import com.sohu.dao.GoodsMapper;
import com.sohu.dao.SaleMapper;
import com.sohu.po.GoodsPO;
import com.sohu.po.SalePO;
import lombok.Setter;

public class BuyGoodsServiceImpl implements BuyGoodsService{

    @Setter
    private SaleMapper saleMapper;

    @Setter
    private GoodsMapper goodsMapper;

    @Override
    public void buy(Integer goodsId, Integer num) {
        System.out.println("start buy");
        // 生成销售记录
        SalePO salePO = new SalePO();
        salePO.setGid(goodsId);
        salePO.setNum(num);
        saleMapper.insertSale(salePO);

        // 查询商品
        GoodsPO goodsPO = goodsMapper.selectById(goodsId);
        if(goodsPO == null) {
            throw new BusinessException(RetCode.PARAM_IS_INVALID.getCode(), "商品不存在");
        } else if (goodsPO.getAmount() < num) {
            throw new BusinessException(RetCode.SYSTEM_ERROR.getCode(), "库存不足");
        }

        // 更新库存
        GoodsPO buyGoodsPO = new GoodsPO();
        buyGoodsPO.setId(goodsId);
        buyGoodsPO.setAmount(num);
        goodsMapper.updateGoods(buyGoodsPO);
        System.out.println("end buy");
    }
}
