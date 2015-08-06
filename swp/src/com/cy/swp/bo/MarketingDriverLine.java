package com.cy.swp.bo;

import java.util.Date;

/**
 * 客户司机运营线路表
 * Created by nixianjing on 14/12/9.
 */
public class MarketingDriverLine extends BaseBo {


    private Integer id;//主键（自增、初始值1）
    private Integer customerDriverId;//pk-t_marketing_driver_info.id
    private String startProvince;//起始地-省
    private String startCity;//起始地-市
    private String startCounty;//起始地-区
    private String endProvince;//目的地-省
    private String endCity;//目的地-市
    private String endCounty;//目的地-区
    private Integer deleteFlag;//状态0有效1无效
    private Integer lineType;//类型（1单线2双线）
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间


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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getLineType() {
        return lineType;
    }

    public void setLineType(Integer lineType) {
        this.lineType = lineType;
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
