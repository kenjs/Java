package com.cy.dcts.transaction.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.transaction.service.ITransactionInfoService;
/**
 *打开修改 导入订单的信息  根据Id查询导入订单的信息 返回domain  (货主版) 
 * @author zdy
 *
 */
public class OpenModifyImportTransactionInfoAction extends BasePageAction{
	private static final long serialVersionUID = -6851976278436983840L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	private TransactionInfoDomain transactionInfoDomain;
	@Override
	protected String execMethod() throws Exception {
//		if(getSessionUser()==null){
//			return LOGIN;
//		}
//		logger.debug("query import transaction info by id begin. userId=[{}], companyId=[{}]",
//				getSessionUser().getId(), getSessionUser().getCompanyId());
		//参数验证
		if(transactionInfoDomain==null||StringUtils.isEmpty(transactionInfoDomain.getId())){
			logger.warn("query transaction info  parameter error");
			return ERROR;
		}
		//根据Id查询导入订单的信息 返回domain  (货主版) 
		transactionInfoDomain=transactionInfoService.queryImportTransactionInfoById(transactionInfoDomain.getId());
		logger.debug("query import transaction info by id success! ");
		return SUCCESS;
	}
	public ITransactionInfoService getTransactionInfoService() {
		return transactionInfoService;
	}
	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}
	public TransactionInfoDomain getTransactionInfoDomain() {
		return transactionInfoDomain;
	}
	public void setTransactionInfoDomain(TransactionInfoDomain transactionInfoDomain) {
		this.transactionInfoDomain = transactionInfoDomain;
	}

}
