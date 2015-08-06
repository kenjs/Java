package com.cy.dcts.mainIndex.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.transaction.service.ITransactionInfoService;

public class TrandeInfoCountAction extends BaseJsonAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	
	private Integer waitingDriverTrade;//等待司机确认
	private Integer inTransitTrades;//运输跟踪
	private Integer driverDisburdenTrades;//司机已卸货
	private Integer waitingReceivingTrade;//待确认收货数（运输跟踪+司机已卸货)
	private Integer successNoAssessTrade;////待评价数（订单完成且未评价）
	
	
	@Override
	protected void execMethod() throws Exception {
		logger.debug("query trande Info count begin");
		//自己货源不同状态下的订单数
		TransactionInfoDomain trandeDomain= transactionInfoService.queryAllStartCount(getSessionUser().getId(), Constants.TRADE_START_SUCCESS_KEY);
		if(StringUtils.isEmpty(trandeDomain.getWaitingDriverTrade())){
			waitingDriverTrade=0;
		}else{
			waitingDriverTrade=Integer.decode(trandeDomain.getWaitingDriverTrade());
		}
		if(StringUtils.isEmpty(trandeDomain.getInTransitTrade())){
			inTransitTrades=0;
		}else{
			inTransitTrades=Integer.decode(trandeDomain.getInTransitTrade());
		}
		if(StringUtils.isEmpty(trandeDomain.getDriverDisburdenTrade())){
			driverDisburdenTrades=0;
		}else{
			driverDisburdenTrades=Integer.decode(trandeDomain.getDriverDisburdenTrade());
		}
		
		waitingReceivingTrade=inTransitTrades+driverDisburdenTrades;
		if(StringUtils.isEmpty(trandeDomain.getSuccessNoAssessTrade())){
			successNoAssessTrade=0;
		}else{
			successNoAssessTrade=Integer.decode(trandeDomain.getSuccessNoAssessTrade());
		}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("waitingDriverTrade", waitingDriverTrade);
		queryMap.put("waitingReceivingTrade", waitingReceivingTrade);
		queryMap.put("successNoAssessTrade", successNoAssessTrade);
		
		String result = this.sendResponseToJson("1","统计交易提醒数据成功!",queryMap);
		logger.warn("query trande Info count success. json=[{}]",new Object[] { result });
		
	}
	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}
	public Integer getWaitingDriverTrade() {
		return waitingDriverTrade;
	}
	public void setWaitingDriverTrade(Integer waitingDriverTrade) {
		this.waitingDriverTrade = waitingDriverTrade;
	}
	public Integer getInTransitTrades() {
		return inTransitTrades;
	}
	public void setInTransitTrades(Integer inTransitTrades) {
		this.inTransitTrades = inTransitTrades;
	}
	public Integer getDriverDisburdenTrades() {
		return driverDisburdenTrades;
	}
	public void setDriverDisburdenTrades(Integer driverDisburdenTrades) {
		this.driverDisburdenTrades = driverDisburdenTrades;
	}
	public Integer getWaitingReceivingTrade() {
		return waitingReceivingTrade;
	}
	public void setWaitingReceivingTrade(Integer waitingReceivingTrade) {
		this.waitingReceivingTrade = waitingReceivingTrade;
	}
	public Integer getSuccessNoAssessTrade() {
		return successNoAssessTrade;
	}
	public void setSuccessNoAssessTrade(Integer successNoAssessTrade) {
		this.successNoAssessTrade = successNoAssessTrade;
	}

}
