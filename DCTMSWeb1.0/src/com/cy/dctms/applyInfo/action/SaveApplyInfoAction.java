package com.cy.dctms.applyInfo.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.ApplyInfoDomain;
import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;
import com.cy.dctms.applyInfo.service.IApplyInfoService;
import com.cy.dctms.workLog.service.IManagerWorkLogInfoService;

public class SaveApplyInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IApplyInfoService applyInfoService;
	private ApplyInfoDomain applyInfoDomain;

	/** 保存身份证验证申请信息
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("save applyInfo start");
		if(getSessionUser()==null){
			sendResponseMessage("loginMx");
			return SUCCESS;
		}
		applyInfoService.saveApplyInfo(applyInfoDomain,getSessionUser().getId());
		return SUCCESS;
	}

	public void setApplyInfoService(IApplyInfoService applyInfoService) {
		this.applyInfoService = applyInfoService;
	}
	public ApplyInfoDomain getApplyInfoDomain() {
		return applyInfoDomain;
	}
	public void setApplyInfoDomain(ApplyInfoDomain applyInfoDomain) {
		this.applyInfoDomain = applyInfoDomain;
	}


}
