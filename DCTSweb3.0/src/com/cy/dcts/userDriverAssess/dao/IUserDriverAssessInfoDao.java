package com.cy.dcts.userDriverAssess.dao;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.UserDriverAssessInfo;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;

public interface IUserDriverAssessInfoDao {
	/**
	 *添加货主对司机的评价
	 */
    String addUserDriverAssessInfo(UserDriverAssessInfo userDriverAssessInfo);
    
    
    /**
	 * 查询货主对司机的评价列表分页
	 * driverId
	 * @author nxj
	 * @param userDriverAssessInfo
	 * @return
	 */
	List<UserDriverAssessInfoDomain> queryUserDriverAssessInfoDomainPage(Map<String,Object> queryMap);
	
	
	 /**
	 * 查询货主对司机的评价列表 总数量
	 * driverId
	 * @author nxj
	 * @return
	 */
	Integer queryUserDriverAssessInfoDomainPageCount(Map<String,Object> queryMap);
	/**
	 * 根据评价状态查询评级级别和
	 * 3好评
	 * 6中评
	 * 9差评
	 * @author nxj
	 * @param tradeEvaluateScore
	 * @return
	 */
	Integer queryUserDriverAssessInfoDomainCount(Map<String, Object> queryMap);
    
    /**
     * 根据交易Id查询货主对司机评价
     *  @param transactionId 交易Id
     *  return Domain
     */
   
    UserDriverAssessInfoDomain qyeryUserDriverAssesByTradeId(String transactionId);
    
    /**
     *  根据交易Id查询货主对司机评价
     * @param transactionId 交易Id
     * @return bo
     */
    UserDriverAssessInfo qyeryUserDriverAssesInfoByTradeId(String transactionId);
    
}

