package com.cy.dctms.common.domain;

import java.util.List;

/**
*货物信息
* wjl
*/
public class OrderCargoInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // 主键（自增、初始值1）
    private String cargoName;  // 货物名称
    private String cargoType;  // 货物类型
    private String cargoWeight;  // 重量（货物）
    private String cargoCubage;  // 体积（货物）
    private String requestCarLength;  // 车型要求（车长）
    private String requestCarPlateType;  // 板-平板、高低板
    private String requestCarBarType;  // 车型要求（车 栏）
    private String freight;  // 发布运费价格
    private String requestStartTime;  // 要求装货时间
    private String requestEndTime;  // 要求到货时间
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
    private String deletedFlag;  // 删除状态
    private String deployUserid;  // 发布用户ID
    private String modifyUserid;  // 修改用户ID
    private String companyId;  // 企业ID
    private String cargoFlag;  // 货源状态
    private String cargoFlagTime;  // 状态修改时间
    private String createTime;  // 创建时间
    private String modifyTime;  // 修改时间
    private List<OrderCargoInfoDomain> dataList;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCargoName() {
        return cargoName;
    }
    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }
    public String getCargoType() {
        return cargoType;
    }
    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }
    public String getCargoWeight() {
        return cargoWeight;
    }
    public void setCargoWeight(String cargoWeight) {
        this.cargoWeight = cargoWeight;
    }
    public String getCargoCubage() {
        return cargoCubage;
    }
    public void setCargoCubage(String cargoCubage) {
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
    public String getFreight() {
        return freight;
    }
    public void setFreight(String freight) {
        this.freight = freight;
    }
    public String getRequestStartTime() {
        return requestStartTime;
    }
    public void setRequestStartTime(String requestStartTime) {
        this.requestStartTime = requestStartTime;
    }
    public String getRequestEndTime() {
        return requestEndTime;
    }
    public void setRequestEndTime(String requestEndTime) {
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
    public String getDeletedFlag() {
        return deletedFlag;
    }
    public void setDeletedFlag(String deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
    public String getDeployUserid() {
        return deployUserid;
    }
    public void setDeployUserid(String deployUserid) {
        this.deployUserid = deployUserid;
    }
    public String getModifyUserid() {
        return modifyUserid;
    }
    public void setModifyUserid(String modifyUserid) {
        this.modifyUserid = modifyUserid;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getCargoFlag() {
        return cargoFlag;
    }
    public void setCargoFlag(String cargoFlag) {
        this.cargoFlag = cargoFlag;
    }
    public String getCargoFlagTime() {
        return cargoFlagTime;
    }
    public void setCargoFlagTime(String cargoFlagTime) {
        this.cargoFlagTime = cargoFlagTime;
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
    public List<OrderCargoInfoDomain> getDataList() {    	return dataList;    }    public void setDataList(List<OrderCargoInfoDomain> dataList) {    	this.dataList = dataList;    }}