package com.cy.dctms.manager.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.ManagerInfoDomain;
import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.manager.service.IManagerInfoService;

public class QueryManagerInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IManagerInfoService managerInfoService;
	private ManagerInfoDomain managerInfoDomain;
	
	

	/** 查询管理员信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query managerInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (managerInfoDomain==null) {
			managerInfoDomain = new ManagerInfoDomain();
		}
		if (managerInfoDomain.getPageInfo()==null) {
			managerInfoDomain.setPageInfo(new PageInfo());
		}
		managerInfoService.queryManagerInfoList(managerInfoDomain);
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
