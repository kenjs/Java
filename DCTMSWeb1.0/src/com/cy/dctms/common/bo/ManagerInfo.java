package com.cy.dctms.common.bo;

import java.util.Date;


/**
 * WEB用户信息
 */
public class ManagerInfo extends BaseBo{
	
	private static final long serialVersionUID = 8816957745566695783L;
	private Long id;
	private String code;//登录帐号(手机号)
	private String password;//登录密码(MD5)
	private String name;//用户姓名
	private Long validateMacFlag;//是否绑定mac地址标志
	private String macAddress;//物理IP地址
	private Date macTime;//物理Ip绑定时间
	private String parentId;//父类IP地址
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	private Integer deleteFlag;//删除标志(0未删除1已删除)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Long getValidateMacFlag() {
		return validateMacFlag;
	}
	public void setValidateMacFlag(Long validateMacFlag) {
		this.validateMacFlag = validateMacFlag;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getCode() {
		return code;
	}
	public Date getMacTime() {
		return macTime;
	}
	public void setMacTime(Date macTime) {
		this.macTime = macTime;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	
	
	
}
