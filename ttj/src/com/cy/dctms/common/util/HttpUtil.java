package com.cy.dctms.common.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by haoy on 2014/10/17.
 */
public class HttpUtil {

    /**
     * post 请求
     * @param url 请求URL
     * @param map 请求参数 key参数名，value参数值
     * @return
     */
    public static String doPostRequest(String url, Map<String, String > map) {
        if (StringUtils.isEmpty(url))
            throw new IllegalArgumentException("url cannot be null! ");

        if (map == null)
            throw new IllegalArgumentException("map cannot be null! ");

        HttpClient httpClient = new HttpClient();

        PostMethod postMethod = new PostMethod(url);

        //使用系统系统的默认的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //设置参数编码
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        //请求参数
        if (map !=null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                postMethod.addParameter(entry.getKey(), entry.getValue());
            }
        }

        String msg = "";
        try {
            httpClient.executeMethod(postMethod);
            byte[] bytes = postMethod.getResponseBody();
            msg = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return msg;
    }
}
