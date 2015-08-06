package com.cy.interfaceService.bo;

import java.io.Serializable;

/**
 * 短信黑名单表
 * Created by nixianjing on 15/1/28.
 */
public class NotesendBlacklist implements Serializable {

    private Integer id;
    private String mobilephone;
    private Integer currentIsreg;
    private Integer intoCause;
    private Integer blackKind;
    private Integer deletedFlag;
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Integer getCurrentIsreg() {
        return currentIsreg;
    }

    public void setCurrentIsreg(Integer currentIsreg) {
        this.currentIsreg = currentIsreg;
    }

    public Integer getIntoCause() {
        return intoCause;
    }

    public void setIntoCause(Integer intoCause) {
        this.intoCause = intoCause;
    }

    public Integer getBlackKind() {
        return blackKind;
    }

    public void setBlackKind(Integer blackKind) {
        this.blackKind = blackKind;
    }

    public Integer getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Integer deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
