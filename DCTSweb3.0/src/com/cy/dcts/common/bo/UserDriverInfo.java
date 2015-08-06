package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * 用户车辆收藏表
 * 
 * @author zdy
 * 
 */
public class UserDriverInfo extends BaseBo {
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String userId;//用户id
	private String driverId;//司机Id
	private String start;//是否删除状态
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

}
