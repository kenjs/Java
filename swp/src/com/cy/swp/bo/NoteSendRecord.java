package com.cy.swp.bo;

import java.io.Serializable;

public class NoteSendRecord implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -1271082417289384648L;
	private String id;//主键id
	private String type;//类型
	private String remark;//备注
	private String telephone;//手机号码
	private String content;//发送内容
	private String returnStatus;//返回状态
	private String createTime;//发送时间
	private String eventFrom;//1 营销平台 2 快到网网站 3 app服务端 
	private String noteSendedId;//短信日志记录表主键(pk-t_note_log_info.id)(自2014-11-21日起增加)
	private String useFor;//用途：1 导入货源配车有司机发送给企业的短信 2 导入货源配车无司机发送给企业的短信
	

	public NoteSendRecord() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUseFor() {
		return useFor;
	}

	public void setUseFor(String useFor) {
		this.useFor = useFor;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public String getEventFrom() {
		return eventFrom;
	}
	public void setEventFrom(String eventFrom) {
		this.eventFrom = eventFrom;
	}
	public String getNoteSendedId() {
		return noteSendedId;
	}
	public void setNoteSendedId(String noteSendedId) {
		this.noteSendedId = noteSendedId;
	}

}
