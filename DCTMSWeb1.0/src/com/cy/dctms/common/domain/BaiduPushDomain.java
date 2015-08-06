package com.cy.dctms.common.domain;


/**
*司机信息
* wjl
*/
public class BaiduPushDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String telephone;  // 手机号
    private String baiduChannelId;  // 百度云推送channelId
    private String baiduUserId;  // 百度云推送userId
    private String title;//推送标题
    private String description;//推送描述信息
    private String uniOrBroadcastFlag;//0单薄推送还是1广播推送
    private String falseFlag;//失败标志0为推送成功1为手机号不存在，2为用户没有绑定
	public String getFalseFlag() {
		return falseFlag;
	}
	public void setFalseFlag(String falseFlag) {
		this.falseFlag = falseFlag;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUniOrBroadcastFlag() {
		return uniOrBroadcastFlag;
	}
	public void setUniOrBroadcastFlag(String uniOrBroadcastFlag) {
		this.uniOrBroadcastFlag = uniOrBroadcastFlag;
	}
}
    
    