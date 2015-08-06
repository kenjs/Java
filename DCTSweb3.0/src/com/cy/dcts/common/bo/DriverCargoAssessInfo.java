package com.cy.dcts.common.bo;

import java.util.Date;

public class DriverCargoAssessInfo extends BaseBo {
	private static final long serialVersionUID = 8617332575268144232L;
	
	private String id;//主键Id
	private String driverId;//司机ID
	private String cargoId;//货物ID
	private String type;//0：货已走，1：货还在
	private String assessInfo;//点评内容（备注）
	private Date  createTime;//点评时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getCargoId() {
		return cargoId;
	}
	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAssessInfo() {
		return assessInfo;
	}
	public void setAssessInfo(String assessInfo) {
		this.assessInfo = assessInfo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
