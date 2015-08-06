package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * 货源报价表
 * @author zdy
 *
 */
public class QuoteInfo extends BaseBo {
	
	private static final long serialVersionUID = 8617332575268144232L;
	
	private String id;
	private String cargoId;//货源ID
	private String driverId;//司机ID
	private double quoteFair;//运费报价
	private double cashAdvance;// 预付现金金额
	private double prepaidOilCard; // 预付油卡金额
	private String quoteType;//报价类型
	private String remark;//备注
	private String start;//状态
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public double getQuoteFair() {
		return quoteFair;
	}

	public void setQuoteFair(double quoteFair) {
		this.quoteFair = quoteFair;
	}

	public String getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
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

	public double getCashAdvance() {
		return cashAdvance;
	}

	public void setCashAdvance(double cashAdvance) {
		this.cashAdvance = cashAdvance;
	}

	public double getPrepaidOilCard() {
		return prepaidOilCard;
	}

	public void setPrepaidOilCard(double prepaidOilCard) {
		this.prepaidOilCard = prepaidOilCard;
	}

}
