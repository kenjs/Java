package com.cy.driver.domain;

/**
 * Created by haoy on 2014/9/25.
 */
public class CountSystemBusiDomain {
    private int id;                         //
    private int totalAppDowns;              //App注册&更新+1/次
    private int totalTruckOrders;          //App注册&更新+1/次
    private int totalTransactions;          //订单完成+1/单
    private int totalRegUsers;              //订单完成+1/单
    private int totalAuthUsers;             //企业认证审核通过+1/单
    private int totalSupplyReleases;        //发布货源+1/次
    private int totalSupplySelf;            //人工导入&爬虫导入+1/条
    private int totalRegDrivers;            //司机注册+1/个
    private int totalAuthDrivers;           //司机认证审核通过+1/单
    private int totalSupplyFinds;           //司机点击找货+1/次

    public CountSystemBusiDomain() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalAppDowns() {
        return totalAppDowns;
    }

    public void setTotalAppDowns(int totalAppDowns) {
        this.totalAppDowns = totalAppDowns;
    }

    public int getTotalTruckOrders() {
        return totalTruckOrders;
    }

    public void setTotalTruckOrders(int totalTruckOrders) {
        this.totalTruckOrders = totalTruckOrders;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public int getTotalRegUsers() {
        return totalRegUsers;
    }

    public void setTotalRegUsers(int totalRegUsers) {
        this.totalRegUsers = totalRegUsers;
    }

    public int getTotalAuthUsers() {
        return totalAuthUsers;
    }

    public void setTotalAuthUsers(int totalAuthUsers) {
        this.totalAuthUsers = totalAuthUsers;
    }

    public int getTotalSupplyReleases() {
        return totalSupplyReleases;
    }

    public void setTotalSupplyReleases(int totalSupplyReleases) {
        this.totalSupplyReleases = totalSupplyReleases;
    }

    public int getTotalSupplySelf() {
        return totalSupplySelf;
    }

    public void setTotalSupplySelf(int totalSupplySelf) {
        this.totalSupplySelf = totalSupplySelf;
    }

    public int getTotalRegDrivers() {
        return totalRegDrivers;
    }

    public void setTotalRegDrivers(int totalRegDrivers) {
        this.totalRegDrivers = totalRegDrivers;
    }

    public int getTotalAuthDrivers() {
        return totalAuthDrivers;
    }

    public void setTotalAuthDrivers(int totalAuthDrivers) {
        this.totalAuthDrivers = totalAuthDrivers;
    }

    public int getTotalSupplyFinds() {
        return totalSupplyFinds;
    }

    public void setTotalSupplyFinds(int totalSupplyFinds) {
        this.totalSupplyFinds = totalSupplyFinds;
    }
}
