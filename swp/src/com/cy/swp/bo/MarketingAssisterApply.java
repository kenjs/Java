package com.cy.swp.bo;

import java.util.Date;

/**
 * 客户分配申请表
 * Created by wyh on 2014/12/9.
 */
public class MarketingAssisterApply extends BaseBo {
    private static final long serialVersionUID = -8966670443402330646L;
    private Integer id;
    private Integer assisterId;//申请者ID(营销专员id)
    private Integer customerKind;//1 客户企业  2 客户司机(默认值2)
    private Integer customerId;//客户id
    private Date applyDate;//申请日期
    private Integer auditStatus;//-1 申请未通过 0 等待审核 1 审核通过(默认值0)
    private Date auditTime;//审核时间
    private Integer auditerId;//审核人id

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

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getAuditerId() {
        return auditerId;
    }

    public void setAuditerId(Integer auditerId) {
        this.auditerId = auditerId;
    }
}
