package com.cy.dctms.common.domain;

import java.util.List;

/**
*��Դ��ʷ״̬
* wjl
*/
public class OrderCargoLastInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ��������������ʼֵ1��
    private String cargoId;  // ��ԴID
    private String driverId;  // ˾��ID
    private String stateType;  // ״̬����
    private String remark;  // ��ע
    private String createTime;  // ����ʱ��
    //����
    private String driverCode;//˾���˺�
    private String driverName;//˾������
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