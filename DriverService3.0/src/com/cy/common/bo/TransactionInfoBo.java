package com.cy.common.bo;

import java.io.Serializable;
/**
 * 订单
 * @date 2014-6-5
 * @author haoyong
 *
 */
public class TransactionInfoBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5062070532391111675L;

	private String id;//主键（自增、初始值1）
	private String orderNumber;//订单编号
	private String cargoId;//货源ID
	private String driverId;//车源ID
	private String deployUserid;//用户ID
	private String companyId;//企业ID
	private String tradeFair;//交易金额
	private String tradeStart;//交易状态
	private String tradeStartTime;//交易状态修改时间
	private String orderStart;//订单状态（是否有效）
	private String remark;//备注
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	private String tradeCancelOrigin;//交易取消来源
	private String shipperCode;//发货方编码代码
	private String shipperOrderNo;//发货单号
	private String deliveryTime;//发布时间
	private String arrivalTime;//到货时间
	private String receiveTime;//收货时间
	private String receiverCode;//收货方编码代码
	private String receiverOrderNo;//收货方订单编号
	private String shipperCompanyCode;//物流公司编码代码
	
	public TransactionInfoBo() {
		super();
	}
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
	public String getTradeFair() {
		return tradeFair;
	}
	public void setTradeFair(String tradeFair) {
		this.tradeFair = tradeFair;
	}
	public String getTradeStart() {
		return tradeStart;
	}
	public void setTradeStart(String tradeStart) {
		this.tradeStart = tradeStart;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getTradeCancelOrigin() {
		return tradeCancelOrigin;
	}
	public void setTradeCancelOrigin(String tradeCancelOrigin) {
		this.tradeCancelOrigin = tradeCancelOrigin;
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
	public String getShipperCompanyCode() {
		return shipperCompanyCode;
	}
	public void setShipperCompanyCode(String shipperCompanyCode) {
		this.shipperCompanyCode = shipperCompanyCode;
	}
	
	
}
