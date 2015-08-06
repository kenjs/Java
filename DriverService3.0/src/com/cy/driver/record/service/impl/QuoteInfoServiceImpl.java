package com.cy.driver.record.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.common.bo.QuoteInfoBo;
import com.cy.driver.record.dao.QuoteInfoDao;
import com.cy.driver.record.domain.QuoteInfoDomain;
import com.cy.driver.record.service.QuoteInfoService;
import com.cy.driver.transaction.domain.TransactionInfoDomain;
/**
 * 所有报过价的订单列表service impl
 * @date 2014-6-6
 * @author haoyong
 *
 */
public class QuoteInfoServiceImpl implements QuoteInfoService {

	private QuoteInfoDao quoteInfoDao;
	
	public void setQuoteInfoDao(QuoteInfoDao quoteInfoDao) {
		this.quoteInfoDao = quoteInfoDao;
	}

	public List<?> selectQuoteTransactionList(String driverId,String fromSize,String listSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("driverId", driverId);
		map.put("fromSize", fromSize);
		map.put("listSize",listSize);
		return quoteInfoDao.selectQuoteTransactionList(map);
	}

	@SuppressWarnings("unchecked")
	public List<TransactionInfoDomain> selectTransactionListByStatus(String driverId,String tradeStart,String fromSize,String listSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("driverId", driverId);
		map.put("tradeStart", tradeStart);
		map.put("fromSize", fromSize);
		map.put("listSize",listSize);
		return (List<TransactionInfoDomain>) quoteInfoDao.selectTransactionListByStatus(map);
	}

	public int addNewQuoteInfo(QuoteInfoBo bo) {
		return quoteInfoDao.addNewQuoteInfo(bo);
	}

	public int selectAttentionCargoCount(String driverId) {
		return quoteInfoDao.selectAttentionCargoCount(driverId);
	}

	public List<?> selectAttentionCargoList(String driverId,String fromSize,String listSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("driverId", driverId);
		map.put("fromSize", fromSize);
		map.put("listSize",listSize);
		return quoteInfoDao.selectAttentionCargoList(map);
	}

	public List<?> selectWaitEvaluationTransacList(String driverId,String fromSize, String listSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("driverId", driverId);
		map.put("fromSize", fromSize);
		map.put("listSize",listSize);
		return quoteInfoDao.selectWaitEvaluationTransactionList(map);
	}

	public QuoteInfoDomain selectQuoteInfoByDriverAndCargoId(
			Map<String, Object> map) {
		return quoteInfoDao.selectQuoteInfoByDriverAndCargoId(map);
	}

	public int updateQuoteInfo(QuoteInfoBo bo) {
		return quoteInfoDao.updateQuoteInfo(bo);
	}

	public List<?> selectDealedTransactionInMyLog(Map<String, Object> map) {
		return quoteInfoDao.selectDealedTransactionInMyLog(map);
	}

	@Override
	public List<?> selectImportDealedTransactionList(Map<String, Object> map)
			throws Exception {
		return quoteInfoDao.selectImportDealedTransactionList(map);
	}

	@Override
	public List<?> selectImportTransactionListByTradeStart(
			Map<String, Object> map) throws Exception {
		return quoteInfoDao.selectImportTransactionListByTradeStart(map);
	}

}
