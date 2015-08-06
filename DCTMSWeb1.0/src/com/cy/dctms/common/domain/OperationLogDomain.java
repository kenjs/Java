package com.cy.dctms.common.domain;

import java.util.List;

/**
*������־
* wjl
*/
public class OperationLogDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ��������������ʼֵ1��
    private String userDriverId;  // ������ID
    private String type;  // ��¼ƽ̨���ͣ�0��web��1��app��
    private String operationType;  // ��������
    private String operationName;  // ������������
    private String remark;  // ��ע
    private String createTime;  // ����ʱ��
    //����
    private String queryTimeQ;//��ѯʱ����
    private String queryTimeZ;//��ѯʱ��ֹ
    private String userDriverName;//����������
    private String userDriverCode;//�������˺�
    private List<OperationLogDomain> dataList;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getQueryTimeZ() {
		return queryTimeZ;
	}
	public void setQueryTimeZ(String queryTimeZ) {
		this.queryTimeZ = queryTimeZ;
	}
	public String getUserDriverId() {
        return userDriverId;
    }
    public String getQueryTimeQ() {
		return queryTimeQ;
	}
	public void setQueryTimeQ(String queryTimeQ) {
		this.queryTimeQ = queryTimeQ;
	}
	public String getUserDriverName() {
		return userDriverName;
	}
	public void setUserDriverName(String userDriverName) {
		this.userDriverName = userDriverName;
	}
	public String getUserDriverCode() {
		return userDriverCode;
	}
	public void setUserDriverCode(String userDriverCode) {
		this.userDriverCode = userDriverCode;
	}
	public void setUserDriverId(String userDriverId) {
        this.userDriverId = userDriverId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getOperationType() {
        return operationType;
    }
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    public String getOperationName() {
        return operationName;
    }
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public List<OperationLogDomain> getDataList() {    
    	return dataList;  
    	} 
    public void setDataList(List<OperationLogDomain> dataList) {    
    	this.dataList = dataList;   
    	}
    }