package com.cy.dctms.transaction.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.TransactionInfoDomain;
import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;
import com.cy.dctms.transaction.service.ITransactionInfoService;
import com.cy.dctms.workLog.service.IManagerWorkLogInfoService;

public class DeleteTransactionInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	private TransactionInfoDomain transactionInfoDomain;
	
	/** 删除交易信息信息
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("delete transactionInfo start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		transactionInfoService.deleteTransactionInfo(transactionInfoDomain.getId(),getSessionUser().getId());
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
