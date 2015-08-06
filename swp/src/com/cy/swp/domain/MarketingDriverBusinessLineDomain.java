package com.cy.swp.domain;

/**
 * 客户司机预约线路表domain
 * Created by wyh on 2014/12/9.
 */
public class MarketingDriverBusinessLineDomain extends BaseDomain {
    private static final long serialVersionUID = 7166324767525518518L;
    private Integer id;
    private Integer customerDriverId;//客户司机ID(pk-t_marketing_driver_info.id)
    private String startTime;//预约开始时间
    private String endTime;//预约结束时间
    private String startProvince;//预约路线起-省
    private String startCity;//预约路线起-市
    private String startCounty;//预约路线起-县区
    private String endProvince;//预约路线目-省
    private String endCity;//预约路线目-市
    private String endCounty;//预约路线目-县区
    private Double quoteFair;//运费价格(字段长度double(11,2))
    private Integer quoteType;//报价类型
    private Integer deleteFlag;//状态(0有效1无效)
    private String createTime;//创建时间
    private String modifyTime;//修改时间

    private String startPcc;
    private String endPcc;

    public String getStartPcc() {
        return startPcc;
    }

    public void setStartPcc(String startPcc) {
        this.startPcc = startPcc;
    }

    public String getEndPcc() {
        return endPcc;
    }

    public void setEndPcc(String endPcc) {
        this.endPcc = endPcc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerDriverId() {
        return customerDriverId;
    }

    public void setCustomerDriverId(Integer customerDriverId) {
        this.customerDriverId = customerDriverId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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
}
