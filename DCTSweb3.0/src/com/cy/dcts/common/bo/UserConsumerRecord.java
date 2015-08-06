package com.cy.dcts.common.bo;
/**
 * @description 用户消费记录
 * @author 		haoy
 *
 */
public class UserConsumerRecord extends BaseBo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2925296285604308819L;

	private int id;					//主键，自增长
	private String userId;			//用户id
	private int consumerType;		//消费类型{0:身份证信息验证}
	private double consumerAmount;	//消费金额
	private String remark;			//备注
	private String createTime;		//创建时间
	
	public UserConsumerRecord() {
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

	public int getConsumerType() {
		return consumerType;
	}

	public void setConsumerType(int consumerType) {
		this.consumerType = consumerType;
	}

	public double getConsumerAmount() {
		return consumerAmount;
	}

	public void setConsumerAmount(double consumerAmount) {
		this.consumerAmount = consumerAmount;
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
