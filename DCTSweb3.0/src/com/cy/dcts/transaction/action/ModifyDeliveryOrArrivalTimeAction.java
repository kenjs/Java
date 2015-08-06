package com.cy.dcts.transaction.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.transaction.service.ITransactionInfoService;

public class ModifyDeliveryOrArrivalTimeAction extends BaseJsonAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	private TransactionInfoDomain transactionInfoDomain;
	@Override
	protected void execMethod() throws Exception {
		//验证是否登录
		if(getSessionUser()==null){
			this.sendResponseToJson("1", "请先登录");
			return ;
		}
		String sessionUserId=getSessionUser().getId();
		logger.debug("modify transaction delivery or arrival time  begin. userId=[{}], companyId=[{}]",
				sessionUserId, getSessionUser().getCompanyId());
		if(StringUtils.isEmpty(transactionInfoDomain.getDeliveryTime())&&StringUtils.isEmpty(transactionInfoDomain.getArrivalTime())){
			logger.debug("modify transaction delivery or arrival time parameter error!");
			this.sendResponseToJson("2", "参数错误");
			return;
		}
		transactionInfoService.modifyDeliveryOrArrivalTime(transactionInfoDomain);
		logger.debug("modify transaction delivery or arrival time  success! userId=[{}]",
				sessionUserId);
		this.sendResponseToJson("0", "success");
	}

	public TransactionInfoDomain getTransactionInfoDomain() {
		return transactionInfoDomain;
	}
	public void setTransactionInfoDomain(TransactionInfoDomain transactionInfoDomain) {
		this.transactionInfoDomain = transactionInfoDomain;
	}

	public ITransactionInfoService getTransactionInfoService() {
		return transactionInfoService;
	}

	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}

}
