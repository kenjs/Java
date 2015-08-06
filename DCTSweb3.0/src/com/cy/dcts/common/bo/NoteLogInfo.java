package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * 短信发送记录表
 * 
 * @author zdy
 * 
 */
public class NoteLogInfo extends BaseBo {
	
	private static final long serialVersionUID = 8617332575268144232L;
	
	
	private String id;
	private String mobilephone;//手机号码
	private String noteCode;//验证码
	private String sendStart;//发送状态
	private String returnedValue;//短信返回值
	private String visitIp;//访问Ip
	private Date sndeTime;//发送时间
	private Date createTime;//创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getNoteCode() {
		return noteCode;
	}

	public void setNoteCode(String noteCode) {
		this.noteCode = noteCode;
	}

	public String getSendStart() {
		return sendStart;
	}

	public void setSendStart(String sendStart) {
		this.sendStart = sendStart;
	}

	public Date getSndeTime() {
		return sndeTime;
	}

	public void setSndeTime(Date sndeTime) {
		this.sndeTime = sndeTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}

	public String getReturnedValue() {
		return returnedValue;
	}

	public void setReturnedValue(String returnedValue) {
		this.returnedValue = returnedValue;
	}

	
}
