package com.cy.dcts.transaction.action;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;
import com.cy.dcts.orderCargoLast.service.IOrderCargoLastService;
import com.cy.dcts.transaction.service.ITransactionInfoService;

/**
 * 添加订单记录
 * @author Administrator
 *
 */
public class AddTransactionInfoAction extends BaseJsonAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	private IOrderCargoLastService orderCargoLastService;
	private ISaveOrderCargoInfoService saveOrderCargoInfoService;
	private TransactionInfo transactionInfo;
	private OrderCargoInfo orderCargoInfo;
	@Override
	protected void execMethod() throws Exception {
		//判断是否登陆
				if (getSessionUser() == null) {
					this.sendResponseToJson("1", "请先登录");
					return ;
				}
				logger.debug(
						"add transaction info begin! userId=[{}], companyId=[{}]",
						new Object[] { getSessionUser().getId(), getSessionUser().getCompanyId()});
				//参数验证
				if(transactionInfo==null||
						StringUtils.isEmpty(transactionInfo.getCargoId())||
						StringUtils.isEmpty(transactionInfo.getDriverId())){
					logger.debug("add transaction info parameter error!");
					this.sendResponseToJson("2", "参数错误");
					return;
				}
				
				//添加交易记录
				String tradeId=transactionInfoService.addTransactionInfo(transactionInfo, getSessionUser());
				//修改货源状态
				saveOrderCargoInfoService.modifyOrderCargoFlag(transactionInfo.getCargoId(), this.getSessionUser().getId(), orderCargoInfo.getCargoFlag());
				logger.debug("update order cargo flag success ! CargoFlag=[{}]",orderCargoInfo.getCargoFlag());
				//添加货源历史记录
				String cargoLastId=orderCargoLastService.addOrderCargoLastInfo(transactionInfo, orderCargoInfo.getCargoFlag());
				logger.debug("isnert order cargo last info success ! cargoLastId=[{}]",cargoLastId);
				logger.debug(
						"add transaction info success! userId=[{}], companyId=[{}], cargoId=[{}]",
						new Object[] { getSessionUser().getId(), getSessionUser().getCompanyId(), tradeId });
				this.sendResponseToJson("0", "success");
		
	}
	public OrderCargoInfo getOrderCargoInfo() {
		return orderCargoInfo;
	}
	public void setOrderCargoInfo(OrderCargoInfo orderCargoInfo) {
		this.orderCargoInfo = orderCargoInfo;
	}
	public TransactionInfo getTransactionInfo() {
		return transactionInfo;
	}
	public void setTransactionInfo(TransactionInfo transactionInfo) {
		this.transactionInfo = transactionInfo;
	}

	public void setOrderCargoLastService(
			IOrderCargoLastService orderCargoLastService) {
		this.orderCargoLastService = orderCargoLastService;
	}
	public void setSaveOrderCargoInfoService(
			ISaveOrderCargoInfoService saveOrderCargoInfoService) {
		this.saveOrderCargoInfoService = saveOrderCargoInfoService;
	}
	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}

}
