package com.cy.dcts.common.domain;


import java.util.List;

import com.cy.dcts.common.constants.Constants;

/**
 * 交易表
 * 
 * @author zdy
 * 
 */
public class TransactionInfoDomain extends BaseDomain {
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String orderNumber;// 订单编号
	private String cargoId;// 货源ID
	private String driverId;// 车源ID
	private String deployUserid;// 用户ID
	private String companyId;// 企业ID
	private double tradeFair;// 交易金额
	private String tradeStart;// 交易状态
	private String tradeStartTime;// 交易状态修改时间
	private String orderStart;// 订单状态（是否有效）
	private String remark;//备注
	private String tradeCancelOrigin;// 交易取消来源 <!-- 20140705 -->
	private String createTime;// 创建时间
	private String modifyTime;// 修改时间
	
	//20140808(货主版新增字段11个) 
	private String shipperCode;//发货方编码代码
	private String shipperOrderNo;//发货单号
	private String receiverCode;//收货方编码代码
	private String receiverOrderNo;//收货方订单编号
	private String deliveryTime;//发货时间
	private String arrivalTime;//到达时间
	private String receiveTime;//收货时间
	private String shipperCompanyCode;//物流公司编码代码
	private String shipperCompanyId;//物流公司id
	private String shipperCodeId;//发货方id
	private String receiverCodeId;//发货方id 
	
	private String cargoName;// 货物名称
	private String cargoType;//货物类型
	private String requestStartTime;// 要求装货时间
	private String startProCityCounty;//装货地:省市区县
	private String endProCityCounty;//卸货地:省市区县 
	private String serarchEndProCityCounty;//查询装货地:省-市-区县
	private String serarchStartProCityCounty;//查询卸货地:省-市-区县 
	private String cargoFlag;//货源状态
	//20140813
	private String startProvince;// 装货地-省
	private String startCity;// 装货地-市
	private String startCounty;// 装货地-县
	private String endProvince;// 卸货-省
	private String endCity;// 卸货-市
	private String endCounty;// 卸货-县
	
	private String tradeStartValue;//交易状态值 
	private String name;//司机姓名
	private String code;//登录帐号(手机号)
	private String telephone;//联系电话
	private String carNumber;//车辆号码
	private String baiduChannelId;//201407013 百度云推送channelId 例子：提醒司机确认拉货(司机在注册登录的时候会生成)
	private String baiduUserId;//201407013百度云推送userId 例子：提醒司机确认拉货(司机在注册登录的时候会生成)
	
	private String userDriverAssessCount;//对应某次交易货主对司机的评价次数<!-- 20140707 -->
	private String tradeCancelOriginVal;//交易取消来源对应值 <!-- 20140705 -->
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String successTradeStart;//订单完成(交易成功)
	private String closeTradeStart;//交易取消
	
	//20140708添加4个值
	private String waitingDriverTrade;//等待司机确认
	private String inTransitTrade;//运输跟踪（运输中）
	private String successTrade;//订单完成
	private String closeTrade;//交易取消
	private String driverDisburdenTrade;//司机已卸货
	private String successNoAssessTrade;//订单完成且未评价
	private String waitingReceivingTrade;//运输跟踪+司机已卸货
	//20140815
	private String shipperComName;//发货方公司名称
	private String receiverComName;//收货方公司名
	private String logisticsComName;//物流公司名称
	  
	
	private String companyName;//公司名称20140717
	private String menuAId;//个人中心左边菜单超链接的Id
	private List<TransactionInfoDomain> list;
	private String errorMessage;
	private String flas;//标记
	    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCargoId() {
		return cargoId;
	}

	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getDeployUserid() {
		return deployUserid;
	}

