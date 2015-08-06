package com.cy.dctms.manager.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.ManagerInfoDomain;

public class ManagerExitAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ManagerInfoDomain managerInfoDomain;

	/**
	 * ÓÃ»§ÍË³ö
	 * */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("web user info login begin...");
		putSessionUser(null);
		logger.info("web user info login success... ");
		return SUCCESS;
	}

	public ManagerInfoDomain getManagerInfoDomain() {
		return managerInfoDomain;
	}

	public void setManagerInfoDomain(ManagerInfoDomain managerInfoDomain) {
		this.managerInfoDomain = managerInfoDomain;
	}


}
