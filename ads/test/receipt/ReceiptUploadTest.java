package receipt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class ReceiptUploadTest {
	private static Logger log = LoggerFactory.getLogger(ReceiptUploadTest.class);

    private static void testSimulateFormToPostFile(String urlPath,String newName,String filePath) {
        File file = null;
        URL url = null;
        StringBuffer sb_body = null;// 报文体
        HttpURLConnection httpUrl = null;// http协议类
        OutputStream fos = null;// 文件流
        FileInputStream fis = null;// 服务器回写响应流
        BufferedReader br = null;// 读取响应
        try {
            file = new File(filePath);
            if (!file.exists()) {
                throw new Exception();
            }
            String boundary = "---------------------------7da2e536604c8";
            url = new URL(urlPath);
            httpUrl = (HttpURLConnection) url.openConnection();// 创建连接
            httpUrl.setDoInput(true);// 创建输入流，必须有
            httpUrl.setDoOutput(true);// 创建输出流，必须有
            httpUrl.setUseCaches(false);// 不缓存
            httpUrl.setConnectTimeout(30000);// 连接超时
            httpUrl.setReadTimeout(30000);// 响应超时
            httpUrl.setRequestMethod("POST");
            httpUrl.setRequestProperty("Content-Length", "" + file.length());// 文件大小
            httpUrl.addRequestProperty("Charset", "UTF-8");
            httpUrl.addRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
            httpUrl.addRequestProperty("Connection", "Keep-Alive");// 连接方式，此处为长连接
            // httpUrl.addRequestProperty("Cookie", cookie);// 权限验证使用
            fos = httpUrl.getOutputStream();
            // 注意，http协议，是流传输，全部内容都要转换为byte类型
            sb_body = new StringBuffer();
            // 分隔符
            sb_body.append("--");
            sb_body.append(boundary);
            sb_body.append("\r\n");
            // 文档类型
            sb_body.append("Content-Disposition:form-data;name=\"file\";"
                    + "filename=\"" + newName + "\"\r\n");
            sb_body.append("Content-Type:application/ms-word\r\n\r\n");
            byte[] head = sb_body.toString().getBytes();
            fos.write(head);
            // 文件内容
            fis = new FileInputStream(file);
            byte[] read = new byte[2048];
            int offset = 0;
            while ((offset = fis.read(read)) != -1) {
                fos.write(read, 0, offset);
            }
            fos.write(("\r\n--" + boundary + "--\r\n").getBytes());
            fos.flush();// 发送请求
            // HTTP响应
            br = new BufferedReader(new InputStreamReader(httpUrl
                    .getInputStream()));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
	 * 测试的main方法
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{

		String path = "http://localhost:8080/DriverService/receiptUpload?driverId=35&transactionId=1&type=1",
				newName = "333333.jpg",
				file = "F:/IMG/20140503015112150.jpg";
		//uploadFile(path, newName, file);
		testSimulateFormToPostFile(path, newName, file);
	}
}
