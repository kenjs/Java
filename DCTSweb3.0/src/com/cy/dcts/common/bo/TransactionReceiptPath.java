package com.cy.dcts.common.bo;
/**
 * 回单上传表
 * @author zdy
 *
 */
public class TransactionReceiptPath extends BaseBo{
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;//回单表ID
	private String transactionId;//关联订单ID
	private String receiptPath;//回单上传图片路径
	private String createTime;//创建时间
	private String type;//标志：0回单
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getReceiptPath() {
		return receiptPath;
	}
	public void setReceiptPath(String receiptPath) {
		this.receiptPath = receiptPath;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
