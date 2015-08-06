package com.cy.dcts.userDriverAssess.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;
import com.cy.dcts.userDriverAssess.service.IUserDriverAssessInfoService;

/**
 * 根据driverId查询评价信息
 * @author nxj
 *
 */
public class QueryUserDriverAssessInfodmListAction extends BaseJsonAction {

	private static final long serialVersionUID = -3237287884831919555L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private IUserDriverAssessInfoService userDriverAssessInfoService;

	@Override
	protected void execMethod() throws Exception {
		try {
			if (getSessionUser() == null) {
				this.sendResponseToJson("1", "请先登录");
				return ;
			}
			String driverId = this.request.getParameter("driverId");
			String pageSize = this.request.getParameter("pageSize");
			String curPage = this.request.getParameter("curPage");
			UserDriverAssessInfoDomain userDriverAssessInfoDomain = new UserDriverAssessInfoDomain();
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage((Integer.parseInt(curPage)-1));
			pageInfo.setCurPageNo(Integer.parseInt(curPage));
			pageInfo.setPageSize(Integer.parseInt(pageSize));
			userDriverAssessInfoDomain.setPageInfo(pageInfo);
			userDriverAssessInfoDomain.setDriverId(driverId);
			List<UserDriverAssessInfoDomain> list = userDriverAssessInfoService.queryUserDriverAssessInfoDomainPage(userDriverAssessInfoDomain);
			userDriverAssessInfoDomain.setList(list);
			String result = this.sendResponseToJson("0","查询评价成功!",userDriverAssessInfoDomain);
			logger.warn("query user driver assess success. json=[{}]",new Object[] { result });
		}catch (Exception e) {
			logger.error("query user driver assess error!");
			throw new RuntimeException();
		}
	}

	public void setUserDriverAssessInfoService(
			IUserDriverAssessInfoService userDriverAssessInfoService) {
		this.userDriverAssessInfoService = userDriverAssessInfoService;
	}

}
