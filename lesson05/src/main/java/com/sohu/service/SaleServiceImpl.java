package com.sohu.service;

import com.sohu.dao.SaleMapper;
import com.sohu.po.SalePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    /**
     * 当使用在属性上的时候，如下代码，
     * @Qualifier 和 @Autowired结合使用可以通过唯一Bean的id实现自动装配，
     * 因为单独的@Autowired注解实现自动装配是按照类型优先原则的，一旦IOC容器中出现了两个类型一样的Bean，@Autowired注解就会无法辨别用那个，
     * 即而报错，但是当我们加上 @Qualifier(value = "Bean的id") 的时候就可以直接通过Bean的唯一标识（id）进行装配了。
     */
    @Autowired
    @Qualifier(value = "saleMapper")
    private static SaleMapper saleMapper;

    @Override
    public List<SalePO> queryAllSales() {
        return saleMapper.queryAllSales();
    }
}
