package com.cy.dcts.ipUrlStr.service.imp;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.ipUrlStr.service.IIpUrlStrService;

public class IpUrlStrServiceImp implements IIpUrlStrService {

	
	private String ipUrlStr;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public String getIpCity(HttpServletRequest request) throws UnsupportedEncodingException {
		String[] addresses = getAddresses(request).split("-");
		StringBuffer city = new StringBuffer();
		city.append(addresses[1]);
		return city.toString();
	}

	public String getIpProvince(HttpServletRequest request) throws UnsupportedEncodingException {
		if(StringUtils.isNotEmpty(getAddresses(request))) {
			String[] addresses = getAddresses(request).split("-");
			StringBuffer province = new StringBuffer();
			province.append(addresses[0]);
			return province.toString();
		}
		return null;
	}

	public String getIpProvinceCity(HttpServletRequest request) throws UnsupportedEncodingException {
		if(StringUtils.isNotEmpty(getAddresses(request))) { 
			return getAddresses(request);
		}
		return null;
	}
	
	public String getAddresses(HttpServletRequest request) throws UnsupportedEncodingException {
		String ip = getIpAddr(request);
		String addresses = getAddresses(ip,"utf-8");
		return addresses;
	}
	
	public String getIpAddr(HttpServletRequest request) {
		logger.debug("获取IP开始====================================================");
	    if(logger.isDebugEnabled()){
		    StringBuffer buf = new StringBuffer("all head info:\n");
		    Enumeration enumeration = request.getHeaderNames();
		    while(enumeration.hasMoreElements()){
		    	Object head = enumeration.nextElement();
		    	if(null != head){
		    		String value = request.getHeader(String.valueOf(head));
		    		buf.append(head + "=" + value + "\n");
		    	}
		    }
		    logger.debug(buf.toString());
	    }
	    String ip = request.getHeader("x-forwarded-for");
	    logger.debug("request.getHeader(\"x-forwarded-for\")=" + ip);

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("X-Forwarded-For");
		    logger.debug("request.getHeader(\"X-Forwarded-For\")=" + ip);
	    }

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("Proxy-Client-IP");
		    logger.debug("request.getHeader(\"Proxy-Client-IP\")=" + ip);
	    }

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("WL-Proxy-Client-IP");
		    logger.debug("request.getHeader(\"WL-Proxy-Client-IP\")=" + ip);
	    }

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("HTTP_CLIENT_IP");
		    logger.debug("request.getHeader(\"HTTP_CLIENT_IP\")=" + ip);
	    }

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		    logger.debug("request.getHeader(\"HTTP_X_FORWARDED_FOR\")=" + ip);
	    }

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    	ip = request.getRemoteAddr();
	    	logger.debug("request.getRemoteAddr()=" + ip);
	    }

	    if(null != ip && ip.indexOf(',') != -1){
		    //如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串 IP 值
		    //取X-Forwarded-For中第一个非unknown的有效IP字符串
		    //如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
		    //用户真实IP为： 192.168.1.110 
		    //注意:当访问地址为 localhost 时 地址格式为 0:0:0:0:0:0:1
		    logger.debug("ip=" + ip);
		    String[] ips = ip.split(",");
		    for (int i = 0; i < ips.length; i++) {
		    	if(null != ips[i] && !"unknown".equalsIgnoreCase(ips[i])){
		    		ip = ips[i];
		    		break;
		    	}
		    }
	    	if("0:0:0:0:0:0:1".equals(ip)){
	    		logger.warn("由于客户端访问地址使用 localhost，获取客户端真实IP地址错误，请使用IP方式访问");
	    	}
	    }

	    if("unknown".equalsIgnoreCase(ip)){
	    	logger.warn("由于客户端通过Squid反向代理软件访问，获取客户端真实IP地址错误，请更改squid.conf配置文件forwarded_for项默认是为on解决");
	    }
	    logger.debug("获取IP结束====================================================");
	    return ip;
	}
	
	
	
	/**
	  * 
	  * @param content
	  *            请求的参数 格式为：name=xxx&pwd=xxx
	  * @param encoding
	  *            服务器端请求编码。如GBK,UTF-8等
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	 public String getAddresses(String content, String encodingString)throws UnsupportedEncodingException {
		 // 这里调用淘宝的接口
		 String ipSplit = content.substring(0, 3);
		 if("192".equals(ipSplit) || "127".equals(ipSplit)) {
			 content = "202.107.197.240";
		 }
		 String returnStr = this.getResult(ipUrlStr, "ip="+content, encodingString);
		 StringBuffer loaoss = new StringBuffer();
		 if (returnStr != null) {// 处理返回的省市区信息
			 String[] temp = returnStr.split(",");
			 if(temp.length<3){
				 return "0";//无效IP，局域网测试
			 }
			 String region = (temp[5].split(":"))[1].replaceAll("\"", "");
			 region = decodeUnicode(region);// 省份
			 
			 String city = (temp[7].split(":"))[1].replaceAll("\"", "");
			 city = decodeUnicode(city);// 城市
			 
			 String county = (temp[9].split(":"))[1].replaceAll("\"", "");
			 county = decodeUnicode(county);// 区
	   
			 if(StringUtils.isNotEmpty(region)) {
				 loaoss.append(region);
			 }
			 if(StringUtils.isNotEmpty(city)) {
				 loaoss.append("-");
				 loaoss.append(city);
			 }
		 }
		   /**
		    * String country = ""; String area = ""; String region = ""; String
		    * city = ""; String county = ""; String isp = ""; for(int i=0;i<temp.length;i++){
		    * switch(i){ case 1:country =
		    * (temp[i].split(":"))[2].replaceAll("\"", ""); country =
		    * decodeUnicode(country);//国家 break; case 3:area =
		    * (temp[i].split(":"))[1].replaceAll("\"", ""); area =
		    * decodeUnicode(area);//地区 break; case 5:region =
		    * (temp[i].split(":"))[1].replaceAll("\"", ""); region =
		    * decodeUnicode(region);//省份 break; case 7:city =
		    * (temp[i].split(":"))[1].replaceAll("\"", ""); city =
		    * decodeUnicode(city);//市区 break; case 9:county =
		    * (temp[i].split(":"))[1].replaceAll("\"", ""); county =
		    * decodeUnicode(county);//地区 break; case 11:isp =
		    * (temp[i].split(":"))[1].replaceAll("\"", ""); isp =
		    * decodeUnicode(isp);//ISP公司 break; } }
		    */
		 /******************************最新******************************************/
