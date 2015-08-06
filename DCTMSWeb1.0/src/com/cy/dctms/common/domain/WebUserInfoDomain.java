package com.cy.dctms.common.domain;

import java.util.List;

/**
*��ҵ
* wjl
*/
public class WebUserInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ��������������ʼֵ1��
    private String code;  // ��¼����
    private String password;  // ����
    private String name;  // �û�����
    private String companyId;  // ��˾ID
    private String mobilephone;  // �ֻ���
    private String userQq;  // QQ����
    private String email;  // ��������
    private String idCardNumber;  // ���֤����
    private String userImages;  // ��Ƭ��ŵ�ַ
    private String deletedFlag;  // �û�״̬��Ĭ��0��Ч��1��Ч��
    private String loginIp;  // ����¼IP��ַ
    private String loginTime;  // ����¼ʱ��
    private String enterpriseFlag;  // ��ҵ��֤��ʶ��Ĭ��0δ��֤��1����֤��
    private String enterpriseTime;  // ��ҵ��֤ʱ��
    private String panymentFlag;  // �ɷ���֤��ʶ��Ĭ��0δ��֤��1����֤��
    private String panymentTime;  // �ɷ���֤ʱ��
    private String personageFlag;  // ������֤��ʶ��Ĭ��0δ��֤��1����֤��
    private String personageTime;  // ������֤ʱ��
    private String createTime;  // ����ʱ��
    private String modifyTime;  // �޸�ʱ��
    private String newoldUserType;  // �ж����û������ֻ���֤0���û�1���û�
    private String userOrigin;  // �û���Դ(0.�Լ�ע�ᣬ1.�����)
    private String submitType;//����ύ��ʶ
    private String freezeFlag;//�����־
    //������Ϣ
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
    private String enterpriseTimeQ;//��֤ʱ����
    private String enterpriseTimeZ;//��֤ʱ��ֹ
    private List<String> reason;   //��������
    private String sendNoteOrMail;//���Ͷ��Ż����ʼ�(0����1�ʼ�)
    private String registerTimeQ;//ע��ʱ����
    private String registerTimeZ;//ע��ʱ��ֹ
    private String lastLoginTime;//����¼ʱ��
    private String lastLoginTimeQ;//����¼ʱ����
    private String lastLoginTimeZ;//����¼ʱ��ֹ
    private String accumulateCargoCount;//�ۼƷ�����Դ����
    private String todayCargoCount;//���շ�����Դ����
    private String accumulateTransactionCount;//�ۼƽ��׳ɹ������������ͳ�ƶ������׳ɹ�������
    private String todayTransactionCount;//���ս�������
    private String orderCargoCount;//���߶�������,���ͳ�Ƶ������ж������������۴���ʲô״̬
    private String orderCargoSuccessCount;//���߶�������,���ͳ�Ƶ������ж������������۴���ʲô״̬
    private String orderCargoRate;//������
    private String accumulateTransactionRate;//���׳ɹ���
    private String userType;//ע���û����ͣ�������ҵ0��������1���ջ���2�� 
    private String deleteOrModifyFlag;//ɾ�������޸ı�־0Ϊɾ����1Ϊ�޸ġ�
    private String deleteReason;//ɾ������
    private String isPushAll;//�Ƿ���ȫ��
    private String sendType;//�������� 0�ʼ����ڷ����ʼ��ʼ������ڷ��Ͷ���  1ֻ���Ͷ��ţ�2ֻ�����ʼ���
    private List<String> IdList;//id�б���������������Ϣʱ������id�б�ȥ����Ϣ
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