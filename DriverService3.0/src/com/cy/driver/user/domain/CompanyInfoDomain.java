package com.cy.driver.user.domain;

import java.util.Date;

import com.cy.common.domain.BaseDomain;
/**
 * 公司信息
 * @author Administrator
 *
 */
public class CompanyInfoDomain extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -419606289486792201L;
	
	private int id;//主键（自增、初始值1）
	private String companyName;//公司名称
	private String companyProvince;//省
	private String companyCity;//市
	private String companyCounty;//县区
	private String companyAddress;//详细地址
	private String businessLicence;//工商注册号
	private String organizationCode;//组织机构代码
	private String businessImages;//营业执照上传图片地址
	private String contactName;//联系人
	private String contactTelephone;//固定电话
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	private int deletedFlag;//状态(默认0有效、1无效)
	
	private String userName;//用户姓名
	private String mobilephone;//手机号
	private int enterpriseFlag;//企业认证标识（默认0未认证、1已认证）
	private int personageFlag;//个人认证标识（默认0未认证、1已认证）
	private int flag;
	
	private int praise;//好评
	private int average;//中评
	private int negativeFeedback;//差评
	public CompanyInfoDomain() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(int deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public int getEnterpriseFlag() {
		return enterpriseFlag;
	}
	public void setEnterpriseFlag(int enterpriseFlag) {
		this.enterpriseFlag = enterpriseFlag;
	}
	public int getPersonageFlag() {
		return personageFlag;
	}
	public void setPersonageFlag(int personageFlag) {
		this.personageFlag = personageFlag;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getPraise() {
		return praise;
	}
	public void setPraise(int praise) {
		this.praise = praise;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public int getNegativeFeedback() {
		return negativeFeedback;
	}
	public void setNegativeFeedback(int negativeFeedback) {
		this.negativeFeedback = negativeFeedback;
	}

}