//		 content = "125.118.53.157";
//		 //这里调用新浪的接口
//		 String returnStr = this.getResult(ipUrlStr, "ip="+content, encodingString);
//		 System.out.println(returnStr);
//		 StringBuffer loaoss = new StringBuffer();
//		 if(returnStr != null) {
//			 JSONObject jsonObject = JSONObject.fromObject(returnStr); 
//			 Map map = new HashMap(); 
//		     for(Iterator iter = jsonObject.keys(); iter.hasNext();){ 
//		          String key = (String)iter.next(); 
//		          map.put(key, jsonObject.get(key)); 
//		     } 
//		     
//		     String province = decodeUnicode(map.get("province").toString());
//		     String city = decodeUnicode(map.get("city").toString());
//		     String country = decodeUnicode(map.get("country").toString());
//		     String isp = decodeUnicode(map.get("isp").toString());
//			 
//		     System.out.println(province);
//		     System.out.println(city);
//		     System.out.println(country);
//		     System.out.println(isp);
//		     if(StringUtils.isNotEmpty(province)) {
//				 loaoss.append(province);
//			 }
//			 if(StringUtils.isNotEmpty(city)) {
//				 loaoss.append("-");
//				 loaoss.append(city);
//				 
//			 }
//	 	}
	     /******************************最新******************************************/
		 return loaoss.toString();
	 }
	 
	 
	 
	 /**
	  * @param urlStr
	  *            请求的地址
	  * @param content
	  *            请求的参数 格式为：name=xxx&pwd=xxx
	  * @param encoding
	  *            服务器端请求编码。如GBK,UTF-8等
	  * @return
	  */
	 private String getResult(String urlStr, String content, String encoding) {
		 URL url = null;
		 HttpURLConnection connection = null;
		 try {
			 url = new URL(urlStr);
			 connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			 connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			 connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			 connection.setDoOutput(true);// 是否打开输出流 true|false
			 connection.setDoInput(true);// 是否打开输入流true|false
			 connection.setRequestMethod("POST");// 提交方法POST|GET
			 connection.setUseCaches(false);// 是否缓存true|false
			 connection.connect();// 打开连接端口
			 DataOutputStream out = new DataOutputStream(connection.getOutputStream());// 打开输出流往对端服务器写数据
			 out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			 out.flush();// 刷新
			 out.close();// 关闭输出流
			 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
			 // ,以BufferedReader流来读取
			 StringBuffer buffer = new StringBuffer();
			 String line = "";
			 while ((line = reader.readLine()) != null) {
				 buffer.append(line);
			 }
			 reader.close();
			 return buffer.toString();
		 } catch (IOException e) {
			 e.printStackTrace();
		 } finally {
			 if (connection != null) {
				 connection.disconnect();// 关闭连接
			 }
		 }
		 return null;
	 }
	 
	 
	 /**
	  * unicode 转换成 中文
	  * 
	  * @author fanhui 2007-3-15
	  * @param theString
	  * @return
	  */
	 public static String decodeUnicode(String theString) {
		 char aChar;
		 int len = theString.length();
		 StringBuffer outBuffer = new StringBuffer(len);
		 for (int x = 0; x < len;) {
			 aChar = theString.charAt(x++);
			 if (aChar == '\\') {
				 aChar = theString.charAt(x++);
				 if (aChar == 'u') {
					 int value = 0;
					 for (int i = 0; i < 4; i++) {
						 aChar = theString.charAt(x++);
						 switch (aChar) {
						 case '0':
						 case '1':
						 case '2':
						 case '3':
						 case '4':
						 case '5':
						 case '6':
						 case '7':
						 case '8':
						 case '9':
							 value = (value << 4) + aChar - '0';
							 break;
						 case 'a':
						 case 'b':
						 case 'c':
						 case 'd':
						 case 'e':
						 case 'f':
							 value = (value << 4) + 10 + aChar - 'a';
							 break;
						 case 'A':
						 case 'B':
						 case 'C':
						 case 'D':
						 case 'E':
						 case 'F':
							 value = (value << 4) + 10 + aChar - 'A';
							 break;
						 default:
							 throw new IllegalArgumentException("Malformed      encoding.");
						 }
					 }
					 outBuffer.append((char) value);
				 } else {
					 if (aChar == 't') {
						 aChar = '\t';
					 } else if (aChar == 'r') {
						 aChar = '\r';
					 } else if (aChar == 'n') {
						 aChar = '\n';
					 } else if (aChar == 'f') {
						 aChar = '\f';
					 }
					 outBuffer.append(aChar);
				 }
			 } else {
				 outBuffer.append(aChar);
			 }
		 }
		 return outBuffer.toString();
	 }
	 
	 
	 
	 
	 public String getIpUrlStr() {
		return ipUrlStr;
	}

	public void setIpUrlStr(String ipUrlStr) {
		this.ipUrlStr = ipUrlStr;
	}
}
