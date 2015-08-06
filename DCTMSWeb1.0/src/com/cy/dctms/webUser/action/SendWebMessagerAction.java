package com.cy.dctms.webUser.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.bo.SendMessagerLog;
import com.cy.dctms.common.domain.WebUserInfoDomain;
import com.cy.dctms.common.util.SentMail;
import com.cy.dctms.common.util.ValidateUtil;
import com.cy.dctms.common.util.XmlParseUtil;
import com.cy.dctms.sendMessagerLog.service.INoteService;
import com.cy.dctms.sendMessagerLog.service.ISendMessagerLogService;

public class SendWebMessagerAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private WebUserInfoDomain webUserInfoDomain;
	private INoteService noteService;
	private ISendMessagerLogService sendMessagerLogService;
	private SentMail sentMail;

	/** 查询企业信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query webUserInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		String description ="【快到网】诚挚邀请您进行企业认证！点击www.56top.cn 登录，马上认证提高诚信，更多报价信息任你选，还在等什么？";
		String title = "【快到网】企业认证邀请函！";
		String mobilephone = "";//将手机连城一个字符串
		List<String> emailList = new ArrayList<String>();//保存要发送email的id列表
		List<String> telephoneList = new ArrayList<String>();//保存要发送短信的id列表
		String sdk = "";//保存发送短信返回值
		boolean successFlag = true;
		if ("0".equals(webUserInfoDomain.getSendType())) {
			for(WebUserInfoDomain domain : webUserInfoDomain.getDataList()){
				if (StringUtils.isNotBlank(domain.getEmail())) {
					new pushEmail(domain.getEmail(),title).start();//开启线程发送邮件
					emailList.add(domain.getEmail());
				}else if (ValidateUtil.validateTelePhone(domain.getMobilephone())) {
					mobilephone +=  "," +  domain.getMobilephone();
					telephoneList.add(domain.getMobilephone());
				}
			}
			if (emailList.size()==0&&telephoneList.size()==0) {
				sendResponseMessage("没有可发送短信和邮箱的企业,不能发送");
			}
		}else if ("1".equals(webUserInfoDomain.getSendType())) {
			for(WebUserInfoDomain domain : webUserInfoDomain.getDataList()){
				if (ValidateUtil.validateTelePhone(domain.getMobilephone())) {
					mobilephone += "," +  domain.getMobilephone();
					telephoneList.add(domain.getMobilephone());
				}
			}
			if (emailList.size()==0&&telephoneList.size()==0) {
				sendResponseMessage("没有可发送短信的企业,不能发送");
			}
		}else if ("2".equals(webUserInfoDomain.getSendType())){
			for(WebUserInfoDomain domain : webUserInfoDomain.getDataList()){
				if (StringUtils.isNotBlank(domain.getEmail())&&"294102148@qq.com".equals(domain.getEmail())) {
					new pushEmail(domain.getEmail(),title).start();//开启线程发送邮件
					emailList.add(domain.getEmail());
				}
			}
			if (emailList.size()==0&&telephoneList.size()==0) {
				sendResponseMessage("没有可发送邮箱的企业,不能发送");
			}
		}
		if (StringUtils.isNotBlank(mobilephone)) {
			//对手机号不是空的进行发短信，别且去掉第一个逗号
			mobilephone = mobilephone.substring(1);
			//sdk = noteService.sendNoteSDKService(mobilephone, description);
			String returnStatus = XmlParseUtil.getXmlStr(sdk, "status");
			if(!"0".equals(returnStatus)) {
				successFlag =false;
			}
		}
		if (!successFlag) {
			sendResponseMessage("发送信息失败");
		}
		//保存发送消息
		if (emailList.size()>0) {
			SendMessagerLog sendMessagerLog = new SendMessagerLog();
			sendMessagerLog.setChannelType(1);
			sendMessagerLog.setTitle(title);
			sendMessagerLog.setSendState(0);
			sendMessagerLog.setManagerId(Long.valueOf(getSessionUser().getId()));
			sendMessagerLog.setContent(description);
			sendMessagerLog.setOrigin(1);
			sendMessagerLog.setSendType(2);
			sendMessagerLogService.saveBunchMessagerLog(sendMessagerLog,emailList);
		}
		if (telephoneList.size()>0) {
			//发送短信
			SendMessagerLog sendMessagerLog = new SendMessagerLog();
			sendMessagerLog.setResultValue(XmlParseUtil.getXmlStr(sdk, "taskid"));
			sendMessagerLog.setChannelType(0);
			if (!successFlag) {
				sendMessagerLog.setSendState(1);
			}else {
				sendMessagerLog.setSendState(0);
			}
			sendMessagerLog.setManagerId(Long.valueOf(getSessionUser().getId()));
			sendMessagerLog.setContent(description);
			sendMessagerLog.setOrigin(1);
			sendMessagerLog.setSendType(2);
			sendMessagerLogService.saveBunchMessagerLog(sendMessagerLog,telephoneList);
		}	
		return SUCCESS;
	}

	public WebUserInfoDomain getWebUserInfoDomain() {
		return webUserInfoDomain;
	}

	public void setWebUserInfoDomain(WebUserInfoDomain webUserInfoDomain) {
		this.webUserInfoDomain = webUserInfoDomain;
	}
	
	public void setNoteService(INoteService noteService) {
		this.noteService = noteService;
	}

	public void setSendMessagerLogService(
			ISendMessagerLogService sendMessagerLogService) {
		this.sendMessagerLogService = sendMessagerLogService;
	}
	public void setSentMail(SentMail sentMail) {
		this.sentMail = sentMail;
	}

	class pushEmail extends Thread{
		private String email;
		private String title;
		public pushEmail(String email ,String title ){
			this.email = email;
			this.title = title;
		}
		public void run() {
			  String description ="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"+
		        "<html>"+
		        "<head><title></title>" +
		        "<style type=\"text/css\">" +
		        "* { margin:0px; padding:0px; color:#333; font-size:14px; line-height:26px; font-family:\"微软雅黑\";}" +
				 " ul { margin-left:46px;}" +
				 " ul li {list-style-type:none;}" +
				 " h4{ font-size:16px; font-weight:400; color:#000;}" +
		        "</style>" +
		        "</head>"+
		        "<body>"+
		        "<h4 >亲爱的：</h4>"+
		        "<br>"+
		        "<div > &nbsp&nbsp&nbsp&nbsp【快到网】诚挚邀请您进行企业认证！点击www.56top.cn 登录，马上认证提高诚信，更多报价信息任你选，还在等什么？</div>"+
		        "<br>"+
		        "<div>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp登录后进入我的快到，点击企业认证，填写并上传资料</div>"+
		        "<IMG SRC=cid:enterprise.png width=80% height=60%><br>"+
		        "<br />"+		
	        	"<div>"+
	        	"<h4>&nbsp&nbsp&nbsp&nbsp提交资料，我们会在3个工作日内为您处理，有任何疑问或问题可随时联系我们^_^</h4>"+
	        	"<h4>&nbsp&nbsp&nbsp&nbsp我们定将竭诚为您服务！</h4>"+
	        	"<br />"+
	            "<ul>"+
	            	"<li>公司名称：杭州灿越网络科技有限公司</li>"+
	            	"<li>公司地址：浙江省杭州市西湖区古翠路10号新亚科技大楼C座3楼</li>"+
	            	"<li>公司电话：4009-904-656</li>"+
	            	"<li>公司传真：0571-89712502</li>"+
	                "<li>公司邮箱：cy@56top.cn</li>"+
	                "<li>联 系 人：乔先生</li>"+
	                "<li>公司网址：<a>http://www.56top.cn</a></li>"+
	            "</ul>"+
	            "</div>"+
	        "</body>"+
	        "</html>";
			  String sendMailImagedir=request.getSession().getServletContext().getRealPath("/");
			  sendMailImagedir = sendMailImagedir+"resource/images/enterprise.png";
			sentMail.sendImage(email, title, description,sendMailImagedir);
		}
	}
}
