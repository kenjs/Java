package com.cy.dctms.common.bo;

import java.util.Date;
public class OperationLog {
    private Long id;  // 主键（自增、初始值1）
    private Long userDriverId;  // 操作人ID
    private Long type;  // 记录平台类型（web或app）
    private Long operationType;  // 操作类型
    private String operationName;  // 操作功能名称
    private String remark;  // 备注
    private Date createTime;  // 创建时间
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