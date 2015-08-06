package com.cy.dctms.transaction.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.TransactionInfoDomain;
import com.cy.dctms.transaction.service.ITransactionInfoService;

public class QueryTransactionInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	private TransactionInfoDomain transactionInfoDomain;

	/** 查询交易信息信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query transactionInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (transactionInfoDomain==null) {
			transactionInfoDomain = new TransactionInfoDomain();
		}
		if (transactionInfoDomain.getPageInfo()==null) {
			transactionInfoDomain.setPageInfo(new PageInfo());
		}
		transactionInfoService.queryTransactionInfoList(transactionInfoDomain);
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
