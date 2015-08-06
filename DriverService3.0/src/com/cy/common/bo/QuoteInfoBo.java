package com.cy.common.bo;

import java.io.Serializable;
import java.util.Date;
/**
 * 货源报价实体类
 * @date 2014-6-6
 * @author haoyong
 *
 */
public class QuoteInfoBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5462200440270951904L;
	
	private int id;//主键（自增、初始值1）
	private int cargoId;//货源ID
	private int driverId;//司机ID
	private double quoteFair;//运费报价
	private int quoteType;//报价类型
	private String remark;//备注
	private int start;//状态
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	private double cashAdvance;//预付现金金额
	private double prepaidOilCard;//预付油卡金额
	public QuoteInfoBo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCargoId() {
		return cargoId;
	}
	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public double getQuoteFair() {
		return quoteFair;
	}
	public void setQuoteFair(double quoteFair) {
		this.quoteFair = quoteFair;
	}
	public int getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(int quoteType) {
		this.quoteType = quoteType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
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
