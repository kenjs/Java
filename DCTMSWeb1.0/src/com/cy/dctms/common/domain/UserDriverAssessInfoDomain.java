package com.cy.dctms.common.domain;

import java.util.List;

/**
*��ҵ��˾������
* wjl
*/
public class UserDriverAssessInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ��������������ʼֵ1��
    private String driverId;  // ˾��ID
    private String cargoId;  // ����ID
    private String userId;  // ������id
    private String transactionId;  // ��������Id
    private String arriverEvaluateScore;  // �����ٶȣ����֣�
    private String serveEvaluateScore;  // ˾������̬�ȣ����֣�
    private String tradeEvaluateScore;  // ����������
    private String assess;  // �û���д����
    private String createTime;  // ����ʱ��
    private String modifyTime;  // �޸�ʱ��
    //�����ֶ�
    private String cargoName;//��������
    private String driverCode;//˾���˺�
    
    private String driverName;//˾������
    private String userCode;//��ҵ�˺�
    private String userName;//��ҵ����
    private String orderNumber;//������
    private String queryTimeQ;//��ѯʱ����
    private String queryTimeZ;//��ѯʱ��ֹ
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