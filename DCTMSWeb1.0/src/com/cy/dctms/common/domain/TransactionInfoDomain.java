package com.cy.dctms.common.domain;

import java.util.List;

/**
*������Ϣ
* wjl
*/
public class TransactionInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ��������������ʼֵ1��
    private String orderNumber;  // �������
    private String cargoId;  // ��ԴID
    private String driverId;  // ��ԴID
    private String deployUserid;  // �û�ID
    private String companyId;  // ��ҵID
    private String tradeFair;  // ���׽��
    private String tradeStart;  // ����״̬
    private String tradeStartTime;  // ����״̬�޸�ʱ��
    private String orderStart;  // ����״̬���Ƿ���Ч��
    private String remark;  // ��ע
    private String tradeCancelOrigin;  // ����ȡ����Դ
    private String createTime;  // ����ʱ��
    private String modifyTime;  // �޸�ʱ��
    //�����ֶ�
    private String cargoName;//��������
    private String driverCode;//˾���˺�
    private String driverName;//˾������
    private String userCode;//��ҵ�˺�
    private String userName;//��ҵ����
    private String queryTimeQ;//��ѯʱ����
    private String queryTimeZ;//��ѯʱ��ֹ
    private String companyName;//��˾����
    private String requestEndTime;//��ȡ����ʱ��
 
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