package com.cy.driver.bo;

import java.io.Serializable;
import java.util.Date;

public class LocationCollectInfoBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8241941317080947505L;
	
	private int id;
	private String driverId;//车源ID
	private String longitude;//经度
	private String latitude;//纬度
	private String province;//省
	private String city;//市
	private String county;//区县
	private String town;//位置-乡镇、街道(包含其它)
	private String location;//完整位置信息
	private Date collectTime;//创建时间
	private Date createTime;//修改时间
	
	public LocationCollectInfoBo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
