package com.cy.dcts.common.domain;

import java.util.List;

import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.util.SysToolsUtil;


public class WebUserInfoDamain extends BaseDomain {

	private static final long serialVersionUID = -125218486297320659L;

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
	private String loginTime;// 最后登陆时间
	private String enterpriseFlag;// 企业认证标识（默认0未认证、1已认证）
	private String enterpriseTime;// 企业认证时间
	private String panymentFlag;// 缴费认证标识（默认0未认证、1已认证）
	private String panymentTime;// 缴费认证时间
	private String personageFlag;// 个人认证标识（默认0未认证、1已认证）
	private String personageTime;// 个人认证时间
	private String userOrigin;//用户来源(0.自己注册，1.导入的)20140716
	private String submitType;//审核提交标识 20140728
	private String pactCarDriverFlag;//权限标识（0无权限1有权限）20140801
	
	private String userTypeVal;//20140821 用户类型对应的值
	
	private String companyName;// 公司名称
	private String companyProvince;// 省
	private String companyCity;// 市
	private String companyCounty;// 区县
	private String companyAddress;// 详细地址
	private String contactName;// 联系人
	
	private String companyPcc;//所在地
	
	
	private String codeNumber;//验证码
	
	private String errorMessage;//用于后台向前台传递错误信息
	
	private String businessLicence; //工商注册号
	private String organizationCode;//组织机构代码
	private String contactTelephone;//固定电话
	
	private String provinceCityCountyStr;//省-市-区县 
	private String menuAId;//个人中心左边菜单超链接的Id
	
	private String userType;//注册用户类型（物流企业0、发货方1、收货方2）
	private String encoded;//编码代码
	private String parentId;//所属物流企业（用户表）标识（父级0，子级为父级id）
	private String deliveryFlag;//发货确认权限（1有权限，0无权限）
	private String arrivalSure;//到货确认权限（1有权限，0无权限）
	private String receiveSure;//收货确认权限（1有权限，0无权限） 
	
	private String createTime;//创建时间
	private String createTimeStart;// 创建时间开始
	private String createTimeEnd;// 创建时间结束
	
	private List<WebUserInfoDamain> list;
	
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
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getEnterpriseFlag() {
		return enterpriseFlag;
	}
	public void setEnterpriseFlag(String enterpriseFlag) {
		this.enterpriseFlag = enterpriseFlag;
	}
	public String getEnterpriseTime() {
		return enterpriseTime;
	}
	public void setEnterpriseTime(String enterpriseTime) {
		this.enterpriseTime = enterpriseTime;
	}
	public String getPanymentFlag() {
		return panymentFlag;
	}
	public void setPanymentFlag(String panymentFlag) {
		this.panymentFlag = panymentFlag;
	}
	public String getPanymentTime() {
		return panymentTime;
	}
	public void setPanymentTime(String panymentTime) {
		this.panymentTime = panymentTime;
	}
	public String getPersonageFlag() {
		return personageFlag;
	}
	public void setPersonageFlag(String personageFlag) {
		this.personageFlag = personageFlag;
	}
	public String getPersonageTime() {
		return personageTime;
	}
	public void setPersonageTime(String personageTime) {
		this.personageTime = personageTime;
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
	public String getCodeNumber() {
		return codeNumber;
	}
	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getContactTelephone() {
		return contactTelephone;
	}
	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
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
	public String getMenuAId() {
		return menuAId;
	}
	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}
	public String getProvinceCityCountyStr() {
		return provinceCityCountyStr;
	}
	public void setProvinceCityCountyStr(String provinceCityCountyStr) {
		this.provinceCityCountyStr = provinceCityCountyStr;
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
		if(SysToolsUtil.isNullOrEmpty(userType)){
			return;
		}
		try {
			if(Constants.USER_TYPE_ENTERPRISE_KEY.equals(userType)){
				this.setUserTypeVal(Constants.USER_TYPE_ENTERPRISE_VALUE);
			}else if(Constants.USER_TYPE_SHIPPER_KEY.equals(userType)){
				this.setUserTypeVal(Constants.USER_TYPE_SHIPPER_VALUE);
			}else if(Constants.USER_TYPE_RECEIVE_KEY.equals(userType)){
				this.setUserTypeVal(Constants.USER_TYPE_RECEIVE_VALUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getCompanyPcc() {
		return companyPcc;
	}
	public void setCompanyPcc(String companyPcc) {
		this.companyPcc = companyPcc;
	}
	public List<WebUserInfoDamain> getList() {
		return list;
	}
	public void setList(List<WebUserInfoDamain> list) {
		this.list = list;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getUserTypeVal() {
		return userTypeVal;
	}
	public void setUserTypeVal(String userTypeVal) {
		this.userTypeVal = userTypeVal;
	}
	
	
}
