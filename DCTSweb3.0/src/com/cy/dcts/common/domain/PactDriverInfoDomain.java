package com.cy.dcts.common.domain;

import java.util.List;

public class PactDriverInfoDomain extends BaseDomain{
	private static final long serialVersionUID = 8617332575268144232L;
	
	private String id;
	private String driverId;//司机Id 
	private String userId;//web用户ID
	private String pactStartTime;//合同开始时间
	private String pactEndTime;//合同结束时间
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	private String deletedFlag;//删除标志（0未删除1删除）
	private String pactValidDay;//合同有效时间
	
	private String code;//登录帐号(手机号)
	private String name;//司机姓名
	private String carNumber;//车牌
	private String carTypes;//车辆类型
	private String lastLocation;//当前位置
	private String driverLine;//运营路线
	
	private String startProvince;//开始省
	private String startCity;//开始市
	private String endProvince;//结束省
	private String endCity;//结束市
	private String startProCityCounty;//起始地
	private String endProCityCounty;//目的地
	private List<PactDriverInfoDomain> list;
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public String getPactValidDay() {
		return pactValidDay;
	}
	public void setPactValidDay(String pactValidDay) {
		this.pactValidDay = pactValidDay;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPactStartTime() {
		return pactStartTime;
	}
	public void setPactStartTime(String pactStartTime) {
		this.pactStartTime = pactStartTime;
	}
	public String getPactEndTime() {
		return pactEndTime;
	}
	public void setPactEndTime(String pactEndTime) {
		this.pactEndTime = pactEndTime;
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
	public String getCarTypes() {
		return carTypes;
	}
	public void setCarTypes(String carTypes) {
		this.carTypes = carTypes;
	}
	public List<PactDriverInfoDomain> getList() {
		return list;
	}
	public void setList(List<PactDriverInfoDomain> list) {
		this.list = list;
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
	
}
