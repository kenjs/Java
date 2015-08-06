package com.cy.dcts.identityVerify.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxy;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.codehaus.xfire.transport.http.EasySSLProtocolSocketFactory;
import org.codehaus.xfire.util.dom.DOMOutHandler;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dcts.common.bo.EffectiveIdentityInfo;
import com.cy.dcts.common.bo.IdentityVerifyLog;
import com.cy.dcts.common.bo.UserAccountInfo;
import com.cy.dcts.common.domain.EffectiveIdentityInfoDomain;
import com.cy.dcts.common.domain.IdentityVerifyLogDomain;
import com.cy.dcts.common.domain.UserAccountInfoDomain;
import com.cy.dcts.identityVerify.dao.IdentityVerifyDao;
import com.cy.dcts.identityVerify.service.IdentityVerifyService;
import com.cy.dcts.webUser.service.NciisServices;
/**
 * @description 身份验证逻辑操作接口实现类
 * @author 		haoy
 *
 */
public class IdentityVerifyServiceImpl implements IdentityVerifyService {

	private String authorizationFileName;
	private String nciisServiceUrl;
	private String nciisSbm;
	
	private IdentityVerifyDao identityVerifyDao;
	private Logger _log = LoggerFactory.getLogger(getClass());
	
	public void setIdentityVerifyDao(IdentityVerifyDao identityVerifyDao) {
		this.identityVerifyDao = identityVerifyDao;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public IdentityVerifyLogDomain identityVerify(Map<String, Object> map, String userId) throws Exception {
		
		IdentityVerifyLogDomain domain = new IdentityVerifyLogDomain();
		
		domain.setIDNumber(map.get("IDNumber").toString());
		domain.setName(map.get("name").toString());
		
		IdentityVerifyLog log = new IdentityVerifyLog();
		
		EffectiveIdentityInfo bo = new EffectiveIdentityInfo();		
		
		log.setOperatorId(userId);
		log.setIDNumber(map.get("IDNumber").toString());
		log.setName(map.get("name").toString());				
			
		//否则判断账户余额是否足够查询
		UserAccountInfoDomain userAccountInfodomain = identityVerifyDao.selectUserAccountInfo(userId);
		
		if(userAccountInfodomain != null) {
			
			if(userAccountInfodomain.getAccountBalance() / 5 > 0) {
				
				//从身份信息合法库中查询是否存在匹配的信息
				EffectiveIdentityInfoDomain effectiveIdentityInfo = identityVerifyDao.selectEffectiveIdentityInfo(map);
				
				if(effectiveIdentityInfo != null) {	
					
					log.setIDNumberVerifyResult("一致");
					log.setNameVerifyResult("一致");
					
					domain.setIDNumberVerifyResult("一致");
					domain.setNameVerifyResult("一致");
				} else {
				
					/**
					 * FSD : 最大长度 6（可以填写 3 个汉字）
					 * YWLX: 最大长度40（可以填写 20 个汉字）
					 */
					StringBuilder condition = new StringBuilder();
					condition.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
					condition.append("<ROWS><INFO><SBM>").append(nciisSbm).append("</SBM></INFO>");
					condition.append("<ROW><GMSFHM>公民身份号码</GMSFHM><XM>姓名</XM></ROW>");
					condition.append("<ROW FSD=\"").append("杭州").append("\"").append(" YWLX=\"").append("灿越科技快到网身份验证").append("\">");
					condition.append("<GMSFHM>").append(map.get("IDNumber").toString()).append("</GMSFHM>");
					condition.append("<XM>").append(map.get("name").toString()).append("</XM></ROW></ROWS>");
					
					String resultString = executeClient(nciisServiceUrl,authorizationFileName,condition.toString());	
					Map<String,String> rstMap = paresXml2Map(resultString);
					
					if(StringUtils.isNotBlank(rstMap.get("result_gmsfhm")) && StringUtils.isNotBlank(rstMap.get("result_xm"))) {
						
						log.setIDNumberVerifyResult(rstMap.get("result_gmsfhm"));
						log.setNameVerifyResult(rstMap.get("result_xm"));
						
						domain.setIDNumberVerifyResult(rstMap.get("result_gmsfhm"));
						domain.setNameVerifyResult(rstMap.get("result_xm"));
						
						if("一致".equals(rstMap.get("result_gmsfhm")) && "一致".equals(rstMap.get("result_xm"))) {
							
							bo.setIDNumber(rstMap.get("gmsfhm"));
							bo.setName(rstMap.get("xm"));
						
							//向身份信息合法库中插入新数据
							identityVerifyDao.insertEffectiveIdentityInfo(bo);
						}
					} else if(StringUtils.isNotBlank(rstMap.get("errorCode"))){
						
						domain.setErrorCode(rstMap.get("errorCode"));
						domain.setErrorMesage(rstMap.get("errorMsg"));
						
						log.setErrorCode(rstMap.get("errorCode"));
						log.setErrorMesage(rstMap.get("errorMsg"));
					} else {
						
						domain.setErrorMesageCol(rstMap.get("errormesagecol"));
						domain.setErrorMesage(rstMap.get("errormesage"));
						
						log.setErrorMesageCol(rstMap.get("errormesagecol"));
						log.setErrorMesage(rstMap.get("errormesage"));
					}
										
				}
				//验证之后口扣除相应的费用
				UserAccountInfo userAccountInfo = new UserAccountInfo();
				userAccountInfo.setAccountBalance(userAccountInfodomain.getAccountBalance() - 5);
				userAccountInfo.setUserId(userId);
				
				identityVerifyDao.updateUserAccountInfo(userAccountInfo);
			} else {
				domain.setAccountMsg("账户余额不足, 请充值！");
				log.setRemark("账户余额不足");
			}
		} else {
			domain.setAccountMsg("账户不存在, 请先开通！");
			log.setRemark("账户不存在");
		}
		
		//向身份验证日志中插入一条数据
		identityVerifyDao.insertIdentityVerifyLog(log);
		
		return domain;
	}

	private String executeClient(String serviceUrl,String license,String condition) throws MalformedURLException {
		ProtocolSocketFactory easy = new EasySSLProtocolSocketFactory();
		Protocol protocol = new Protocol("https", easy, 443);
		Protocol.registerProtocol("https", protocol);
		
		Service serviceModel = new ObjectServiceFactory().create(NciisServices.class, "NciisServices", null, null);
		
		NciisServices service = (NciisServices) new XFireProxyFactory().create(serviceModel, serviceUrl + "NciicServices");
		
		Client client = ((XFireProxy)Proxy.getInvocationHandler(service)).getClient();
		client.addOutHandler(new DOMOutHandler());
		
		//压缩传输
		client.setProperty(CommonsHttpMessageSender.GZIP_ENABLED, Boolean.TRUE);
		
		//忽略超时
		client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "1");
		client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "0");
		
