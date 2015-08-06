package tf56.exchange.services.impl;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.serfj.client.Client;
import net.sf.serfj.client.WebServiceException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
//import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.exchange.services.ElectronichubDao;
import tf56.sofa.http.client.HttpClient;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.SysUtil;

import com.wondersgroup.cuteinfo.client.exchangeserver.exchangetransport.dao.IMessageTransporterDAO;
import com.wondersgroup.cuteinfo.client.exchangeserver.exchangetransport.exception.UMessageTransportException;
import com.wondersgroup.cuteinfo.client.exchangeserver.exchangetransport.factory.ITransportClientFactory;
import com.wondersgroup.cuteinfo.client.exchangeserver.exchangetransport.impl.USendRequset;
import com.wondersgroup.cuteinfo.client.exchangeserver.exchangetransport.impl.USendResponse;
import com.wondersgroup.cuteinfo.client.exchangeserver.usersecurty.UserSecurityClient;
import com.wondersgroup.cuteinfo.client.exchangeserver.usersecurty.UserSecurityClientFactory;
import com.wondersgroup.cuteinfo.client.exchangeserver.usersecurty.UserSecurityException;
import com.wondersgroup.cuteinfo.client.exchangeserver.usersecurty.UserToken;
import com.wondersgroup.cuteinfo.client.util.UUIDHex; 



