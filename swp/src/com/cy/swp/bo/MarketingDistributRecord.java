package com.cy.swp.bo;

import java.util.Date;

/**
 * 客户分配记录表
 * Created by wyh on 2014/12/17.
 */
public class MarketingDistributRecord extends BaseBo {
    private static final long serialVersionUID = -7564995679287632852L;

    private Integer id;
    private Integer customerKind;//1 客户企业  2 客户司机
    private Integer customerId;//客户id
    private Integer distributerId;//分配者id
    private Integer assisterId;//专员id
    private Date distributTime;//分配时间
    private String customerLevel;//客户分配时的等级：A B C D
    private Integer distributType;//1 绑定 -1 解绑

    public Integer getDistributType() {
        return distributType;
    }

    public void setDistributType(Integer distributType) {
        this.distributType = distributType;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDistributerId() {
        return distributerId;
    }

    public void setDistributerId(Integer distributerId) {
        this.distributerId = distributerId;
    }

    public Integer getAssisterId() {
        return assisterId;
    }

    public void setAssisterId(Integer assisterId) {
        this.assisterId = assisterId;
    }

    public Date getDistributTime() {
        return distributTime;
    }

    public void setDistributTime(Date distributTime) {
        this.distributTime = distributTime;
    }
}
