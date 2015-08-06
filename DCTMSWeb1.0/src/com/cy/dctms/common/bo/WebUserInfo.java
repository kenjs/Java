package com.cy.dctms.common.bo;

import java.util.Date;
public class WebUserInfo {
    private Long id;  // 主键（自增、初始值1）
    private String code;  // 登录名称
    private String password;  // 密码
    private String name;  // 用户姓名
    private Long companyId;  // 公司ID
    private String mobilephone;  // 手机号
    private String userQq;  // QQ号码
    private String email;  // 电子邮箱
    private String idCardNumber;  // 身份证号码
    private String userImages;  // 照片存放地址
    private Long deletedFlag;  // 用户状态（默认0有效、1无效）
    private String loginIp;  // 最后登录IP地址
    private Date loginTime;  // 最后登录时间
    private Long enterpriseFlag;  // 企业认证标识（默认0未认证、1已认证）
    private Date enterpriseTime;  // 企业认证时间
    private Long panymentFlag;  // 缴费认证标识（默认0未认证、1已认证）
    private Date panymentTime;  // 缴费认证时间
    private Long personageFlag;  // 个人认证标识（默认0未认证、1已认证）
    private Date personageTime;  // 个人认证时间
    private Date createTime;  // 创建时间
    private Date modifyTime;  // 修改时间
    private Long newoldUserType;  // 判断老用户进行手机验证0新用户1老用户
    private Long userOrigin;  // 用户来源(0.自己注册，1.导入的)
    private Long submitType;//审核提交标识
    private Integer pactCarDriverFlag;//VIP会员功能权限标识（0无权限1有权限）
    private Integer pactCardFlag;//'身份证查询功能权限标识（0无权限1有权限）'
    private Integer pactCargoFlag;//'货主版功能权限标识（0无权限1有权限）'
    
    public Integer getPactCarDriverFlag() {
		return pactCarDriverFlag;
	}
	public void setPactCarDriverFlag(Integer pactCarDriverFlag) {
		this.pactCarDriverFlag = pactCarDriverFlag;
	}
	public Integer getPactCardFlag() {
		return pactCardFlag;
	}
	public void setPactCardFlag(Integer pactCardFlag) {
		this.pactCardFlag = pactCardFlag;
	}
	public Integer getPactCargoFlag() {
		return pactCargoFlag;
	}
	public void setPactCargoFlag(Integer pactCargoFlag) {
		this.pactCargoFlag = pactCargoFlag;
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSubmitType() {
		return submitType;
	}
	public void setSubmitType(Long submitType) {
		this.submitType = submitType;
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
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
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
    public Long getDeletedFlag() {
        return deletedFlag;
    }
    public void setDeletedFlag(Long deletedFlag) {
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
    public Long getEnterpriseFlag() {
        return enterpriseFlag;
    }
    public void setEnterpriseFlag(Long enterpriseFlag) {
        this.enterpriseFlag = enterpriseFlag;
    }
    public Date getEnterpriseTime() {
        return enterpriseTime;
    }
    public void setEnterpriseTime(Date enterpriseTime) {
        this.enterpriseTime = enterpriseTime;
    }
    public Long getPanymentFlag() {
        return panymentFlag;
    }
    public void setPanymentFlag(Long panymentFlag) {
        this.panymentFlag = panymentFlag;
    }
    public Date getPanymentTime() {
        return panymentTime;
    }
    public void setPanymentTime(Date panymentTime) {
        this.panymentTime = panymentTime;
    }
    public Long getPersonageFlag() {
        return personageFlag;
    }
    public void setPersonageFlag(Long personageFlag) {
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
    public Long getNewoldUserType() {
        return newoldUserType;
    }
    public void setNewoldUserType(Long newoldUserType) {
        this.newoldUserType = newoldUserType;
    }
    public Long getUserOrigin() {
        return userOrigin;
    }
    public void setUserOrigin(Long userOrigin) {
        this.userOrigin = userOrigin;
    }
}