		/**
		 * 读取授权文件内容
		 */
		String licenseCode = null;
		String result = null;
		BufferedReader in;
		try {
			String filePath = ServletActionContext.getServletContext().getRealPath(license + ".txt");
			in = new BufferedReader(new FileReader(filePath));
			licenseCode = in.readLine();
			
			//调用核查方法
			result = service.nciicCheck(licenseCode, condition);
			
			//服务条件模板获取
			//result = service.nciicGetCondition(licenseCode);
			
			in.close();//关闭流
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		_log.info(result);
		return result;
	}
	
	//解析返回结果
	private Map<String, String> paresXml2Map(String xml) {
		Map<String,String> map = new HashMap<String, String>();
		Document document = null;
		String errormesage = "";
		String errormesagecol = "";
		String idNumberRst = "";
		String xmRst = "";
		String errorMsg = "";
		String errorCode = "";
		try {
			document = DocumentHelper.parseText(xml);
			Element rootElement = document.getRootElement();//获取根节点
			
			Attribute rootAttr = rootElement.attribute("errorcode");
			if(rootAttr != null) {
				Node errorCodeNode = rootElement.selectSingleNode("//RESPONSE/ROWS/ROW/ErrorCode");
				Node errorMsgNode = rootElement.selectSingleNode("//RESPONSE/ROWS/ROW/ErrorMsg");
				if(errorCodeNode != null && errorMsgNode != null) {
					errorMsg = errorMsgNode.getText();
					errorCode = errorCodeNode.getText();
				}
				map.put("errorCode", errorCode);
				map.put("errorMsg", errorMsg);
				return map;
			}
			
			Node idNumberNode = rootElement.selectSingleNode("//ROWS/ROW/INPUT/gmsfhm");
			String idNumber = idNumberNode.getText();
			String name = rootElement.selectSingleNode("//ROWS/ROW/INPUT/xm").getText();
			map.put("gmsfhm", idNumber);
			map.put("xm", name);
			
			Node sfNode = rootElement.selectSingleNode("//ROWS/ROW/OUTPUT/ITEM/result_gmsfhm");
			Node xmNode = rootElement.selectSingleNode("//ROWS/ROW/OUTPUT/ITEM/result_xm");
			if(sfNode == null || xmNode == null) {
				Node errorMsgNode = rootElement.selectSingleNode("//ROWS/ROW/OUTPUT/ITEM/errormesage");
				Node errorColNode = rootElement.selectSingleNode("//ROWS/ROW/OUTPUT/ITEM/errormesagecol");
				if(errorMsgNode != null && errorColNode != null) {
					errormesage = errorMsgNode.getText();
					errormesagecol = errorColNode.getText();
				}				
			} else {
				idNumberRst = sfNode.getText();
				xmRst = xmNode.getText();			
			}
			
			map.put("result_gmsfhm", idNumberRst);
			map.put("result_xm", xmRst);
			map.put("errormesage", errormesage);
			map.put("errormesagecol", errormesagecol);
		} catch (DocumentException e) {			
			e.printStackTrace();
		}
		return map;
	}
		
	public String getNciisServiceUrl() {
		return nciisServiceUrl;
	}

	public void setNciisServiceUrl(String nciisServiceUrl) {
		this.nciisServiceUrl = nciisServiceUrl;
	}

	public String getNciisSbm() {
		return nciisSbm;
	}

	public void setNciisSbm(String nciisSbm) {
		this.nciisSbm = nciisSbm;
	}

	public String getAuthorizationFileName() {
		return authorizationFileName;
	}

	public void setAuthorizationFileName(String authorizationFileName) {
		this.authorizationFileName = authorizationFileName;
	}

	public IdentityVerifyDao getIdentityVerifyDao() {
		return identityVerifyDao;
	}

}
