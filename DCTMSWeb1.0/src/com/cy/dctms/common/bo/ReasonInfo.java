package com.cy.dctms.common.bo;

import java.util.Date;
public class ReasonInfo {
    private Long id;  // ��������������ʼֵ1��
    private int type; // ��������1Ϊɾ��˾�����ɣ�2 ɾ����ҵ����
    private Long driverUserId;//˾��������ҵID
    private String reason;//����
    private Date createTime;  // ����ʱ��
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