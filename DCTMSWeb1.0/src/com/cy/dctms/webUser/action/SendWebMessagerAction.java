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

	/** ��ѯ��ҵ��Ϣ�б�
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query webUserInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		String description ="���쵽������ֿ������������ҵ��֤�����www.56top.cn ��¼��������֤��߳��ţ����౨����Ϣ����ѡ�����ڵ�ʲô��";
		String title = "���쵽������ҵ��֤���뺯��";
		String mobilephone = "";//���ֻ�����һ���ַ���
		List<String> emailList = new ArrayList<String>();//����Ҫ����email��id�б�
		List<String> telephoneList = new ArrayList<String>();//����Ҫ���Ͷ��ŵ�id�б�
		String sdk = "";//���淢�Ͷ��ŷ���ֵ
		boolean successFlag = true;
		if ("0".equals(webUserInfoDomain.getSendType())) {
			for(WebUserInfoDomain domain : webUserInfoDomain.getDataList()){
				if (StringUtils.isNotBlank(domain.getEmail())) {
					new pushEmail(domain.getEmail(),title).start();//�����̷߳����ʼ�
					emailList.add(domain.getEmail());
				}else if (ValidateUtil.validateTelePhone(domain.getMobilephone())) {
					mobilephone +=  "," +  domain.getMobilephone();
					telephoneList.add(domain.getMobilephone());
				}
			}
			if (emailList.size()==0&&telephoneList.size()==0) {
				sendResponseMessage("û�пɷ��Ͷ��ź��������ҵ,���ܷ���");
			}
		}else if ("1".equals(webUserInfoDomain.getSendType())) {
			for(WebUserInfoDomain domain : webUserInfoDomain.getDataList()){
				if (ValidateUtil.validateTelePhone(domain.getMobilephone())) {
					mobilephone += "," +  domain.getMobilephone();
					telephoneList.add(domain.getMobilephone());
				}
			}
			if (emailList.size()==0&&telephoneList.size()==0) {
				sendResponseMessage("û�пɷ��Ͷ��ŵ���ҵ,���ܷ���");
			}
		}else if ("2".equals(webUserInfoDomain.getSendType())){
			for(WebUserInfoDomain domain : webUserInfoDomain.getDataList()){
				if (StringUtils.isNotBlank(domain.getEmail())&&"294102148@qq.com".equals(domain.getEmail())) {
					new pushEmail(domain.getEmail(),title).start();//�����̷߳����ʼ�
					emailList.add(domain.getEmail());
				}
			}
			if (emailList.size()==0&&telephoneList.size()==0) {
				sendResponseMessage("û�пɷ����������ҵ,���ܷ���");
			}
		}
		if (StringUtils.isNotBlank(mobilephone)) {
			//���ֻ��Ų��ǿյĽ��з����ţ�����ȥ����һ������
			mobilephone = mobilephone.substring(1);
			//sdk = noteService.sendNoteSDKService(mobilephone, description);
			String returnStatus = XmlParseUtil.getXmlStr(sdk, "status");
			if(!"0".equals(returnStatus)) {
				successFlag =false;
			}
		}
		if (!successFlag) {
			sendResponseMessage("������Ϣʧ��");
		}
		//���淢����Ϣ
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
			//���Ͷ���
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
		        "* { margin:0px; padding:0px; color:#333; font-size:14px; line-height:26px; font-family:\"΢���ź�\";}" +
				 " ul { margin-left:46px;}" +
				 " ul li {list-style-type:none;}" +
				 " h4{ font-size:16px; font-weight:400; color:#000;}" +
		        "</style>" +
		        "</head>"+
		        "<body>"+
		        "<h4 >�װ��ģ�</h4>"+
		        "<br>"+
		        "<div > &nbsp&nbsp&nbsp&nbsp���쵽������ֿ������������ҵ��֤�����www.56top.cn ��¼��������֤��߳��ţ����౨����Ϣ����ѡ�����ڵ�ʲô��</div>"+
		        "<br>"+
		        "<div>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp��¼������ҵĿ쵽�������ҵ��֤����д���ϴ�����</div>"+
		        "<IMG SRC=cid:enterprise.png width=80% height=60%><br>"+
		        "<br />"+		
	        	"<div>"+
	        	"<h4>&nbsp&nbsp&nbsp&nbsp�ύ���ϣ����ǻ���3����������Ϊ���������κ����ʻ��������ʱ��ϵ����^_^</h4>"+
	        	"<h4>&nbsp&nbsp&nbsp&nbsp���Ƕ����߳�Ϊ������</h4>"+
	        	"<br />"+
	            "<ul>"+
	            	"<li>��˾���ƣ����ݲ�Խ����Ƽ����޹�˾</li>"+
	            	"<li>��˾��ַ���㽭ʡ�������������Ŵ�·10�����ǿƼ���¥C��3¥</li>"+
	            	"<li>��˾�绰��4009-904-656</li>"+
	            	"<li>��˾���棺0571-89712502</li>"+
	                "<li>��˾���䣺cy@56top.cn</li>"+
	                "<li>�� ϵ �ˣ�������</li>"+
	                "<li>��˾��ַ��<a>http://www.56top.cn</a></li>"+
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
