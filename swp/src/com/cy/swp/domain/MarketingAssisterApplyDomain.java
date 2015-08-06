package com.cy.swp.domain;

/**
 * 客户分配申请表domain
 * Created by wyh on 2014/12/9.
 */
public class MarketingAssisterApplyDomain extends BaseDomain {
    private static final long serialVersionUID = 7470971186500958172L;
    private Integer id;
    private Integer assisterId;//申请者ID(营销专员id)
    private Integer customerKind;//1 客户企业  2 客户司机(默认值2)
    private Integer customerId;//客户id
    private String applyDate;//申请日期
    private Integer auditStatus;//-1 申请未通过 0 等待审核 1 审核通过(默认值0)
    private String auditTime;//审核时间
    private Integer auditerId;//审核人id

    private String assisterName;//申请人姓名
    private String assisterJoinGroup;//申请人所属组： 0 未分组1 营销一组 2 营销二组 3 营销三组
    private String auditerName;//审核人姓名
    private String customerName;//客户名称
    private String customerMphone;//客户手机号码
    private String customerCarNumber;//客户司机车牌号

    public String getAssisterName() {
        return assisterName;
    }

    public void setAssisterName(String assisterName) {
        this.assisterName = assisterName;
    }

    public String getAssisterJoinGroup() {
        return assisterJoinGroup;
    }

    public void setAssisterJoinGroup(String assisterJoinGroup) {
        this.assisterJoinGroup = assisterJoinGroup;
    }

    public String getAuditerName() {
        return auditerName;
    }

    public void setAuditerName(String auditerName) {
        this.auditerName = auditerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMphone() {
        return customerMphone;
    }

    public void setCustomerMphone(String customerMphone) {
        this.customerMphone = customerMphone;
    }

    public String getCustomerCarNumber() {
        return customerCarNumber;
    }

    public void setCustomerCarNumber(String customerCarNumber) {
        this.customerCarNumber = customerCarNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssisterId() {
        return assisterId;
    }

    public void setAssisterId(Integer assisterId) {
        this.assisterId = assisterId;
    }

    public Integer getCustomerKind() {
        return customerKind;
    }

    public void setCustomerKind(Integer customerKind) {
        this.customerKind = customerKind;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getAuditerId() {
        return auditerId;
    }

    public void setAuditerId(Integer auditerId) {
        this.auditerId = auditerId;
    }
}
