package com.sohu;

import com.sohu.bean.req.QueryStudentReq;
import com.sohu.bean.resp.QueryStudentRsp;
import com.sohu.tool.HttpMessageServiceUtil;
import org.junit.Test;

public class TestHttp {
    @Test
    public void display1() {
        QueryStudentReq req = new QueryStudentReq();
        req.setId(123);
        req.setCorpId(121344L);

        try {
            QueryStudentRsp resp = HttpMessageServiceUtil.queryEmployee2(req);
            System.out.println(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    @Test
    public void display2() {
        QueryStudentReq req = new QueryStudentReq();
        req.setId(123);
        req.setCorpId(121344L);

        try {
            QueryStudentRsp resp = HttpMessageServiceUtil.queryEmployee1(req);
            System.out.println(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }
}
