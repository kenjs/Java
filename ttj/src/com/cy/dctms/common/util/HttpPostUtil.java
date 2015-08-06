package com.cy.dctms.common.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpPostUtil {

    private static Logger log = LoggerFactory.getLogger(HttpPostUtil.class);
    
    
    
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
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str= "";
            while((str = br.readLine()) != null){
                stringBuffer .append(str );
            }
            msg = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return msg;
    }

    public static String postXml(String urlStr, Map<String, String> params) {
        URL url = null;
        HttpURLConnection conn = null;
        //构建请求参数
        StringBuffer sb = new StringBuffer();
        String str = "";
        if (params != null) {
            for (String key : params.keySet()) {
                sb.append(key);
                sb.append("=");
                sb.append(params.get(key));
                sb.append("&");
            }
            str = sb.substring(0, sb.length() - 1);
        }

        log.info("send_url:" + urlStr);
        log.info("send_data:" + str);
        //尝试发�?请求
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            osw.write(str);
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        //读取返回内容
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
