package com.cy.dcts.common.domain;
/**
 * @description 身份信息合法库
 * @author 		haoy
 *
 */
public class EffectiveIdentityInfoDomain extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5426888245674332615L;
	
	private String id;							//主键，自增长
	private String IDNumber;				//身份证号码
	private String name;					//身份证姓名
	private String remark;					//备注
	private String createTime;				//创建时间

	public EffectiveIdentityInfoDomain() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
