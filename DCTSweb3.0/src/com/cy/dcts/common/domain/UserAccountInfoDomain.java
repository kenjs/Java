package com.cy.dcts.common.domain;
/**
 * @description 用户账户
 * @author 		haoy
 *
 */
public class UserAccountInfoDomain extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4461596728688885762L;

	private String id;					//主键，自增长
	private String userId;			//用户id
	private double accountBalance;	//账户余额
	private String remark;			//备注
	private String createTime;		//创建时间
	private String modifyTime;		//修改时间
	
	public UserAccountInfoDomain() {
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

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
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

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}
