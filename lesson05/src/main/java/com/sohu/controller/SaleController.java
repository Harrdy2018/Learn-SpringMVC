package com.sohu.controller;

import com.oppo.bean.BaseRespBean;
import com.oppo.tool.BeanConvertUtils;
import com.sohu.bo.GoodsBO;
import com.sohu.bo.SaleBO;
import com.sohu.po.SalePO;
import com.sohu.service.GoodsService;
import com.sohu.service.SaleService;
import com.sohu.vo.GoodsVO;
import com.sohu.vo.SaleVO;
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
public class SaleController {
    @Resource(name = "saleServiceImpl")
    private SaleService saleService;

    /**
     * http://localhost:8080/lesson05/v5/queryAllSales
     * @param request request
     * @param response response
     * @param session session
     * @return
     */
    @RequestMapping(value = "/queryAllSales", method = RequestMethod.GET)
    @ResponseBody
    public BaseRespBean<List<GoodsVO>> queryAllSales(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        List<SaleBO> saleBOS = saleService.queryAllSales();
        // BO->VO
        List<SaleVO> saleVOS = BeanConvertUtils.convertListTo(saleBOS, SaleVO::new);
        return BaseRespBean.success(saleVOS);
    }
}
