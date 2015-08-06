package com.cy.dctms.common.bo;

import java.util.Date;
public class WebUserInfo {
    private Long id;  // ��������������ʼֵ1��
    private String code;  // ��¼����
    private String password;  // ����
    private String name;  // �û�����
    private Long companyId;  // ��˾ID
    private String mobilephone;  // �ֻ���
    private String userQq;  // QQ����
    private String email;  // ��������
    private String idCardNumber;  // ���֤����
    private String userImages;  // ��Ƭ��ŵ�ַ
    private Long deletedFlag;  // �û�״̬��Ĭ��0��Ч��1��Ч��
    private String loginIp;  // ����¼IP��ַ
    private Date loginTime;  // ����¼ʱ��
    private Long enterpriseFlag;  // ��ҵ��֤��ʶ��Ĭ��0δ��֤��1����֤��
    private Date enterpriseTime;  // ��ҵ��֤ʱ��
    private Long panymentFlag;  // �ɷ���֤��ʶ��Ĭ��0δ��֤��1����֤��
    private Date panymentTime;  // �ɷ���֤ʱ��
    private Long personageFlag;  // ������֤��ʶ��Ĭ��0δ��֤��1����֤��
    private Date personageTime;  // ������֤ʱ��
    private Date createTime;  // ����ʱ��
    private Date modifyTime;  // �޸�ʱ��
    private Long newoldUserType;  // �ж����û������ֻ���֤0���û�1���û�
    private Long userOrigin;  // �û���Դ(0.�Լ�ע�ᣬ1.�����)
    private Long submitType;//����ύ��ʶ
    private Integer pactCarDriverFlag;//VIP��Ա����Ȩ�ޱ�ʶ��0��Ȩ��1��Ȩ�ޣ�
    private Integer pactCardFlag;//'���֤��ѯ����Ȩ�ޱ�ʶ��0��Ȩ��1��Ȩ�ޣ�'
    private Integer pactCargoFlag;//'�����湦��Ȩ�ޱ�ʶ��0��Ȩ��1��Ȩ�ޣ�'
    
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