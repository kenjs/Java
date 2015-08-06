package com.cy.dctms.common.domain;

import java.util.List;


/**
 * WEB�û���Ϣ
 */
public class ManagerInfoDomain extends BaseDomain{
	
	private static final long serialVersionUID = 8816957745566695783L;
	private String id;
	private String code;//��¼�ʺ�(�ֻ���)
	private String password;//��¼����(MD5)
	private String name;//�û�����
	private String validateMacFlag;//�Ƿ���Ҫ��֤��־
	private String macAddress;//����IP��ַ
	private String macTime;//mac��ʱ��
	private String parentId;//������Id
	private String createTime;//����ʱ��
	private String modifyTime;//�޸�ʱ��
	private String deleteFlag;//ɾ����־(0δɾ��1��ɾ��)
	private String errorMessage;
	//����
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
