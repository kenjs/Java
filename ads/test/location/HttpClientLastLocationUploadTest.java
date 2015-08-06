package location;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpClientLastLocationUploadTest {
	private static Logger log = LoggerFactory.getLogger(HttpClientLastLocationUploadTest.class);

	/**
	 * post方式
	 * @return
	 * @throws Exception 
	 */
	public static String getHttp() throws Exception {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		
		String driverId = "18232";
		String longitude = "120.127078";
		String latitude = "30.279411";
		String province = "浙江省";
		String city = "杭州市";
		String county = "西湖区";
		String town = "古翠路";
		String location = "";
		
		String url = "http://localhost:8080/DriverService/locationLastInfoInsertAction";
		
		// 2.创建GetMethod的实例
		PostMethod postMethod = new PostMethod(url);
		
		// 使用系统系统的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		//设置参数编码
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
		//添加POST请求参数
		postMethod.addParameter("driverId", driverId);
		postMethod.addParameter("longitude", longitude);
		postMethod.addParameter("latitude", latitude);
		postMethod.addParameter("province", province);
		postMethod.addParameter("city", city);
		postMethod.addParameter("county", county);
		postMethod.addParameter("town", town);
		postMethod.addParameter("location", location);
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
        System.out.println(getHttp());

    }
}