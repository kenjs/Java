package com.cy.driver.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 货源关注
 * @date 2014-6-11
 * @author haoyong
 *
 */
public class DriverCargoCollectInfoBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3867282160816517725L;

	private int id;           //  主键（自增、初始值1）
	private int driverId;     //  司机ID
	private int cargoId;       //  货物ID
	private Date createTime;//  点评时间
	
	public DriverCargoCollectInfoBo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public int getCargoId() {
		return cargoId;
	}
	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
