package com.cy.dctms.common.bo;

import java.util.Date;
public class UserDriverAssessInfo {
    private Long id;  // 主键（自增、初始值1）
    private Long driverId;  // 司机ID
    private Long cargoId;  // 货物ID
    private Long userId;  // 评价人id
    private Long transactionId;  // 订单交易Id
    private Long arriverEvaluateScore;  // 到达速度（评分）
    private Long serveEvaluateScore;  // 司机服务态度（评分）
    private Long tradeEvaluateScore;  // 交易总评价
    private String assess;  // 用户填写评语
    private Date createTime;  // 创建时间
    private Date modifyTime;  // 修改时间
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDriverId() {
        return driverId;
    }
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
    public Long getCargoId() {
        return cargoId;
    }
    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
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
    public Long getArriverEvaluateScore() {
        return arriverEvaluateScore;
    }
    public void setArriverEvaluateScore(Long arriverEvaluateScore) {
        this.arriverEvaluateScore = arriverEvaluateScore;
    }
    public Long getServeEvaluateScore() {
        return serveEvaluateScore;
    }
    public void setServeEvaluateScore(Long serveEvaluateScore) {
        this.serveEvaluateScore = serveEvaluateScore;
    }
    public Long getTradeEvaluateScore() {
        return tradeEvaluateScore;
    }
    public void setTradeEvaluateScore(Long tradeEvaluateScore) {
        this.tradeEvaluateScore = tradeEvaluateScore;
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