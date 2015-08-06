package com.cy.dcts.common.domain;

import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * 司机预约表
 * @author nxj
 *
 */
public class DriverBusinessLineInfoDomain extends BaseDomain {
	private static final long serialVersionUID = -5978700588325500568L;
	
	private String id;
	private String driverId;//司机Id 
	private String startTime;//预约开始时间
	private String endTime;//预约结束时间
	private String startProvince;//预约路线起-省
	private String startCity;//预约路线起-市
	private String startCounty;//预约路线起-县区
	private String endProvince;//预约路线目-省
	private String endCity;//预约路线目-市
	private String endCounty;//预约路线目-县区
	private String quoteFair;//运费价格
	private String quoteType;//报价类型
	private String start;//状态
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	
	private String startBusinessLine;// 起始预约线路
	private String endBusinessLine;// 目的预约线路
	
	private String code;
	private String carNumber;//车辆号码
	private String carLength;//车长
	private String carWeight;//运力-吨位
	private String carCubage;//运力-体积
	private String carPlateType;//板-平板、高低板
	private String carBarType;//栏-高栏、低栏
	private String carTypes;//车辆类型（长、板、栏、吨位、体积）
	private String auditFlag;//审核标志（0:审核中,1:审核通过,-1审核未通过）
	private String freezeFlag;//冻结标志（0:正常，1:冻结)
	private String deleteFlag;//删除标志（1:已删除,0:未删除）
	
	private String startPcc;//开始地址
	
	private String endPcc;//结束地址
	
	
	
	private List<DriverBusinessLineInfoDomain> list;
	
	
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public String getStartCounty() {
		return startCounty;
	}
	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
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
	public String getEndCounty() {
		return endCounty;
	}
	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}
	public String getQuoteFair() {
		return quoteFair;
	}
	public void setQuoteFair(String quoteFair) {
		this.quoteFair = quoteFair;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
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
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
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
	public String getStartPcc() {
		return startPcc;
	}
	public void setStartPcc(String startPcc) {
		this.startPcc = startPcc;
	}
	public String getEndPcc() {
		return endPcc;
	}
	public void setEndPcc(String endPcc) {
		this.endPcc = endPcc;
	}
	public List<DriverBusinessLineInfoDomain> getList() {
		return list;
	}
	public void setList(List<DriverBusinessLineInfoDomain> list) {
		this.list = list;
	}
	public String getCarTypes() {
		StringBuffer str = new StringBuffer();
		if(StringUtils.isNotEmpty(this.getCarLength())) {
			str.append(this.getCarLength());
		}
		if(StringUtils.isNotEmpty(this.getCarPlateType())) {
			if(StringUtils.isNotEmpty(str.toString())) {
				str.append(this.getCarPlateType());
			}else {
				str.append("/"+this.getCarPlateType());
			}
		}
		if(StringUtils.isNotEmpty(this.getCarBarType())) {
			if(StringUtils.isNotEmpty(str.toString())) {
				str.append(this.getCarBarType());
			}else {
				str.append("/"+this.getCarBarType());
			}
		}
		carTypes = str.toString();
		return carTypes;
	}
	public void setCarTypes(String carTypes) {
		this.carTypes = carTypes;
	}
	public String getAuditFlag() {
		return auditFlag;
	}
	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}
	public String getFreezeFlag() {
		return freezeFlag;
	}
	public void setFreezeFlag(String freezeFlag) {
		this.freezeFlag = freezeFlag;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStartBusinessLine() {
		return startBusinessLine;
	}
	public void setStartBusinessLine(String startBusinessLine) {
		this.startBusinessLine = startBusinessLine;
	}
	public String getEndBusinessLine() {
		return endBusinessLine;
	}
	public void setEndBusinessLine(String endBusinessLine) {
		this.endBusinessLine = endBusinessLine;
	}
	
}
