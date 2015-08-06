package com.cy.driver.service.impl;

import com.cy.driver.bo.TransactionReceiptPathBo;
import com.cy.driver.dao.TransactionReceiptPathDao;
import com.cy.driver.domain.TransactionReceiptPathDomain;
import com.cy.driver.service.TransactionReceiptPathService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service("transactionReceiptPathService")
public class TransactionReceiptPathServiceimpl implements
		TransactionReceiptPathService {

	@Resource
	private TransactionReceiptPathDao transactionReceiptPathDao;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public int insertTransactionReceiptPath(TransactionReceiptPathBo bo)
			throws Exception {
		return transactionReceiptPathDao.insertTransactionReceiptPath(bo);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public int deleteTransactionReceiptPath(List<String> list) throws Exception {
		return transactionReceiptPathDao.deleteTransactionReceiptPath(list);
	}

	@Override
	public List<String> selectReceiptPathById(List<String> list)
			throws Exception {
		return transactionReceiptPathDao.selectReceiptPathById(list);
	}

	@Override
	public List<TransactionReceiptPathDomain> selectReceiptListByTransactionId(
			Map<String,String> map) throws Exception {
		return transactionReceiptPathDao.selectReceiptListByTransactionId(map);
	}	

	@Override
	public TransactionReceiptPathDomain selectReceiptById(String id)
			throws Exception {
		return transactionReceiptPathDao.selectReceiptById(id);
	}

	public void setTransactionReceiptPathDao(
			TransactionReceiptPathDao transactionReceiptPathDao) {
		this.transactionReceiptPathDao = transactionReceiptPathDao;
	}

}
