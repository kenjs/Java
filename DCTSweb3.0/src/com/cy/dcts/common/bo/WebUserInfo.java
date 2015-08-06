package com.cy.dcts.common.bo;

import java.util.Date;

/**
 * web用户表
 * 
 * @author zdy
 * 
 */
public class WebUserInfo extends BaseBo {
	
	private static final long serialVersionUID = 8617332575268144232L;
	
	private String id;// 主键（自增、初始值1）
	private String code;// 登录名称
	private String password;// 密码
	private String name;// 用户姓名
	private String companyId;// 公司ID
	private String mobilephone;// 手机号
	private String userQq;// 用户qq
	private String email;// 电子邮箱
	private String idCardNumber;// 身份证号码
	private String userImages;// 照片存放地址
	private String deletedFlag;// 用户状态（默认0有效、1无效）
	private String loginIp;// 最后登陆Ip
	private Date loginTime;// 最后登陆时间
	private String enterpriseFlag;// 企业认证标识（默认0未认证、1已认证）
	private Date enterpriseTime;// 企业认证时间
	private String panymentFlag;// 缴费认证标识（默认0未认证、1已认证）
	private Date panymentTime;// 缴费认证时间
	private String personageFlag;// 个人认证标识（默认0未认证、1已认证）
	private Date personageTime;// 个人认证时间
	private Date createTime;// 创建时间
	private Date modifyTime;// 修改时间
	private String newoldUserType ;//判断老用户进行手机验证0新用户1老用户
	private String userOrigin;//用户来源(0.自己注册，1.导入的)20140716
	private String submitType;//审核提交标识 20140728
	private String pactCarDriverFlag;//权限标识（0无权限1有权限）20140801
	
	private String userType;//注册用户类型（物流企业0、发货方1、收货方2）
	private String encoded;//编码代码
	private String parentId;//所属物流企业（用户表）标识（父级0，子级为父级id）
	private String deliveryFlag;//发货确认权限（1有权限，0无权限）
	private String arrivalSure;//到货确认权限（1有权限，0无权限）
	private String receiveSure;//收货确认权限（1有权限，0无权限）
	
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getUserQq() {
		return userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getUserImages() {
		return userImages;
	}

	public void setUserImages(String userImages) {
		this.userImages = userImages;
	}

	public String getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getEnterpriseFlag() {
		return enterpriseFlag;
	}

	public void setEnterpriseFlag(String enterpriseFlag) {
		this.enterpriseFlag = enterpriseFlag;
	}

	public Date getEnterpriseTime() {
		return enterpriseTime;
	}

	public void setEnterpriseTime(Date enterpriseTime) {
		this.enterpriseTime = enterpriseTime;
	}

	public String getPanymentFlag() {
		return panymentFlag;
	}

	public void setPanymentFlag(String panymentFlag) {
		this.panymentFlag = panymentFlag;
	}

	public Date getPanymentTime() {
		return panymentTime;
	}

	public void setPanymentTime(Date panymentTime) {
		this.panymentTime = panymentTime;
	}

	public String getPersonageFlag() {
		return personageFlag;
	}

	public void setPersonageFlag(String personageFlag) {
		this.personageFlag = personageFlag;
	}

	public Date getPersonageTime() {
		return personageTime;
	}

	public void setPersonageTime(Date personageTime) {
		this.personageTime = personageTime;
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

	public String getNewoldUserType() {
		return newoldUserType;
	}

	public void setNewoldUserType(String newoldUserType) {
		this.newoldUserType = newoldUserType;
	}

	public String getUserOrigin() {
		return userOrigin;
	}

	public void setUserOrigin(String userOrigin) {
		this.userOrigin = userOrigin;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getPactCarDriverFlag() {
		return pactCarDriverFlag;
	}

	public void setPactCarDriverFlag(String pactCarDriverFlag) {
		this.pactCarDriverFlag = pactCarDriverFlag;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEncoded() {
		return encoded;
	}

	public void setEncoded(String encoded) {
		this.encoded = encoded;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDeliveryFlag() {
		return deliveryFlag;
	}

	public void setDeliveryFlag(String deliveryFlag) {
		this.deliveryFlag = deliveryFlag;
	}

	public String getArrivalSure() {
		return arrivalSure;
	}

	public void setArrivalSure(String arrivalSure) {
		this.arrivalSure = arrivalSure;
	}

	public String getReceiveSure() {
		return receiveSure;
	}

	public void setReceiveSure(String receiveSure) {
		this.receiveSure = receiveSure;
	}
	
}
