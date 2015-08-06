package com.cy.common.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 货源点评
 * @date 2014-6-11
 * @author haoyong
 *
 */
public class DriverCargoAssessInfoBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3727005516747627245L;

	private int id;//主键（自增、初始值1）
	private int driverId;//司机ID
	private int cargoId;//货物ID
	private int type;//0：货已走，1：货还在
	private String assessInfo;//点评内容（备注）
	private Date createTime;//点评时间
	public DriverCargoAssessInfoBo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public int getCargoId() {
		return cargoId;
	}
	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
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
