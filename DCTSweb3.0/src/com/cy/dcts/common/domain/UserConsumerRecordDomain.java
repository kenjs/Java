package com.cy.dcts.common.domain;
/**
 * @description 用户消费记录
 * @author 		haoy
 *
 */
public class UserConsumerRecordDomain extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6968900527734682729L;
	
	private String id;					//主键，自增长
	private String userId;				//用户id
	private String consumerType;		//消费类型{0:身份证信息验证}
	private String consumerAmount;		//消费金额
	private String remark;				//备注
	private String createTime;			//创建时间

	public UserConsumerRecordDomain() {
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

	public String getConsumerType() {
		return consumerType;
	}

	public void setConsumerType(String consumerType) {
		this.consumerType = consumerType;
	}

	public String getConsumerAmount() {
		return consumerAmount;
	}

	public void setConsumerAmount(String consumerAmount) {
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
