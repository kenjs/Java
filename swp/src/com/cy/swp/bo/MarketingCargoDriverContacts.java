package com.cy.swp.bo;

import java.util.Date;
import java.util.List;

/**
 * 货源司机电话回访记录表（每个营销专员针对每一条货源与司机电话联系的结果）
 * @author zdy
 *
 */

public class MarketingCargoDriverContacts extends BaseBo{
	private static final long serialVersionUID = 5521961998551825890L;
	
	private String id;
	private String assistId;//协助ID (pk-t_marketing_cargo_assist.id)
	private String driverUserId;//司机ID (pk-t_driver_user_info.id)
	private String callTime;//电话联系时间
	private String replyResult;//司机答复结果：(0有意向，1.无意向，2.未明确态度)
	private String remark;//备注
	private Date createTime;
	private String assisterName;//协助专员名称



	private String driverPhone;//司机号码
	private String companyPhone;//企业号码
	private String carNumber;//车牌号码
	private String companyName;//公司名称
	private String baiduChannelId;//201407013 百度云推送channelId 
    private String baiduUserId;//201407013百度云推送userId 
	
    
    private MarketingCargoDriverContacts marketingCargoDriverContactsArry[];


	public String getAssisterName() {
		return assisterName;
	}

	public void setAssisterName(String assisterName) {
		this.assisterName = assisterName;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAssistId() {
		return assistId;
	}
	public void setAssistId(String assistId) {
		this.assistId = assistId;
	}
	public String getDriverUserId() {
		return driverUserId;
	}
	public void setDriverUserId(String driverUserId) {
		this.driverUserId = driverUserId;
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
	public MarketingCargoDriverContacts[] getMarketingCargoDriverContactsArry() {
		return marketingCargoDriverContactsArry;
	}
	public void setMarketingCargoDriverContactsArry(
			MarketingCargoDriverContacts[] marketingCargoDriverContactsArry) {
		this.marketingCargoDriverContactsArry = marketingCargoDriverContactsArry;
	}
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBaiduChannelId() {
		return baiduChannelId;
	}
	public void setBaiduChannelId(String baiduChannelId) {
		this.baiduChannelId = baiduChannelId;
	}
	public String getBaiduUserId() {
		return baiduUserId;
	}
	public void setBaiduUserId(String baiduUserId) {
		this.baiduUserId = baiduUserId;
	}
	
	
}
