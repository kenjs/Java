package com.cy.dcts.webUser.service;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.domain.DriverUserAssessInfoDomain;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;

/**
 * 评价管理service
 * @author haoyong
 *
 */
public interface IEvaluationManagementService {
	/**
	 * 给他人的评价(货主给司机的评价)
	 * @param asses
	 * @param index
	 * @return
	 */
	public List<UserDriverAssessInfoDomain> queryUserDriverAssessList(UserDriverAssessInfoDomain userDriverAssessInfoDomain,String userId);
	
	/**
	 * 来自司机的评价（司机给货主的评价）
	 * @param asses
	 * @param index
	 * @return
	 */
	public List<DriverUserAssessInfoDomain> queryDriverUserAssessList(DriverUserAssessInfoDomain driverUserAssessInfoDomain,String userId);
	
	/**
	 * 根据货主Id（userId）查询司机对货主评价数(好评价，中评价，差评价)
	 * @param map
	 * @return
	 */
	public DriverUserAssessInfoDomain queryDriverUserAssessCountByAssessScore(String userId);
}
