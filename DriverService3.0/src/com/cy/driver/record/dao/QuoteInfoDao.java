package com.cy.driver.record.dao;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.QuoteInfoBo;
import com.cy.driver.record.domain.QuoteInfoDomain;

/**
 * 所有报过价的订单列表dao
 * @date 2014-6-6
 * @author haoyong
 *
 */
public interface QuoteInfoDao {

	/**
	 * 所有报过价的订单列表
	 * @return
	 */
	public List<?> selectQuoteTransactionList(Map<String, Object> map);
	
	/**
	 * 根据订单状态查找订单
	 * @param map
	 * @return
	 */
	public List<?> selectTransactionListByStatus(Map<String,Object> map);
	
	/**
	 * 新增货源报价
	 * @param bo
	 * @return
	 */
	public int addNewQuoteInfo(QuoteInfoBo bo);
	
	/**
	 * 我关注的货源数目
	 * @param driverId
	 * @return
	 */
	public int selectAttentionCargoCount(String driverId);
	
	/**
	 * 我关注的货源集合
	 * @param driverId
	 * @return
	 */
	public List<?> selectAttentionCargoList(Map<String,Object> map);
	
	/**
	 * 待评价的订单
	 * @param driverId
	 * @return
	 */
	public List<?> selectWaitEvaluationTransactionList(Map<String,Object> map);
	
	/**
	 * 根据货源id和司机id查找报价信息 
	 * @param map
	 * @return
	 */
	public QuoteInfoDomain selectQuoteInfoByDriverAndCargoId(Map<String,Object> map);
	
	/**
	 * 修改货源报价
	 * @param bo
	 * @return
	 */
	public int updateQuoteInfo(QuoteInfoBo bo);
	
	/**
	 * 我的记录（已经成交的订单）
	 * @param map
	 * @return
	 */
	public List<?> selectDealedTransactionInMyLog(Map<String,Object> map);
	
	/**
	 * 查询导入的已经成交的订单
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<?> selectImportDealedTransactionList(Map<String,Object> map) throws Exception;
	
	/**
	 * 根据订单交易状态查询导入的订单
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<?> selectImportTransactionListByTradeStart(Map<String,Object> map) throws Exception;
}
