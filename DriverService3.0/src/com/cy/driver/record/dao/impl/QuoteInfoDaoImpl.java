package com.cy.driver.record.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cy.common.bo.QuoteInfoBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.cargo.domain.OrderCargoInfoDomain;
import com.cy.driver.record.dao.QuoteInfoDao;
import com.cy.driver.record.domain.QuoteInfoDomain;
import com.cy.driver.transaction.domain.TransactionInfoDomain;
/**
 * 所有报过价的订单列表dao impl
 * @date 2014-6-6
 * @author haoyong
 *
 */
public class QuoteInfoDaoImpl extends BaseDao implements QuoteInfoDao {

	@SuppressWarnings("unchecked")
	public List<?> selectQuoteTransactionList(Map<String, Object> map) {
		List<QuoteInfoDomain> list = null;
		try {
			list = (List<QuoteInfoDomain>) queryForList("iBatisSelectQuoteTransactionList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<?> selectTransactionListByStatus(Map<String, Object> map) {
		List<TransactionInfoDomain> list = null;
		try {
			list = (List<TransactionInfoDomain>) queryForList("iBatisSelectTransactionListByStat", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addNewQuoteInfo(QuoteInfoBo bo) {
		int i = 0;
		try {
			i = addObject("iBatisInsertQuoteInfo", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public int selectAttentionCargoCount(String driverId) {
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisQueryDriverCargoCollectInfoCount", driverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<?> selectAttentionCargoList(Map<String,Object> map) {
		List<OrderCargoInfoDomain> list = null;
		try {
			list = (List<OrderCargoInfoDomain>) queryForList("iBatisQueryDriverCargoCollectInfoList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<?> selectWaitEvaluationTransactionList(Map<String, Object> map) {
		List<OrderCargoInfoDomain> list = null;
		try {
			list = (List<OrderCargoInfoDomain>) queryForList("iBatisSelectWaitTransList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public QuoteInfoDomain selectQuoteInfoByDriverAndCargoId(Map<String, Object> map) {
		QuoteInfoDomain domain = null;
		try {
			domain = (QuoteInfoDomain) queryForObject("iBatisSelectQuoteInfoByDriverAndCargoId", map);
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
		return domain;
	}

	public int updateQuoteInfo(QuoteInfoBo bo) {
		int i = 0;
		try {
			i = saveObject("iBatisUpdateQuoteInfo", bo);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<?> selectDealedTransactionInMyLog(Map<String, Object> map) {
		List<OrderCargoInfoDomain> list = null;
		try {
			list = (List<OrderCargoInfoDomain>) queryForList("iBatisSelectTransactionListInMyLog", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> selectImportDealedTransactionList(Map<String, Object> map)
			throws Exception {
		
		return (List<OrderCargoInfoDomain>)queryForList("iBatisSelectImportTransactionListInMyLog", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> selectImportTransactionListByTradeStart(
			Map<String, Object> map) throws Exception {
		
		return (List<OrderCargoInfoDomain>)queryForList("iBatisSelectImportTransactionListByStat", map);
	}

}
