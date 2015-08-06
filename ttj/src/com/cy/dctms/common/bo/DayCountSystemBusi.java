package com.cy.dctms.common.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author haoy
 * @description 系统按天统计业务数表
 */
public class DayCountSystemBusi implements Serializable {
// ------------------------------ FIELDS ------------------------------

    /**
     *
     */
    private static final long serialVersionUID = -8367878032642049668L;

    private int id;                        //主键
    private Date recordDay;                //按天统计
    private int appDowns;                //当天app下载数
    private int regUsers;                //当天注册货源企业数
    private int enddayAuthUsers;        //截止到当天认证企业总数
    private int supplyReleases;            //当天企业发布货源数
    private int supplySelf;                //当天人工导入或爬虫导入货源数
    private int regDrivers;                //当天注册司机数
    private int enddayAuthDrivers;      //截止到当天认证司机总数
    private int forOrders;                //当天订车数（当天交易数）
    private int enddayRegAppusers;        //截止到当天app用户注册总数
    private int enddayRegWebusers;        //截止到当天web用户注册总数

    private int daySurvivalDrivers;        //司机日存活数
    private int dayActiveDrivers;        //司机日活跃数
    private int weekSurvivalDrivers;    //司机周存活数
    private int weekActiveDrivers;        //司机周活跃数
    private int monthSurvivalDrivers;    //司机月存活数
    private int monthActiveDrivers;        //司机月活跃数
    private int month2SurvivalDrivers;    //司机2个月存活数
    private int month2ActiveDrivers;    //司机2个月活跃数

// --------------------------- CONSTRUCTORS ---------------------------

    public DayCountSystemBusi() {
        super();
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public int getAppDowns() {
        return appDowns;
    }

    public void setAppDowns(int appDowns) {
        this.appDowns = appDowns;
    }

    public int getDayActiveDrivers() {
        return dayActiveDrivers;
    }

    public void setDayActiveDrivers(int dayActiveDrivers) {
        this.dayActiveDrivers = dayActiveDrivers;
    }

    public int getDaySurvivalDrivers() {
        return daySurvivalDrivers;
    }

    public void setDaySurvivalDrivers(int daySurvivalDrivers) {
        this.daySurvivalDrivers = daySurvivalDrivers;
    }

    public int getEnddayAuthDrivers() {
        return enddayAuthDrivers;
    }

    public void setEnddayAuthDrivers(int enddayAuthDrivers) {
        this.enddayAuthDrivers = enddayAuthDrivers;
    }

    public int getEnddayAuthUsers() {
        return enddayAuthUsers;
    }

    public void setEnddayAuthUsers(int enddayAuthUsers) {
        this.enddayAuthUsers = enddayAuthUsers;
    }

    public int getEnddayRegAppusers() {
        return enddayRegAppusers;
    }

    public void setEnddayRegAppusers(int enddayRegAppusers) {
        this.enddayRegAppusers = enddayRegAppusers;
    }

    public int getEnddayRegWebusers() {
        return enddayRegWebusers;
    }

    public void setEnddayRegWebusers(int enddayRegWebusers) {
        this.enddayRegWebusers = enddayRegWebusers;
    }

    public int getForOrders() {
        return forOrders;
    }

    public void setForOrders(int forOrders) {
        this.forOrders = forOrders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth2ActiveDrivers() {
        return month2ActiveDrivers;
    }

    public void setMonth2ActiveDrivers(int month2ActiveDrivers) {
        this.month2ActiveDrivers = month2ActiveDrivers;
    }

    public int getMonth2SurvivalDrivers() {
        return month2SurvivalDrivers;
    }

    public void setMonth2SurvivalDrivers(int month2SurvivalDrivers) {
        this.month2SurvivalDrivers = month2SurvivalDrivers;
    }

    public int getMonthActiveDrivers() {
        return monthActiveDrivers;
    }

    public void setMonthActiveDrivers(int monthActiveDrivers) {
        this.monthActiveDrivers = monthActiveDrivers;
    }

    public int getMonthSurvivalDrivers() {
        return monthSurvivalDrivers;
    }

    public void setMonthSurvivalDrivers(int monthSurvivalDrivers) {
        this.monthSurvivalDrivers = monthSurvivalDrivers;
    }

    public Date getRecordDay() {
        return recordDay;
    }

    public void setRecordDay(Date recordDay) {
        this.recordDay = recordDay;
    }

    public int getRegDrivers() {
        return regDrivers;
    }

    public void setRegDrivers(int regDrivers) {
        this.regDrivers = regDrivers;
    }

    public int getRegUsers() {
        return regUsers;
    }

    public void setRegUsers(int regUsers) {
        this.regUsers = regUsers;
    }

    public int getSupplyReleases() {
        return supplyReleases;
    }

    public void setSupplyReleases(int supplyReleases) {
        this.supplyReleases = supplyReleases;
    }

    public int getSupplySelf() {
        return supplySelf;
    }

    public void setSupplySelf(int supplySelf) {
        this.supplySelf = supplySelf;
    }

    public int getWeekActiveDrivers() {
        return weekActiveDrivers;
    }

    public void setWeekActiveDrivers(int weekActiveDrivers) {
        this.weekActiveDrivers = weekActiveDrivers;
    }

    public int getWeekSurvivalDrivers() {
        return weekSurvivalDrivers;
    }

    public void setWeekSurvivalDrivers(int weekSurvivalDrivers) {
        this.weekSurvivalDrivers = weekSurvivalDrivers;
    }
}
