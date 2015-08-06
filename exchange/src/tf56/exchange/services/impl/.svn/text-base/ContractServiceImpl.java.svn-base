package tf56.exchange.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.client.WebServiceException;

import org.apache.log4j.Logger;

import tf56.exchange.services.ContractService;
import tf56.sofa.util.ClientUtil;

//import tf56.sofa.util.MD5;

public class ContractServiceImpl implements ContractService {

	public static Logger logger = Logger.getLogger(ContractServiceImpl.class);

	
	/**
	 * @athor changmeng.liu
	 * @date 2014-4-16
	 * @version 1.0
	 * @function 
	 * @param 
	 * @update 
	 */
	@Override
	public String sendWaybillXml(String sendStr) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @athor changmeng.liu
	 * @date 2014-4-16
	 * @version 1.0
	 * @function 
	 * @param 
	 * @update 
	 */
	@Override
	public String sendOderXml(Map map) {
		String msg="";
		String url="contract/outwaybillcs/saveOutWaybill";
		ClientUtil client=new ClientUtil(url);
		try {
			msg=client.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public void outputXml(String xmlStr, HttpServletResponse response) {
		/**
		   StringBuffer sb=new StringBuffer();
		   sb.append("<report_data>");
		   sb.append("<respon_sys>SCM</respon_sys>");
		   sb.append("<respon_time>2014-04-08 14:30:59</respon_time>");
		   sb.append("<result>");
		   sb.append("<code>200</code>");
		   sb.append("<comment>成功</comment>");
		   sb.append("</result>");
		   sb.append("</report_data>");
		   xmlStr=sb.toString();
		 **/
		OutputStream outputStream=null;
		 response.setContentType("text/xml;charset=UTF-8");   
         response.setHeader("Cache-Control", "no-cache");
         response.setHeader("Content-type", "text/xml;charset=UTF-8");//指定消息头以UTF-8码表读数据  
         try {
        	 outputStream = response.getOutputStream();
        	 outputStream.write(xmlStr.getBytes("UTF-8"));
        	 outputStream.flush();
        	 outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         
	}

	/**
	 * @author haoyong
	 * @throws Exception 
	 * @date 2014-4-17
	 * @description 发送xml到化工接口
	 */
	@Override
	public String sendXml2Ryp(Map map) throws Exception {
		String msg = "";
		BufferedReader reader= null;
		//要发送的报文
		String sendMsg = map.containsKey("buffer") ? map.get("buffer").toString() : "";
		try {
			//接收报文的url
			String urlStr = "http://localhost:8080/contract/reviceWaybillsTestcs/reviceWaybills";
			URL url = new URL(urlStr);
			HttpURLConnection servletConnection = (HttpURLConnection) url.openConnection();
			
			// 设置连接参数
            servletConnection.setRequestMethod("POST");
            servletConnection.setDoOutput(true);
            servletConnection.setDoInput(true);
            servletConnection.setAllowUserInteraction(true);
            
            // 开启流，写入XML数据
            OutputStream output = servletConnection.getOutputStream();
            output.write(sendMsg.getBytes());
            output.flush();
            output.close();
            
            // 获取返回的数据
            String strMsg = "";
            StringBuffer buffer = new StringBuffer();
            InputStream inputStream = servletConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((strMsg = reader.readLine()) != null) {
                buffer.append(strMsg);
            }
            
            msg = buffer.toString();
            logger.debug("返回的报文：" + msg);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
		return msg;
	}

}
