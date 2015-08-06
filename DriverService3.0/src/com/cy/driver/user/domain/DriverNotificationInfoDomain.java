package com.cy.driver.user.domain;

import com.cy.common.domain.BaseDomain;
/**
 * 司机个人消息domain
 * @date 2014-6-11
 * @author haoyong
 *
 */
public class DriverNotificationInfoDomain extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4483587041658211810L;
	private String id;//主键（自增、初始值1）
	private String driverId;//司机ID
	private String cagoId;//货物ID
	private String type;//-1：快到网通知，0:用车确认，1：待卸货，2：带评价
	private String describeInfo;//消息备注
	private String pushSuccess;//0:成功，1：未成功
	private String createTime;//创建时间
	public DriverNotificationInfoDomain() {
		super();
	}
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
	public String getCagoId() {
		return cagoId;
	}
	public void setCagoId(String cagoId) {
		this.cagoId = cagoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescribeInfo() {
		return describeInfo;
	}
	public void setDescribeInfo(String describeInfo) {
		this.describeInfo = describeInfo;
	}
	public String getPushSuccess() {
		return pushSuccess;
	}
	public void setPushSuccess(String pushSuccess) {
		this.pushSuccess = pushSuccess;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
