package com.cy.dctms.common.bo;

import java.util.Date;
public class OperationLog {
    private Long id;  // ��������������ʼֵ1��
    private Long userDriverId;  // ������ID
    private Long type;  // ��¼ƽ̨���ͣ�web��app��
    private Long operationType;  // ��������
    private String operationName;  // ������������
    private String remark;  // ��ע
    private Date createTime;  // ����ʱ��
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserDriverId() {
        return userDriverId;
    }
    public void setUserDriverId(Long userDriverId) {
        this.userDriverId = userDriverId;
    }
    public Long getType() {
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }
    public Long getOperationType() {
        return operationType;
    }
    public void setOperationType(Long operationType) {
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
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}