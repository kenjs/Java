package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * 司机对货主评价
 * 
 * @author zdy
 * 
 */
public class DriverUserAssessInfo extends BaseBo {
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String cargoId;//货源ID
	private String driverId;//司机Id
	private String transactionId;//交易订单ID (20140704)
	private String userId;//用户id（企业即物流公司用户，公司和用户是一对一的关系）
	private String assessEvaluateScore;//评分
	private String assess;//评语
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCargoId() {
		return cargoId;
	}

	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
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

	public String getAssessEvaluateScore() {
		return assessEvaluateScore;
	}

	public void setAssessEvaluateScore(String assessEvaluateScore) {
		this.assessEvaluateScore = assessEvaluateScore;
	}

	public String getAssess() {
		return assess;
	}

	public void setAssess(String assess) {
		this.assess = assess;
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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
