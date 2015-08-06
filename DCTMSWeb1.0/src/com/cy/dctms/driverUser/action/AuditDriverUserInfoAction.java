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

	/** 审核司机信息
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
		//推送
		DriverUserInfoDomain domain = driverUserInfoService.queryDriverUserInfoMxById(driverUserInfoDomain.getId());
		SendMessagerLog sendMessagerLog = new SendMessagerLog();
		if (domain==null||StringUtils.isBlank(domain.getCode())) {
			sendResponseMessage("error");
			return SUCCESS;
		}
		if (StringUtils.isBlank(domain.getBaiduUserId())||StringUtils.isBlank(domain.getBaiduChannelId())) {
			sendResponseMessage("保存成功，但由于没有绑定推送失败");
			return SUCCESS;
		}
		//通过的推送固定内容，失败的推送不固定内容
		String description = "";
		String title = "";
		if ("3".equals(driverUserInfoDomain.getSubmitType())) {
			title = "【快到网】感谢您的认证！";
			sendMessagerLog.setSendType(0);
			description = "恭喜您已通过审核，成为认证用户啦！";
		} else {
			sendMessagerLog.setSendType(1);
			title = "【快到网】感谢您的认证！";
			if (3==driverUserInfoDomain.getReason().size()) {
				description = "您上传的信息都不符合要求，请检查";
			}else{
				description = "您上传的";
				for (String str : driverUserInfoDomain.getReason()) {
					description += java.net.URLDecoder.decode(str,"utf-8");
				}
				description += "不符合要求，请检查";
			}
		}
		boolean sucessFlag = BaiduPushChannelClient.pushUnicastNotification(Long.valueOf(domain.getBaiduChannelId()),
				domain.getBaiduUserId(), title,description );
		if (sucessFlag) {
			sendMessagerLog.setSendState(0);
		}else {
			sendMessagerLog.setSendState(1);
			sendResponseMessage("保存成功，但推送失败");
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
