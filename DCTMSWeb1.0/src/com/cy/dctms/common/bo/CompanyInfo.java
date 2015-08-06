package com.cy.dctms.common.bo;

import java.util.Date;
public class CompanyInfo {
    private Long id;  // ��������������ʼֵ1��
    private String companyName;  // ��˾����
    private String companyProvince;  // ʡ
    private String companyCity;  // ��
    private String companyCounty;  // ����
    private String companyAddress;  // ��ϸ��ַ
    private String businessLicence;  // Ӫҵִ��ע���
    private String organizationCode;  // ��֯��������
    private String organizationImages;  // ��֯�����ϴ���Ƭ·��
    private String businessImages;  // Ӫҵִ���ϴ�ͼƬ��ַ
    private String contactName;  // ��ϵ��
    private String contactTelephone;  // �̶��绰
    private Date createTime;  // ����ʱ��
    private Date modifyTime;  // �޸�ʱ��
    private Long deletedFlag;  // ״̬(Ĭ��0��Ч��1��Ч��
    private Long oldid;  // ��ʶid�Ժ�ɾ��
    private Long userOrigin;  // ��˾��Դ(0.�Լ�ע�ᣬ1.�����)
    private Long parentCompanyId;  // ����������ҵ��ʶ������0���Ӽ�Ϊ����id��
    private Long companyType;  // ע�ṫ˾���ͣ�������ҵ0��������1���ջ���2��
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