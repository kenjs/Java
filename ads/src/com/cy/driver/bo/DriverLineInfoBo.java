package com.cy.driver.bo;

import java.io.Serializable;
/**
 * WEB司机线路bo
 * @date 2014-6-3
 * @author haoyong
 *
 */
public class DriverLineInfoBo implements Serializable{

	private static final long serialVersionUID = -3666715963878940096L;

	private int id;//主键（自增、初始值1）
	private String startProvince;//起始地-省
	private String startCity;//起始地-市
	private String startCounty;//起始地-区
	private String endProvince;//目的地-省
	private String endCity;//目的地-市
	private String endCounty;//目的地-区
	private String driverId;//司机id
	private String start;//状态
	private String lineType;//类型（1单线2双线）
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	
	public DriverLineInfoBo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
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
