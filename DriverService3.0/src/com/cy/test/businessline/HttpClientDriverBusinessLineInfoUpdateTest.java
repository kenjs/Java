package com.cy.test.businessline;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.test.user.HttpClientLoginTest;

public class HttpClientDriverBusinessLineInfoUpdateTest {
	private static Logger log = LoggerFactory.getLogger(HttpClientLoginTest.class);

	/**
	 * get方式
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String urlClient() throws Exception {
		int id = 47;
		long driverId = 1l;
		String startTime = "2014-7-28";
		String endTime = "2014-8-20";
		String startProvince  = "四川省";
		String startCity = "成都市";
		
		String endProvince = "浙江省";
		String endCity = "杭州市";
		
		String quoteFair = "5.2";
		String quoteType = "1";
		
		String data = "&id="+id+"&driverId=" + driverId + "&startTime=" + startTime + "&endTime="+endTime
							+ "&startProvince="+startProvince + "&startCity="+startCity
							+ "&endProvince="+endProvince + "&endCity="+endCity 
							+ "&quoteFair="+quoteFair + "&quoteType="+quoteType;
		String urlStr = "http://localhost:8080/DriverService/driverBusinessLineInfoUpdateAction";
		URL url = new URL(urlStr);
		HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
		BufferedReader reader = null;
		urlConnect.setRequestMethod("GET");
		urlConnect.setAllowUserInteraction(true);
		urlConnect.setDoInput(true);
		urlConnect.setDoOutput(true);
		
		PrintWriter out = new PrintWriter(new OutputStreamWriter(urlConnect.getOutputStream(),"utf-8"));
		out.println(data);
		out.close();
		
		String strMessage = "";
        StringBuffer buffer = new StringBuffer();

		// 获取返回的数据
        InputStream inputStream = urlConnect.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        while ((strMessage = reader.readLine()) != null) {
            buffer.append(strMessage);
        }
        
        log.info(buffer.toString());
        return buffer.toString();
	}

	/**
	 * 测试的main方法
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("get方式调用http接口\n" + urlClient());

		// System.out.println("post方式调用http接口\n"+postHttp(param1,param2));
	}
}
