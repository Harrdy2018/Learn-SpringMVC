package com.sohu.tool;

import com.fasterxml.jackson.core.type.TypeReference;
import com.oppo.bean.BaseRespBean;
import com.sohu.bean.req.QueryStudentReq;
import com.sohu.bean.resp.QueryStudentRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpMessageServiceUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMessageServiceUtil.class);

    public static QueryStudentRsp queryEmployee2(QueryStudentReq req) throws Exception {
        BaseRespBean<QueryStudentRsp> resp = HttpUtil.postMessage(req.getCorpId(), "/lesson04/v4/queryEmployee2", req,
                new TypeReference<BaseRespBean<QueryStudentRsp>>() {});

        if (resp != null && resp.getCode() == 200) {
            LOGGER.debug("query info success");
            return resp.getData();
        } else {
            LOGGER.error("query info failed {}", resp == null ? "" : resp.getCode());
            throw new Exception("query info failed", new Throwable("retcode = "+resp.getCode()));
        }
    }

    public static QueryStudentRsp queryEmployee1(QueryStudentReq req) throws Exception {
        BaseRespBean<QueryStudentRsp> resp = HttpUtil.postMessage(req.getCorpId(), "/lesson04/v4/queryEmployee1", req,
                new TypeReference<BaseRespBean<QueryStudentRsp>>() {});

        if (resp != null && resp.getCode() == 200) {
            LOGGER.debug("query info success");
            return resp.getData();
        } else {
            LOGGER.error("query info failed {}", resp == null ? "" : resp.getCode());
            throw new Exception("query info failed", new Throwable("retcode = "+resp.getCode()));
        }
    }
}
