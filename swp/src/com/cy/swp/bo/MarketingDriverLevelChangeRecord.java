package com.cy.swp.bo;

import java.util.Date;

/**
 * 客户司机等级变更记录表(非客户类别，而是指real_level)
 * Created by nixianjing on 14/12/9.
 */
public class MarketingDriverLevelChangeRecord extends BaseBo {

	private Integer id;//主键
    private Integer customerId;//客户司机ID
    private String beforeChangeLevel;//变更前客户等级(ABCD)
    private String afterChangeLevel;//变更后客户等级(ABCD)
    private Date changeDate;//变更时间
    private Integer beforeChangeAssisterId;//变更前该客户所属专员id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBeforeChangeLevel() {
        return beforeChangeLevel;
    }

    public void setBeforeChangeLevel(String beforeChangeLevel) {
        this.beforeChangeLevel = beforeChangeLevel;
    }

    public String getAfterChangeLevel() {
        return afterChangeLevel;
    }

    public void setAfterChangeLevel(String afterChangeLevel) {
        this.afterChangeLevel = afterChangeLevel;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public Integer getBeforeChangeAssisterId() {
        return beforeChangeAssisterId;
    }

    public void setBeforeChangeAssisterId(Integer beforeChangeAssisterId) {
        this.beforeChangeAssisterId = beforeChangeAssisterId;
    }
}
