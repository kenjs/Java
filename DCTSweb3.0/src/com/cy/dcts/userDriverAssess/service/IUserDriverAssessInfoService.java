package com.cy.dcts.userDriverAssess.service;

import java.util.List;

import com.cy.dcts.common.bo.UserDriverAssessInfo;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;

public interface IUserDriverAssessInfoService {
	/**
	 *添加货主对司机的评价
	 */
	String addUserDriverAssessInfo(UserDriverAssessInfo userDriverAssessInfo);
	
	/**
	 * 查询货主对司机的评价列表
	 * driverId
	 * @author nxj
	 * @param userDriverAssessInfo
	 * @return
	 */
	List<UserDriverAssessInfoDomain> queryUserDriverAssessInfoDomainPage(UserDriverAssessInfoDomain userDriverAssessInfoDomain);
	
	
	/**
	 * 根据评价状态查询评级级别和
	 * 3好评
	 * 6中评
	 * 9差评
	 * @author nxj
	 * @param tradeEvaluateScore
	 * @return
	 */
	Integer queryUserDriverAssessInfoDomainCount(String driverId, String tradeEvaluateScore);
	
	/**
     * 根据交易Id查询货主对司机评价
     *  @param transactionId 交易Id
     */
   
	UserDriverAssessInfoDomain queryUserDriverAssesByTradeId(String transactionId);
	
	 /**
     *  根据交易Id查询货主对司机评价
     * @param transactionId 交易Id
     * @return bo
     */
    UserDriverAssessInfo queryUserDriverAssesInfoByTradeId(String transactionId);
}
