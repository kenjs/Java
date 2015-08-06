package com.cy.dcts.common.bo;
/**
 * @description 身份验证日志
 * @author      haoy
 *
 */
public class IdentityVerifyLog extends BaseBo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3280955223655153370L;

	private int id;							//主键，自增长
	private String operatorId;				//操作人id
	private String IDNumber;				//身份证号码
	private String name;					//身份证姓名
	private String IDNumberVerifyResult;	//身份证号码验证结果
	private String nameVerifyResult;		//身份证姓名验证结果
	private String errorMesage;				//错误信息
	private String errorMesageCol;			//错误列
	private String errorCode;				//错误代码
	private String remark;					//备注
	private String createTime;				//创建时间
	
	public IdentityVerifyLog() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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

	public String getIDNumberVerifyResult() {
		return IDNumberVerifyResult;
	}

	public void setIDNumberVerifyResult(String iDNumberVerifyResult) {
		IDNumberVerifyResult = iDNumberVerifyResult;
	}

	public String getNameVerifyResult() {
		return nameVerifyResult;
	}

	public void setNameVerifyResult(String nameVerifyResult) {
		this.nameVerifyResult = nameVerifyResult;
	}

	public String getErrorMesage() {
		return errorMesage;
	}

	public void setErrorMesage(String errorMesage) {
		this.errorMesage = errorMesage;
	}

	public String getErrorMesageCol() {
		return errorMesageCol;
	}

	public void setErrorMesageCol(String errorMesageCol) {
		this.errorMesageCol = errorMesageCol;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
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
