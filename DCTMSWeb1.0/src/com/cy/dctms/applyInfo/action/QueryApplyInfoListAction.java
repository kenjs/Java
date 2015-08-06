package com.cy.dctms.applyInfo.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.ApplyInfoDomain;
import com.cy.dctms.applyInfo.service.IApplyInfoService;

public class QueryApplyInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IApplyInfoService applyInfoService;
	private ApplyInfoDomain applyInfoDomain;

	/** 查询身份证验证申请信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query applyInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (applyInfoDomain==null) {
			applyInfoDomain = new ApplyInfoDomain();
		}
		if (applyInfoDomain.getPageInfo()==null) {
			applyInfoDomain.setPageInfo(new PageInfo());
		}
		applyInfoService.queryApplyInfoList(applyInfoDomain);
		return SUCCESS;
	}

	public void setApplyInfoService(IApplyInfoService applyInfoService) {
		this.applyInfoService = applyInfoService;
	}
	public ApplyInfoDomain getApplyInfoDomain() {
		return applyInfoDomain;
	}

	public void setApplyInfoDomain(ApplyInfoDomain applyInfoDomain) {
		this.applyInfoDomain = applyInfoDomain;
	}


}
