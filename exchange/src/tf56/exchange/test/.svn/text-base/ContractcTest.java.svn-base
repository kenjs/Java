package tf56.exchange.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ContractcTest {
	
	public static void main(String[] args) throws Exception {
		sendOrder(); //发送订单xml文件
    }
	
	public static void sendOrder() throws Exception{
		StringBuffer sendStr = new StringBuffer();
		sendStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sendStr.append("<report_data>");
		sendStr.append("  <request_sys>RYP</request_sys><!-- 组织编码-->");
		sendStr.append("  <request_time>2014-04-08 14:30:59</request_time>");
		sendStr.append("  <request_param>");
		sendStr.append("     <clientNumber>180-FHTZD-2014-0733</clientNumber> <!--客户单号-->");
		sendStr.append("    <consignDate>2014-04-08</consignDate> <!--托运日期-->");
		sendStr.append("     <consignee>测试使用</consignee> <!--收货人 公司/个人-->");
		sendStr.append("     <consigneeLinkMan>2222</consigneeLinkMan> <!--收货联系人-->");
		sendStr.append("     <consigneeTelephoneNumber>035556564747</consigneeTelephoneNumber> <!--收货人电话-->");
		sendStr.append("     <consigneeMobileNumber>13211112222</consigneeMobileNumber> <!--收货人手机-->");
		sendStr.append("     <consigneeProvince>山西省</consigneeProvince> <!--收货人省-->");
		sendStr.append("     <consigneeCity>长治市</consigneeCity> <!--收货人 市-->");
		sendStr.append("     <consigneeRegion>黎城县</consigneeRegion> <!--收货人 区/县-->");
		sendStr.append("     <consigneeTown>东关路299号</consigneeTown> <!--收货人详细地址-->");
		sendStr.append("     <goodsList> <!--货物信息列表-->");
		sendStr.append("          <goods> <!--货物信息-->");
		sendStr.append("               <goodsName>洗衣粉</goodsName> <!--货物名称-->");
		sendStr.append("               <goodsType>普货</goodsType> <!--货物类型-->");
		sendStr.append("               <spec>100</spec> <!--规格-->");
		sendStr.append("               <model>100</model> <!--型号-->");
		sendStr.append("               <num>10</num> <!--数量-->");
		sendStr.append("               <packageName>纸箱</packageName> <!--包装-->");
		sendStr.append("               <weight>100</weight> <!--重量 单位为kg-->");
		sendStr.append("               <volume>10</volume> <!--体积-->");
		sendStr.append("          </goods> <!--货物信息-->");
		sendStr.append("          <goods> <!--货物信息-->");
		sendStr.append("               <goodsName>洗衣粉</goodsName> <!--货物名称-->");
		sendStr.append("               <goodsType>普货</goodsType> <!--货物类型-->");
		sendStr.append("               <spec>100</spec> <!--规格-->");
		sendStr.append("               <model>100</model> <!--型号-->");
		sendStr.append("               <num>10</num> <!--数量-->");
		sendStr.append("               <packageName>纸箱</packageName> <!--包装-->");
		sendStr.append("               <weight>100</weight> <!--重量 单位为kg-->");
		sendStr.append("               <volume>10</volume> <!--体积-->");
		sendStr.append("          </goods> <!--货物信息-->");
		sendStr.append("     </goodsList> <!--货物信息列表-->");  
		sendStr.append("  </request_param>");
		sendStr.append("</report_data>");

        BufferedReader reader = null;
        try {
            String strMessage = "";
            StringBuffer buffer = new StringBuffer();

            // 接报文的地址
            URL uploadServlet = new URL("http://localhost:8080/exchange/contractcs/receiveXml");
//            URL uploadServlet = new URL("http://localhost:8080/contract/reviceWaybillsTestcs/reviceWaybills");
            HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet.openConnection();
            // 设置连接参数
            servletConnection.setRequestMethod("POST");
            servletConnection.setDoOutput(true);
            servletConnection.setDoInput(true);
            servletConnection.setAllowUserInteraction(true);

            // 开启流，写入XML数据
            OutputStream output = servletConnection.getOutputStream();
            System.out.println("发送的报文：");
            System.out.println(sendStr.toString());

            output.write(sendStr.toString().getBytes());
            output.flush();
            output.close();

            // 获取返回的数据
            InputStream inputStream = servletConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((strMessage = reader.readLine()) != null) {
                buffer.append(strMessage);
            }

            System.out.println("接收返回值:" + buffer);
            getContentOfXml(buffer.toString());

        } catch (java.net.ConnectException e) {
            throw new Exception();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
	}
	
	private static void getContentOfXml(String xmlDoc){
        
        /** 获取不重复的单个节点的内容*/
        Document document =  StrToXMLDocment(xmlDoc);
        List<Element> list0 = document.selectNodes("/report_data/result/comment");
        for(Element el : list0){
            System.out.println(el.getText());
        } 
        
    }

	private static Document StrToXMLDocment(String xmlDoc) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlDoc);// DocumentHelper.parseText(str)这个方法将传入的XML字符串转换处理后返回一个Document对象
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