public class ElectronichubcDaoImpl  implements
		ElectronichubDao {

	//public static Logger logger = Logger.getLogger(Loginc.class); 
	/**
	 * @author tlp
	 * @funtion 点子枢纽专线上传数据--类型XML
	 * @return String
	 * @date 2013-01-09
	 * @throws IOException
	 */
	public String uploadLineXML(Map map) {
		String result="上传失败";
		String json = "";

		//以下为正式代码
		String url = "party/flightcs/getElectronicHubData";
		Client client = new Client(url);
		//测试map;
		List<String> li= new ArrayList<String>();
		try {
			//json=super.postRequest(url, map).toString();  正式上线时，要将super 改为client
			json = client.postRequest(url, map).toString();
	
			List<Map<String, Object>> list=Json2ObjectUtil.parseJSON2List(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JsonGenerateUtil.getMsgJson(result+":"+e.getMessage());
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 传输（测试）地址 切换到正式环境后，应注意调整相应的服务地址
		//String targetURL = "http://test.logink.org:8080/cuteinfo/services/ExchangeTransportService";
		// 认证（测试）地址
		//String securityURL = "http://test.logink.org:8080/cuteinfo/services/ExchangeUserSecurity";
		// 安全认证服务(正式)地址: 
		String securityURL  = "http://exa.logink.org:81/cuteinfo/services/ExchangeUserSecurity?wsdl";
	    // 交换传输服务（正式）地址：
		String targetURL = "http://exa.logink.org:81/cuteinfo/services/ExchangeTransportService?wsdl";
		//String user = "8412";//联调口令
		//String pass = "111111";//联调密码 
		String user = "727599472";//6206用户物流交换代码，应注意调整
		String pass = "tf56.com";//436400用户密码，应注意调整
		IMessageTransporterDAO transporter = null;
		UserSecurityClient security = null;
		UserToken token=null;
		// 首先需要通过平台的认证服务，使用物流交换代码和密码取得令牌
		try {
			security = UserSecurityClientFactory
					.createUserSecurityClient(securityURL);
			token = security.authenticate(user, pass);

		} catch (UserSecurityException e1) {
			// TODO Auto-generated catch block
			result="上传失败：token对象不存在";
			e1.printStackTrace();
		}
		//172.16.118.202:8080
		List<Map<String, Object>> list=Json2ObjectUtil.parseJSON2List(json);
		//return createLineXml(list.get(0));
		if(null!=token.getTokenID()){
			for (int i = 0; i < 10; i++) {
				Map m = (Map) list.get(i);
				try {
					USendRequset sendReq = new USendRequset();
					// 以下是各个业务报文的发送实例,具体返回结果请按照NRtest中接收的方法来接收处理
					// 4.5 客户端意见上传 需要枢纽人员进行人工操作后方可下载，回复意见下载参照接收接口中相关操作
					sendReq.setToaddress(new String[] { "jyzx00002" });
					//货运专线信息上传 ZJWL_LOGINK_JYZX_InfoSpecialLineIssueRequest
					String linexml=createLineXml(m);//构建班线XML
					//System.out.println("xml"+i+":"+linexml);
					sendReq.setSendRequestTypeXML("ZJWL_LOGINK_JYZX_InfoSpecialLineIssueRequest",linexml);
					// 最后，调用平台提供的发送服务发送报文
					transporter = ITransportClientFactory.createMessageTransporter(
							token, targetURL);
					USendResponse response = transporter.send(sendReq);
					if (response.isSendResult()) {
						result="上传成功";
						li.add(result);
						
					} else {
						// 错误的情况下，会返回异常代码以及异常信息。异常代码请参照《3.2 共建指导性文件：交换接入》中的异常代码信息
						System.out.println("send error");
						System.out.println(response.getGenericFault().getCode());
						System.out.println(response.getGenericFault().getMessage());
						System.out.println(response.getSendResponseResultsList().get(0));
						result="上传失败";
						li.add(result);
					}
				} catch (UMessageTransportException e) {
					result="上传失败："+e.getMessage();
					e.printStackTrace();
				}
			}
		}
		return JsonGenerateUtil.list2json(li);
		
	}

	/**
	 * @author tlp
	 * @funtion 创建电子枢纽专线上传XML
	 * @return String
	 * @date 2013-01-05
	 * @throws IOException
	 */
	public String createLineXml(Map map) {
		
		/*  File inputXml = new File("C:\\Users\\tlp\\Desktop\\aa.xml");
		  SAXReader saxReader = new SAXReader(); Document document; document =
		  saxReader.read(inputXml);
		 */
		Document document = DocumentHelper.createDocument();
		String url = "party/flightcs/getCountrySubdivisionCode";
		Client client = new Client(url);
		// 设置文件编码
		 document.setXMLEncoding("UTF-8");
		// 添加根标签
		Element Root = document.addElement("Root");
		// 添加Header标签
		Element Header = Root.addElement("Header");// 报文头
		Element MessageReferenceNumber = Header
				.addElement("MessageReferenceNumber");// 报文参考号
		MessageReferenceNumber.setText(UUIDHex.getUUIDHex()); //正式上传时启用
		Element DocumentName = Header.addElement("DocumentName");// 单证名称
		DocumentName.setText("专线信息上传");
		Element DocumentVersionNumber = Header
				.addElement("DocumentVersionNumber");// 报文版本号
		DocumentVersionNumber.setText("V3.1");
		Element SenderCode = Header.addElement("SenderCode");// 发送方代码
		SenderCode.setText("727599472");//发送方代码
		Element MessageSendingDateTime = Header
				.addElement("MessageSendingDateTime");// 发送日期时间
		SimpleDateFormat format= new SimpleDateFormat("YYYYMMddhhmmss");
		MessageSendingDateTime.setText(format.format(new Date()));
		Element MessageFunctionCode = Header.addElement("MessageFunctionCode");// 报文功能代码
		MessageFunctionCode.setText("2");

		// 添加body标签
		Element Body = Root.addElement("Body");// 报文体
		Element UserName = Body.addElement("UserName");// 用户名
		UserName.setText("");//设置用户名
		Element IssuerName = Body.addElement("IssuerName");// 发布者名称
		IssuerName.setText("传化公路港物流有限公司");
		Element LineName = Body.addElement("LineName");// 专线名
		if(null!=map.get("tuliplinename")){
			LineName.setText(map.get("tuliplinename").toString());//tuliplinename
		}
		// ....线路起点
		Element LineStart = Body.addElement("LineStart");// 线路起点
		Element DeparturePlace = LineStart.addElement("DeparturePlace");// 启运地 tulipFromCity
		Element CountrySubdivisionName = LineStart
				.addElement("CountrySubdivisionName");// 国家行政区划名称
		Element CountrySubdivisionCode = LineStart
				.addElement("CountrySubdivisionCode");// 国家行政区划标识符
		if(null!=map.get("tulipfromcity")){
			DeparturePlace.setText(map.get("tulipfromcity").toString());
			CountrySubdivisionName.setText(map.get("tulipfromcity").toString());
			String code = "";
			Map parm= new HashMap<String, Object>();
			parm.put("name", map.get("tulipfromcity"));
			try {
				code = client.postRequest(url, parm).toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WebServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CountrySubdivisionCode.setText(code);
		}
		// ....线路起点
		
		// ....线路终点
		Element LineEnd = Body.addElement("LineEnd");// 线路终点 tulipToCity
		Element Destination = LineEnd.addElement("Destination");// 目的地
		Element CountrySubdivisionName2 = LineEnd
			.addElement("CountrySubdivisionName");// 国家行政区划名称
		
		Element CountrySubdivisionCode2 = LineEnd
			.addElement("CountrySubdivisionCode");// 国家行政区划标识符
		
		if(null!=map.get("tuliptocity")){
			Destination.setText(map.get("tuliptocity").toString());
			CountrySubdivisionName2.setText(map.get("tuliptocity").toString());
			String code = "";
			Map parm= new HashMap<String, Object>();
			parm.put("name", map.get("tuliptocity"));
			try {
				code = client.postRequest(url, parm).toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WebServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CountrySubdivisionCode2.setText(code);
		}
		
		// ....线路终点
		Element IssueDate = Body.addElement("IssueDate");// 发布时间
		if(null!=map.get("tulipupdatetime")){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dd=sdf.format(new Date());
			map.put("tulipupdatetime",dd);
			Date d=null;
			try {
				d=sdf.parse(map.get("tulipupdatetime").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			format.format(d);
			IssueDate.setText(format.format(d));
		}
		Element LogisticsExchangeCode = Body
				.addElement("LogisticsExchangeCode");// 物流交换代码
		LogisticsExchangeCode.setText("727599472");// 设置物流交换代码
		Element FreeText = Body.addElement("FreeText");// 自由文本
		//设置自由文本
		if(null!=map.get("description")){
			FreeText.setText(map.get("description").toString());
		}else{
		//FreeText.setText("");
		}
		Element NetAccessAddress = Body.addElement("NetAccessAddress");// 网络访问地址
		NetAccessAddress.setText("");//设置网络访问地址
		Element ModeOfTransport = Body.addElement("ModeOfTransport");// 运输方式
		ModeOfTransport.setText("公路");
		Element BusinessScope = Body.addElement("BusinessScope");// 经营范围
		BusinessScope.setText("");//设置经营范围
		Element EnterpriseRegistrationAddress = Body
				.addElement("EnterpriseRegistrationAddress");// 企业注册地址
		if(null!=map.get("officeaddress")){
			EnterpriseRegistrationAddress.setText(map.get("officeaddress").toString());
		}
		Element RoadRouteDescription = Body.addElement("RoadRouteDescription");// 路线描述
		RoadRouteDescription.setText("");//设置线路描述
		Element ContactName = Body.addElement("ContactName");// 联系人姓名
		//设置联系人姓名
		if(null!=map.get("legalperson")){
			ContactName.setText(map.get("legalperson").toString());
		}else{
		    ContactName.setText("");
		}
		Element TelephoneNumber = Body.addElement("TelephoneNumber");// 电话号码
		// 设置电话号码
		if(null!=map.get("telephonenumber")){
			TelephoneNumber.setText(map.get("telephonenumber").toString());
		}
		TelephoneNumber.setText("");
		
		Element Period = Body.addElement("BusinPeriodessScope");// 期限
		Period.setText("");//设置期限
		Element DeliveryOrTransportTermsDescription = Body
				.addElement("DeliveryOrTransportTermsDescription");// 交货或运输条款描述
		DeliveryOrTransportTermsDescription.setText("");//设置交货或运输条款描述
		Element ContractCarriageConditionCode = Body
				.addElement("ContractCarriageConditionCode");// 合同和运输条件
		ContractCarriageConditionCode.setText("102");// 设置合同和运输条件
		Element Remark = Body.addElement("Remark");// 备注
		Remark.setText("");// 备注
		// ......价格信息
		Element PriceInfo = Body.addElement("PriceInfo");// 价格信息
		Element PriceType = PriceInfo.addElement("PriceType");// 价格类型代码
		PriceType.setText("001");
		Element Price = PriceInfo.addElement("Price");// 价格
		if(null!=map.get("heavyprice")){
			Price.setText(map.get("heavyprice").toString());
		}else{
			if(null!=map.get("lightprice")){
			PriceType.setText("003");
			Price.setText(map.get("lightprice").toString());
			}
		}
		Element PriceCurrencyCode = PriceInfo.addElement("PriceCurrencyCode");// 价格货币代码
		PriceCurrencyCode.setText("CNY");
		// ......价格信息
		/*// 创建输出流，以及字符编码的设定
		try {
			OutputFormat format1 = OutputFormat.createPrettyPrint(); // **
																	// 指定XML编码

			format1.setEncoding("utf-8"); // C:\Users\tlp\Desktop\aa.xml //**
			// 将document中的内容写入文件中

			XMLWriter output = new XMLWriter(new FileWriter(new File(
					"C:\\Users\\tlp\\Desktop\\aa.xml")), format1);
			output.write(document);
			output.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		// 将xml转化为字符串
		return document.asXML();
	}
	
}


