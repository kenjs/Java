package com.cy.common.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpPostUtil {
	
	private static Logger log = LoggerFactory.getLogger(HttpPostUtil.class); 
	
	public static String postXml(String urlStr, Map<String, String> params) {
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
		
		log.info("send_url:"+urlStr);
		log.info("send_data:"+str);
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
