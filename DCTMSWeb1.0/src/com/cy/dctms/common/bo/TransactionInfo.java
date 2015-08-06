package com.cy.dctms.common.bo;

import java.util.Date;
public class TransactionInfo {
    private Long id;  // ��������������ʼֵ1��
    private String orderNumber;  // �������
    private Long cargoId;  // ��ԴID
    private Long driverId;  // ��ԴID
    private Long deployUserid;  // �û�ID
    private Long companyId;  // ��ҵID
    private Double tradeFair;  // ���׽��
    private Long tradeStart;  // ����״̬
    private Date tradeStartTime;  // ����״̬�޸�ʱ��
    private Long orderStart;  // ����״̬���Ƿ���Ч��
    private String remark;  // ��ע
    private Long tradeCancelOrigin;  // ����ȡ����Դ
    private Date createTime;  // ����ʱ��
    private Date modifyTime;  // �޸�ʱ��
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