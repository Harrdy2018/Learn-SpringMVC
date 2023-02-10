package com.sohu.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static <T> T post(BaseInnerRequest<T> baseInnerRequest) {
        long preTime = System.currentTimeMillis();

        ObjectMapper objectMapper = new ObjectMapper();
        String body = null;
        try {
            body = objectMapper.writeValueAsString(baseInnerRequest.getReq());
        } catch (JsonProcessingException e) {
            LOGGER.info("faild to 反序列化 e={} "+e.getMessage());
        }


        String result = null;
        T response = null;
        String returnCode = "0";
        HttpEntity httpEntity = null;
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = null;
        try {
            // 1.拿到一个httpclient的对象
            httpClient = HttpClients.createDefault();

            // 2.设置请求方式
            HttpPost httpPost = new HttpPost(baseInnerRequest.getServerUrl() + baseInnerRequest.getUri());

            // 3.header头信息
            httpPost.addHeader("user-agent", "My Own Computer");

            // 4.提交请求信息
            StringEntity stringEntity = new StringEntity(body, "utf-8");
            stringEntity.setContentEncoding("utf-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            // 5.执行请求
            httpResponse = httpClient.execute(httpPost);

            // 6.获取返回值
            httpEntity= httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, Charset.forName("utf-8"));
            if (result == null) {
                LOGGER.error("resp from {} is null, url is:{}.", baseInnerRequest.getModuleName(), baseInnerRequest.getUri());
            }

            response = objectMapper.readValue(result, baseInnerRequest.getTypeReference());
        } catch (Exception e) {
            LOGGER.error("fail to request {} service,Exception,uri is:{},e={}",baseInnerRequest.getModuleName(), baseInnerRequest.getUri(), e.getMessage());
            // 记接口日志
        }
        return response;
    }

    public static <T> T postMessage(Long corpId, String uri, Object req, TypeReference<T> typeReference) {
        BaseInnerRequest<T> baseInnerRequest = new BaseInnerRequest<>(uri, req, typeReference, "MyService2DestService",
                "http://127.0.0.1:8080", "DestinationServiceName");
        return post(baseInnerRequest);
    }
}
