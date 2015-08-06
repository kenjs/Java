package com.cy.dcts.common.bo;

import java.util.Date;

import com.cy.dcts.common.constants.Constants;

/**
 * 交易表
 * 
 * @author zdy
 * 
 */
public class TransactionInfo extends BaseBo {
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String orderNumber;// 订单编号
	private String cargoId;// 货源ID
	private String driverId;// 车源ID
	private String deployUserid;// 用户ID
	private String companyId;// 企业ID
	private double tradeFair;// 交易金额
	private String tradeStart;// 交易状态
	private Date tradeStartTime;// 交易状态修改时间
	private String orderStart;// 订单状态（是否有效）
	private String remark;//备注
	private String tradeCancelOrigin;// 交易取消来源<!-- 20140705 -->
	private Date createTime;// 创建时间
	private Date modifyTime;// 修改时间
	
	private String tradeCancelOriginVal;//交易取消来源对应值 <!-- 20140705 -->
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

	public double getTradeFair() {
		return tradeFair;
	}

	public void setTradeFair(double tradeFair) {
		this.tradeFair = tradeFair;
	}

	public String getTradeStart() {
		return tradeStart;
	}

	public void setTradeStart(String tradeStart) {
		this.tradeStart = tradeStart;
	}

	public Date getTradeStartTime() {
		return tradeStartTime;
	}

	public void setTradeStartTime(Date tradeStartTime) {
		this.tradeStartTime = tradeStartTime;
	}

	public String getOrderStart() {
		return orderStart;
	}

	public void setOrderStart(String orderStart) {
		this.orderStart = orderStart;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getShipperCompanyId() {
		return shipperCompanyId;
	}

	public void setShipperCompanyId(String shipperCompanyId) {
		this.shipperCompanyId = shipperCompanyId;
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

}
