package com.cy.dctms.baiduPush.action;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.BaiduPushDomain;
import com.cy.dctms.common.domain.DriverUserInfoDomain;
import com.cy.dctms.common.util.BaiduPushChannelClient;
import com.cy.dctms.driverUser.service.IDriverUserInfoService;

public class BaiduPushAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserInfoService driverUserInfoService;
	private BaiduPushDomain baiduPushDomain;

	/** 审核司机信息
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("baidu push start");
		if(baiduPushDomain==null){
			baiduPushDomain = new BaiduPushDomain();
		}
		boolean sucessFlag = false;
		//推送,0为单播推送，1为广播推送
		if ("0".equals(baiduPushDomain.getUniOrBroadcastFlag())) {
			//获取userId和channelId
			DriverUserInfoDomain domain = new DriverUserInfoDomain();
			domain = driverUserInfoService.queryDriverUserInfoMxByCode(baiduPushDomain.getTelephone());
			if (domain==null||StringUtils.isBlank(domain.getCode())) {
				baiduPushDomain.setFalseFlag("1");
				return SUCCESS;
			}
			if (StringUtils.isBlank(domain.getBaiduUserId())||StringUtils.isBlank(domain.getBaiduChannelId())) {
				baiduPushDomain.setFalseFlag("2");
				return SUCCESS;
			}
			 sucessFlag =BaiduPushChannelClient.pushUnicastNotification(
					Long.valueOf(domain.getBaiduChannelId()), domain.getBaiduUserId(), 
					baiduPushDomain.getTitle(), baiduPushDomain.getDescription());
		}else if("1".endsWith(baiduPushDomain.getUniOrBroadcastFlag())){
			 sucessFlag = BaiduPushChannelClient.pushBroadcastNotification(
					baiduPushDomain.getTitle(), baiduPushDomain.getDescription());
		}
		if (sucessFlag) {
			baiduPushDomain.setFalseFlag("0");
		}else {
			baiduPushDomain.setFalseFlag("3");
		}
		return SUCCESS;
	}

	public void setDriverUserInfoService(IDriverUserInfoService driverUserInfoService) {
		this.driverUserInfoService = driverUserInfoService;
	}

	public BaiduPushDomain getBaiduPushDomain() {
		return baiduPushDomain;
	}

	public void setBaiduPushDomain(BaiduPushDomain baiduPushDomain) {
		this.baiduPushDomain = baiduPushDomain;
	}


}
