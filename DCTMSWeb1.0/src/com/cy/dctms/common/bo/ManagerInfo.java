package com.cy.dctms.common.bo;

import java.util.Date;


/**
 * WEB�û���Ϣ
 */
public class ManagerInfo extends BaseBo{
	
	private static final long serialVersionUID = 8816957745566695783L;
	private Long id;
	private String code;//��¼�ʺ�(�ֻ���)
	private String password;//��¼����(MD5)
	private String name;//�û�����
	private Long validateMacFlag;//�Ƿ��mac��ַ��־
	private String macAddress;//����IP��ַ
	private Date macTime;//����Ip��ʱ��
	private String parentId;//����IP��ַ
	private Date createTime;//����ʱ��
	private Date modifyTime;//�޸�ʱ��
	private Integer deleteFlag;//ɾ����־(0δɾ��1��ɾ��)
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
