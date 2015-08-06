package com.cy.common.bo;

import java.io.Serializable;

public class DriverUserInfoBo implements Serializable{

	private static final long serialVersionUID = -8035002082438287481L;

	private int id;//主键（自增、初始值1）
	private String code;//登录帐号(手机号)
	private String newCode;
	private String password;//登录密码(MD5)
	private String name;//司机姓名
	private String carNumber;//车辆牌照号码
	private String freezeFlag;//冻结标志（0:正常，1:冻结)
	private String auditFlag;//审核标志（0:审核中,1:通过,-1未通过）
	private String telephone;//联系电话
	private String identityLicenseNum;//身份证
	private String operatingLicense;//营运证路径
    private String driversLicense;//驾驶证路径
	private String drivingLicense;//行驶证路径
    private String carLength;//车长
	private String carWeight;//运力-吨位
	private String carCubage;//运力-体积
	private String carPlateType;//板-平板、高低板
	private String carBarType;//栏-高栏、低栏
	private String carTypes;//车型（车长、板、栏、运力等合起来)
	private String remark;//备注
	private String deleteFlag;//删除标志（1:已删除,0:未删除）
	private String carStateType;//当前运营状态（默认1求货、2满载、3休息）
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	private String headPortrait;//用户头像 
	
	private String identityLicenseNumFront;//司机身份证正面照片
	private String identityLicenseNumContrary;//司机身份证反面照片
	private String newOrOldAppUser;//新老用户标识（默认0（0新用户，1老用户））
	
	private String submitType;//审核提交标识（0未提交1已提交2审核未通过3审核以通过）
	
	private String mobileBrand;//手机品牌
	private String operatingSystemVersionNnumber;//操作系统版本号
	private String mobilePhoneModel;//手机型号
	private String noIimei;//IMEI号
	
	public DriverUserInfoBo() {
		super();
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNewCode() {
		return newCode;
	}
	public void setNewCode(String newCode) {
		this.newCode = newCode;
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
	public String getNewOrOldAppUser() {
		return newOrOldAppUser;
	}
	public void setNewOrOldAppUser(String newOrOldAppUser) {
		this.newOrOldAppUser = newOrOldAppUser;
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
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	public String getMobileBrand() {
		return mobileBrand;
	}
	public void setMobileBrand(String mobileBrand) {
		this.mobileBrand = mobileBrand;
	}
	public String getOperatingSystemVersionNnumber() {
		return operatingSystemVersionNnumber;
	}
	public void setOperatingSystemVersionNnumber(
			String operatingSystemVersionNnumber) {
		this.operatingSystemVersionNnumber = operatingSystemVersionNnumber;
	}
	public String getMobilePhoneModel() {
		return mobilePhoneModel;
	}
	public void setMobilePhoneModel(String mobilePhoneModel) {
		this.mobilePhoneModel = mobilePhoneModel;
	}
	public String getNoIimei() {
		return noIimei;
	}
	public void setNoIimei(String noIimei) {
		this.noIimei = noIimei;
	}
}
