package com.cy.dctms.function.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.FunctionInfoDomain;
import com.cy.dctms.function.service.IFunctionInfoService;

public class GainFunctionInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private FunctionInfoDomain functionInfoDomain;
	private IFunctionInfoService functionInfoService;
	/**
	 * 获取功能菜单
	 * */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("web user info login begin...");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		functionInfoDomain = new FunctionInfoDomain();
		functionInfoDomain = functionInfoService.queryFunciontInfo(functionInfoDomain,getSessionUser().getId());
		return SUCCESS;
	}
	public FunctionInfoDomain getFunctionInfoDomain() {
		return functionInfoDomain;
	}
	public void setFunctionInfoDomain(FunctionInfoDomain functionInfoDomain) {
		this.functionInfoDomain = functionInfoDomain;
	}
	public void setFunctionInfoService(IFunctionInfoService functionInfoService) {
		this.functionInfoService = functionInfoService;
	}

}
