package com.cy.swp.common.util;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
/**
 * 短信发送
 * @author Administrator
 *
 */
public class NoteUtil {
	private static final String httpUrl = "http://localhost:8080/CyInterfaceService/sendNote";
	private static final String userid = "1000663"; 
	private static final String password = "kdwyzm6688";	
	
	public static String sendNote(String phones,String content){
		StringBuffer posturl = new StringBuffer(httpUrl+"/sismsapi.go?method=smssend");
		String sendtermid="9999";//扩展号
		String sendtime = "1";//发送时间 1为立即发送,定时发送 格式为: yyyyMMddHHmmss
		String md5 = MD5Util.MD5(userid+"||"+phones+"||"+password);
		
		try {
			content = URLEncoder.encode(content,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		Map<String, String> params = new  HashMap<String, String>();
		params.put("userid", userid);
		params.put("smstype", "0");
		params.put("phones", phones);
		params.put("content", content);
		params.put("sendtermid", sendtermid);
		params.put("sendtime", sendtime); 
		params.put("md5", md5.toLowerCase());
		
		return HttpPostUtil.postXml(posturl.toString(), params);
	}

    public static String hzQunzNoteSend(String urlStr , Map<String, Object> map) throws IOException {
        String parameter = "";

        for (Map.Entry<String,Object> entry : map.entrySet()) {
            parameter += entry.getKey() + "=" + entry.getValue() + "&";
        }

        parameter = parameter.substring(0, parameter.length() - 1);

        URL url1 = new URL(urlStr);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(parameter.getBytes());
        outputStream.flush();
        outputStream.close();

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            stringBuilder.append(temp);
        }
        bufferedReader.close();
        httpURLConnection.disconnect();
        return stringBuilder.toString();
    }
	
}
