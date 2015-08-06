package com.cy.dctms.transaction.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.TransactionInfoDomain;
import com.cy.dctms.transaction.service.ITransactionInfoService;

public class QueryTransactionInfoMxAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	private TransactionInfoDomain transactionInfoDomain;

	/** 查询交易信息信息明细
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query transactionInfo Mx start");
		if(getSessionUser()==null){
			return "loginMx";
		}
		if(!transactionInfoDomain.getId().equals("0")){
			transactionInfoDomain = transactionInfoService.queryTransactionInfoMxById(transactionInfoDomain.getId());
		}else {
			transactionInfoDomain = new TransactionInfoDomain();
			transactionInfoDomain.setId("0");
		}
		return SUCCESS;
	}

	public void setTransactionInfoService(ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}
	public TransactionInfoDomain getTransactionInfoDomain() {
		return transactionInfoDomain;
	}

	public void setTransactionInfoDomain(TransactionInfoDomain transactionInfoDomain) {
		this.transactionInfoDomain = transactionInfoDomain;
	}


}
