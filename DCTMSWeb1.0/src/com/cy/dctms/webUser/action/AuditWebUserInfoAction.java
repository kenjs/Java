package com.cy.dctms.webUser.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.bo.SendMessagerLog;
import com.cy.dctms.common.domain.WebUserInfoDomain;
import com.cy.dctms.common.util.SentMail;
import com.cy.dctms.common.util.XmlParseUtil;
import com.cy.dctms.sendMessagerLog.service.INoteService;
import com.cy.dctms.sendMessagerLog.service.ISendMessagerLogService;
import com.cy.dctms.webUser.service.IWebUserInfoService;

public class AuditWebUserInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IWebUserInfoService webUserInfoService;
	private WebUserInfoDomain webUserInfoDomain;
	private SentMail sentMail;
	private INoteService noteService;
	private ISendMessagerLogService sendMessagerLogService;

	/** �����ҵ��Ϣ
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("save webUserInfo start");
		if(getSessionUser()==null){
			sendResponseMessage("loginMx");
			return SUCCESS;
		}
		webUserInfoService.auditWebUserInfo(webUserInfoDomain,getSessionUser().getId());
		//�����ʼ�
		WebUserInfoDomain domain = webUserInfoService.queryWebUserInfoMxById(webUserInfoDomain.getId());
		SendMessagerLog sendMessagerLog = new SendMessagerLog();//������Ϣ��־
		String description ="";
		String title = "���쵽������ҵ��֤֪ͨ";
		if ("3".equals(webUserInfoDomain.getSubmitType())) {
			sendMessagerLog.setSendType(0);
			description = "���쵽������л��������ҵ��֤����ͨ����ˣ���ϲ���ѳ�Ϊ��֤�û�����";
		} else {
			sendMessagerLog.setSendType(1);
			if (4==webUserInfoDomain.getReason().size()) {
				description = "���쵽������л��������ҵ��֤���ܱ�Ǹ���ϴ�����Ϣ��������Ҫ�����������ϴ���";
			}else {
				description ="���쵽������л��������ҵ��֤���ܱ�Ǹ���ϴ���";
				for (int i = 0; i< webUserInfoDomain.getReason().size();i++) {
					if (i!=0) {
						description += "��"+java.net.URLDecoder.decode(webUserInfoDomain.getReason().get(i),"utf-8");
					}else {
						description += java.net.URLDecoder.decode(webUserInfoDomain.getReason().get(i),"utf-8");
					}
				}
				description += "������Ҫ�����������ϴ���";
			}
		}
		boolean successFlag = true;//�Ƿ�ɹ�
		if ("0".equals(webUserInfoDomain.getSendNoteOrMail())) {
			//���Ͷ���
			String sdk = noteService.sendNoteSDKService(domain.getMobilephone(), description);
			String returnStatus = XmlParseUtil.getXmlStr(sdk, "status");
			if(!"0".equals(returnStatus)) {
				successFlag =false;
			}
			sendMessagerLog.setResultValue(XmlParseUtil.getXmlStr(sdk, "taskid"));
			sendMessagerLog.setChannelType(0);
			sendMessagerLog.setSendNumber(domain.getMobilephone());
		}else {
			//�����ʼ�
			successFlag = sentMail.send(domain.getEmail(), title, description);
			sendMessagerLog.setChannelType(1);
			sendMessagerLog.setSendNumber(domain.getEmail());
			sendMessagerLog.setTitle(title);
		}
		if (successFlag) {
			sendMessagerLog.setSendState(0);
		}else {
			sendResponseMessage("����ɹ�����������Ϣʧ��");
			sendMessagerLog.setSendState(1);
		}
		sendMessagerLog.setManagerId(Long.valueOf(getSessionUser().getId()));
		sendMessagerLog.setContent(description);
		sendMessagerLog.setOrigin(1);
		sendMessagerLogService.saveSendMessagerLog(sendMessagerLog);
		return SUCCESS;
	}

	public void setWebUserInfoService(IWebUserInfoService webUserInfoService) {
		this.webUserInfoService = webUserInfoService;
	}
	public WebUserInfoDomain getWebUserInfoDomain() {
		return webUserInfoDomain;
	}
	public void setWebUserInfoDomain(WebUserInfoDomain webUserInfoDomain) {
		this.webUserInfoDomain = webUserInfoDomain;
	}

	public void setSentMail(SentMail sentMail) {
		this.sentMail = sentMail;
	}

	public void setNoteService(INoteService noteService) {
		this.noteService = noteService;
	}

	public void setSendMessagerLogService(
			ISendMessagerLogService sendMessagerLogService) {
		this.sendMessagerLogService = sendMessagerLogService;
	}


}
