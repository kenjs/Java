package com.cy.driver.domain;

/**
 * Created by haoy on 2014/9/23.
 */
public class DriverCallDomain {
    private String driverId;
    private int callCargoNum;
    private int callTransactionNum;

    public DriverCallDomain() {
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public int getCallCargoNum() {
        return callCargoNum;
    }

    public void setCallCargoNum(int callCargoNum) {
        this.callCargoNum = callCargoNum;
    }

    public int getCallTransactionNum() {
        return callTransactionNum;
    }

    public void setCallTransactionNum(int callTransactionNum) {
        this.callTransactionNum = callTransactionNum;
    }
}
