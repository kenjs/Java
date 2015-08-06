package com.cy.dcts.transaction.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.transaction.service.ITransactionInfoService;
/**
 * 查询首页的今日动态
 * @author Administrator
 *
 */
public class QueryTodayDynamicAction extends BaseJsonAction{
	private static final long serialVersionUID = -6851976278436983840L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	private IDriverUserCarInfoService driverUserCarInfoService;

	@Override
	protected void execMethod() throws Exception {
		try {
		logger.debug("query today dynamic info begin! ");
		List<TransactionInfoDomain> transactionList=new ArrayList<TransactionInfoDomain>();
		List<DriverUserInfoDomain> driverList=new ArrayList<DriverUserInfoDomain>();
		//查询今日交易
		transactionList=transactionInfoService.queryTodayDynamicTimeAndStart();
		//查询今日新增司机
		driverList=driverUserCarInfoService.queryTodayDynamicDriverCarByTime();
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("tradeList", transactionList);
		queryMap.put("driverList", driverList);
		String result = this.sendResponseToJson("1","查询今日动态成功!",queryMap);
		
		logger.debug("query today dynamic success!json=[{}]",new Object[] { result });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ITransactionInfoService getTransactionInfoService() {
		return transactionInfoService;
	}

	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}

	public IDriverUserCarInfoService getDriverUserCarInfoService() {
		return driverUserCarInfoService;
	}

	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
  
}
