package com.cy.dctms.common.domain;

import java.util.List;

/**
*����Ա������־��Ϣ
* wjl
*/
public class ManagerWorkLogInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // 
    private String name;  // ������־����
    private String tableName;  // ����
    private String columnId;//��id
    private String managerId;  // ����ԱID
    private String content;  // ���ݣ���ע
    private String createTime;  // ����ʱ��
    private String modifyTime;  // �޸�ʱ��
    private String deleteFlag;  // ɾ����־
    //����
    private String managerName;//����Ա����
    private String managerCode;//����ԱCode
    public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	private List<ManagerWorkLogInfoDomain> dataList;
	public String getId() {
		return id;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public List<ManagerWorkLogInfoDomain> getDataList() {
		return dataList;
	}
	public void setDataList(List<ManagerWorkLogInfoDomain> dataList) {
		this.dataList = dataList;
	}
}