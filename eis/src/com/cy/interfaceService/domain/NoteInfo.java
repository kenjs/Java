package com.cy.interfaceService.domain;

/**
 * Created by haoy on 2014/10/22.
 */
public class NoteInfo {

    private String phoneNum;
    private int returnValue;
    private long logKey;

    public NoteInfo(String phoneNum, int returnValue, long logKey) {
        this.phoneNum = phoneNum;
        this.returnValue = returnValue;
        this.logKey = logKey;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(int returnValue) {
        this.returnValue = returnValue;
    }

    public long getLogKey() {
        return logKey;
    }

    public void setLogKey(long logKey) {
        this.logKey = logKey;
    }
}
