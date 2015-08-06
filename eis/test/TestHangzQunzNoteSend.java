import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

/**
 * Created by Administrator on 2014/9/16.
 */
public class TestHangzQunzNoteSend {
    public static void main(String[] args) throws IOException {
    	sendNote();
    }

    protected static void sendNote() throws IOException {
        String str = "测试POST";
        String url = "http://localhost:8080/CyInterfaceService/sendNote";
        String responseMsg = "";

        String json = "[{\"phone\":\"18757178302\",\"content\":\"t1\"},{\"phone\":\"15555382670\",\"content\":\"t2\"}]";

        // 1.构造HttpClient的实例
        HttpClient httpClient = new HttpClient();

        // 2.创建PostMethod的实例
        PostMethod postMethod = new PostMethod(url);

        // 使用系统系统的默认的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        //设置参数编码
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
        //添加POST请求参数
        postMethod.addParameter("channelType", "zs");
        postMethod.addParameter("phoneNum", "18757178302");
        postMethod.addParameter("content", str);
//        postMethod.addParameter("json", json);
        postMethod.addParameter("requestIp","192.168.10.110");
        try {

            // 3.执行getMethod,调用http接口
            httpClient.executeMethod(postMethod);

            // 4.读取内容
            byte[] responseBody = postMethod.getResponseBody();

            // 5.处理返回的内容
            responseMsg = new String(responseBody);

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6.释放连接
            postMethod.releaseConnection();
        }
        System.out.println("msg: " + responseMsg);
    }
}
