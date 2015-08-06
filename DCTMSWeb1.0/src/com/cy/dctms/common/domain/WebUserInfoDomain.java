package com.cy.dctms.common.domain;

import java.util.List;

/**
*企业
* wjl
*/
public class WebUserInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // 主键（自增、初始值1）
    private String code;  // 登录名称
    private String password;  // 密码
    private String name;  // 用户姓名
    private String companyId;  // 公司ID
    private String mobilephone;  // 手机号
    private String userQq;  // QQ号码
    private String email;  // 电子邮箱
    private String idCardNumber;  // 身份证号码
    private String userImages;  // 照片存放地址
    private String deletedFlag;  // 用户状态（默认0有效、1无效）
    private String loginIp;  // 最后登录IP地址
    private String loginTime;  // 最后登录时间
    private String enterpriseFlag;  // 企业认证标识（默认0未认证、1已认证）
    private String enterpriseTime;  // 企业认证时间
    private String panymentFlag;  // 缴费认证标识（默认0未认证、1已认证）
    private String panymentTime;  // 缴费认证时间
    private String personageFlag;  // 个人认证标识（默认0未认证、1已认证）
    private String personageTime;  // 个人认证时间
    private String createTime;  // 创建时间
    private String modifyTime;  // 修改时间
    private String newoldUserType;  // 判断老用户进行手机验证0新用户1老用户
    private String userOrigin;  // 用户来源(0.自己注册，1.导入的)
    private String submitType;//审核提交标识
    private String freezeFlag;//冻结标志
    //冗余信息
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
    private String enterpriseTimeQ;//认证时间起
    private String enterpriseTimeZ;//认证时间止
    private List<String> reason;   //推送理由
    private String sendNoteOrMail;//发送短信还是邮件(0短信1邮件)
    private String registerTimeQ;//注册时间起
    private String registerTimeZ;//注册时间止
    private String lastLoginTime;//最后登录时间
    private String lastLoginTimeQ;//最后登录时间起
    private String lastLoginTimeZ;//最后登录时间止
    private String accumulateCargoCount;//累计发布货源数量
    private String todayCargoCount;//今日发布货源数量
    private String accumulateTransactionCount;//累计交易成功数量，这个是统计订单交易成功的数量
    private String todayTransactionCount;//今日交易数量
    private String orderCargoCount;//在线订车数量,这个统计的是所有订单数量，无论处于什么状态
    private String orderCargoSuccessCount;//在线订车数量,这个统计的是所有订单数量，无论处于什么状态
    private String orderCargoRate;//订车率
    private String accumulateTransactionRate;//交易成功率
    private String userType;//注册用户类型（物流企业0、发货方1、收货方2） 
    private String deleteOrModifyFlag;//删除还是修改标志0为删除，1为修改。
    private String deleteReason;//删除理由
    private String isPushAll;//是否发送全部
    private String sendType;//发送类型 0邮件存在发送邮件邮件不存在发送短信  1只发送短信，2只发送邮件。
    private List<String> IdList;//id列表。用于批量发送信息时候，拿着id列表去查信息
	public List<String> getIdList() {
		return IdList;
	}
	public void setIdList(List<String> idList) {
		IdList = idList;
	}
	public String getIsPushAll() {
		return isPushAll;
	}
	public void setIsPushAll(String isPushAll) {
		this.isPushAll = isPushAll;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public String getOrderCargoCount() {
		return orderCargoCount;
	}
	public String getDeleteOrModifyFlag() {
		return deleteOrModifyFlag;
	}
	public String getFreezeFlag() {
		return freezeFlag;
	}
	public void setFreezeFlag(String freezeFlag) {
		this.freezeFlag = freezeFlag;
	}
	public void setDeleteOrModifyFlag(String deleteOrModifyFlag) {
		this.deleteOrModifyFlag = deleteOrModifyFlag;
	}
	public void setOrderCargoCount(String orderCargoCount) {
		this.orderCargoCount = orderCargoCount;
	}
	public String getUserType() {
		return userType;
	}
	public String getOrderCargoSuccessCount() {
		return orderCargoSuccessCount;
	}
	public void setOrderCargoSuccessCount(String orderCargoSuccessCount) {
		this.orderCargoSuccessCount = orderCargoSuccessCount;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getOrderCargoRate() {
		return orderCargoRate;
	}
	public void setOrderCargoRate(String orderCargoRate) {
		this.orderCargoRate = orderCargoRate;
	}
	public String getAccumulateTransactionRate() {
		return accumulateTransactionRate;
	}
	public void setAccumulateTransactionRate(String accumulateTransactionRate) {
		this.accumulateTransactionRate = accumulateTransactionRate;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getEnterpriseTimeQ() {
		return enterpriseTimeQ;
	}
	public String getSendNoteOrMail() {
		return sendNoteOrMail;
	}
	public void setSendNoteOrMail(String sendNoteOrMail) {
		this.sendNoteOrMail = sendNoteOrMail;
	}
	public List<String> getReason() {
		return reason;
	}
	public void setReason(List<String> reason) {
		this.reason = reason;
	}
	public String getAccumulateCargoCount() {
		return accumulateCargoCount;
	}
	public void setAccumulateCargoCount(String accumulateCargoCount) {
		this.accumulateCargoCount = accumulateCargoCount;
	}
	public String getTodayCargoCount() {
		return todayCargoCount;
	}
	public void setTodayCargoCount(String todayCargoCount) {
		this.todayCargoCount = todayCargoCount;
	}
	public String getAccumulateTransactionCount() {
		return accumulateTransactionCount;
	}
	public void setAccumulateTransactionCount(String accumulateTransactionCount) {
		this.accumulateTransactionCount = accumulateTransactionCount;
	}
	public String getTodayTransactionCount() {
		return todayTransactionCount;
	}
	public void setTodayTransactionCount(String todayTransactionCount) {
		this.todayTransactionCount = todayTransactionCount;
	}

	public void setEnterpriseTimeQ(String enterpriseTimeQ) {
		this.enterpriseTimeQ = enterpriseTimeQ;
	}
	public String getEnterpriseTimeZ() {
		return enterpriseTimeZ;
	}
	public String getRegisterTimeQ() {
		return registerTimeQ;
	}
	public void setRegisterTimeQ(String registerTimeQ) {
		this.registerTimeQ = registerTimeQ;
	}
	public String getRegisterTimeZ() {
		return registerTimeZ;
	}
	public void setRegisterTimeZ(String registerTimeZ) {
		this.registerTimeZ = registerTimeZ;
	}
	public String getLastLoginTimeQ() {
		return lastLoginTimeQ;
	}
	public void setLastLoginTimeQ(String lastLoginTimeQ) {
		this.lastLoginTimeQ = lastLoginTimeQ;
	}
	public String getLastLoginTimeZ() {
		return lastLoginTimeZ;
	}
	public void setLastLoginTimeZ(String lastLoginTimeZ) {
		this.lastLoginTimeZ = lastLoginTimeZ;
	}
	public void setEnterpriseTimeZ(String enterpriseTimeZ) {
		this.enterpriseTimeZ = enterpriseTimeZ;
	}
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	private List<WebUserInfoDomain> dataList;
    public String getId() {
        return id;
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
    public List<WebUserInfoDomain> getDataList() { 
    	return dataList;  
    	}   
    public void setDataList(List<WebUserInfoDomain> dataList) {   
    	this.dataList = dataList;   
    	}
    }