package com.cy.dctms.common.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by haoy on 2014/9/5.
 */
public class DaycountWebUserBusi implements Serializable{
    private int id;
    private Date recordDay;
    private int webUserId;
    private int noConfirmOrders;
    private int noConfirmReceives;
    private int noAssessmentOrders;
    private int supplyReleases;//发布货源+1/次/用户
    private int truckOrders;//在线订车+1/次/用户
    private int transactions;//'订单完成+1/单/用户

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

    public DaycountWebUserBusi() {
    }
}
