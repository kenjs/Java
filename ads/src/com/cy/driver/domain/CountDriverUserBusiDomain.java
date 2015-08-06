package com.cy.driver.domain;

/**
 * Created by haoy on 2014/9/25.
 */
public class CountDriverUserBusiDomain {
    private int id;                     //
    private int driverId;               //关联app用户id
    private int noConfirmOrders;        //（订车+1，取消/确认-1）/单/用户
    private int noAssessmentOrders;     //（卸货+1 取消/已评-1)/单/用户
    private int supplyFinds;            //点击找货+1/次/用户
    private int priceQuotes;            //报价+1/次/用户
    private int priceQuotesSucceed;     //报价被订车+1/次/用户
    private int forOrders;              //在线被订车+1/次/用户
    private int transactions;           //订单完成+1/单/用户
    private int goodEvaluates;          //被企业好评+1/次/用户
    private int generalEvaluates;       //被企业中评+1/次/用户
    private int badEvaluates;           //被企业差评+1/次/用户
    private int callCargoNum;           //货源拨打电话次数
    private int callTransactionNum;     //订单拨打电话次数

    public CountDriverUserBusiDomain() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPriceQuotesSucceed() {
        return priceQuotesSucceed;
    }

    public void setPriceQuotesSucceed(int priceQuotesSucceed) {
        this.priceQuotesSucceed = priceQuotesSucceed;
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

    public int getGoodEvaluates() {
        return goodEvaluates;
    }

    public void setGoodEvaluates(int goodEvaluates) {
        this.goodEvaluates = goodEvaluates;
    }

    public int getGeneralEvaluates() {
        return generalEvaluates;
    }

    public void setGeneralEvaluates(int generalEvaluates) {
        this.generalEvaluates = generalEvaluates;
    }

    public int getBadEvaluates() {
        return badEvaluates;
    }

    public void setBadEvaluates(int badEvaluates) {
        this.badEvaluates = badEvaluates;
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
