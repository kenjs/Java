package com.cy.dctms.common.domain;

import java.util.List;

/**
*交易信息
* wjl
*/
public class TransactionInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // 主键（自增、初始值1）
    private String orderNumber;  // 订单编号
    private String cargoId;  // 货源ID
    private String driverId;  // 车源ID
    private String deployUserid;  // 用户ID
    private String companyId;  // 企业ID
    private String tradeFair;  // 交易金额
    private String tradeStart;  // 交易状态
    private String tradeStartTime;  // 交易状态修改时间
    private String orderStart;  // 订单状态（是否有效）
    private String remark;  // 备注
    private String tradeCancelOrigin;  // 交易取消来源
    private String createTime;  // 创建时间
    private String modifyTime;  // 修改时间
    //冗余字段
    private String cargoName;//货物名称
    private String driverCode;//司机账号
    private String driverName;//司机名称
    private String userCode;//企业账号
    private String userName;//企业名称
    private String queryTimeQ;//查询时间起
    private String queryTimeZ;//查询时间止
    private String companyName;//公司名称
    private String requestEndTime;//获取结束时间
 
	public String getRequestEndTime() {
		return requestEndTime;
	}
	public void setRequestEndTime(String requestEndTime) {
		this.requestEndTime = requestEndTime;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getQueryTimeQ() {
		return queryTimeQ;
	}
	public void setQueryTimeQ(String queryTimeQ) {
		this.queryTimeQ = queryTimeQ;
	}
	public String getQueryTimeZ() {
		return queryTimeZ;
	}
	public void setQueryTimeZ(String queryTimeZ) {
		this.queryTimeZ = queryTimeZ;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	private List<TransactionInfoDomain> dataList;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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
    public String getDeployUserid() {
        return deployUserid;
    }
    public void setDeployUserid(String deployUserid) {
        this.deployUserid = deployUserid;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getTradeFair() {
        return tradeFair;
    }
    public void setTradeFair(String tradeFair) {
        this.tradeFair = tradeFair;
    }
    public String getTradeStart() {
        return tradeStart;
    }
    public void setTradeStart(String tradeStart) {
        this.tradeStart = tradeStart;
    }
    public String getTradeStartTime() {
        return tradeStartTime;
    }
    public void setTradeStartTime(String tradeStartTime) {
        this.tradeStartTime = tradeStartTime;
    }
    public String getOrderStart() {
        return orderStart;
    }
    public void setOrderStart(String orderStart) {
        this.orderStart = orderStart;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getTradeCancelOrigin() {
        return tradeCancelOrigin;
    }
    public void setTradeCancelOrigin(String tradeCancelOrigin) {
        this.tradeCancelOrigin = tradeCancelOrigin;
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
    public List<TransactionInfoDomain> getDataList() {    	return dataList;    }    public void setDataList(List<TransactionInfoDomain> dataList) {    	this.dataList = dataList;    }}