package user;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

/**
 * Created by haoy on 2014/9/25.
 */
public class DriverTelephoneInfoTest {

    public static void main(String[] args) {
        String msg = doPostRequest();
        System.out.println(msg);
    }

    private static String doPostRequest() {
        String url = "http://localhost:8080/DriverService/driverTelephoneInfoAction";

        String  responseMsg = "";

        PostMethod postMethod = new PostMethod(url);

        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        postMethod.addParameter("driverId", "2550");
        postMethod.addParameter("mobilePhoneModel", "SCH-I619");
        postMethod.addParameter("noImei", "A00000401BA957");
        postMethod.addParameter("mobileBrand", "samsung");

        HttpClient httpClient = new HttpClient();

        try {
            httpClient.executeMethod(postMethod);

            byte[] buff = postMethod.getResponseBody();

            responseMsg = new String(buff);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return responseMsg;
    }
}
