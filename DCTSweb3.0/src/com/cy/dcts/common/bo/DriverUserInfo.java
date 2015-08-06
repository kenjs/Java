package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * 司机表
 * 
 * @author zdy
 * 
 */
public class DriverUserInfo extends BaseBo {
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String code;//登录帐号(手机号)
	private String password;//登录密码(MD5)
	private String name;//司机姓名
	//private String mobilePhone;//手机
	private String carNumber;//车辆号码
	//private String carType;
	private String freezeFlag;//冻结标志（0:正常，1:冻结)
	private String auditFlag;//审核标志（0:审核中,1:审核通过,-1审核未通过）
	private String telephone;//联系电话
	private String identityLicenseNum;//身份证
	private String operatingLicense;//营运证号码
	private String carLength;//车长
	private String carWeight;//运力-吨位
	private String remark;//备注
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	private String drivingLicense;//行驶证号码
	private String driversLicense;//驾驶证号
	private String deleteFlag;//删除标志（1:已删除,0:未删除）
	private String carStateType;//当前运营状态（1空车求货、2满载运营、3休息(默认空车求货)）
	private String carCubage;//运力-体积
	private String carPlateType;//板-平板、高低板
	private String carBarType;//栏-高栏、低栏
	//private Date carStateModifyTime;//运营状态更新时间
	//private String companyCode;//用户/车辆所属公司CODE
	private String carTypes;//车型（车长、车宽、板、栏、运力等合起来）
	private String identityLicenseNumFront;//司机身份证正面照片
	private String identityLicenseNumContrary;//司机身份证反面照片
	
	private String baiduChannelId;//201407013 百度云推送channelId 例子：提醒司机确认拉货(司机在注册登录的时候会生成)
	private String baiduUserId;//201407013百度云推送userId 例子：提醒司机确认拉货(司机在注册登录的时候会生成)

	private String submitType;//审核提交标识 20140728
	
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getOperatingLicense() {
		return operatingLicense;
	}

	public void setOperatingLicense(String operatingLicense) {
		this.operatingLicense = operatingLicense;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public String getDriversLicense() {
		return driversLicense;
	}

	public void setDriversLicense(String driversLicense) {
		this.driversLicense = driversLicense;
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

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

}
