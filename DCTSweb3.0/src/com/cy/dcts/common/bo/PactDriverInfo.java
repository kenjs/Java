package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * 企业合同车源信息
 * @author zdy
 *
 */
public class PactDriverInfo extends BaseBo{
	private static final long serialVersionUID = 8617332575268144232L;
	
	private String id;
	private String driverId;//司机Id 
	private String userId;//web用户ID
	private Date pactStartTime;//合同开始时间
	private Date pactEndTime;//合同结束时间
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	private String deletedFlag;//删除标志（0未删除1删除）
	private String pactValidDay;//合同有效时间
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getPactStartTime() {
		return pactStartTime;
	}
	public void setPactStartTime(Date pactStartTime) {
		this.pactStartTime = pactStartTime;
	}
	public Date getPactEndTime() {
		return pactEndTime;
	}
	public void setPactEndTime(Date pactEndTime) {
		this.pactEndTime = pactEndTime;
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
	public String getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public String getPactValidDay() {
		return pactValidDay;
	}
	public void setPactValidDay(String pactValidDay) {
		this.pactValidDay = pactValidDay;
	};//合同有效天数
	}
