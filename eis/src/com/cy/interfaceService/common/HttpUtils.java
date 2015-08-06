package com.cy.interfaceService.common;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by haoy on 2014/10/18.
 */
public class HttpUtils {

    /**
     * URL get 请求
     * @param url
     * @return    请求结果
     * @throws java.io.IOException
     */
    public static String doGetRequest(String url) throws IOException {
        if (StringUtils.isBlank(url))
            throw new IllegalArgumentException("url cannot be null! ");

        StringBuilder stringBuilder = new StringBuilder();
        String msg;
        BufferedReader bufferedReader = null;
        try {
            URL httUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) httUrl.openConnection();

            //设置连接参数
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type","text/html; charset=UTF-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);

            int resCode = httpURLConnection.getResponseCode();
            if (resCode == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                while ((msg = bufferedReader.readLine()) != null) {
                    stringBuilder.append(msg);
                }
            } else {
                msg = "Http request error code :" + resCode;
                stringBuilder.append(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * POST 请求
     * @param urlStr
     * @param params
     * @return
     */
    public static String doPostRequest(String urlStr, Map<String, String> params) {
        if (StringUtils.isBlank(urlStr))
            throw new IllegalArgumentException("url cannot be null! ");

        if (params == null)
            throw new IllegalArgumentException("params cannot be null! ");

        StringBuffer buffer = new StringBuffer();

        URL url = null;
        HttpURLConnection conn = null;
        //构建请求参数
        StringBuffer sb = new StringBuffer();
        String str = "";
        if(params!=null){
            for (String key : params.keySet()) {
                sb.append(key);
                sb.append("=");
                sb.append(params.get(key));
                sb.append("&");
            }
            str = sb.substring(0,sb.length() - 1);
        }

        //尝试发送请求
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

            if (200 == conn.getResponseCode()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                    buffer.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return buffer.toString().trim();
    }

    /**
     * 沃动
     * @param requestUrl
     * @return
     */
    public static String noteSendTow(String requestUrl) throws Exception{
        if (StringUtils.isBlank(requestUrl))
            throw new IllegalArgumentException("url cannot be null! ");

        URL url;
        BufferedReader reader = null;
        String msg;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            url = new URL(requestUrl);
            reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            while ((msg = reader.readLine()) != null) {
                stringBuilder.append(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * apache http get
     * @param url
     * @return
     * @throws Exception
     */
    public static String getHttp(String url) throws Exception {
        if (StringUtils.isBlank(url))
            throw new IllegalArgumentException("url cannot be null! ");

        String str = "";

        HttpClient client = new HttpClient();

        GetMethod getMethod = new GetMethod(url);

        // 使用系统系统的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());

        client.executeMethod(getMethod);

        try {
            byte[] bytes = getMethod.getResponseBody();

            str = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
        return str;
    }

    /**
     * apache post
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static String postHttp(String url, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(url))
            throw new IllegalArgumentException("url cannot be null! ");

        if (map == null)
            throw new IllegalArgumentException("map cannot be null! ");

        String str = "";

        HttpClient client = new HttpClient();

        PostMethod postMethod = new PostMethod(url);

        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            postMethod.addParameter(entry.getKey(), entry.getValue());
        }

        try {
            client.executeMethod(postMethod);
            byte[] bytes = postMethod.getResponseBody();
            str = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return str;
    }
 }
