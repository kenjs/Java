package com.cy.dctms.transactionLast.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dctms.common.domain.TransactionLastInfoDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.transactionLast.dao.ITransactionLastInfoDao;
import com.cy.dctms.transactionLast.service.ITransactionLastInfoService;

public class TransactionLastInfoServiceImp implements ITransactionLastInfoService {

	private ITransactionLastInfoDao transactionLastInfoDao;

	@Override
	public void queryTransactionLastInfoList(TransactionLastInfoDomain transactionLastInfoDomain) {
		transactionLastInfoDao.queryTransactionLastInfoList(transactionLastInfoDomain);
	}

	@Override
	public void exportTransactionLastInfo(TransactionLastInfoDomain transactionLastInfoDomain) {
		 transactionLastInfoDao.exportTransactionLastInfo(transactionLastInfoDomain);
	}

	public void setTransactionLastInfoDao(ITransactionLastInfoDao transactionLastInfoDao) {
		this.transactionLastInfoDao = transactionLastInfoDao;
	}
}
