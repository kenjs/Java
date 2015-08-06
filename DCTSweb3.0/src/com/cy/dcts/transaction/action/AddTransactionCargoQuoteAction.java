package com.cy.dcts.transaction.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;
import com.cy.dcts.orderCargoLast.service.IOrderCargoLastService;
import com.cy.dcts.transaction.service.ITransactionInfoService;

/**
 * 车辆详细页订车
 * @author nxj
 *
 */
public class AddTransactionCargoQuoteAction  extends BaseJsonAction {

	private static final long serialVersionUID = -6851976278436983840L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private ITransactionInfoService transactionInfoService;
	private IOrderCargoLastService orderCargoLastService;
	private ISaveOrderCargoInfoService saveOrderCargoInfoService;
	
	
	@Override
	protected void execMethod() throws Exception {
		try{
			if (getSessionUser() == null) {
				this.sendResponseToJson("1", "请先登录");
				return ;
			}
			String driverId = this.request.getParameter("driverId");
			String orderCargoId = this.request.getParameter("orderCargoId");
			if(StringUtils.isEmpty(driverId) || StringUtils.isEmpty(orderCargoId) ) {
				this.sendResponseToJson("2", "参数错误");
				return;
			}
			TransactionInfo transactionInfo = new TransactionInfo();
			transactionInfo.setCargoId(orderCargoId);
			transactionInfo.setDriverId(driverId);
			
			//添加交易记录
			String tradeId=transactionInfoService.addTransactionInfo(transactionInfo, getSessionUser());
			//修改货源状态
			saveOrderCargoInfoService.modifyOrderCargoFlag(transactionInfo.getCargoId(), this.getSessionUser().getId(), Constants.CARGO_FLAG_TRADING_KEY);
			logger.debug("update order cargo flag success ! CargoFlag=[{}]",Constants.CARGO_FLAG_TRADING_KEY);
			//添加货源历史记录
			String cargoLastId=orderCargoLastService.addOrderCargoLastInfo(transactionInfo, Constants.CARGO_FLAG_TRADING_KEY);
			logger.debug("isnert order cargo last info success ! cargoLastId=[{}]",cargoLastId);
			logger.debug(
					"add transaction info success! userId=[{}], companyId=[{}], cargoId=[{}]",
					new Object[] { getSessionUser().getId(), getSessionUser().getCompanyId(), tradeId });
			String result = this.sendResponseToJson("0", "订车成功！");
			logger.warn("save transactionInfo success. json=[{}]",new Object[] { result });
		}catch(Exception e) {
			logger.error("save transactionInfo error!");
			throw new RuntimeException();
		}
	}


	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}


	public void setOrderCargoLastService(
			IOrderCargoLastService orderCargoLastService) {
		this.orderCargoLastService = orderCargoLastService;
	}


	public void setSaveOrderCargoInfoService(
			ISaveOrderCargoInfoService saveOrderCargoInfoService) {
		this.saveOrderCargoInfoService = saveOrderCargoInfoService;
	}
	
	

}
