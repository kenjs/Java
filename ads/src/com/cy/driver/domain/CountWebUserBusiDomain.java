package com.cy.driver.domain;

/**
 * Created by haoy on 2014/10/13.
 */
public class CountWebUserBusiDomain {
    private int id;
    private int webUserId;
    private int noConfirmOrders;
    private int noConfirmReceives;
    private int noAssessmentOrders;
    private int supplyReleases;
    private int truckOrders;
    private int transactions;
    private int goodEvaluates;
    private int generalEvaluates;
    private int badEvaluates;

    public CountWebUserBusiDomain() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(int webUserId) {
        this.webUserId = webUserId;
    }

    public int getNoConfirmOrders() {
        return noConfirmOrders;
    }

    public void setNoConfirmOrders(int noConfirmOrders) {
        this.noConfirmOrders = noConfirmOrders;
    }

    public int getNoConfirmReceives() {
        return noConfirmReceives;
    }

    public void setNoConfirmReceives(int noConfirmReceives) {
        this.noConfirmReceives = noConfirmReceives;
    }

    public int getNoAssessmentOrders() {
        return noAssessmentOrders;
    }

    public void setNoAssessmentOrders(int noAssessmentOrders) {
        this.noAssessmentOrders = noAssessmentOrders;
    }

    public int getSupplyReleases() {
        return supplyReleases;
    }

    public void setSupplyReleases(int supplyReleases) {
        this.supplyReleases = supplyReleases;
    }

    public int getTruckOrders() {
        return truckOrders;
    }

    public void setTruckOrders(int truckOrders) {
        this.truckOrders = truckOrders;
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
}
