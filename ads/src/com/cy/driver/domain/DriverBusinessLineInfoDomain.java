package com.cy.driver.domain;

/**
 * WEB司机预约线路domain
 * 2014-5-27
 * @author haoyong
 *
 */
public class DriverBusinessLineInfoDomain {

	private static final long serialVersionUID = 6250162048423414506L;

	private String id;//主键（自增、初始值1）
	private String driverId;//车源ID
	private String startTime;//预约开始时间
	private String endTime;//预约结束间
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
	
	public DriverBusinessLineInfoDomain() {
		super();
	}

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
	
	
}
