package com.cy.swp.bo;

import java.util.Date;

public class SystemNoteTemplateInfo extends BaseBo{

	/**
	 * 系统短信模板表
	 */
	private static final long serialVersionUID = 7971422280585056874L;
	
	private Integer id;//主键id
	private Integer businessType;//业务类型（1主动2被动）
	private Integer sendType;//发送类型（1司机2企业）
	private String content;//模板内容

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getSendType() {
		return sendType;
	}
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
