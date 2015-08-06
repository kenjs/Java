package com.cy.dctms.common.domain;

import java.util.List;


/**
 * WEB用户信息
 */
public class ManagerInfoDomain extends BaseDomain{
	
	private static final long serialVersionUID = 8816957745566695783L;
	private String id;
	private String code;//登录帐号(手机号)
	private String password;//登录密码(MD5)
	private String name;//用户姓名
	private String validateMacFlag;//是否需要验证标志
	private String macAddress;//物理IP地址
	private String macTime;//mac绑定时间
	private String parentId;//创建人Id
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	private String deleteFlag;//删除标志(0未删除1已删除)
	private String errorMessage;
	//冗余
	private List<ManagerInfoDomain> dataList;
	public List<ManagerInfoDomain> getDataList() {
		return dataList;
	}
	public String getMacTime() {
		return macTime;
	}
	public String getValidateMacFlag() {
		return validateMacFlag;
	}
	public void setValidateMacFlag(String validateMacFlag) {
		this.validateMacFlag = validateMacFlag;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public void setMacTime(String macTime) {
		this.macTime = macTime;
	}
	public void setDataList(List<ManagerInfoDomain> dataList) {
		this.dataList = dataList;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getCode() {
		return code;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	
	
	
	
}
