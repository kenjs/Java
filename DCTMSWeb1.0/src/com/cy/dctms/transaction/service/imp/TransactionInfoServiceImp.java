package com.cy.dctms.transaction.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dctms.common.domain.TransactionInfoDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.transaction.dao.ITransactionInfoDao;
import com.cy.dctms.transaction.service.ITransactionInfoService;

public class TransactionInfoServiceImp implements ITransactionInfoService {

	private ITransactionInfoDao transactionInfoDao;

	@Override
	public void queryTransactionInfoList(TransactionInfoDomain transactionInfoDomain) {
		transactionInfoDao.queryTransactionInfoList(transactionInfoDomain);
	}

	@Override
	public void exportTransactionInfo(TransactionInfoDomain transactionInfoDomain) {
		 transactionInfoDao.exportTransactionInfo(transactionInfoDomain);
	}

	@Override
	public TransactionInfoDomain queryTransactionInfoMxById(String id) {
		 return transactionInfoDao.queryTransactionInfoById(id);
	}

	@Override
	public void saveTransactionInfo(TransactionInfoDomain transactionInfoDomain ,String userId) {		
		transactionInfoDao.saveTransactionInfo(transactionInfoDomain,userId);
	}

	@Override
	public void deleteTransactionInfo(String id,String userId) {
		transactionInfoDao.deleteTransactionInfo(id,userId);
		
	}

	public void setTransactionInfoDao(ITransactionInfoDao transactionInfoDao) {
		this.transactionInfoDao = transactionInfoDao;
	}
}
