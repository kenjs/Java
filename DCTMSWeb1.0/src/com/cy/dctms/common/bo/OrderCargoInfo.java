package com.cy.dctms.common.bo;

import java.util.Date;
public class OrderCargoInfo {
    private Long id;  // 主键（自增、初始值1）
    private String cargoName;  // 货物名称
    private Long cargoType;  // 货物类型
    private Double cargoWeight;  // 重量（货物）
    private Double cargoCubage;  // 体积（货物）
    private String requestCarLength;  // 车型要求（车长）
    private String requestCarPlateType;  // 板-平板、高低板
    private String requestCarBarType;  // 车型要求（车 栏）
    private Double freight;  // 发布运费价格
    private Date requestStartTime;  // 要求装货时间
    private Date requestEndTime;  // 要求到货时间
    private String startProvince;  // 装货地-省
    private String startCity;  // 装货地-市
    private String startCounty;  // 装货地-县
    private String startTown;  // 装货地-自定义地址
    private String endProvince;  // 卸货地-省
    private String endCity;  // 卸货地-市
    private String endCounty;  // 卸货地-县
    private String endTown;  // 卸货地-自定义地址
    private String contactName;  // 联系人
    private String contactMobilephone;  // 手机号
    private String contactTelephone;  // 固定电话
    private String remark;  // 备注
    private Long deletedFlag;  // 删除状态
    private Long deployUserid;  // 发布用户ID
    private Long modifyUserid;  // 修改用户ID
    private Long companyId;  // 企业ID
    private Long cargoFlag;  // 货源状态
    private Date cargoFlagTime;  // 状态修改时间
    private Date createTime;  // 创建时间
    private Date modifyTime;  // 修改时间
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCargoName() {
        return cargoName;
    }
    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }
    public Long getCargoType() {
        return cargoType;
    }
    public void setCargoType(Long cargoType) {
        this.cargoType = cargoType;
    }
    public Double getCargoWeight() {
        return cargoWeight;
    }
    public void setCargoWeight(Double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }
    public Double getCargoCubage() {
        return cargoCubage;
    }
    public void setCargoCubage(Double cargoCubage) {
        this.cargoCubage = cargoCubage;
    }
    public String getRequestCarLength() {
        return requestCarLength;
    }
    public void setRequestCarLength(String requestCarLength) {
        this.requestCarLength = requestCarLength;
    }
    public String getRequestCarPlateType() {
        return requestCarPlateType;
    }
    public void setRequestCarPlateType(String requestCarPlateType) {
        this.requestCarPlateType = requestCarPlateType;
    }
    public String getRequestCarBarType() {
        return requestCarBarType;
    }
    public void setRequestCarBarType(String requestCarBarType) {
        this.requestCarBarType = requestCarBarType;
    }
    public Double getFreight() {
        return freight;
    }
    public void setFreight(Double freight) {
        this.freight = freight;
    }
    public Date getRequestStartTime() {
        return requestStartTime;
    }
    public void setRequestStartTime(Date requestStartTime) {
        this.requestStartTime = requestStartTime;
    }
    public Date getRequestEndTime() {
        return requestEndTime;
    }
    public void setRequestEndTime(Date requestEndTime) {
        this.requestEndTime = requestEndTime;
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
    public String getStartTown() {
        return startTown;
    }
    public void setStartTown(String startTown) {
        this.startTown = startTown;
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
    public String getEndTown() {
        return endTown;
    }
    public void setEndTown(String endTown) {
        this.endTown = endTown;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactMobilephone() {
        return contactMobilephone;
    }
    public void setContactMobilephone(String contactMobilephone) {
        this.contactMobilephone = contactMobilephone;
    }
    public String getContactTelephone() {
        return contactTelephone;
    }
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Long getDeletedFlag() {
        return deletedFlag;
    }
    public void setDeletedFlag(Long deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
    public Long getDeployUserid() {
        return deployUserid;
    }
    public void setDeployUserid(Long deployUserid) {
        this.deployUserid = deployUserid;
    }
    public Long getModifyUserid() {
        return modifyUserid;
    }
    public void setModifyUserid(Long modifyUserid) {
        this.modifyUserid = modifyUserid;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public Long getCargoFlag() {
        return cargoFlag;
    }
    public void setCargoFlag(Long cargoFlag) {
        this.cargoFlag = cargoFlag;
    }
    public Date getCargoFlagTime() {
        return cargoFlagTime;
    }
    public void setCargoFlagTime(Date cargoFlagTime) {
        this.cargoFlagTime = cargoFlagTime;
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