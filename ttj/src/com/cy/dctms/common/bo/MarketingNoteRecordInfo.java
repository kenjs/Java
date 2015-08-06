package com.cy.dctms.common.bo;

import java.io.Serializable;
import java.util.Date;

public class MarketingNoteRecordInfo implements Serializable {

	/**
	 * 营销短信发送记录表
	 */
	private static final long serialVersionUID = 5521961998551825890L;
	
	private Integer id;//主键
	private String mobilephone;//被发送手机号码
	private String content;//发送内容
	private Date sendTime;//发送时间
	private Integer userId;//操作人id（营销人员）
	private Integer noteLogId;//短信发送日志记录Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getNoteLogId() {
		return noteLogId;
	}
	public void setNoteLogId(Integer noteLogId) {
		this.noteLogId = noteLogId;
	}

}
