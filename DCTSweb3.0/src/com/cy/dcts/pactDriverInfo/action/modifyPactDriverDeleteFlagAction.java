package com.cy.dcts.pactDriverInfo.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.PactDriverInfoDomain;
import com.cy.dcts.pactDriverInfo.service.IPactDriverInfoService;

public class modifyPactDriverDeleteFlagAction  extends BaseJsonAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IPactDriverInfoService pactDriverInfoService;
	private PactDriverInfoDomain pactDriverInfoDomain;
	
	@Override
	protected void execMethod() throws Exception {
		//判断是否登陆
		if (getSessionUser() == null) {
			this.sendResponseToJson("1", "请先登录");
			return ;
		}
		logger.debug("modify pact driver delete flag begin .userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		if(StringUtils.isEmpty(pactDriverInfoDomain.getId())){
			this.sendResponseToJson("2", "参数错误");
			return ;
		}
		//修改合同司机信息删除标志
		pactDriverInfoService.deletePactDriverInfo(pactDriverInfoDomain.getId());
		logger.debug("modify pact driver delete flag success!");
		this.sendResponseToJson("0", "success");
	}

	public IPactDriverInfoService getPactDriverInfoService() {
		return pactDriverInfoService;
	}

	public void setPactDriverInfoService(
			IPactDriverInfoService pactDriverInfoService) {
		this.pactDriverInfoService = pactDriverInfoService;
	}

	public PactDriverInfoDomain getPactDriverInfoDomain() {
		return pactDriverInfoDomain;
	}

	public void setPactDriverInfoDomain(PactDriverInfoDomain pactDriverInfoDomain) {
		this.pactDriverInfoDomain = pactDriverInfoDomain;
	}

}
