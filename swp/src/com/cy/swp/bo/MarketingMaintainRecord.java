package com.cy.swp.bo;

import java.util.Date;

/**
 * 客户维护记录表
 * Created by nixianjing on 14/12/9.
 */
public class MarketingMaintainRecord extends BaseBo{

	private Integer id;//主键
    private Integer assisterId;//营销专员id
    private Integer customerKind;//1 客户企业  2 客户司机
    private Integer category;//客户类别(1 2 3 4 5)
    private Integer customerId;//客户id
    private Integer maintainAction;//维护动作类型:1 电话联系 2 发送短信 3 修改信息
    private Integer isValidCall;//是否有效电话： 0 否 1 是
    private String recordFilePath;//录音文件PATH
    private String recordContent;//记录内容
    private Date actionTime;//维护动作时间


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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMaintainAction() {
        return maintainAction;
    }

    public void setMaintainAction(Integer maintainAction) {
        this.maintainAction = maintainAction;
    }

    public Integer getIsValidCall() {
        return isValidCall;
    }

    public void setIsValidCall(Integer isValidCall) {
        this.isValidCall = isValidCall;
    }

    public String getRecordFilePath() {
        return recordFilePath;
    }

    public void setRecordFilePath(String recordFilePath) {
        this.recordFilePath = recordFilePath;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}
