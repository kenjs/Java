package com.cy.dctms.common.domain;

import java.util.List;

/**
*���֤��֤����
* wjl
*/
public class ApplyInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ����ID
    private String userId;  // ������ID
    private String companyId;  // ���빫˾ID
    private String applyType;  // ���빦������(0�������湦�ܡ�1�����֤��ѯ���ܡ�2��VIP����)
    private String applyTime;  // ����ʱ��
    private String contactName;  // ����������
    private String contactTelephone;  // �����˵绰
    private String applyComment;  // ��������
    private String verifyTime;  // ���ʱ��
    private String verifyStart;  // ���״̬��0�ȴ���ˡ�-1���ʧ�ܡ�1��˳ɹ���
    private String verifyComment;  // ��˻ظ�����
    private String operatorId;  // ������ID
    //����
    private String applyTimeQ;// ����ʱ����
    private String applyTimeZ;// ����ʱ��ֹ
    public String getApplyTimeQ() {
		return applyTimeQ;
	}
	public void setApplyTimeQ(String applyTimeQ) {
		this.applyTimeQ = applyTimeQ;
	}
	public String getApplyTimeZ() {
		return applyTimeZ;
	}
	public void setApplyTimeZ(String applyTimeZ) {
		this.applyTimeZ = applyTimeZ;
	}
	private List<ApplyInfoDomain> dataList;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getApplyType() {
        return applyType;
    }
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
    public String getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
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
    public String getApplyComment() {
        return applyComment;
    }
    public void setApplyComment(String applyComment) {
        this.applyComment = applyComment;
    }
    public String getVerifyTime() {
        return verifyTime;
    }
    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }
    public String getVerifyStart() {
        return verifyStart;
    }
    public void setVerifyStart(String verifyStart) {
        this.verifyStart = verifyStart;
    }
    public String getVerifyComment() {
        return verifyComment;
    }
    public void setVerifyComment(String verifyComment) {
        this.verifyComment = verifyComment;
    }
    public String getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    public List<ApplyInfoDomain> getDataList() {    	return dataList;    }    public void setDataList(List<ApplyInfoDomain> dataList) {    	this.dataList = dataList;    }}