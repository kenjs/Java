package com.cy.dctms.function.action;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.FunctionInfoDomain;
import com.cy.dctms.function.service.IFunctionInfoService;

public class QueryFunctionInfoMxAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;

	private IFunctionInfoService functionInfoService;
	private FunctionInfoDomain functionInfoDomain;

	/** 查询系统功能信息列表
	 * @author:wjl
 	 */
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			return "loginMx";
		}
		if(!functionInfoDomain.getId().equals("0")){
			functionInfoDomain = functionInfoService.queryFunctionInfoMxById(functionInfoDomain.getId());
		}else {
			functionInfoDomain = new FunctionInfoDomain();
			functionInfoDomain.setId("0");
		}
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
