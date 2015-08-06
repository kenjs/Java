package com.cy.dctms.bo;

import java.io.Serializable;
import java.util.Date;
/**
 * 操作日志记录实体�?
 * @author Administrator
 *
 */
public class OperationLogInfoBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1304689442097432993L;
	
	private int id;//主键（自增�?�初始�??1�?
	private int userDriverId;//操作人ID
	private int type = 1;//记录平台类型（web或app�?
	private int  operationType;//操作类型
	private String operationName;//操作功能名称
	private String remark;//备注
	private Date createTime;//创建时间
	public OperationLogInfoBo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;		
	}
	public int getUserDriverId() {
		return userDriverId;
	}
	public void setUserDriverId(int userDriverId) {
		this.userDriverId = userDriverId;		
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
