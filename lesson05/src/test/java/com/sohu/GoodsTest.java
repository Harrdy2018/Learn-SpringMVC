package com.sohu;

import com.sohu.dao.GoodsMapper;
import com.sohu.service.BuyGoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GoodsTest {
    /**
     * 查询商品表全部
     */
    @Test
    public void display1(){
        String config = "springmvc.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        GoodsMapper goodsMapper =(GoodsMapper)ctx.getBean("goodsMapper");
        System.out.println(goodsMapper.queryAllGoods());

    }

    @Test
    public void display2(){
        String config = "springmvc.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        BuyGoodsService buyService =(BuyGoodsService) ctx.getBean("buyService");
        buyService.buy(1001, 100);
    }
}
