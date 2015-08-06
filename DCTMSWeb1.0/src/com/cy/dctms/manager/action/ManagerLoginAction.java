package com.cy.dctms.manager.action;



import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.ManagerInfoDomain;
import com.cy.dctms.manager.service.IManagerInfoService;

public class ManagerLoginAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IManagerInfoService managerInfoService;
	private ManagerInfoDomain managerInfoDomain;
	
	

	/**
	 * ÓÃ»§µÇÂ¼
	 * */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("web user info login begin...");
		if (managerInfoDomain == null // ||
				// StringUtils.isEmpty(managerInfoDomain.getSimpleEnCode())
				|| StringUtils.isEmpty(managerInfoDomain.getPassword())
				|| StringUtils.isEmpty(managerInfoDomain.getCode())) {
			return LOGIN;
		}
		managerInfoDomain.setErrorMessage("");
		managerInfoDomain = managerInfoService.userLogin(managerInfoDomain);
		if (StringUtils.isNotBlank(managerInfoDomain.getErrorMessage())) {
			return ERROR;
		}
		this.putSessionUser(managerInfoDomain);
		managerInfoDomain = new ManagerInfoDomain();
		logger.info(
				"web user info login success... userId=[{}], userCode=[{}], userName=[{}], simpleEnCode=[{}]",
				new Object[] { getSessionUser().getId(),
						getSessionUser().getCode(), getSessionUser().getName()});

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
