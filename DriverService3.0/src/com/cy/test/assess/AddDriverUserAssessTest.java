package com.cy.test.assess;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.test.user.HttpClientLoginTest;

public class AddDriverUserAssessTest {

	private static Logger log = LoggerFactory.getLogger(HttpClientLoginTest.class);

	/**
	 * post方式
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 * @throws Exception 
	 */
	public static String getHttp() throws Exception {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		
		String cargoId = "18";
		String driverId = "1";
		String userId = "1";
		String assess = "比较好了";
		String assessEvaluateScore = "4";
		
		String url = "http://localhost:8080/DriverService/addNewDriverUserAssessAction";
		
		// 2.创建GetMethod的实例
		PostMethod postMethod = new PostMethod(url);
		
		// 使用系统系统的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		//设置参数编码
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
		//添加POST请求参数
		postMethod.addParameter("cargoId", cargoId);
		postMethod.addParameter("driverId", driverId);
		postMethod.addParameter("userId", userId);
		postMethod.addParameter("assess", assess);
		postMethod.addParameter("assessEvaluateScore", assessEvaluateScore);
					
		try {
			
			// 3.执行getMethod,调用http接口
			httpClient.executeMethod(postMethod);
			
			// 4.读取内容
			byte[] responseBody = postMethod.getResponseBody();

			// 5.处理返回的内容
			responseMsg = new String(responseBody);
			log.info(responseMsg);

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 6.释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
	}

	/**
	 * 测试的main方法
	 * 
	 * @param args 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{

		System.out.println("post方式调用http接口\n" + getHttp());

		// System.out.println("post方式调用http接口\n"+postHttp(param1,param2));
	}
}
