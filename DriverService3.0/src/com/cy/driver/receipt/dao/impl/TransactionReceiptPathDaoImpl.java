package com.cy.driver.receipt.dao.impl;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.TransactionReceiptPathBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.receipt.dao.TransactionReceiptPathDao;
import com.cy.driver.receipt.domain.TransactionReceiptPathDomain;

public class TransactionReceiptPathDaoImpl extends BaseDao implements TransactionReceiptPathDao {

	@Override
	public int insertTransactionReceiptPath(TransactionReceiptPathBo bo)
			throws Exception {
		return addObject("iBatisInsertTransactionReceiptPath", bo);
	}

	@Override
	public int deleteTransactionReceiptPath(List<String> list) throws Exception {
		return deleteObject("iBatisDeleteReceiptPpath", list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> selectReceiptPathById(List<String> receiptIdList)
			throws Exception {
		return (List<String>) queryForList("iBatisSelectReceiptPathById", receiptIdList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransactionReceiptPathDomain> selectReceiptListByTransactionId(Map<String,String> map)
			throws Exception {
		return (List<TransactionReceiptPathDomain>) queryForList("iBatisSelectReceiptListByTransactionId", map);
	}

	@Override
	public TransactionReceiptPathDomain selectReceiptById(String id)
			throws Exception {		
		return (TransactionReceiptPathDomain) queryForObject("iBatisSelectById", id);
	}
	
}
