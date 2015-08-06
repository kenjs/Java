package com.cy.dctms.funRelation.action;

import java.util.List;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.FunRealationInfoDomain;
import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.funRelation.service.IFunRealationInfoService;

public class QueryFunRealationInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;

	private IFunRealationInfoService funRealationInfoService;
	private FunRealationInfoDomain funRealationInfoDomain;

	/** 查询赋权信息列表
	 * @author:wjl
 	 */
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			return "loginMx";
		}
		if (funRealationInfoDomain==null) {
			funRealationInfoDomain = new FunRealationInfoDomain();
		}
		List<FunRealationInfoDomain> dataList = funRealationInfoService.queryFunRealationInfoList(funRealationInfoDomain,getSessionUser().getId());
		funRealationInfoDomain.setDataList(dataList);
		return SUCCESS;
	}
	public void setFunRealationInfoService(IFunRealationInfoService funRealationInfoService) {
		this.funRealationInfoService = funRealationInfoService;
	}
	public FunRealationInfoDomain getFunRealationInfoDomain() {
		return funRealationInfoDomain;
	}
	public void setFunRealationInfoDomain(FunRealationInfoDomain funRealationInfoDomain) {
		this.funRealationInfoDomain = funRealationInfoDomain;
	}

}
