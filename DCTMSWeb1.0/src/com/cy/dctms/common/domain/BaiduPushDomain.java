package com.cy.dctms.common.domain;


/**
*˾����Ϣ
* wjl
*/
public class BaiduPushDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String telephone;  // �ֻ���
    private String baiduChannelId;  // �ٶ�������channelId
    private String baiduUserId;  // �ٶ�������userId
    private String title;//���ͱ���
    private String description;//����������Ϣ
    private String uniOrBroadcastFlag;//0�������ͻ���1�㲥����
    private String falseFlag;//ʧ�ܱ�־0Ϊ���ͳɹ�1Ϊ�ֻ��Ų����ڣ�2Ϊ�û�û�а�
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
    
    