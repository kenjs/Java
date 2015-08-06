package com.cy.driver.service;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.QuoteInfoBo;
import com.cy.driver.domain.QuoteInfoDomain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 所有报过价的订单列表service
 * @date 2014-6-6
 * @author haoyong
 *
 */
public interface QuoteInfoService {

	/**
	 * 所有报过价的订单列表
	 * @return
	 */
	public List<?> selectQuoteTransactionList(String driverId,String fromSize,String listSize);
	
	/**
	 * 根据订单状态查找订单
	 * @param 
	 * @return
	 */
	public List<?> selectTransactionListByStatus(String driverId,String tradeStart,String fromSize,String listSize);
	
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
	public List<?> selectAttentionCargoList(String driverId,String fromSize,String listSize);
	
	/**
	 * 待评价订单
	 * @param driverId
	 * @return
	 */
	public List<?> selectWaitEvaluationTransacList(String driverId,String fromSize,String listSize);
	
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

    /**
     * 查询导入的成功交易的订单
     * @param map
     * @return
     * @throws java.sql.SQLException
     */
    public List<?> queryImportSuccessTransactions(Map<String,Object> map) throws SQLException;

	/**
	 * 司机报价
	 * @param quoteInfoBo
	 * @return
	 * @throws SQLException
	 */
	public JSonResponse driverQuote(QuoteInfoBo quoteInfoBo) throws SQLException;
}
