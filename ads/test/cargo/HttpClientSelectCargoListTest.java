package cargo;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpClientSelectCargoListTest {
	private static Logger log = LoggerFactory.getLogger(HttpClientSelectCargoListTest.class);

	/**
	 * get方式
	 * @return
	 * @throws Exception 
	 */
	public static String getHttp() throws Exception {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		
		String startTime = "2014-5-1";
		String startProvince = "";
		String endProvince = "";
		String startCity = "";
		String endCity = "";
		String carType = "";
		String carLength = "";
		String companyName = "杭州";
		
		String url = "http://localhost:8080/DriverService/selectCargoListAction";
		
		// 2.创建GetMethod的实例
		PostMethod postMethod = new PostMethod(url);
		
		// 使用系统系统的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8"); 
		postMethod.addParameter("driverId", "1");
		postMethod.addParameter("startTime", startTime);
		postMethod.addParameter("startProvince", startProvince);
		postMethod.addParameter("endProvince", endProvince);
		postMethod.addParameter("startCity", startCity);
		postMethod.addParameter("endCity", endCity);
		postMethod.addParameter("carType", carType);
		postMethod.addParameter("carLength", carLength);
		postMethod.addParameter("companyName", companyName);
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

		System.out.println("get方式调用http接口\n" + getHttp());

		// System.out.println("post方式调用http接口\n"+postHttp(param1,param2));
	}
}