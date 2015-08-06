package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_SRDZ_QD_MX is created by tools.
 * @author HJH
 */

public class JsSrdzQdMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // 清单登记序号(SEQ_QD_DJXH)
	private String ywDjxh;                           // 业务登记序号
	private String ywMxXh;							 // 业务明细序号
	private String ywLydm;							 // 业务来源代码 1：收入对账；2：费用登记；3：物流损失
	private String czRq;          					//操作日期           

	public JsSrdzQdMx() {
	}

	//获取清单登记序号(SEQ_QD_DJXH)
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//设置清单登记序号(SEQ_QD_DJXH)
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	public String getYwDjxh() {
		return ywDjxh;
	}

	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh = ywDjxh;
	}

	public String getYwMxXh() {
		return ywMxXh;
	}

	public void setYwMxXh(String ywMxXh) {
		this.ywMxXh = ywMxXh;
	}

	public String getYwLydm() {
		return ywLydm;
	}

	public void setYwLydm(String ywLydm) {
		this.ywLydm = ywLydm;
	}

	public String getCzRq() {
		return czRq;
	}

	public void setCzRq(String czRq) {
		this.czRq = czRq;
	}
}