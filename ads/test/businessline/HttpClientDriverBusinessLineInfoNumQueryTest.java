package businessline;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpClientDriverBusinessLineInfoNumQueryTest {
	private static Logger log = LoggerFactory.getLogger(HttpClientDriverBusinessLineInfoNumQueryTest.class);

	/**
	 * get方式
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getHttp() {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		
		Long driverId = 1l;
		
		String url = "http://localhost:8080/DriverService/driverBusinessLineInfoNumQueryAction?driverId=" + driverId;
		
		// 2.创建GetMethod的实例
		GetMethod getMethod = new GetMethod(url);
		
		// 使用系统系统的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		
		try {
			// 3.执行getMethod,调用http接口
			httpClient.executeMethod(getMethod);
			
			// 4.读取内容
			byte[] responseBody = getMethod.getResponseBody();

			// 5.处理返回的内容
			responseMsg = new String(responseBody);
			log.info(responseMsg);

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 6.释放连接
			getMethod.releaseConnection();
		}
		return responseMsg;
	}

	/**
	 * 测试的main方法
	 * 
	 * @param args 
	 */
	public static void main(String[] args){

		System.out.println("get方式调用http接口\n" + getHttp());

		// System.out.println("post方式调用http接口\n"+postHttp(param1,param2));
	}
}
