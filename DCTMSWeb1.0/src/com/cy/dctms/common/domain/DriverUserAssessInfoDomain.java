package com.cy.dctms.common.domain;

import java.util.List;

/**
*������־
* wjl
*/
public class DriverUserAssessInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ��������������ʼֵ1��
    private String cargoId;  // ��ԴID
    private String driverId;  // ˾��ID
    private String userId;  // �û�id����ҵ�û���
    private String transactionId;  // ���׶���Id
    private String assessEvaluateScore;  // ����
    private String assess;  // ����
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
	private List<DriverUserAssessInfoDomain> dataList;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getAssessEvaluateScore() {
        return assessEvaluateScore;
    }
    public void setAssessEvaluateScore(String assessEvaluateScore) {
        this.assessEvaluateScore = assessEvaluateScore;
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
    public List<DriverUserAssessInfoDomain> getDataList() {    	return dataList;    }    public void setDataList(List<DriverUserAssessInfoDomain> dataList) {    	this.dataList = dataList;    }}