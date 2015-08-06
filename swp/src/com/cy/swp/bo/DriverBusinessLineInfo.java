package com.cy.swp.bo;

import java.util.Date;

/**
 * 司机预约线路
 * Created by nixianjing on 14/12/9.
 */
public class DriverBusinessLineInfo extends BaseBo{


    private Integer id;//
    private Integer driverId;//司机id
    private Date startTime;//预约开始时间
    private Date endTime;//预约结束时间
    private String startProvince;//预约路线起-省
    private String startCity;//预约路线起-市
    private String startCounty;//预约路线起-县区
    private String endProvince;//预约路线目-省
    private String endCity;//预约路线目-市
    private String endCounty;//预约路线目-县区
    private Double quoteFair;//运费价格
    private Integer quoteType;//报价类型
    private Integer start;//状态
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Double getQuoteFair() {
        return quoteFair;
    }

    public void setQuoteFair(Double quoteFair) {
        this.quoteFair = quoteFair;
    }

    public Integer getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(Integer quoteType) {
        this.quoteType = quoteType;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
