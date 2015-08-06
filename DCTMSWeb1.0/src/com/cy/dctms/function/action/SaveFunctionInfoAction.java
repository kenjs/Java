package com.cy.dctms.function.action;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.FunctionInfoDomain;
import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;
import com.cy.dctms.function.service.IFunctionInfoService;
import com.cy.dctms.workLog.service.IManagerWorkLogInfoService;

public class SaveFunctionInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;

	private IFunctionInfoService functionInfoService;
	private FunctionInfoDomain functionInfoDomain;

	/** 保存系统功能信息
	 * @author:wjl
 	 */
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			sendResponseMessage("loginMx");
			return SUCCESS;
		}
		functionInfoService.saveFunctionInfo(functionInfoDomain);
		return SUCCESS;
	}

	public void setFunctionInfoService(IFunctionInfoService functionInfoService) {
		this.functionInfoService = functionInfoService;
	}
	public FunctionInfoDomain getFunctionInfoDomain() {
		return functionInfoDomain;
	}


	public void setFunctionInfoDomain(FunctionInfoDomain functionInfoDomain) {
		this.functionInfoDomain = functionInfoDomain;
	}

}
