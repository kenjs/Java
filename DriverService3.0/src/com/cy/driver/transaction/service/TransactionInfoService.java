package com.cy.driver.transaction.service;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.TransactionLastInfoBo;
import com.cy.driver.transaction.domain.TransactionInfoDomain;
import com.cy.driver.webUser.domain.WebUserInfoDomain;

/**
 * 订单service
 * @date 2014-6-5
 * @author haoyong
 */
public interface TransactionInfoService {
	/**
	 * 司机订单条数
	 * @param driverId
	 * @return
	 */
	public int selectDriverOrderNumber(String driverId);
	
	/**
	 * 我的订单条数
	 * @param driverId
	 * @return
	 */
	public int selectMyOrderNumber(String driverId);
	
	/**
	 * 司机订单列表
	 * @param driverId
	 * @return
	 */
	public List<?> selectDriverOrderList(String driverId,String fromSize,String listSize);
	
	/**
	 * 订单详情
	 * @param driverId
	 * @param id
	 * @return
	 */
	public TransactionInfoDomain selectDriverOrderDetail(String id);
	
	
	/**
	 * 更改订单状态
	 * @param map
	 * @return
	 */
	public int updateTransactionInfoById(Map<String, Object> map);
	
	/**
	 * 已经成交的货源
	 * @param driverId
	 * @return
	 */
	public List<TransactionInfoDomain> selectDoneCargoList(Map<String,Object> map);
	
	/**
	 * 新增交易状态历史
	 * @param bo
	 * @return
	 */
	public int addTransactionLastInfo(TransactionLastInfoBo bo);
	
	/**
	 * 根据id查找订单状态
	 * @param id
	 * @return
	 */
	public Map<String,Long> selectTransactionStartById(String id);
	
	/**
	 * 根据 ‘发货单号’查询订单信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<TransactionInfoDomain> selectTransactionByShipperOrderNo(Map<String,String> map) 
			throws Exception;
	
	/**
	 * 查找物流公司id
	 * @param deployUserId 用户ID
	 * @param shipperCompanyCode 物流公司编码代码
	 * @return
	 * @throws Exception
	 */
	public WebUserInfoDomain selectShipperCompanyId(String deployUserId,String shipperCompanyCode) throws Exception;
	
}
