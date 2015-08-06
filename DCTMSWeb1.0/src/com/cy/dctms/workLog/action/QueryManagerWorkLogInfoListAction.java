package com.cy.dctms.workLog.action;

import java.util.List;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;
import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.workLog.service.IManagerWorkLogInfoService;

public class QueryManagerWorkLogInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;

	private IManagerWorkLogInfoService managerWorkLogInfoService;
	private ManagerWorkLogInfoDomain managerWorkLogInfoDomain;

	/** 查询管理员操作日志信息列表
	 * @author:wjl
 	 */
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (managerWorkLogInfoDomain==null) {
			managerWorkLogInfoDomain = new ManagerWorkLogInfoDomain();
		}
		if (managerWorkLogInfoDomain.getPageInfo()==null) {
			managerWorkLogInfoDomain.setPageInfo(new PageInfo());
		}
		List<ManagerWorkLogInfoDomain> dataList = managerWorkLogInfoService.queryManagerWorkLogInfoList(managerWorkLogInfoDomain);
		managerWorkLogInfoDomain.setDataList(dataList);
		return SUCCESS;
	}
	public void setManagerWorkLogInfoService(IManagerWorkLogInfoService managerWorkLogInfoService) {
		this.managerWorkLogInfoService = managerWorkLogInfoService;
	}
	public ManagerWorkLogInfoDomain getManagerWorkLogInfoDomain() {
		return managerWorkLogInfoDomain;
	}
	public void setManagerWorkLogInfoDomain(ManagerWorkLogInfoDomain managerWorkLogInfoDomain) {
		this.managerWorkLogInfoDomain = managerWorkLogInfoDomain;
	}

}
