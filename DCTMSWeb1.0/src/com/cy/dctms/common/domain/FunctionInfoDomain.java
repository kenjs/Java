package com.cy.dctms.common.domain;

import java.util.List;



/**
 * ϵͳ������Ϣ
 */
public class FunctionInfoDomain extends BaseDomain{
	
	private static final long serialVersionUID = 8816957745566695783L;
	private String id;
	private String parentId; //����ID
	private String name;//�û�����
	private String url;//URL
	private String createTime;//����ʱ��
	private String modifyTime;//�޸�ʱ��
	private String deleteFlag;//ɾ����־
	private String parentNumber;
	private String number;
	public String getParentNumber() {
		return parentNumber;
	}
	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	//����
	private List<FunctionInfoDomain> dataList;
	private String parentName; //��������
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<FunctionInfoDomain> getDataList() {
		return dataList;
	}
	public void setDataList(List<FunctionInfoDomain> dataList) {
		this.dataList = dataList;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	private String deletedFlag;//ɾ����־(0δɾ��1��ɾ��)
	
	
	
	
}
