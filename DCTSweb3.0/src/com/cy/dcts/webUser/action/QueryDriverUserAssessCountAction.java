package com.cy.dcts.webUser.action;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.DriverUserAssessInfoDomain;
import com.cy.dcts.webUser.service.IEvaluationManagementService;
/**
 * 获取物流公司评价数量
 * @author nxj
 *
 */
class QueryDriverUserAssessCountAction extends BaseJsonAction {
	
	private static final long serialVersionUID = -8685840125576735773L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IEvaluationManagementService evaluationManagementService;
	@Override
	protected void execMethod() throws Exception {
		try {
			//判断是否登陆
			if(this.getSessionUser()==null){ 
				this.sendResponseToJson("1", "请先登录");
				return;
			}
			DriverUserAssessInfoDomain driverUserAsDom = evaluationManagementService.queryDriverUserAssessCountByAssessScore(getSessionUser().getId());
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("cargoName", driverUserAsDom.getCargoName());
			queryMap.put("satisfactory", driverUserAsDom.getSatisfactory());
			queryMap.put("arial", driverUserAsDom.getArial());
			queryMap.put("noSatisfactory", driverUserAsDom.getNoSatisfactory());
			String result = this.sendResponseToJson("0","查询评价数量成功!",queryMap);
			logger.warn("query driver user assess count success. json=[{}]",new Object[] { result });
		}catch(Exception e) {
			logger.error("query driver user assess count error!");
			throw new RuntimeException();
		}
	}
	public void setEvaluationManagementService(
			IEvaluationManagementService evaluationManagementService) {
		this.evaluationManagementService = evaluationManagementService;
	}
}
