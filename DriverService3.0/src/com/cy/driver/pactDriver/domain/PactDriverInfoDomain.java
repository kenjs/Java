package com.cy.driver.pactDriver.domain;

import com.cy.common.domain.BaseDomain;

/**
 * @description 企业合同车源信息
 * @author 		haoy
 *
 */
public class PactDriverInfoDomain extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3900228900924559031L;

	private String id;					//主键ID
	private String driverId;		//司机ID
	private String userId;			//web用户ID
	private String pactStartTime;	//合同开始时间
	private String pactEndTime;		//合同结束时间
	private String createTime;		//创建时间
	private String modifyTime;		//修改时间
	private String deletedFlag;		//删除标志（0未删除1删除）
	private String pactValidDay;	//合同有效天数
	private String pactType;		//会员类型（0普通会员，1VIP会员）
	private String pactStart;		//会员类型（0待确认，1同意.-1不同意）
	
	private String companyName;
	private String driverName;
	
	public PactDriverInfoDomain() {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPactStartTime() {
		return pactStartTime;
	}

	public void setPactStartTime(String pactStartTime) {
		this.pactStartTime = pactStartTime;
	}

	public String getPactEndTime() {
		return pactEndTime;
	}

	public void setPactEndTime(String pactEndTime) {
		this.pactEndTime = pactEndTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
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
	}

	public String getPactType() {
		return pactType;
	}

	public void setPactType(String pactType) {
		this.pactType = pactType;
	}

	public String getPactStart() {
		return pactStart;
	}

	public void setPactStart(String pactStart) {
		this.pactStart = pactStart;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

}
