package com.cy.dcts.transactionLast.service.imp;

import java.util.HashMap;
import java.util.Map;

import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.TransactionLastInfo;
import com.cy.dcts.transactionLast.dao.ITransactionLastDao;
import com.cy.dcts.transactionLast.service.ITransactionLastService;

public class TransactionLastServiceImp implements ITransactionLastService{
    private ITransactionLastDao transactionLastDao;
	public String addTransactionLastInfo(TransactionInfo transactionInfo) {
		Map<String, Object> addMap=new HashMap<String, Object>();
		if(transactionInfo!=null){
			addMap.put("cargoId", transactionInfo.getCargoId());
			addMap.put("driverId", transactionInfo.getDriverId());
			addMap.put("transactionId", transactionInfo.getId());
			addMap.put("start", transactionInfo.getTradeStart());
			addMap.put("remark", transactionInfo.getRemark());
		}
		return transactionLastDao.addTransactionLastInfo(addMap);
	}
	public TransactionLastInfo queryTransactionLastByIdAndStart(
			String transactionId, String start) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("transactionId", transactionId);
		queryMap.put("start", start);
		return transactionLastDao.queryTransactionLastByIdAndStart(queryMap);
	}
	public ITransactionLastDao getTransactionLastDao() {
		return transactionLastDao;
	}
	public void setTransactionLastDao(ITransactionLastDao transactionLastDao) {
		this.transactionLastDao = transactionLastDao;
	}
	
}
