package com.cy.dctms.common.bo;

import java.util.Date;
public class UserDriverAssessInfo {
    private Long id;  // ��������������ʼֵ1��
    private Long driverId;  // ˾��ID
    private Long cargoId;  // ����ID
    private Long userId;  // ������id
    private Long transactionId;  // ��������Id
    private Long arriverEvaluateScore;  // �����ٶȣ����֣�
    private Long serveEvaluateScore;  // ˾������̬�ȣ����֣�
    private Long tradeEvaluateScore;  // ����������
    private String assess;  // �û���д����
    private Date createTime;  // ����ʱ��
    private Date modifyTime;  // �޸�ʱ��
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