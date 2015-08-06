package com.cy.driver.bo;

import java.io.Serializable;

/**
 * Created by haoy on 2014/12/26.
 */
public class MarketingDriverLine implements Serializable {
    private int id;                         //主键（自增、初始值1
    private String customerDriverId;        //pk-t_marketing_driver_info.id
    private String startProvince;           //起始地-省
    private String startCity;               //起始地-市
    private String startCounty;             //起始地-区
    private String endProvince;             //目的地-省
    private String endCity;                 //目的地-市
    private String endCounty;               //目的地-区
    private int deleteFlag;                 //状态0有效1无效
    private String lineType;                //类型（1单线2双线）
    private String createTime;              //创建时间
    private String modifyTime;              //修改时间

    private String driverId;

    public MarketingDriverLine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerDriverId() {
        return customerDriverId;
    }

    public void setCustomerDriverId(String customerDriverId) {
        this.customerDriverId = customerDriverId;
    }

    public String getStartProvince() {
        return startProvince;
    }

    public void setStartProvince(String startProvince) {
        this.startProvince = startProvince;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartCounty() {
        return startCounty;
    }

    public void setStartCounty(String startCounty) {
        this.startCounty = startCounty;
    }

    public String getEndProvince() {
        return endProvince;
    }

    public void setEndProvince(String endProvince) {
        this.endProvince = endProvince;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getEndCounty() {
        return endCounty;
    }

    public void setEndCounty(String endCounty) {
        this.endCounty = endCounty;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
