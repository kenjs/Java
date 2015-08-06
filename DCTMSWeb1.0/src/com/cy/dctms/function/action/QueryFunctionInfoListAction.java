package com.cy.dctms.function.action;

import java.util.List;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.FunctionInfoDomain;
import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.function.service.IFunctionInfoService;

public class QueryFunctionInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;

	private IFunctionInfoService functionInfoService;
	private FunctionInfoDomain functionInfoDomain;

	/** 查询系统功能信息列表
	 * @author:wjl
 	 */
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (functionInfoDomain==null) {
			functionInfoDomain = new FunctionInfoDomain();
			functionInfoDomain.setPageInfo(new PageInfo());
		}
		List<FunctionInfoDomain> dataList = functionInfoService.queryFunctionInfoList(functionInfoDomain);
		functionInfoDomain.setDataList(dataList);
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
