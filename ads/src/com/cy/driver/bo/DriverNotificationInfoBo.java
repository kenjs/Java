package com.cy.driver.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 司机个人消息
 * @date 2014-6-11
 * @author haoyong
 *
 */
public class DriverNotificationInfoBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1101775957838176472L;

	private int id;//主键（自增、初始值1）
	private int driverId;//司机ID
	private int cagoId;//货物ID
	private int type;//-1：快到网通知，0:用车确认，1：待卸货，2：带评价
	private String describeInfo;//消息备注
	private int pushSuccess;//0:成功，1：未成功
	private Date createTime;//创建时间
	public DriverNotificationInfoBo() {
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
	public int getCagoId() {
		return cagoId;
	}
	public void setCagoId(int cagoId) {
		this.cagoId = cagoId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDescribeInfo() {
		return describeInfo;
	}
	public void setDescribeInfo(String describeInfo) {
		this.describeInfo = describeInfo;
	}
	public int getPushSuccess() {
		return pushSuccess;
	}
	public void setPushSuccess(int pushSuccess) {
		this.pushSuccess = pushSuccess;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
