package com.cy.dcts.transaction.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.TransactionReceiptPath;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.common.util.PrimaryGenerater;
import com.cy.dcts.orderCargo.dao.IOrderCargoInfoDao;
import com.cy.dcts.transaction.dao.ITransactionInfoDao;
import com.cy.dcts.transaction.service.ITransactionInfoService;
import com.cy.dcts.webUser.dao.IWebUserInfoDao;

public class TransactionInfoServiceImp implements ITransactionInfoService{
	 private Logger logger=LoggerFactory.getLogger(this.getClass());
	
    private ITransactionInfoDao transactionInfoDao;
    private IWebUserInfoDao webUserInfoDao;
	
    private IOrderCargoInfoDao orderCargoInfoDao;
    
    public boolean modifyTrandeDriverAndOrderStart(
			TransactionInfoDomain transactionInfoDomain) {
		return transactionInfoDao.modifyTrandeDriverAndOrderStart(transactionInfoDomain);
	}
    
    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackForClassName="Exception")
    public List<String> batchAddTransactionInfos(List<TransactionInfo> list,List<OrderCargoInfo> cargoList,
			WebUserInfo userInfo) throws Exception {
    	List<String> tradeListId = new ArrayList<String>();
    	List <String> cargoListId=new ArrayList<String>();
    	for (int i = 0; i < list.size(); i++) {
    	  
    //---------新增货源信息---------------
    		OrderCargoInfo orderCargoInfo=cargoList.get(i);
    		orderCargoInfo.setCargoOrigin(Constants.CARGO_ORIGIN_IMPORT);
    		orderCargoInfo.setCargoFlag(Constants.CARGO_FLAG_PENDING_TRADE_KEY);//货源是待交易（导入的货源都是待交易？？）
    		orderCargoInfo.setCargoFlag(String.valueOf(Constants.CARGO_FLAG_PENDING_TRADE_KEY));//待交易
			orderCargoInfo.setCompanyId(userInfo.getCompanyId());
			orderCargoInfo.setDeployUserid(userInfo.getId());
			orderCargoInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));//未删除
			
			String cargoId = orderCargoInfoDao.addOrderCargoInfo(orderCargoInfo);
			cargoListId.add(cargoId);
			logger.debug("batch add order cargo success.....countie...........!  CARGOID=[{}]",
				new Object[]{cargoId});
    		
    //------------新增订单信息----------------------
			TransactionInfo transactionInfos=list.get(i);

    		//交易状态，货源Id
			transactionInfos.setCargoId(orderCargoInfo.getId());
			//交易状态
			if(StringUtils.isEmpty(transactionInfos.getDriverId())){//导入承运人的司机号为空，即订单中driverId为空
				transactionInfos.setTradeStart(Constants.TRADE_START_INVALID_KEY);//新增订单状态无效0
	    	  }else{//有承运人即driverId不为空
	    		  transactionInfos.setTradeStart(Constants.TRADE_START_WAITING_DRIVER_CONFIRM_KEY);//订单状态待司机确认1
	    	  }
			
    		//获取订单编号
    		String TradeOrderNumber=getTradeOrderNumber();
    		transactionInfos.setOrderNumber(TradeOrderNumber);
    		transactionInfos.setDeployUserid(userInfo.getId());
    		transactionInfos.setCompanyId(userInfo.getCompanyId());
    		transactionInfos.setOrderStart(String.valueOf(Constants.DELETED_FLAG_FALSE));
    		transactionInfos.setTradeCancelOrigin(Constants.TRADE_CANCEL_ORIGIN_DEFAULT_KEY);
			String id = transactionInfoDao.addTransactionInfos(transactionInfos);
    		logger.debug("batch add transaction infos success.  CARGOID=[{}]",
    				new Object[]{id});
    		tradeListId.add(id);
		}
    	logger.debug("batch add order cargo success!!!! cargoListId.size=[{}]",cargoListId.size());
		return tradeListId;
	}

	public TransactionInfoDomain queryImportTransactionInfoById(String id) {
		return transactionInfoDao.queryImportTransactionInfoById(id);
	}
	public boolean modifyImportTransactionInfo(
			TransactionInfoDomain transactionInfoDomain) {
		return transactionInfoDao.modifyImportTransactionInfo(transactionInfoDomain);
	}

    public boolean modifyDeliveryOrArrivalTime(
			TransactionInfoDomain transactionInfoDomain) {
		return transactionInfoDao.modifyDeliveryOrArrivalTime(transactionInfoDomain);
	}
	public TransactionInfo queryTransactionInfoById(String id) {
		return transactionInfoDao.queryTransactionInfoById(id);
	}
    
	public List<TransactionInfoDomain> queryTransactionInfoDomain(
			TransactionInfoDomain transactionInfoDomain,String userId) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("orderStart", Constants.DELETED_FLAG_FALSE);//0未删除
		queryMap.put("deployUserid", userId);
		if(transactionInfoDomain!=null){
			queryMap.put("cargoId", transactionInfoDomain.getCargoId());
			queryMap.put("cargoType", transactionInfoDomain.getCargoType());
			queryMap.put("cargoName", transactionInfoDomain.getCargoName());   
			queryMap.put("requestStartTime", transactionInfoDomain.getRequestStartTime());
			queryMap.put("startProCityCounty", transactionInfoDomain.getStartProCityCounty());
			queryMap.put("endProCityCounty", transactionInfoDomain.getEndProCityCounty());
			 //20140702加条件 
			queryMap.put("name", transactionInfoDomain.getName());
			queryMap.put("code", transactionInfoDomain.getCode());
			queryMap.put("carNumber", transactionInfoDomain.getCarNumber());
			queryMap.put("orderNumber", transactionInfoDomain.getOrderNumber());
			
			//20140722 pm
			queryMap.put("tradeStart", transactionInfoDomain.getTradeStart());
			if(transactionInfoDomain.getPageInfo()!=null){
				int pageSizes=transactionInfoDomain.getPageInfo().getPageSize();
				int curPages=transactionInfoDomain.getPageInfo().getCurPage();
				queryMap.put("beginNum",pageSizes*(curPages-1) );
				queryMap.put("endNum",pageSizes);
				transactionInfoDomain.getPageInfo().setTotalRecords(transactionInfoDao.queryTransactionInfoDomainCount(queryMap));
				return	transactionInfoDao.queryTransactionInfoDomainByPage(queryMap);
			}
		}
		return transactionInfoDao.queryTransactionInfoDomain(queryMap);
	}
	
	public List<TransactionInfoDomain> queryTransactionInfoDomain2(
			TransactionInfoDomain transactionInfoDomain,String userId,String parentId) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		
		WebUserInfo info = webUserInfoDao.queryWebUserInfoById(userId);
		if(info != null) {
			WebUserInfo parentInfo = webUserInfoDao.queryWebUserInfoById(info.getParentId());
			if("2".equals(info.getUserType())) {
				queryMap.put("receiverCode", info.getEncoded());
				queryMap.put("deployUserid", parentId);
				
			} else if("1".equals(info.getUserType())) {
				queryMap.put("shipperCode", info.getEncoded());
				queryMap.put("deployUserid", parentId);
			}else {
				queryMap.put("deployUserid", userId);
			}
			queryMap.put("userType", info.getUserType());
			if(parentInfo != null) {
				queryMap.put("shipperCompanyCode", parentInfo.getEncoded());
			}
		}
		
		queryMap.put("orderStart", Constants.DELETED_FLAG_FALSE);//0未删除
		if(transactionInfoDomain!=null){
			queryMap.put("cargoId", transactionInfoDomain.getCargoId());
			queryMap.put("cargoType", transactionInfoDomain.getCargoType());
			queryMap.put("cargoName", transactionInfoDomain.getCargoName());   
			queryMap.put("requestStartTime", transactionInfoDomain.getRequestStartTime());
			queryMap.put("startProCityCounty", transactionInfoDomain.getStartProCityCounty());
			queryMap.put("endProCityCounty", transactionInfoDomain.getEndProCityCounty());
			
			//20140702加条件 
			queryMap.put("name", transactionInfoDomain.getName());
			queryMap.put("code", transactionInfoDomain.getCode());
			queryMap.put("carNumber", transactionInfoDomain.getCarNumber());
			queryMap.put("orderNumber", transactionInfoDomain.getOrderNumber());
			
			//20140722 pm
			queryMap.put("tradeStart", transactionInfoDomain.getTradeStart());
			if(transactionInfoDomain.getPageInfo()!=null){
				int pageSizes=transactionInfoDomain.getPageInfo().getPageSize();
				int curPages=transactionInfoDomain.getPageInfo().getCurPage();
				queryMap.put("beginNum",pageSizes*(curPages-1) );
				queryMap.put("endNum",pageSizes);				
			} else {
				queryMap.put("beginNum",0);
				queryMap.put("endNum",Integer.MAX_VALUE);
			}			
		} else {
			queryMap.put("beginNum",0);
			queryMap.put("endNum",Integer.MAX_VALUE);
		}
		transactionInfoDomain.getPageInfo().setTotalRecords(transactionInfoDao.queryTransactionInfoDomainCount2(queryMap));
		return	transactionInfoDao.queryTransactionInfoDomainByPage2(queryMap);
	}
	  
	public List<TransactionInfoDomain> queryInOrNotInSuccessCloseTransactionInfoDomain(
			TransactionInfoDomain transactionInfoDomain, String userId) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		
		queryMap.put("menuAId", transactionInfoDomain.getMenuAId());
		queryMap.put("orderStart", Constants.DELETED_FLAG_FALSE);//0未删除
		queryMap.put("deployUserid", userId);
		queryMap.put("successTradeStart", Constants.TRADE_START_SUCCESS_KEY);
		queryMap.put("closeTradeStart", Constants.TRADE_START_CLOSE_KEY );
		if(transactionInfoDomain!=null){
			queryMap.put("tradeStart", transactionInfoDomain.getTradeStart());
			queryMap.put("startTime", transactionInfoDomain.getStartTime());
			queryMap.put("endTime", transactionInfoDomain.getEndTime());
			if(transactionInfoDomain.getPageInfo()!=null){
				int pageSizes=transactionInfoDomain.getPageInfo().getPageSize();
				int curPages=transactionInfoDomain.getPageInfo().getCurPage();
				queryMap.put("beginNum",pageSizes*(curPages-1) );
				queryMap.put("endNum",pageSizes);
				transactionInfoDomain.getPageInfo().setTotalRecords(transactionInfoDao.queryInOrNotInSuccessCloseTrandeDomainCount(queryMap));
				return	transactionInfoDao.queryInOrNotInSuccessCloseTrandeDomainByPage(queryMap);
			}
		}
		return transactionInfoDao.queryInOrNotInSuccessCloseTrandeDomain(queryMap);
	}
	
	public boolean modifyTransactionInfoOrderStart(
			TransactionInfoDomain transactionInfoDomain) {
		return transactionInfoDao.modifyTransactionInfoOrderStart(transactionInfoDomain);
	}


	public boolean modifyTransactionInfoTradeStart(
			TransactionInfoDomain transactionInfoDomain) {
		return transactionInfoDao.modifyTransactionInfoTradeStart(transactionInfoDomain);
	}
	
	public Integer queryTransactionCount(String createTime) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(createTime)) {
			queryMap.put("createTime", createTime);
		}
		Integer count = transactionInfoDao.queryTransactionCount(queryMap);
		return count;
	}

	public String addTransactionInfo(TransactionInfo transactionInfo, WebUserInfo userInfo) {
		//获取订单编号
		String TradeOrderNumber=getTradeOrderNumber();
		
		Map<String, Object> addMap=new HashMap<String, Object>();
		addMap.put("orderNumber",TradeOrderNumber);
		addMap.put("deployUserid", userInfo.getId());
		addMap.put("companyId", userInfo.getCompanyId());
		addMap.put("tradeStart", Constants.TRADE_START_WAITING_DRIVER_CONFIRM_KEY);//添加的新货源-待司机确认
		addMap.put("orderStart", Constants.DELETED_FLAG_FALSE);
		addMap.put("remark", "");
		addMap.put("cargoId", transactionInfo.getCargoId());
		addMap.put("driverId", transactionInfo.getDriverId());
		addMap.put("tradeFair", transactionInfo.getTradeFair());
		addMap.put("tradeCancelOrigin", Constants.TRADE_CANCEL_ORIGIN_DEFAULT_KEY);
		return transactionInfoDao.addTransactionInfo(addMap);
	}
	
	
	
	public List<TransactionInfoDomain> queryDriverCarTransactionInfo(TransactionInfoDomain transactionInfoDomain) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		if(transactionInfoDomain != null) {
			if(StringUtils.isNotEmpty(transactionInfoDomain.getDriverId())) {
				queryMap.put("driverId", transactionInfoDomain.getDriverId());
			}
			if(StringUtils.isNotEmpty(transactionInfoDomain.getTradeStart())) {
				queryMap.put("tradeStart", transactionInfoDomain.getTradeStart());
			}
			if(transactionInfoDomain.getPageInfo() != null) {
				queryMap.put("pageSize", transactionInfoDomain.getPageInfo().getPageSize());
				queryMap.put("curPage", transactionInfoDomain.getPageInfo().getPageSize()*transactionInfoDomain.getPageInfo().getCurPage());
			}
			transactionInfoDomain.getPageInfo().setTotalRecords(transactionInfoDao.queryDriverCarTransactionInfoPageCount(queryMap));
		}
		 List<TransactionInfoDomain> trandeList=transactionInfoDao.queryDriverCarTransactionInfoPage(queryMap);
		return trandeList;
	}
	
	public TransactionInfoDomain queryAllStartCount(String userId,String tradeStart) {
		TransactionInfoDomain transactionInfoDomain=transactionInfoDao.queryAllStartCount(userId);
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("userId", userId);
		queryMap.put("tradeStart", tradeStart);
		String tradeCount= transactionInfoDao.queryAllStartNoAssessCount(queryMap);
		transactionInfoDomain.setSuccessNoAssessTrade(tradeCount);
		return transactionInfoDomain;
	}
	
	public List<TransactionInfoDomain> queryTodayDynamicTimeAndStart() {
		
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("orderStart", Constants.DELETED_FLAG_FALSE);
		queryMap.put("inTransitTrade", Constants.TRADE_START_IN_TRANSIT_KEY);
		queryMap.put("driverDisburdenTrade", Constants.TRADE_START_DISBURDEN_KEY);
		queryMap.put("successTrade", Constants.TRADE_START_SUCCESS_KEY);
		return transactionInfoDao.queryTodayDynamicTimeAndStart(queryMap);
		
	}
	
	//获取订单编号
	public String getTradeOrderNumber(){
		//获取当前日期
		Date nowDate=new Date();
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMdd"); 
		//根据CREATE_TIME=当前的日期（年月日）查询倒序 取第一条的Id 
		String serialNumberLast=transactionInfoDao.queryTransactionIdByCreateTime();
	    //工具类获取订单流水号
		PrimaryGenerater primaryGenerater=new PrimaryGenerater();
		String serialNumber=primaryGenerater.generaterNextNumber(serialNumberLast);
		String orderNumber=formate.format(nowDate)+serialNumber;//当前日期+6位数字
		return orderNumber;
	}
	
	public List<TransactionReceiptPath> queryTransactionReceiptPathByTradeId(
			String transactionId,String type) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("type", type);
		queryMap.put("transactionId", transactionId);
		return transactionInfoDao.queryTransactionReceiptPathByTradeId(queryMap);
	}
	public ITransactionInfoDao getTransactionInfoDao() {
		return transactionInfoDao;
	}

	public void setTransactionInfoDao(ITransactionInfoDao transactionInfoDao) {
		this.transactionInfoDao = transactionInfoDao;
	}

	public void setWebUserInfoDao(IWebUserInfoDao webUserInfoDao) {
		this.webUserInfoDao = webUserInfoDao;
	}

	public IOrderCargoInfoDao getOrderCargoInfoDao() {
		return orderCargoInfoDao;
	}
	public void setOrderCargoInfoDao(IOrderCargoInfoDao orderCargoInfoDao) {
		this.orderCargoInfoDao = orderCargoInfoDao;
	}

}
