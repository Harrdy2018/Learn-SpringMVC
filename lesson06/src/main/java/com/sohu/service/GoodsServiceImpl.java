package com.sohu.service;

import com.oppo.tool.BeanConvertUtils;
import com.sohu.bo.GoodsBO;
import com.sohu.dao.GoodsMapper;
import com.sohu.po.GoodsPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    /**
     * @Resource (基于类的名称)注解
     */
    @Resource(name = "goodsMapper")
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsBO> queryAllGoods() {
        List<GoodsPO> goodsPOS = goodsMapper.queryAllGoods();
        // PO->BO
        List<GoodsBO> goodsBOS = BeanConvertUtils.convertListTo(goodsPOS, GoodsBO::new);
        return goodsBOS;
    }
}
