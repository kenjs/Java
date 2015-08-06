package com.cy.dctms.driverUser.action;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.bo.SendMessagerLog;
import com.cy.dctms.common.domain.DriverUserInfoDomain;
import com.cy.dctms.common.util.BaiduPushChannelClient;
import com.cy.dctms.driverUser.service.IDriverUserInfoService;
import com.cy.dctms.sendMessagerLog.service.ISendMessagerLogService;

public class AuditDriverUserInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserInfoService driverUserInfoService;
	private DriverUserInfoDomain driverUserInfoDomain;
	private ISendMessagerLogService sendMessagerLogService;

	/** ���˾����Ϣ
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("save driverUserInfo start");
		if(getSessionUser()==null){
			sendResponseMessage("loginMx");
			return SUCCESS;
		}
		driverUserInfoService.auditDriverUserInfo(driverUserInfoDomain,getSessionUser().getId());
		//����
		DriverUserInfoDomain domain = driverUserInfoService.queryDriverUserInfoMxById(driverUserInfoDomain.getId());
		SendMessagerLog sendMessagerLog = new SendMessagerLog();
		if (domain==null||StringUtils.isBlank(domain.getCode())) {
			sendResponseMessage("error");
			return SUCCESS;
		}
		if (StringUtils.isBlank(domain.getBaiduUserId())||StringUtils.isBlank(domain.getBaiduChannelId())) {
			sendResponseMessage("����ɹ���������û�а�����ʧ��");
			return SUCCESS;
		}
		//ͨ�������͹̶����ݣ�ʧ�ܵ����Ͳ��̶�����
		String description = "";
		String title = "";
		if ("3".equals(driverUserInfoDomain.getSubmitType())) {
			title = "���쵽������л������֤��";
			sendMessagerLog.setSendType(0);
			description = "��ϲ����ͨ����ˣ���Ϊ��֤�û�����";
		} else {
			sendMessagerLog.setSendType(1);
			title = "���쵽������л������֤��";
			if (3==driverUserInfoDomain.getReason().size()) {
				description = "���ϴ�����Ϣ��������Ҫ������";
			}else{
				description = "���ϴ���";
				for (String str : driverUserInfoDomain.getReason()) {
					description += java.net.URLDecoder.decode(str,"utf-8");
				}
				description += "������Ҫ������";
			}
		}
		boolean sucessFlag = BaiduPushChannelClient.pushUnicastNotification(Long.valueOf(domain.getBaiduChannelId()),
				domain.getBaiduUserId(), title,description );
		if (sucessFlag) {
			sendMessagerLog.setSendState(0);
		}else {
			sendMessagerLog.setSendState(1);
			sendResponseMessage("����ɹ���������ʧ��");
		}
		sendMessagerLog.setChannelType(2);
		sendMessagerLog.setSendNumber(domain.getCode());
		sendMessagerLog.setTitle(title);
		sendMessagerLog.setManagerId(Long.valueOf(getSessionUser().getId()));
		sendMessagerLog.setContent(description);
		sendMessagerLog.setOrigin(0);
		sendMessagerLogService.saveSendMessagerLog(sendMessagerLog);
		return SUCCESS;
	}

	public void setDriverUserInfoService(IDriverUserInfoService driverUserInfoService) {
		this.driverUserInfoService = driverUserInfoService;
	}
	public DriverUserInfoDomain getDriverUserInfoDomain() {
		return driverUserInfoDomain;
	}
	public void setDriverUserInfoDomain(DriverUserInfoDomain driverUserInfoDomain) {
		this.driverUserInfoDomain = driverUserInfoDomain;
	}

	public void setSendMessagerLogService(
			ISendMessagerLogService sendMessagerLogService) {
		this.sendMessagerLogService = sendMessagerLogService;
	}


}
