package com.cy.dctms.common.bo;

import java.util.Date;
public class DriverUserAssessInfo {
    private Long id;  // ��������������ʼֵ1��
    private Long cargoId;  // ��ԴID
    private Long driverId;  // ˾��ID
    private Long userId;  // �û�id����ҵ�û���
    private Long transactionId;  // ���׶���Id
    private Long assessEvaluateScore;  // ����
    private String assess;  // ����
    private Date createTime;  // ����ʱ��
    private Date modifyTime;  // �޸�ʱ��
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