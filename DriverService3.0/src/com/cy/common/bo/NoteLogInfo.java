package com.cy.common.bo;

import java.io.Serializable;
import java.util.Date;
/**
 * 短信发送记录bo
 * @author haoyong
 *
 */
public class NoteLogInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -663559051507891020L;
	private int id;
	private String mobilephone;  //主键（自增、初始值1）
	private String noteCode;	 //手机号码
	private int sendStart;		 //验证码
	private Date sendTime;		 //发送状态
	private Date createTime;	 //发送时间
	private String visitIp;		 //创建时间
	private String returnedValue;//短信发送返回值
	public NoteLogInfo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getSendStart() {
		return sendStart;
	}
	public void setSendStart(int sendStart) {
		this.sendStart = sendStart;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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
