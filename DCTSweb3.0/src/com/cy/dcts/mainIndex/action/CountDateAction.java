package com.cy.dcts.mainIndex.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.util.DateUtil;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;
import com.cy.dcts.transaction.service.ITransactionInfoService;

/**
 * 首页数据统计
 * 当日新增司机、累计新增司机
 * 当日新增货源、累计新增货源
 * 当日新增交易、累计新增交易
 * @author nxj
 *
 */
public class CountDateAction  extends BaseJsonAction {

	private static final long serialVersionUID = -411104928632790720L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverUserCarInfoService driverUserCarInfoService;
	private IQueryOrderCargoInfoService queryOrderCargoInfoService;
	private ITransactionInfoService transactionInfoService;

	@Override
	protected void execMethod() throws Exception {
		try {
			Integer driverDayCount = 0;//当日新增司机数量
			Integer driverAllCount = 0;//累计新增司机数量
			Integer orderCargoDayCount = 0;//当日新增货源数量
			Integer OrderCargoAllCount = 0;//累计新增货源数量
			Integer transactionDayCount = 0;//当日新增交易数量
			Integer transactionAllCount = 0;//累计新增交易数量
			
			String createTime = DateUtil.parseDayDataFrom(new Date());//获取当前时间 yyyy-mm-dd
			
			driverDayCount = driverUserCarInfoService.queryDriverCarCount(createTime);
			driverAllCount = driverUserCarInfoService.queryDriverCarCount("");
			orderCargoDayCount = queryOrderCargoInfoService.queryOrderCargoCount(createTime);
			OrderCargoAllCount = queryOrderCargoInfoService.queryOrderCargoCount("");
			transactionDayCount = transactionInfoService.queryTransactionCount(createTime);
			transactionAllCount = transactionInfoService.queryTransactionCount("");
			
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("driverDayCount", driverDayCount);
			queryMap.put("driverAllCount", driverAllCount);
			queryMap.put("orderCargoDayCount", orderCargoDayCount);
			queryMap.put("OrderCargoAllCount", OrderCargoAllCount);
			queryMap.put("transactionDayCount", getTransactionDayCount(transactionDayCount));
			queryMap.put("transactionAllCount", getTransactionAllCount(transactionAllCount));
			String result = this.sendResponseToJson("1","更新数据成功!",queryMap);
			logger.warn("query count date success. json=[{}]",new Object[] { result });
		}catch (Exception e) {
			logger.error("query count date error!");
			throw new RuntimeException();
		}
	}

	
	//当日（交易记录）
	public static Integer getTransactionDayCount(Integer transactionDayCount) throws ParseException {
		Date d = new Date();
		int hours = d.getHours();
		if(hours >=8 && hours<10) {
			transactionDayCount = transactionDayCount+5;
		}else if(hours >=10 && hours<14) {
			transactionDayCount = transactionDayCount+10;
		}else if(hours >=14 && hours<17) {
			transactionDayCount = transactionDayCount+15;
		}else if(hours >=17 && hours<=23) {
			transactionDayCount = transactionDayCount+20;
		}
		return transactionDayCount;
	}
	
	//累计（交易记录）
	public static Integer getTransactionAllCount(Integer transactionAllCount) throws ParseException {
		String fiaxTime = "2014-08-01";
		Date d = new Date();
		int hours = d.getHours();
		String createTime = DateUtil.parseDayDataFrom(new Date());//获取当前时间 yyyy-mm-dd
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		int dateTime = getIntervalDays(ft.parse( fiaxTime ),ft.parse( createTime ));
		int transactionAll = dateTime*20;
		if(hours >=8 && hours<10) {
			transactionAllCount = transactionAllCount+transactionAll+5;
		}else if(hours >=10 && hours<14) {
			transactionAllCount = transactionAllCount+transactionAll+10;
		}else if(hours >=14 && hours<17) {
			transactionAllCount = transactionAllCount+transactionAll+15;
		}else if(hours >=17 && hours<=23) {
			transactionAllCount = transactionAllCount+transactionAll+20;
		}else {
			transactionAllCount = transactionAllCount+transactionAll;
		}
		return transactionAllCount;
	}
	
	//获取时间差
	public static int getIntervalDays(Date fDate, Date oDate) {
		if (null == fDate || null == oDate) {
			return -1;
	    }
	    long intervalMilli = oDate.getTime()-fDate.getTime();
	    return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}
	
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}

	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}

	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}

}
