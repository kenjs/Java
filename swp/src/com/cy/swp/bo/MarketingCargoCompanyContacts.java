package com.cy.swp.bo;

import java.util.Date;

/**
 * 货源企业电话回访记录表（每个营销专员针对每一条货源与企业电话联系的结果）
 * @author zdy
 *
 */
public class MarketingCargoCompanyContacts extends BaseBo{
	private static final long serialVersionUID = 5521961998551825890L;
	
	private String id;
	private String callTime;//电话联系时间
	private String replyResult;//企业答复结果：
	private String remark;//备注
	private Date createTime;
	private String contactTelephone;//联系人电话
	private String contactMobilephone;//联系人手机号
	private String assisterId;//营销专员Id
	private String assisterName;//营销专员名称(扩展字段，表中不存在这个字段)
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	
	public String getReplyResult() {
		return replyResult;
	}
	public void setReplyResult(String replyResult) {
		this.replyResult = replyResult;
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
	public String getContactTelephone() {
		return contactTelephone;
	}
	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}
	public String getContactMobilephone() {
		return contactMobilephone;
	}
	public void setContactMobilephone(String contactMobilephone) {
		this.contactMobilephone = contactMobilephone;
	}
	public String getAssisterId() {
		return assisterId;
	}
	public void setAssisterId(String assisterId) {
		this.assisterId = assisterId;
	}
	public String getAssisterName() {
		return assisterName;
	}
	public void setAssisterName(String assisterName) {
		this.assisterName = assisterName;
	}
	
	
}
