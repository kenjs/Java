package com.cy.driver.dao;

import com.cy.driver.bo.QuoteInfoBo;
import com.cy.driver.domain.QuoteInfoDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 所有报过价的订单列表dao
 * @date 2014-6-6
 * @author haoyong
 *
 */
@Repository("quoteInfoDao")
public interface QuoteInfoDao {

    /**
     * 新增货源报价
     * @param quoteInfoBo
     * @return
     * @throws SQLException
     */
    public int addNewQuoteInfo(QuoteInfoBo quoteInfoBo) throws SQLException;

	/**
	 * 所有报过价的订单列表
	 * @return
	 */
	public List<?> selectQuoteTransactionList(Map<String, Object> map);
	
	/**
	 * 我关注的货源数目
	 * @param driverId
	 * @return
	 */
	public int selectAttentionCargoCount(String driverId);
	
	/**
	 * 我关注的货源集合
	 * @param map
	 * @return
	 */
	public List<?> selectAttentionCargoList(Map<String,Object> map);
	
	/**
	 * 待评价的订单
	 * @param map
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
     * 查询导入的成功交易的订单
     * @param map
     * @return
     * @throws SQLException
     */
    public List<?> queryImportSuccessTransactions(Map<String,Object> map) throws SQLException;
	
	/**
	 * 根据订单交易状态查询导入的订单
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<?> selectImportTransactionListByTradeStart(Map<String,Object> map) throws Exception;

    /**
     * 根据交易状态查询
     * @param map
     * @return
     * @throws SQLException
     */
    public List<?> selectTransactionListByStatus(Map<String ,Object> map);
}
