package com.cy.common.bo;

import java.io.Serializable;

public class TransactionReceiptPathBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3531805531293321543L;
	
	private int id;//回单表主键
	private String transactionId;//关联订单ID
	private String receiptPath;//回单上传图片路径
	private String createTime;//创建时间
	private String type;//0：回单
	public TransactionReceiptPathBo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
