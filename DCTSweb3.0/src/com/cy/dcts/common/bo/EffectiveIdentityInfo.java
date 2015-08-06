package com.cy.dcts.common.bo;
/**
 * @description 身份信息合法库
 * @author      haoy
 *
 */
public class EffectiveIdentityInfo extends BaseBo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -216671766917234802L;

	private int id;							//主键，自增长
	private String IDNumber;				//身份证号码
	private String name;					//身份证姓名
	private String remark;					//备注
	private String createTime;				//创建时间
	
	public EffectiveIdentityInfo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
