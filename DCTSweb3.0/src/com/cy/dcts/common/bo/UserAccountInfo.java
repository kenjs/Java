package com.cy.dcts.common.bo;
/**
 * @description 用户账户
 * @author 		haoy
 *
 */
public class UserAccountInfo extends BaseBo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9018355677325803098L;
	
	private int id;					//主键，自增长
	private String userId;			//用户id
	private double accountBalance;	//账户余额
	private String remark;			//备注
	private String createTime;		//创建时间
	private String modifyTime;		//修改时间

	public UserAccountInfo() {		
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
