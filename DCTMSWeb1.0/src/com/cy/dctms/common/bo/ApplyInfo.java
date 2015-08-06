package com.cy.dctms.common.bo;

import java.util.Date;
public class ApplyInfo {
    private Long id;  // 主键ID
    private Long userId;  // 申请人ID
    private Long companyId;  // 申请公司ID
    private Long applyType;  // 申请功能类型(0：货主版功能、1：身份证查询功能、2：VIP功能)
    private Date applyTime;  // 申请时间
    private String contactName;  // 申请人姓名
    private String contactTelephone;  // 申请人电话
    private String applyComment;  // 申请内容
    private Date verifyTime;  // 审核时间
    private Long verifyStart;  // 审核状态（0等待审核、-1审核失败、1审核成功）
    private String verifyComment;  // 审核回复内容
    private Long operatorId;  // 操作人ID
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public Long getApplyType() {
        return applyType;
    }
    public void setApplyType(Long applyType) {
        this.applyType = applyType;
    }
    public Date getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactTelephone() {
        return contactTelephone;
    }
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }
    public String getApplyComment() {
        return applyComment;
    }
    public void setApplyComment(String applyComment) {
        this.applyComment = applyComment;
    }
    public Date getVerifyTime() {
        return verifyTime;
    }
    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }
    public Long getVerifyStart() {
        return verifyStart;
    }
    public void setVerifyStart(Long verifyStart) {
        this.verifyStart = verifyStart;
    }
    public String getVerifyComment() {
        return verifyComment;
    }
    public void setVerifyComment(String verifyComment) {
        this.verifyComment = verifyComment;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}