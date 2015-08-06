package com.cy.driver.bo;

import java.io.Serializable;
/**
 * @description 企业合同车源信息实体类
 * @author 		haoy
 *
 */
public class PactDriverInfo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5892333613868972052L;

	private int id;					//主键ID
	private String driverId;		//司机ID
	private String companyId;		//web用户ID
	private String pactStartTime;	//合同开始时间
	private String pactEndTime;		//合同结束时间
	private String createTime;		//创建时间
	private String modifyTime;		//修改时间
	private String deletedFlag;		//删除标志（0未删除1删除）
	private String pactValidDay;	//合同有效天数
	private String pactType;		//会员类型（0普通会员，1VIP会员）
	private String pactStart;		//会员类型（0待确认，1同意.-1不同意）

	public PactDriverInfo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

}
