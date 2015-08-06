package com.cy.dctms.manager.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.ManagerInfoDomain;
import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;
import com.cy.dctms.manager.service.IManagerInfoService;
import com.cy.dctms.workLog.service.IManagerWorkLogInfoService;

public class SaveManagerInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IManagerInfoService managerInfoService;
	private ManagerInfoDomain managerInfoDomain;

	

	/** 保存管理员信息
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			sendResponseMessage("loginMx");
			return SUCCESS;
		}
		logger.debug("save managerInfo start");
		managerInfoService.saveManagerInfo(managerInfoDomain,getSessionUser().getId());
	//	sendResponseMessage("loginMx");
		return SUCCESS;
	}

	public void setManagerInfoService(IManagerInfoService managerInfoService) {
		this.managerInfoService = managerInfoService;
	}
	public ManagerInfoDomain getManagerInfoDomain() {
		return managerInfoDomain;
	}

	public void setManagerInfoDomain(ManagerInfoDomain managerInfoDomain) {
		this.managerInfoDomain = managerInfoDomain;
	}


}
