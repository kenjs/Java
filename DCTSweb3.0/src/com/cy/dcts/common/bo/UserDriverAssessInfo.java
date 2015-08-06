package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * 货主对司机评价
 * 
 * @author zdy
 * 
 */



public class UserDriverAssessInfo {
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String driverId;//司机ID
	private String cargoId;//货物ID
	private String transactionId;//交易订单ID (20140704)
	private String userId;//评价人id
	private String arriverEvaluateScore;//到达速度（评分）
	private String serveEvaluateScore;//司机服务态度（评分）
	private String tradeEvaluateScore;//交易总评价
	private String assess;//用户填写评语
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间

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

	public String getCargoId() {
		return cargoId;
	}

	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getArriverEvaluateScore() {
		return arriverEvaluateScore;
	}

	public void setArriverEvaluateScore(String arriverEvaluateScore) {
		this.arriverEvaluateScore = arriverEvaluateScore;
	}

	public String getServeEvaluateScore() {
		return serveEvaluateScore;
	}

	public void setServeEvaluateScore(String serveEvaluateScore) {
		this.serveEvaluateScore = serveEvaluateScore;
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

	public String getTradeEvaluateScore() {
		return tradeEvaluateScore;
	}

	public void setTradeEvaluateScore(String tradeEvaluateScore) {
		this.tradeEvaluateScore = tradeEvaluateScore;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
