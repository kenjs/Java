package com.cy.dctms.common.domain;

import java.util.List;

/**
*˾����Ϣ
* wjl
*/
public class DriverUserInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ��������������ʼֵ1��
    private String code;  // ��¼�ʺ�(�ֻ���)
    private String password;  // ��¼����(MD5)
    private String name;  // ˾������
    private String carNumber;  // �������պ���
    private String freezeFlag;  // �����־��0:������1:����)
    private String auditFlag;  // ��˱�־��0:�����,1:ͨ��,-1δͨ����
    private String telephone;  // ��ϵ�绰
    private String identityLicenseNum;  // ���֤
    private String operatingLicense;  // Ӫ��֤·��
    private String driversLicense;  // ��ʻ֤·��
    private String drivingLicense;  // ��ʻ֤·��
    private String carLength;  // ����
    private String carWeight;  // ����-��λ
    private String carCubage;  // ����-���
    private String carPlateType;  // ��-ƽ�塢�ߵͰ�
    private String carBarType;  // ��-����������
    private String carTypes;  // ���ͣ��������塢�����֣����Ⱥ�������
    private String remark;  // ��ע
    private String deleteFlag;  // ɾ����־��1:��ɾ��,0:δɾ����
    private String carStateType;  // ��ǰ��Ӫ״̬��Ĭ��1�����2���ء�3��Ϣ��
    private String createTime;  // ����ʱ��
    private String modifyTime;  // �޸�ʱ��
    private String headPortrait;  // �û�ͷ��
    private String identityLicenseNumFront;  // ˾�����֤������Ƭ
    private String identityLicenseNumContrary;  // ˾�����֤������Ƭ
    private String newOrOldAppUser;  // �����û���ʶ��Ĭ��0��0���û���1���û�����
    private String baiduChannelId;  // �ٶ�������channelId
    private String baiduUserId;  // �ٶ�������userId
    private String submitType;   //����ύ��ʾ
    
    //�����ֶ�
    private List<DriverUserInfoDomain> dataList;
    private String commitAuditTimeQ;
    private String commitAuditTimeZ;
    private List<String> reason;   //��������
    private String location; //˾������λ��
    private String lastTime; //���λʱ��
    private String registerTimeQ;//ע��ʱ����
    private String registerTimeZ;//ע��ʱ��ֹ
    private String currentLocationTimeQ;//��ǰλ��ʱ����
    private String currentLocationTimeZ;//��ǰλ��ʱ��ֹ
    private String accumulateTransactionCount;//�����ۻ�����
    private String todayTransactionCount;//���콻������
    private String passOrderCount;//�ɽ�������
    private String orderingCount;//����������
    private String orderingRate;//��������
    private String orderingTotalCount;//����������
    private String driverLine;//��Ӫ��·
    private String driverBusinessLine;//ԤԼ��·
	private String queryTime;//��ѯ����
	private String queryTimeQ;//��ѯ������
	private String queryTimeZ;//��ѯ����ֹ
	private String registerCount;//ע������ 
	private String goodFindNum;  // �һ��ܴ���
	private String phoneCallNum;  // �绰����������
    private String quoteCount;//���۴���
    private String passOrderRate;//ͨ��������
    private String isPushAll;//�Ƿ����������ˣ�����������Ϊ1������Ϊ0
    private String deleteOrModifyFlag;//ɾ�������޸ı�־0Ϊɾ����1Ϊ�޸ġ�
    private String deleteReason;//ɾ������
    private List<String> idList;//id�б���������������Ϣʱ������id�б�ȥ����Ϣ
	public String getDeleteReason() {
		return deleteReason;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public String getIsPushAll() {
		return isPushAll;
	}
	public String getDeleteOrModifyFlag() {
		return deleteOrModifyFlag;
	}
	public void setDeleteOrModifyFlag(String deleteOrModifyFlag) {
		this.deleteOrModifyFlag = deleteOrModifyFlag;
	}
	public void setIsPushAll(String isPushAll) {
		this.isPushAll = isPushAll;
	}
	public String getPassOrderRate() {
		return passOrderRate;
	}
	public void setPassOrderRate(String passOrderRate) {
		this.passOrderRate = passOrderRate;
	}
	public String getGoodFindNum() {
		return goodFindNum;
	}
	public void setGoodFindNum(String goodFindNum) {
		this.goodFindNum = goodFindNum;
	}
	public String getPhoneCallNum() {
		return phoneCallNum;
	}
	public void setPhoneCallNum(String phoneCallNum) {
		this.phoneCallNum = phoneCallNum;
	}
	public String getQuoteCount() {
		return quoteCount;
	}
	public void setQuoteCount(String quoteCount) {
		this.quoteCount = quoteCount;
	}
	public String getOrderingRate() {
		return orderingRate;
	}
	public void setOrderingRate(String orderingRate) {
		this.orderingRate = orderingRate;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
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
	public String getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(String registerCount) {
		this.registerCount = registerCount;
	}
	public String getRegisterTimeQ() {
		return registerTimeQ;
	}
	public String getDriverLine() {
		return driverLine;
	}
	public void setDriverLine(String driverLine) {
		this.driverLine = driverLine;
	}
	public String getDriverBusinessLine() {
		return driverBusinessLine;
	}
	public void setDriverBusinessLine(String driverBusinessLine) {
		this.driverBusinessLine = driverBusinessLine;
	}
	public void setRegisterTimeQ(String registerTimeQ) {
		this.registerTimeQ = registerTimeQ;
	}
	public String getRegisterTimeZ() {
		return registerTimeZ;
	}
	public void setRegisterTimeZ(String registerTimeZ) {
		this.registerTimeZ = registerTimeZ;
	}
	public String getOrderingTotalCount() {
		return orderingTotalCount;
	}
	public void setOrderingTotalCount(String orderingTotalCount) {
		this.orderingTotalCount = orderingTotalCount;
	}
	public String getPassOrderCount() {
		return passOrderCount;
	}
	public void setPassOrderCount(String passOrderCount) {
		this.passOrderCount = passOrderCount;
	}
	public String getOrderingCount() {
		return orderingCount;
	}
	public void setOrderingCount(String orderingCount) {
		this.orderingCount = orderingCount;
	}
	public String getCurrentLocationTimeQ() {
		return currentLocationTimeQ;
	}
	public void setCurrentLocationTimeQ(String currentLocationTimeQ) {
		this.currentLocationTimeQ = currentLocationTimeQ;
	}
	public String getCurrentLocationTimeZ() {
		return currentLocationTimeZ;
	}
	public void setCurrentLocationTimeZ(String currentLocationTimeZ) {
		this.currentLocationTimeZ = currentLocationTimeZ;
	}
	public List<String> getReason() {
		return reason;
	}
	public void setReason(List<String> reason) {
		this.reason = reason;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getAccumulateTransactionCount() {
		return accumulateTransactionCount;
	}
	public void setAccumulateTransactionCount(String accumulateTransactionCount) {
		this.accumulateTransactionCount = accumulateTransactionCount;
	}
	public String getTodayTransactionCount() {
		return todayTransactionCount;
	}
	public void setTodayTransactionCount(String todayTransactionCount) {
		this.todayTransactionCount = todayTransactionCount;
	}
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	public String getCommitAuditTimeQ() {
		return commitAuditTimeQ;
	}
	public void setCommitAuditTimeQ(String commitAuditTimeQ) {
		this.commitAuditTimeQ = commitAuditTimeQ;
	}
	public String getCommitAuditTimeZ() {
		return commitAuditTimeZ;
	}
	public void setCommitAuditTimeZ(String commitAuditTimeZ) {
		this.commitAuditTimeZ = commitAuditTimeZ;
	}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCarNumber() {
        return carNumber;
    }
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public String getFreezeFlag() {
        return freezeFlag;
    }
    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag;
    }
    public String getAuditFlag() {
        return auditFlag;
    }
    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getIdentityLicenseNum() {
        return identityLicenseNum;
    }
    public void setIdentityLicenseNum(String identityLicenseNum) {
        this.identityLicenseNum = identityLicenseNum;
    }
    public String getOperatingLicense() {
        return operatingLicense;
    }
    public void setOperatingLicense(String operatingLicense) {
        this.operatingLicense = operatingLicense;
    }
    public String getDriversLicense() {
        return driversLicense;
    }
    public void setDriversLicense(String driversLicense) {
        this.driversLicense = driversLicense;
    }
    public String getDrivingLicense() {
        return drivingLicense;
    }
    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }
    public String getCarLength() {
        return carLength;
    }
    public void setCarLength(String carLength) {
        this.carLength = carLength;
    }
    public String getCarWeight() {
        return carWeight;
    }
    public void setCarWeight(String carWeight) {
        this.carWeight = carWeight;
    }
    public String getCarCubage() {
        return carCubage;
    }
    public void setCarCubage(String carCubage) {
        this.carCubage = carCubage;
    }
    public String getCarPlateType() {
        return carPlateType;
    }
    public void setCarPlateType(String carPlateType) {
        this.carPlateType = carPlateType;
    }
    public String getCarBarType() {
        return carBarType;
    }
    public void setCarBarType(String carBarType) {
        this.carBarType = carBarType;
    }
    public String getCarTypes() {
        return carTypes;
    }
    public void setCarTypes(String carTypes) {
        this.carTypes = carTypes;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public String getCarStateType() {
        return carStateType;
    }
    public void setCarStateType(String carStateType) {
        this.carStateType = carStateType;
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
    public String getHeadPortrait() {
        return headPortrait;
    }
    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
    public String getIdentityLicenseNumFront() {
        return identityLicenseNumFront;
    }
    public void setIdentityLicenseNumFront(String identityLicenseNumFront) {
        this.identityLicenseNumFront = identityLicenseNumFront;
    }
    public String getIdentityLicenseNumContrary() {
        return identityLicenseNumContrary;
    }
    public void setIdentityLicenseNumContrary(String identityLicenseNumContrary) {
        this.identityLicenseNumContrary = identityLicenseNumContrary;
    }
    public String getNewOrOldAppUser() {
        return newOrOldAppUser;
    }
    public void setNewOrOldAppUser(String newOrOldAppUser) {
        this.newOrOldAppUser = newOrOldAppUser;
    }
    public String getBaiduChannelId() {
        return baiduChannelId;
    }
    public void setBaiduChannelId(String baiduChannelId) {
        this.baiduChannelId = baiduChannelId;
    }
    public String getBaiduUserId() {
        return baiduUserId;
    }
    public void setBaiduUserId(String baiduUserId) {
        this.baiduUserId = baiduUserId;
    }
    public List<DriverUserInfoDomain> getDataList() {    
    	return dataList;  
    	}  
    public void setDataList(List<DriverUserInfoDomain> dataList) { 
    	this.dataList = dataList;   
    	}
    }