package com.cy.dctms.common.domain;

import java.util.List;

/**
*操作日志
* wjl
*/
public class OperationLogDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // 主键（自增、初始值1）
    private String userDriverId;  // 操作人ID
    private String type;  // 记录平台类型（0：web或1：app）
    private String operationType;  // 操作类型
    private String operationName;  // 操作功能名称
    private String remark;  // 备注
    private String createTime;  // 创建时间
    //冗余
    private String queryTimeQ;//查询时间起
    private String queryTimeZ;//查询时间止
    private String userDriverName;//操作人姓名
    private String userDriverCode;//操作人账号
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