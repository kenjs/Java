package com.cy.dctms.common.bo;

import java.util.Date;
public class ApplyInfo {
    private Long id;  // ����ID
    private Long userId;  // ������ID
    private Long companyId;  // ���빫˾ID
    private Long applyType;  // ���빦������(0�������湦�ܡ�1�����֤��ѯ���ܡ�2��VIP����)
    private Date applyTime;  // ����ʱ��
    private String contactName;  // ����������
    private String contactTelephone;  // �����˵绰
    private String applyComment;  // ��������
    private Date verifyTime;  // ���ʱ��
    private Long verifyStart;  // ���״̬��0�ȴ���ˡ�-1���ʧ�ܡ�1��˳ɹ���
    private String verifyComment;  // ��˻ظ�����
    private Long operatorId;  // ������ID
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public Long getApplyType() {
        return applyType;
    }
    public void setApplyType(Long applyType) {
        this.applyType = applyType;
    }
    public Date getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(Date applyTime) {
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
    public Date getVerifyTime() {
        return verifyTime;
    }
    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }
    public Long getVerifyStart() {
        return verifyStart;
    }
    public void setVerifyStart(Long verifyStart) {
        this.verifyStart = verifyStart;
    }
    public String getVerifyComment() {
        return verifyComment;
    }
    public void setVerifyComment(String verifyComment) {
        this.verifyComment = verifyComment;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}