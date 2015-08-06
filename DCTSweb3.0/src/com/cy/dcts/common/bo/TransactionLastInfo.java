package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * 交易状态历史记录表
 * 
 * @author zdy
 * 
 */
public class TransactionLastInfo extends BaseBo {
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String cargoId;//货源ID
	private String driverId;//司机ID
	private String transactionId;//交易订单ID
	private String start;//状态
	private String remark;//备注
	private Date createTime;//创建时间

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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
