package com.cy.dcts.transaction.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.transaction.service.ITransactionInfoService;
/**
 *  修改订单删除状态（订单是否有效）
 * @author zdy
 *
 */
public class ModifyTransactionInfoOrderStartAction extends BaseJsonAction{
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
		logger.debug("modify transaction info order flag begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		if(transactionInfoDomain==null||StringUtils.isEmpty(transactionInfoDomain.getId())){
			this.sendResponseToJson("2", "参数错误");
			return;
		}
		transactionInfoDomain.setOrderStart(String.valueOf(Constants.DELETED_FLAG_TRUE));
		//修改货源的删除状态
		transactionInfoService.modifyTransactionInfoOrderStart(transactionInfoDomain);
		logger.debug("modify transaction info order flag success !");
		this.sendResponseToJson("0", "success");
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
