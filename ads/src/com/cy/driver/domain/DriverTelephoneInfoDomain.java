package com.cy.driver.domain;

/**
 * Created by haoy on 2014/9/24.
 */
public class DriverTelephoneInfoDomain {
    private String id;                                 //主键（自增、初始值1）
    private String driverId;                           //车源ID
    private String noImei;                          //获取手机IMEI号
    private String mobilePhoneModel;                //手机型号
    private String operatingSystemVersionNumber;    //系统版本号
    private String mobileBrand;                     //获取手机品牌
    private String softwareList;                    //手机安装的安全软件情况
    private String resolution;                      //手机分辨率
    private String createTime;                      //创建时间
    private String modifyTime;                      //修改时间

    public DriverTelephoneInfoDomain() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getNoImei() {
        return noImei;
    }

    public void setNoImei(String noImei) {
        this.noImei = noImei;
    }

    public String getMobilePhoneModel() {
        return mobilePhoneModel;
    }

    public void setMobilePhoneModel(String mobilePhoneModel) {
        this.mobilePhoneModel = mobilePhoneModel;
    }

    public String getOperatingSystemVersionNumber() {
        return operatingSystemVersionNumber;
    }

    public void setOperatingSystemVersionNumber(String operatingSystemVersionNumber) {
        this.operatingSystemVersionNumber = operatingSystemVersionNumber;
    }

    public String getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    public String getSoftwareList() {
        return softwareList;
    }

    public void setSoftwareList(String softwareList) {
        this.softwareList = softwareList;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
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
