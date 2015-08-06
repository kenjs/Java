package user;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class HttpClientUpdateDriverUserInfoTest {
	private static Logger log = LoggerFactory.getLogger(HttpClientUpdateDriverUserInfoTest.class);

	public static String getHttp() throws Exception {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();

		String id = "1";
		String carLength = "7M";
		String name = "张山风";
		String carNumber = "浙A55B59";
		String carPlateType = "高板";
		String carBarType = "低栏";
		String carCubage = "120M^2";
		String carWeight = "8吨";

		String url = "http://localhost:8080/DriverService/updateDriverUserInfoAction";

		// 2.创建GetMethod的实例
		PostMethod postMethod = new PostMethod(url);

		// 使用系统系统的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		//设置参数编码
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
		//添加POST请求参数
		postMethod.addParameter("id", id);
		postMethod.addParameter("name", name);
		postMethod.addParameter("carNumber", carNumber);
		postMethod.addParameter("carPlateType", carPlateType);
		postMethod.addParameter("carBarType", carBarType);
		postMethod.addParameter("carCubage", carCubage);
		postMethod.addParameter("carLength", carLength);
		postMethod.addParameter("carWeight", carWeight);
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
