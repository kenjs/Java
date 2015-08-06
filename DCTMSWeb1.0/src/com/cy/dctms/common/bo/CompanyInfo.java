package com.cy.dctms.common.bo;

import java.util.Date;
public class CompanyInfo {
    private Long id;  // 主键（自增、初始值1）
    private String companyName;  // 公司名称
    private String companyProvince;  // 省
    private String companyCity;  // 市
    private String companyCounty;  // 县区
    private String companyAddress;  // 详细地址
    private String businessLicence;  // 营业执照注册号
    private String organizationCode;  // 组织机构代码
    private String organizationImages;  // 组织机构上传照片路径
    private String businessImages;  // 营业执照上传图片地址
    private String contactName;  // 联系人
    private String contactTelephone;  // 固定电话
    private Date createTime;  // 创建时间
    private Date modifyTime;  // 修改时间
    private Long deletedFlag;  // 状态(默认0有效、1无效）
    private Long oldid;  // 标识id以后删除
    private Long userOrigin;  // 公司来源(0.自己注册，1.导入的)
    private Long parentCompanyId;  // 所属物流企业标识（父级0，子级为父级id）
    private Long companyType;  // 注册公司类型（物流企业0、发货方1、收货方2）
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyProvince() {
        return companyProvince;
    }
    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }
    public String getCompanyCity() {
        return companyCity;
    }
    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }
    public String getCompanyCounty() {
        return companyCounty;
    }
    public void setCompanyCounty(String companyCounty) {
        this.companyCounty = companyCounty;
    }
    public String getCompanyAddress() {
        return companyAddress;
    }
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    public String getBusinessLicence() {
        return businessLicence;
    }
    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }
    public String getOrganizationCode() {
        return organizationCode;
    }
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
    public String getOrganizationImages() {
        return organizationImages;
    }
    public void setOrganizationImages(String organizationImages) {
        this.organizationImages = organizationImages;
    }
    public String getBusinessImages() {
        return businessImages;
    }
    public void setBusinessImages(String businessImages) {
        this.businessImages = businessImages;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactTelephone() {
        return contactTelephone;
    }
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
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
    public Long getDeletedFlag() {
        return deletedFlag;
    }
    public void setDeletedFlag(Long deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
    public Long getOldid() {
        return oldid;
    }
    public void setOldid(Long oldid) {
        this.oldid = oldid;
    }
    public Long getUserOrigin() {
        return userOrigin;
    }
    public void setUserOrigin(Long userOrigin) {
        this.userOrigin = userOrigin;
    }
    public Long getParentCompanyId() {
        return parentCompanyId;
    }
    public void setParentCompanyId(Long parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }
    public Long getCompanyType() {
        return companyType;
    }
    public void setCompanyType(Long companyType) {
        this.companyType = companyType;
    }
}