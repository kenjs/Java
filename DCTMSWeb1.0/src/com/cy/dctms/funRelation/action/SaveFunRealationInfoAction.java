package com.cy.dctms.funRelation.action;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.FunRealationInfoDomain;
import com.cy.dctms.funRelation.service.IFunRealationInfoService;

public class SaveFunRealationInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;

	private IFunRealationInfoService funRealationInfoService;
	private FunRealationInfoDomain funRealationInfoDomain;

	/** 保存赋权信息
	 * @author:wjl
 	 */
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			sendResponseMessage("loginMx");
			return SUCCESS;
		}
		funRealationInfoService.saveFunRealationInfo(funRealationInfoDomain,getSessionUser().getId());
		//清楚列表信息
		funRealationInfoDomain.setFunIdList(null);
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
