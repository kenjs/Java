package com.cy.dcts.common.bo;
/**
 * @description 用户充值记录
 * @author 		haoy
 *
 */
public class UserRechargeInfo extends BaseBo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4214075589173782283L;
	
	private int id;					//主键，自增长
	private String userId;			//用户id
	private String operatorId;		//操作人id
	private int rechargeType;		//充值类型{0:现金}
	private double rechargeAmount;	//充值金额
	private String remark;			//备注
	private String createTime;		//创建时间

	public UserRechargeInfo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(int rechargeType) {
		this.rechargeType = rechargeType;
	}

	public double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(double rechargeAmount) {
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
