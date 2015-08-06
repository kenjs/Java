package com.cy.dctms.driverUser.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.bo.SendMessagerLog;
import com.cy.dctms.common.domain.DriverUserInfoDomain;
import com.cy.dctms.common.util.BaiduPushChannelClient;
import com.cy.dctms.driverUser.service.IDriverUserInfoService;
import com.cy.dctms.sendMessagerLog.service.ISendMessagerLogService;

public class PushDriverMessagerAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private DriverUserInfoDomain driverUserInfoDomain;
	private ISendMessagerLogService sendMessagerLogService;
	private IDriverUserInfoService driverUserInfoService;

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
		if (driverUserInfoDomain==null) {
			sendResponseMessage("û�п����͵�˾��,���ܷ���");
		}
		String description ="�ύ��֤��ȡ�����Ƽ����ᡣ";
		String title = "���쵽������������";
		List<String> codeList = new ArrayList<String>();//����Ҫ�������͵��ֻ����б�
		driverUserInfoDomain.setIsPushAll("0");
		driverUserInfoService.auditDriverUserInfoList(driverUserInfoDomain);
		for (DriverUserInfoDomain domain : driverUserInfoDomain.getDataList()) {
			//�����߳�
			if (StringUtils.isNotBlank(domain.getBaiduUserId())||StringUtils.isNotBlank(domain.getBaiduChannelId())) {
				//new push(domain.getBaiduUserId(),domain.getBaiduChannelId(),title,description).start();
				codeList.add(domain.getCode());
			}
		}
		//���淢����Ϣ
		if (codeList.size()>0) {
			SendMessagerLog sendMessagerLog = new SendMessagerLog();
			sendMessagerLog.setChannelType(2);
			sendMessagerLog.setTitle(title);
			sendMessagerLog.setSendState(0);
			sendMessagerLog.setManagerId(Long.valueOf(getSessionUser().getId()));
			sendMessagerLog.setContent(description);
			sendMessagerLog.setOrigin(0);
			sendMessagerLog.setSendType(2);
			sendMessagerLogService.saveBunchMessagerLog(sendMessagerLog,codeList);
		}
		return SUCCESS;
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

	public void setDriverUserInfoService(
			IDriverUserInfoService driverUserInfoService) {
		this.driverUserInfoService = driverUserInfoService;
	}

	class push extends Thread{
		private String baiduUserId;
		private String baiduChannelId;
		private String title;
		private String description;
		public push(String baiduUserId,String baiduChannelId,String title,String description){
			this.baiduUserId = baiduUserId;
			this.baiduChannelId = baiduChannelId;
			this.title = title;
			this.description = description;
		}
		public void run() {
			BaiduPushChannelClient.pushUnicastNotification(Long.valueOf(baiduChannelId),
					baiduUserId, title,description );
		}
	}
}

