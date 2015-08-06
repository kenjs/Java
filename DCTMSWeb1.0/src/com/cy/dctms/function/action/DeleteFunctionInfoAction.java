package com.cy.dctms.function.action;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.FunctionInfoDomain;
import com.cy.dctms.function.service.IFunctionInfoService;

public class DeleteFunctionInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;

	private IFunctionInfoService functionInfoService;
	private FunctionInfoDomain functionInfoDomain;

	/** 删除系统功能信息
	 * @author:wjl
 	 */
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		functionInfoService.deleteFunctionInfo(functionInfoDomain.getId());
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
