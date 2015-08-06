package com.cy.dctms.common.domain;

import java.util.List;

/**
*企业对司机评价
* wjl
*/
public class UserDriverAssessInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // 主键（自增、初始值1）
    private String driverId;  // 司机ID
    private String cargoId;  // 货物ID
    private String userId;  // 评价人id
    private String transactionId;  // 订单交易Id
    private String arriverEvaluateScore;  // 到达速度（评分）
    private String serveEvaluateScore;  // 司机服务态度（评分）
    private String tradeEvaluateScore;  // 交易总评价
    private String assess;  // 用户填写评语
    private String createTime;  // 创建时间
    private String modifyTime;  // 修改时间
    //冗余字段
    private String cargoName;//货物名称
    private String driverCode;//司机账号
    
    private String driverName;//司机名称
    private String userCode;//企业账号
    private String userName;//企业名称
    private String orderNumber;//订单号
    private String queryTimeQ;//查询时间起
    private String queryTimeZ;//查询时间止
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
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	private List<UserDriverAssessInfoDomain> dataList;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDriverId() {
        return driverId;
    }
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    public String getCargoId() {
        return cargoId;
    }
    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getArriverEvaluateScore() {
        return arriverEvaluateScore;
    }
    public void setArriverEvaluateScore(String arriverEvaluateScore) {
        this.arriverEvaluateScore = arriverEvaluateScore;
    }
    public String getServeEvaluateScore() {
        return serveEvaluateScore;
    }
    public void setServeEvaluateScore(String serveEvaluateScore) {
        this.serveEvaluateScore = serveEvaluateScore;
    }
    public String getTradeEvaluateScore() {
        return tradeEvaluateScore;
    }
    public void setTradeEvaluateScore(String tradeEvaluateScore) {
        this.tradeEvaluateScore = tradeEvaluateScore;
    }
    public String getAssess() {
        return assess;
    }
    public void setAssess(String assess) {
        this.assess = assess;
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
    public List<UserDriverAssessInfoDomain> getDataList() {    	return dataList;    }    public void setDataList(List<UserDriverAssessInfoDomain> dataList) {    	this.dataList = dataList;    }}