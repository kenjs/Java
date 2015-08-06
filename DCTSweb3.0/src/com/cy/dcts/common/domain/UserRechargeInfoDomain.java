package com.cy.dcts.common.domain;
/**
 * @description 用户充值记录
 * @author 		haoy
 *
 */
public class UserRechargeInfoDomain extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2857587101842406927L;

	private String id;				//主键，自增长
	private String userId;			//用户id
	private String operatorId;		//操作人id
	private String rechargeType;	//充值类型{0:现金}
	private String rechargeAmount;	//充值金额
	private String remark;			//备注
	private String createTime;		//创建时间
	
	public UserRechargeInfoDomain() {
	}

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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(String rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
