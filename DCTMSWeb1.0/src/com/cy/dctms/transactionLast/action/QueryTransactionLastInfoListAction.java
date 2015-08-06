package com.cy.dctms.transactionLast.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.TransactionLastInfoDomain;
import com.cy.dctms.transactionLast.service.ITransactionLastInfoService;

public class QueryTransactionLastInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionLastInfoService transactionLastInfoService;
	private TransactionLastInfoDomain transactionLastInfoDomain;

	/** 查询订单历史状态信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query transactionLastInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (transactionLastInfoDomain==null) {
			transactionLastInfoDomain = new TransactionLastInfoDomain();
		}
		if (transactionLastInfoDomain.getPageInfo()==null) {
			transactionLastInfoDomain.setPageInfo(new PageInfo());
		}
		transactionLastInfoService.queryTransactionLastInfoList(transactionLastInfoDomain);
		return SUCCESS;
	}

	public void setTransactionLastInfoService(ITransactionLastInfoService transactionLastInfoService) {
		this.transactionLastInfoService = transactionLastInfoService;
	}
	public TransactionLastInfoDomain getTransactionLastInfoDomain() {
		return transactionLastInfoDomain;
	}

	public void setTransactionLastInfoDomain(TransactionLastInfoDomain transactionLastInfoDomain) {
		this.transactionLastInfoDomain = transactionLastInfoDomain;
	}


}
