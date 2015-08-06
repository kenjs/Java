package com.cy.dcts.transactionLast.dao.imp;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.TransactionLastInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.transactionLast.dao.ITransactionLastDao;

public class TransactionLastDaoImp extends BaseDao implements ITransactionLastDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public String addTransactionLastInfo(Map<String, Object> addMap) {
		
		try {
			return this.addObjectKeyString("isnert_transaction_last_info", addMap);
		} catch (Exception e) {
			logger.warn("isnert_transaction_last_info error", e);
			throw new RuntimeException();
		}
	}

	public TransactionLastInfo queryTransactionLastByIdAndStart(
			Map<String, Object> queryMap) {
		try {
			return (TransactionLastInfo)this.queryForObject("query_transaction_last_info_byIdAndStart", queryMap);
		} catch (Exception e) {
			logger.warn("query_transaction_last_info_byIdAndStart error", e);
			throw new RuntimeException();
		}
	}

}
