package com.cy.dctms.common.bo;

import java.util.Date;
public class DriverUserInfo {
    private Long id;  // 主键（自增、初始值1）
    private String code;  // 登录帐号(手机号)
    private String password;  // 登录密码(MD5)
    private String name;  // 司机姓名
    private String carNumber;  // 车辆牌照号码
    private Long freezeFlag;  // 冻结标志（0:正常，1:冻结)
    private Long auditFlag;  // 审核标志（0:审核中,1:通过,-1未通过）
    private String telephone;  // 联系电话
    private String identityLicenseNum;  // 身份证
    private String operatingLicense;  // 营运证路径
    private String driversLicense;  // 驾驶证路径
    private String drivingLicense;  // 行驶证路径
    private String carLength;  // 车长
    private String carWeight;  // 运力-吨位
    private String carCubage;  // 运力-体积
    private String carPlateType;  // 板-平板、高低板
    private String carBarType;  // 栏-高栏、低栏
    private String carTypes;  // 车型（车长、板、栏、吨，方等合起来）
    private String remark;  // 备注
    private Long deleteFlag;  // 删除标志（1:已删除,0:未删除）
    private Long carStateType;  // 当前运营状态（默认1求货、2满载、3休息）
    private Date createTime;  // 创建时间
    private Date modifyTime;  // 修改时间
    private String headPortrait;  // 用户头像
    private String identityLicenseNumFront;  // 司机身份证正面照片
    private String identityLicenseNumContrary;  // 司机身份证反面照片
    private Long newOrOldAppUser;  // 新老用户标识（默认0（0新用户，1老用户））
    private Long baiduChannelId;  // 百度云推送channelId
    private Long baiduUserId;  // 百度云推送userId
    private Long submitType;//审核标志
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