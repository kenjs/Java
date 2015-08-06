package com.cy.dctms.common.domain;

import java.util.List;

/**
*货源历史状态
* wjl
*/
public class OrderCargoLastInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // 主键（自增、初始值1）
    private String cargoId;  // 货源ID
    private String driverId;  // 司机ID
    private String stateType;  // 状态类型
    private String remark;  // 备注
    private String createTime;  // 创建时间
    //冗余
    private String driverCode;//司机账号
    private String driverName;//司机姓名
    private List<OrderCargoLastInfoDomain> dataList;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDriverCode() {
		return driverCode;
	}
	public void setDriverCode(String driverCode) {
		this.driverCode = driverCode;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getCargoId() {
        return cargoId;
    }
    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }
    public String getDriverId() {
        return driverId;
    }
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    public String getStateType() {
        return stateType;
    }
    public void setStateType(String stateType) {
        this.stateType = stateType;
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
    public List<OrderCargoLastInfoDomain> getDataList() {    	return dataList;    }    public void setDataList(List<OrderCargoLastInfoDomain> dataList) {    	this.dataList = dataList;    }}