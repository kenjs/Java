package com.cy.dcts.userDriverAssess.action;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.userDriverAssess.service.IUserDriverAssessInfoService;

/**
 * 获取司机评价数量
 * @author nxj
 *
 */
public class QueryUserDrivrAssessCountAction extends BaseJsonAction {

	private static final long serialVersionUID = 1647281816918273756L;
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
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("assessThree", userDriverAssessInfoService.queryUserDriverAssessInfoDomainCount(driverId,"3"));
			queryMap.put("assessSix", userDriverAssessInfoService.queryUserDriverAssessInfoDomainCount(driverId,"6"));
			queryMap.put("assessNine", userDriverAssessInfoService.queryUserDriverAssessInfoDomainCount(driverId,"9"));
			String result = this.sendResponseToJson("0","查询评价数量成功!",queryMap);
			logger.warn("query user driver assess count success. json=[{}]",new Object[] { result });
		}catch (Exception e) {
			logger.error("query user driver assess count error!");
			throw new RuntimeException();
		}
	}

	public void setUserDriverAssessInfoService(
			IUserDriverAssessInfoService userDriverAssessInfoService) {
		this.userDriverAssessInfoService = userDriverAssessInfoService;
	}
}
