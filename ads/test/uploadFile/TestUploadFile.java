package uploadFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUploadFile {

	public static void main(String[] args) {
		//testSimulateFormToPostFile();
		String path = "http://localhost:8080/DriverService/uploadFile?driverId=36532&type=6",
				newName = "77777777.jpg",
				file = "F:\\IMG\\6a5bdac5gw1emdpa6tj3yj20go0ptdib.jpg";
		//uploadFile(path, newName, file);

        String pathUrl = "http://localhost:8080/DriverService/receiptUpload?driverId=35&transactionId=1&type=1";

		testSimulateFormToPostFile(path, newName, file);
	}

	/* 上传文件Server的method */
	private static void uploadFile(String path,String newName,String file) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		try {
			URL url = new URL(path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			/* 允许Input、Output，不使用Cache */
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			/* 设定传送的method=POST */
			con.setRequestMethod("POST");
			/* setRequestProperty */
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			/* 设定DataOutputStream */
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data;"
					+ "name=\"file1\";filename=\"" + newName + "\"" + end);
			ds.writeBytes(end);

			/* 取得文件的FileInputStream */
			FileInputStream fStream = new FileInputStream(file);
			/* 设定每次写入1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			int length = -1;
			/* 从文件读取数据到缓冲区 */
			while ((length = fStream.read(buffer)) != -1) {
				/* 将数据写入DataOutputStream中 */
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

			/* close streams */
			fStream.close();
			ds.flush();

			/* 取得Response内容 */
			InputStream is = con.getInputStream();
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			/* 将Response显示于Dialog */
			// showDialog(b.toString().trim());
			/* 关闭DataOutputStream */
			ds.close();
			System.out.println(b.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			if (httpUrl.getResponseCode() == 200) {
				br = new BufferedReader(new InputStreamReader(httpUrl
						.getInputStream()));
				String line = null;
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				System.out.println(sb.toString());
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
