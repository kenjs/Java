package com.cy.dctms.common.bo;

import java.io.Serializable;

/**
 * Created by haoy on 2014/12/24.
 */
public class MarketingDriverLevelChangeRecord implements Serializable {
    private int id;
    private String customerId;//客户司机ID
    private String beforeChangeLevel;//变更前客户等级(ABCD)
    private String afterChangeLevel;//  更后客户等级(ABCD)
    private String changeDate;//变更时间
    private String beforeChangeAssisterId;//变更前专员id

    public MarketingDriverLevelChangeRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
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

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getBeforeChangeAssisterId() {
        return beforeChangeAssisterId;
    }

    public void setBeforeChangeAssisterId(String beforeChangeAssisterId) {
        this.beforeChangeAssisterId = beforeChangeAssisterId;
    }
}
