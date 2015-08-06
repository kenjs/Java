package com.cy.common.bo;

import java.io.Serializable;
import java.util.Date;
/**
 * 评价货源
 * @date 2014-6-9
 * @author haoyong
 *
 */
public class DriverUserAssessInfoBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6865349921679557241L;

    private int id;//主键（自增、初始值1）
    private int cargoId;//货源ID
    private int driverId;//司机ID
    private int userId;//用户id（企业用户）
    private int transactionId;//交易订单Id
    private int assessEvaluateScore;//评分
    private String assess;//评语
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间
	public DriverUserAssessInfoBo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCargoId() {
		return cargoId;
	}
	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAssessEvaluateScore() {
		return assessEvaluateScore;
	}
	public void setAssessEvaluateScore(int assessEvaluateScore) {
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
}
