package com.cy.driver.bo;

import java.io.Serializable;
/**
 * 订单更新
 * @date 2014-7-3
 * @author haoyong
 *
 */
public class TransactionLastInfoBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5062070532391111675L;

	private int id;//主键（自增、初始值1）
	private String transactionId;//订单编号
	private String cargoId;//货源ID
	private String driverId;//车源ID
	private String start;//交易状态
	private String remark;//备注
	private String createTime;//创建时间
	
	public TransactionLastInfoBo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
