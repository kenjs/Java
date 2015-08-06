package user;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class TestPushMessage {
	private static Logger log = LoggerFactory.getLogger(TestPushMessage.class);

	/**
	 * post方式
	 * @return
	 * @throws Exception
	 */
	public static String getHttp() throws Exception {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();

		String url = "http://192.168.10.27:8080/eis/cySendPush";

		// 2.创建GetMethod的实例
		PostMethod postMethod = new PostMethod(url);

		// 使用系统系统的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		//设置参数编码
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
		//添加POST请求参数
		postMethod.addParameter("type", "4");
		postMethod.addParameter("pushTitle", "快到网");
		postMethod.addParameter("pushContent","测试请忽略");
		postMethod.addParameter("pushChannel", "1");
//		postMethod.addParameter("alias", "郝勇");
//		postMethod.addParameter("tag","快到网");
		postMethod.addParameter("driverId","39833");
		postMethod.addParameter("jumpType", "1");
		postMethod.addParameter("tarId", "16887");
		postMethod.addParameter("companyName", "测试");
		postMethod.addParameter("webUserId", "12");

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
	}
}
