package com.cy.dctms.common.bo;

import java.util.Date;
public class TransactionInfo {
    private Long id;  // 主键（自增、初始值1）
    private String orderNumber;  // 订单编号
    private Long cargoId;  // 货源ID
    private Long driverId;  // 车源ID
    private Long deployUserid;  // 用户ID
    private Long companyId;  // 企业ID
    private Double tradeFair;  // 交易金额
    private Long tradeStart;  // 交易状态
    private Date tradeStartTime;  // 交易状态修改时间
    private Long orderStart;  // 订单状态（是否有效）
    private String remark;  // 备注
    private Long tradeCancelOrigin;  // 交易取消来源
    private Date createTime;  // 创建时间
    private Date modifyTime;  // 修改时间
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public Long getCargoId() {
        return cargoId;
    }
    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }
    public Long getDriverId() {
        return driverId;
    }
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
    public Long getDeployUserid() {
        return deployUserid;
    }
    public void setDeployUserid(Long deployUserid) {
        this.deployUserid = deployUserid;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public Double getTradeFair() {
		return tradeFair;
	}
	public void setTradeFair(Double tradeFair) {
		this.tradeFair = tradeFair;
	}
	public Long getTradeStart() {
        return tradeStart;
    }
    public void setTradeStart(Long tradeStart) {
        this.tradeStart = tradeStart;
    }
    public Date getTradeStartTime() {
        return tradeStartTime;
    }
    public void setTradeStartTime(Date tradeStartTime) {
        this.tradeStartTime = tradeStartTime;
    }
    public Long getOrderStart() {
        return orderStart;
    }
    public void setOrderStart(Long orderStart) {
        this.orderStart = orderStart;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Long getTradeCancelOrigin() {
        return tradeCancelOrigin;
    }
    public void setTradeCancelOrigin(Long tradeCancelOrigin) {
        this.tradeCancelOrigin = tradeCancelOrigin;
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
}