	public void setDeployUserid(String deployUserid) {
		this.deployUserid = deployUserid;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getTradeStart() {
		return tradeStart;
	}

	public void setTradeStart(String tradeStart) {
		this.tradeStart = tradeStart;
		if(this.tradeStart==null){
			return;
		}
		try {
			if(Constants.TRADE_START_WAITING_DRIVER_CONFIRM_KEY.equals(tradeStart)){
				this.setTradeStartValue(Constants.TRADE_START_WAITING_DRIVER_CONFIRM_VALUE);
			//}else if(Constants.TRADE_START_AFTER_LOADING_KEY.equals(tradeStart)){
				//this.setTradeStartValue(Constants.TRADE_START_AFTER_LOADING_VALUE);
			}else if(Constants.TRADE_START_IN_TRANSIT_KEY.equals(tradeStart)){
				this.setTradeStartValue(Constants.TRADE_START_IN_TRANSIT_VALUE);
			//}else if(Constants.TRADE_START_ARRIVED_KEY.equals(tradeStart)){
				//this.setTradeStartValue(Constants.TRADE_START_ARRIVED_VALUE);
			}else if(Constants.TRADE_START_SUCCESS_KEY.equals(tradeStart)){
				this.setTradeStartValue(Constants.TRADE_START_SUCCESS_VALUE);
			}else if(Constants.TRADE_START_CLOSE_KEY.equals(tradeStart)){
				this.setTradeStartValue(Constants.TRADE_START_CLOSE_VALUE);
			}else if(Constants.TRADE_START_DISBURDEN_KEY.equals(tradeStart)){
				this.setTradeStartValue(Constants.TRADE_START_DISBURDEN_VALUE);
			}
			//20140815导入的订单没有承运人（货主版，修改后若确定了承运人就改变交易状态：待司机确认）
			else if(Constants.TRADE_START_INVALID_KEY.equals(tradeStart)){
				this.setTradeStartValue(Constants.TRADE_START_INVALID_VALUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String getTradeStartTime() {
		return tradeStartTime;
	}

	public void setTradeStartTime(String tradeStartTime) {
		this.tradeStartTime = tradeStartTime;
	}

	public String getOrderStart() {
		return orderStart;
	}

	public void setOrderStart(String orderStart) {
		this.orderStart = orderStart;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(String requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public String getStartProCityCounty() {
		return startProCityCounty;
	}

	public void setStartProCityCounty(String startProCityCounty) {
		this.startProCityCounty = startProCityCounty;
	}

	public String getEndProCityCounty() {
		return endProCityCounty;
	}

	public void setEndProCityCounty(String endProCityCounty) {
		this.endProCityCounty = endProCityCounty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public List<TransactionInfoDomain> getList() {
		return list;
	}

	public void setList(List<TransactionInfoDomain> list) {
		this.list = list;
	}

	public String getCargoType() {
		return cargoType;
	}

	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getTradeFair() {
		return tradeFair;
	}

	public void setTradeFair(double tradeFair) {
		this.tradeFair = tradeFair;
	}

	public String getMenuAId() {
		return menuAId;
	}

	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}

	public String getSerarchEndProCityCounty() {
		return serarchEndProCityCounty;
	}

	public void setSerarchEndProCityCounty(String serarchEndProCityCounty) {
		this.serarchEndProCityCounty = serarchEndProCityCounty;
	}

	public String getSerarchStartProCityCounty() {
		return serarchStartProCityCounty;
	}

	public void setSerarchStartProCityCounty(String serarchStartProCityCounty) {
		this.serarchStartProCityCounty = serarchStartProCityCounty;
	}

	public String getTradeStartValue() {
		return tradeStartValue;
	}

	public void setTradeStartValue(String tradeStartValue) {
		this.tradeStartValue = tradeStartValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCargoFlag() {
		return cargoFlag;
	}

	public void setCargoFlag(String cargoFlag) {
		this.cargoFlag = cargoFlag;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSuccessTradeStart() {
		return successTradeStart;
	}

	public void setSuccessTradeStart(String successTradeStart) {
		this.successTradeStart = successTradeStart;
	}

	public String getCloseTradeStart() {
		return closeTradeStart;
	}

	public void setCloseTradeStart(String closeTradeStart) {
		this.closeTradeStart = closeTradeStart;
	}

	public String getTradeCancelOrigin() {
		return tradeCancelOrigin;
	}

	public void setTradeCancelOrigin(String tradeCancelOrigin) {
		this.tradeCancelOrigin = tradeCancelOrigin;
		if(this.tradeCancelOrigin==null){
			return;
		}
		try {
			if(Constants.TRADE_CANCEL_ORIGIN_DEFAULT_KEY.equals(tradeCancelOrigin)){
				this.setTradeCancelOriginVal(Constants.TRADE_CANCEL_ORIGIN_DEFAULT_VALUE);
			}else if(Constants.TRADE_CANCEL_ORIGIN_OPPOSITE_KEY.equals(tradeCancelOrigin)){
				this.setTradeCancelOriginVal(Constants.TRADE_CANCEL_ORIGIN_OPPOSITE_VALUE);
			}else if(Constants.TRADE_CANCEL_ORIGIN_OUR_KEY.equals(tradeCancelOrigin)){
				this.setTradeCancelOriginVal(Constants.TRADE_CANCEL_ORIGIN_OUR_VALUE);
			}else if(Constants.TRADE_CANCEL_ORIGIN_OUR_PASSIVE_KEY.equals(tradeCancelOrigin)){
				this.setTradeCancelOriginVal(Constants.TRADE_CANCEL_ORIGIN_OUR_PASSIVE_VALUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTradeCancelOriginVal() {
		return tradeCancelOriginVal;
	}

	public void setTradeCancelOriginVal(String tradeCancelOriginVal) {
		this.tradeCancelOriginVal = tradeCancelOriginVal;
	}

	public String getUserDriverAssessCount() {
		return userDriverAssessCount;
	}

	public void setUserDriverAssessCount(String userDriverAssessCount) {
		this.userDriverAssessCount = userDriverAssessCount;
	}

	public String getWaitingDriverTrade() {
		return waitingDriverTrade;
	}

	public void setWaitingDriverTrade(String waitingDriverTrade) {
		this.waitingDriverTrade = waitingDriverTrade;
	}

	public String getInTransitTrade() {
		return inTransitTrade;
	}

	public void setInTransitTrade(String inTransitTrade) {
		this.inTransitTrade = inTransitTrade;
	}

	public String getSuccessTrade() {
		return successTrade;
	}

	public void setSuccessTrade(String successTrade) {
		this.successTrade = successTrade;
	}

	public String getCloseTrade() {
		return closeTrade;
	}

	public void setCloseTrade(String closeTrade) {
		this.closeTrade = closeTrade;
	}

	public String getDriverDisburdenTrade() {
		return driverDisburdenTrade;
	}

	public void setDriverDisburdenTrade(String driverDisburdenTrade) {
		this.driverDisburdenTrade = driverDisburdenTrade;
	}

	public String getSuccessNoAssessTrade() {
		return successNoAssessTrade;
	}

	public void setSuccessNoAssessTrade(String successNoAssessTrade) {
		this.successNoAssessTrade = successNoAssessTrade;
	}

	public String getWaitingReceivingTrade() {
		return waitingReceivingTrade;
	}

	public void setWaitingReceivingTrade(String waitingReceivingTrade) {
		this.waitingReceivingTrade = waitingReceivingTrade;
	}

	public String getBaiduChannelId() {
		return baiduChannelId;
	}

	public void setBaiduChannelId(String baiduChannelId) {
		this.baiduChannelId = baiduChannelId;
	}

	public String getBaiduUserId() {
		return baiduUserId;
	}

	public void setBaiduUserId(String baiduUserId) {
		this.baiduUserId = baiduUserId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getShipperOrderNo() {
		return shipperOrderNo;
	}

	public void setShipperOrderNo(String shipperOrderNo) {
		this.shipperOrderNo = shipperOrderNo;
	}

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getReceiverOrderNo() {
		return receiverOrderNo;
	}

	public void setReceiverOrderNo(String receiverOrderNo) {
		this.receiverOrderNo = receiverOrderNo;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getShipperCompanyCode() {
		return shipperCompanyCode;
	}

	public void setShipperCompanyCode(String shipperCompanyCode) {
		this.shipperCompanyCode = shipperCompanyCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStartProvince() {
		return startProvince;
	}

	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getStartCounty() {
		return startCounty;
	}

	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}

	public String getEndProvince() {
		return endProvince;
	}

	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public String getEndCounty() {
		return endCounty;
	}

	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}

	public String getFlas() {
		return flas;
	}

	public void setFlas(String flas) {
		this.flas = flas;
	}

	public String getShipperCodeId() {
		return shipperCodeId;
	}

	public void setShipperCodeId(String shipperCodeId) {
		this.shipperCodeId = shipperCodeId;
	}

	public String getReceiverCodeId() {
		return receiverCodeId;
	}

	public void setReceiverCodeId(String receiverCodeId) {
		this.receiverCodeId = receiverCodeId;
	}

	public String getShipperCompanyId() {
		return shipperCompanyId;
	}

	public void setShipperCompanyId(String shipperCompanyId) {
		this.shipperCompanyId = shipperCompanyId;
	}

	public String getShipperComName() {
		return shipperComName;
	}

	public void setShipperComName(String shipperComName) {
		this.shipperComName = shipperComName;
	}

	public String getReceiverComName() {
		return receiverComName;
	}

	public void setReceiverComName(String receiverComName) {
		this.receiverComName = receiverComName;
	}

	public String getLogisticsComName() {
		return logisticsComName;
	}

	public void setLogisticsComName(String logisticsComName) {
		this.logisticsComName = logisticsComName;
	}

}
