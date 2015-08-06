package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * 操作日志记录表
 * 
 * @author zdy
 * 
 */
public class OperationLogInfo extends BaseBo {
	private static final long serialVersionUID = 8617332575268144232L;
	private int id;
	private int userriverId;//操作人ID
	private int type = 0;//记录平台类型（web或app）
	private int operationType;//操作类型
	private String operationName;//操作功能名称
	private String remark;//备注
	private Date createTime;//创建时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserriverId() {
		return userriverId;
	}

	public void setUserriverId(int userriverId) {
		this.userriverId = userriverId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
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
