package com.cy.dctms.common.bo;

import java.util.Date;
public class DriverUserAssessInfo {
    private Long id;  // 主键（自增、初始值1）
    private Long cargoId;  // 货源ID
    private Long driverId;  // 司机ID
    private Long userId;  // 用户id（企业用户）
    private Long transactionId;  // 交易订单Id
    private Long assessEvaluateScore;  // 评分
    private String assess;  // 评语
    private Date createTime;  // 创建时间
    private Date modifyTime;  // 修改时间
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCargoId() {
        return cargoId;
    }
    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }
    public Long getDriverId() {
        return driverId;
    }
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    public Long getAssessEvaluateScore() {
        return assessEvaluateScore;
    }
    public void setAssessEvaluateScore(Long assessEvaluateScore) {
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