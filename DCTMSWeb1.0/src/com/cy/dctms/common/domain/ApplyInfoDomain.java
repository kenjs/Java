package com.cy.dctms.common.domain;

import java.util.List;

/**
*身份证验证申请
* wjl
*/
public class ApplyInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // 主键ID
    private String userId;  // 申请人ID
    private String companyId;  // 申请公司ID
    private String applyType;  // 申请功能类型(0：货主版功能、1：身份证查询功能、2：VIP功能)
    private String applyTime;  // 申请时间
    private String contactName;  // 申请人姓名
    private String contactTelephone;  // 申请人电话
    private String applyComment;  // 申请内容
    private String verifyTime;  // 审核时间
    private String verifyStart;  // 审核状态（0等待审核、-1审核失败、1审核成功）
    private String verifyComment;  // 审核回复内容
    private String operatorId;  // 操作人ID
    //冗余
    private String applyTimeQ;// 申请时间起
    private String applyTimeZ;// 申请时间止
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