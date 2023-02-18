package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import com.oppo.tool.BeanConvertUtils;
import com.sohu.bo.GoodsBO;
import com.sohu.service.GoodsService;
import com.sohu.vo.GoodsVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v5")
public class GoodsController {
    @Resource(name = "goodsServiceImpl")
    private GoodsService goodsService;

    @RequestMapping(value = "/queryAllGoods", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<List<GoodsVO>> queryAllGoods(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        List<GoodsBO> goodsBOS = goodsService.queryAllGoods();
        // BO->VO
        List<GoodsVO> goodsVOS = BeanConvertUtils.convertListTo(goodsBOS, GoodsVO::new);
        return BaseRespBean.success(goodsVOS);
    }
}
