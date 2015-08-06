package com.cy.dctms.common.bo;

import java.util.Date;
public class DriverUserInfo {
    private Long id;  // ��������������ʼֵ1��
    private String code;  // ��¼�ʺ�(�ֻ���)
    private String password;  // ��¼����(MD5)
    private String name;  // ˾������
    private String carNumber;  // �������պ���
    private Long freezeFlag;  // �����־��0:������1:����)
    private Long auditFlag;  // ��˱�־��0:�����,1:ͨ��,-1δͨ����
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
    private Long deleteFlag;  // ɾ����־��1:��ɾ��,0:δɾ����
    private Long carStateType;  // ��ǰ��Ӫ״̬��Ĭ��1�����2���ء�3��Ϣ��
    private Date createTime;  // ����ʱ��
    private Date modifyTime;  // �޸�ʱ��
    private String headPortrait;  // �û�ͷ��
    private String identityLicenseNumFront;  // ˾�����֤������Ƭ
    private String identityLicenseNumContrary;  // ˾�����֤������Ƭ
    private Long newOrOldAppUser;  // �����û���ʶ��Ĭ��0��0���û���1���û�����
    private Long baiduChannelId;  // �ٶ�������channelId
    private Long baiduUserId;  // �ٶ�������userId
    private Long submitType;//��˱�־
	public Long getSubmitType() {
		return submitType;
	}
	public void setSubmitType(Long submitType) {
		this.submitType = submitType;
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public Long getFreezeFlag() {
        return freezeFlag;
    }
    public void setFreezeFlag(Long freezeFlag) {
        this.freezeFlag = freezeFlag;
    }
    public Long getAuditFlag() {
        return auditFlag;
    }
    public void setAuditFlag(Long auditFlag) {
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
    public Long getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public Long getCarStateType() {
        return carStateType;
    }
    public void setCarStateType(Long carStateType) {
        this.carStateType = carStateType;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
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
    public Long getNewOrOldAppUser() {
        return newOrOldAppUser;
    }
    public void setNewOrOldAppUser(Long newOrOldAppUser) {
        this.newOrOldAppUser = newOrOldAppUser;
    }
    public Long getBaiduChannelId() {
        return baiduChannelId;
    }
    public void setBaiduChannelId(Long baiduChannelId) {
        this.baiduChannelId = baiduChannelId;
    }
    public Long getBaiduUserId() {
        return baiduUserId;
    }
    public void setBaiduUserId(Long baiduUserId) {
        this.baiduUserId = baiduUserId;
    }
}