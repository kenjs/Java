package com.cy.swp.bo;

import java.util.Date;

/**
 * 公司信息表
 *
 * @author zdy
 */
public class CompanyInfo extends BaseBo {
    private static final long serialVersionUID = 8617332575268144232L;
    private String id;//
    private String companyName;// 公司名称
    private String companyProvince;// 省
    private String companyCity;// 市
    private String companyCounty;// 区县
    private String companyAddress;// 详细地址
    private String businessLicence;// 营业执照注册号
    private String organizationCode;// 组织机构代码
    private String businessImages;// 营业执照上传图片地址
    private String contactName;// 联系人
    private String contactTelephone;// 固定电话
    private String deletedFlag;//状态（默认0有效、1无效）
    private Date createTime;// 创建时间
    private Date modifyTime;// 修改时间
    private String organizationImages;//组织机构上传照片路径(20140703)
    private String userOrigin;//用户来源(0.自己注册，1.导入的)20140716
    //20140808(货主版新增字段1个)
    private String parentCompanyId;//所属物流企业标识（父级0，子级为父级id）
    private String companyType;//注册公司类型（物流企业0、发货方1、收货方2）

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(String deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public String getOrganizationImages() {
        return organizationImages;
    }

    public void setOrganizationImages(String organizationImages) {
        this.organizationImages = organizationImages;
    }

    public String getUserOrigin() {
        return userOrigin;
    }

    public void setUserOrigin(String userOrigin) {
        this.userOrigin = userOrigin;
    }

    public String getParentCompanyId() {
        return parentCompanyId;
    }

    public void setParentCompanyId(String parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }


}
