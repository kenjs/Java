package com.cy.dcts.common.domain;

import java.util.List;

/**
 * 车辆位置历史信息表
 * 
 * @author zdy
 * 
 */
public class LocationCollectInfoDomain extends BaseDomain {
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String driverId;//车源ID
	private String longitude;//经度
	private String latitude;//纬度
	private String province;//省
	private String city;//市
	private String county;//区县
	private String town;//位置-乡镇、街道(包含其它)
	private String location;//完整位置信息
	private String collectTime;//位置采集时间
	private String createTime;//创建时间
    
	private String searchLocation;//位置 省-市-区
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String tradeCrateTime;//交易创建时间
	private String tradeModifyTime;//交易状态修改时间
	
	private String tradeId;//交易Id
	private String tradeStart;// 交易状态
	private String afterLoadTradeModifyTime; //待装货状态的修改时间
	private String arrivedTradeModifyTime;  //订单完成(交易成功)状态的修改时间

	private List<LocationCollectInfoDomain> list;
	
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

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public List<LocationCollectInfoDomain> getList() {
		return list;
	}

	public void setList(List<LocationCollectInfoDomain> list) {
		this.list = list;
	}

	public String getSearchLocation() {
		return searchLocation;
	}

	public void setSearchLocation(String searchLocation) {
		this.searchLocation = searchLocation;
	}

	public String getTradeCrateTime() {
		return tradeCrateTime;
	}

	public void setTradeCrateTime(String tradeCrateTime) {
		this.tradeCrateTime = tradeCrateTime;
	}

	public String getTradeModifyTime() {
		return tradeModifyTime;
	}

	public void setTradeModifyTime(String tradeModifyTime) {
		this.tradeModifyTime = tradeModifyTime;
	}

	public String getAfterLoadTradeModifyTime() {
		return afterLoadTradeModifyTime;
	}

	public void setAfterLoadTradeModifyTime(String afterLoadTradeModifyTime) {
		this.afterLoadTradeModifyTime = afterLoadTradeModifyTime;
	}

	public String getArrivedTradeModifyTime() {
		return arrivedTradeModifyTime;
	}

	public void setArrivedTradeModifyTime(String arrivedTradeModifyTime) {
		this.arrivedTradeModifyTime = arrivedTradeModifyTime;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeStart() {
		return tradeStart;
	}

	public void setTradeStart(String tradeStart) {
		this.tradeStart = tradeStart;
	}


}
