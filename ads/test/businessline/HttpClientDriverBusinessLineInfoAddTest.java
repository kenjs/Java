package businessline;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientDriverBusinessLineInfoAddTest {
	
	public static String urlClient() throws Exception {
		long driverId = 1l;
		String startTime = "2014-7-15";
		String endTime = "2014-8-22";
		String startProvince  = "四川省";
		String startCity = "成都市";
		String startCounty = "";
		String endProvince = "北京";
		String endCity = "北京市";
		String endCounty = "";
		String quoteFair = "5.2";
		String quoteType = "1";		
		String data = "&driverId=" + driverId + "&startTime=" + startTime + "&endTime="+endTime
							+ "&startProvince="+startProvince + "&startCity="+startCity + "&startCounty="+startCounty
							+ "&endProvince="+endProvince + "&endCity="+endCity
							+ "&endCounty="+endCounty + "&quoteFair="+quoteFair
							+ "&quoteType="+quoteType;
		String urlStr = "http://localhost:8080/DriverService/driverBusinessLineInfoAddAction";
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
        
        System.out.println(buffer.toString());
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
