package com.cy.dcts.common.domain;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.constants.Constants;

/**
 * 司机表
 * 
 * @author zdy
 *  
 */
public class DriverUserInfoDomain extends BaseDomain {
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String code;//登录帐号(手机号)
	private String password;//登录密码(MD5)
	private String name;//司机姓名
	private String carNumber;//车辆号码
	private String freezeFlag;//冻结标志（0:正常，1:冻结)
	private String auditFlag;//审核标志（0:审核中,1:审核通过,-1审核未通过）
	private String telephone;//联系电话
	private String identityLicenseNum;//身份证
	private String operatingLicense;//营运证号码
	private String carLength;//车长
	private String carWeight;//运力-吨位
	private String remark;//备注
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	private String drivingLicense;//行驶证号码
	private String driversLicense;//驾驶证号
	private String deleteFlag;//删除标志（1:已删除,0:未删除）
	private String carStateType;//当前运营状态（1空车求货、2满载运营、3休息(默认空车求货)）
	private String carCubage;//运力-体积
	private String carPlateType;//板-平板、高低板
	private String carBarType;//栏-高栏、低栏
	private String carTypes;//车型（车长、车宽、板、栏、运力等合起来）
	private String identityLicenseNumFront;//司机身份证正面照片
	private String identityLicenseNumContrary;//司机身份证反面照片
	
	private String baiduChannelId;// 201407013百度云推送channelId (司机在登录的时候会生成)
	private String baiduUserId;//201407013百度云推送userId(司机在登录的时候会生成)
	
	private String submitType;//审核提交标识 20140728
	
	private String lastLocation;// 当前位置
	private String province;//当前位置省
	private String city;//当前位置市
	private String county;//当前位置区
	private String searchLastLocation;// 页面查询当前位置
	private String driverLine;// 司机运营路线
	private String driverBusinessLine;//司机预约线路
	private String startProCityCounty;//装货地
	private String endProCityCounty;//卸货地
	private double quoteFair;//运费的报价
	private String quoteType;//报价类型
	private String orderId;//货源Id
	private String startProvince;//开始省
	private String startCity;//开始市
	private String startCounty;//开始区
	private String endProvince;//结束省
	private String endCity;//结束市
	private String endCounty;//结束区
	
	private String startPcc;//开始地区
	private String endPcc;//结束地区
	
	private String locationModifyTime;//当请位置的修改时间
	private String longitude;//经度
	private String latitude;//纬度
	private String quoteTypeVal;//报价类型值
	private String transactionStep;
	private String menuAId;//个人中心左边菜单超链接的Id
	private List<DriverUserInfoDomain> list;
	
	private String lineType;//线路类型 1运营线路2预约线路

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

	public String getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(String lastLocation) {
		this.lastLocation = lastLocation;
	}

	public String getDriverLine() {
		return driverLine;
	}

	public void setDriverLine(String driverLine) {
		this.driverLine = driverLine;
	}

	public List<DriverUserInfoDomain> getList() {
		return list;
	}

	public void setList(List<DriverUserInfoDomain> list) {
		this.list = list;
	}

	public String getStartProCityCounty() {
		return startProCityCounty;
	}

	public void setStartProCityCounty(String startProCityCounty) {
		this.startProCityCounty = startProCityCounty;
	}

	public String getEndProCityCounty() {
		return endProCityCounty;
	}

	public void setEndProCityCounty(String endProCityCounty) {
		this.endProCityCounty = endProCityCounty;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public double getQuoteFair() {
		return quoteFair;
	}

	public void setQuoteFair(double quoteFair) {
		this.quoteFair = quoteFair;
	}

	public String getMenuAId() {
		return menuAId;
	}

	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}

	public String getSearchLastLocation() {
		return searchLastLocation;
	}

	public void setSearchLastLocation(String searchLastLocation) {
		this.searchLastLocation = searchLastLocation;
	}

	public String getStartProvince() {
		return startProvince;
	}

	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndProvince() {
		return endProvince;
	}

	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStartCounty() {
		return startCounty;
	}

	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}

	public String getEndCounty() {
		return endCounty;
	}

	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}

	public String getStartPcc() {
		StringBuffer start = new StringBuffer();
		if(StringUtils.isNotEmpty(getStartProvince())) {
			start.append(getStartProvince());
		}
		if(StringUtils.isNotEmpty(getStartCity())) {
			start.append("-"+getStartCity());
		}
		if(StringUtils.isNotEmpty(getStartCounty())) {
			start.append("-"+getStartCounty());
		}
		startPcc = start.toString();
		return startPcc;
	}

	public void setStartPcc(String startPcc) {
		this.startPcc = startPcc;
	}

	public String getEndPcc() {
		StringBuffer end = new StringBuffer();
		if(StringUtils.isNotEmpty(getEndProvince())) {
			end.append(getEndProvince());
		}
		if(StringUtils.isNotEmpty(getEndCity())) {
			end.append("-"+getEndCity());
		}
		if(StringUtils.isNotEmpty(getEndCounty())) {
			end.append("-"+getEndCounty());
		}
		endPcc = end.toString();
		return endPcc;
	}

	public void setEndPcc(String endPcc) {
		this.endPcc = endPcc;
	}

	public String getTransactionStep() {
		return transactionStep;
	}

	public void setTransactionStep(String transactionStep) {
		this.transactionStep = transactionStep;
	}

	public String getDriverBusinessLine() {
		return driverBusinessLine;
	}

	public void setDriverBusinessLine(String driverBusinessLine) {
		this.driverBusinessLine = driverBusinessLine;
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

	public String getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
		if(this.quoteType==null){
			return;
		}
		try {
			if(Constants.QUOTE_TYPE_VEHICLES_KEY.equals(quoteType)){
				this.setQuoteTypeVal(Constants.QUOTE_TYPE_VEHICLES_VALUE);
			}else if(Constants.QUOTE_TYPE_BY_TON_KEY.equals(quoteType)){
				this.setQuoteTypeVal(Constants.QUOTE_TYPE_BY_TON_VALUE);
			}else if(Constants.QUOTE_TYPE_PRESS_SQUARE_KEY.equals(quoteType)){
				this.setQuoteTypeVal(Constants.QUOTE_TYPE_PRESS_SQUARE_VALUE);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getQuoteTypeVal() {
		return quoteTypeVal;
	}

	public void setQuoteTypeVal(String quoteTypeVal) {
		this.quoteTypeVal = quoteTypeVal;
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

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocationModifyTime() {
		return locationModifyTime;
	}

	public void setLocationModifyTime(String locationModifyTime) {
		this.locationModifyTime = locationModifyTime;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

}
