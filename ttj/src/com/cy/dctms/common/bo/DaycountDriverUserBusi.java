package com.cy.dctms.common.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by haoy on 2014/9/5.
 */
public class DaycountDriverUserBusi implements Serializable{

    private int id;
    private Date recordDay;
    private int driverId;           //关联app用户id
    private int noConfirmOrders;
    private int noAssessmentOrders;
    private int supplyFinds;        //点击找货+1/次/用户
    private int priceQuotes;        //报价+1/次/用户
    private int priceQuotesSucceed; //报价被订车+1/次/用户
    private int forOrders;          //在线被订车+1/次/用户
    private int transactions;       //订单完成+1/单/用户
    private int callCargoNum;       //货源拨打电话次数
    private int callTransactionNum; //订单拨打电话次数

    public DaycountDriverUserBusi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRecordDay() {
        return recordDay;
    }

    public void setRecordDay(Date recordDay) {
        this.recordDay = recordDay;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getNoConfirmOrders() {
        return noConfirmOrders;
    }

    public void setNoConfirmOrders(int noConfirmOrders) {
        this.noConfirmOrders = noConfirmOrders;
    }

    public int getNoAssessmentOrders() {
        return noAssessmentOrders;
    }

    public void setNoAssessmentOrders(int noAssessmentOrders) {
        this.noAssessmentOrders = noAssessmentOrders;
    }

    public int getSupplyFinds() {
        return supplyFinds;
    }

    public void setSupplyFinds(int supplyFinds) {
        this.supplyFinds = supplyFinds;
    }

    public int getPriceQuotes() {
        return priceQuotes;
    }

    public void setPriceQuotes(int priceQuotes) {
        this.priceQuotes = priceQuotes;
    }

    public int getForOrders() {
        return forOrders;
    }

    public void setForOrders(int forOrders) {
        this.forOrders = forOrders;
    }

    public int getTransactions() {
        return transactions;
    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    public int getPriceQuotesSucceed() {
        return priceQuotesSucceed;
    }

    public void setPriceQuotesSucceed(int priceQuotesSucceed) {
        this.priceQuotesSucceed = priceQuotesSucceed;
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
