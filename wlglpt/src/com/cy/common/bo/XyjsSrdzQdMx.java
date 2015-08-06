package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For XYJS_SRDZ_QD_MX is created by tools.
 * @author HJH
 */

public class XyjsSrdzQdMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // 清单登记序号(SEQ_QD_DJXH)
	private String ywDjxh;                           // 结算登记序号
	private String ywMxXh;                           // 1：配送费；2：到付款；3：代收货款
	private String ywLydm;                           // 1：收入对账；2：费用登记；3：物流损失

	public XyjsSrdzQdMx() {
	}

	//获取清单登记序号(SEQ_QD_DJXH)
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//设置清单登记序号(SEQ_QD_DJXH)
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	//获取结算登记序号
	public String getYwDjxh() {
		return this.ywDjxh;
	}

	//设置结算登记序号
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh=ywDjxh;
	}

	//获取1：配送费；2：到付款；3：代收货款
	public String getYwMxXh() {
		return this.ywMxXh;
	}

	//设置1：配送费；2：到付款；3：代收货款
	public void setYwMxXh(String ywMxXh) {
		this.ywMxXh=ywMxXh;
	}

	//获取1：收入对账；2：费用登记；3：物流损失
	public String getYwLydm() {
		return this.ywLydm;
	}

	//设置1：收入对账；2：费用登记；3：物流损失
	public void setYwLydm(String ywLydm) {
		this.ywLydm=ywLydm;
	}
}