package com.cy.dctms.common.bo;

import java.util.Date;
public class ReasonInfo {
    private Long id;  // 主键（自增、初始值1）
    private int type; // 理由类型1为删除司机理由，2 删除企业理由
    private Long driverUserId;//司机或者企业ID
    private String reason;//理由
    private Date createTime;  // 创建时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Long getDriverUserId() {
		return driverUserId;
	}
	public void setDriverUserId(Long driverUserId) {
		this.driverUserId = driverUserId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}