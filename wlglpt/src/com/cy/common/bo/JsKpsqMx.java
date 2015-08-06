package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_KPSQ_MX is created by tools.
 * @author HJH
 */

public class JsKpsqMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpsqmxDjxh;                       // 开票申请登记序号
	private String kpsqDjxh;                         // 开票申请登记序号(SEQ_QD_DJXH)
	private String kpsqMxflDm;                       // 开票申请明细分类代码
	private String ywDjxh;                           // 清单登记序号
	private Double sqKpje;                           // 申请开票金额
	private String bzsm;                             // 备注说明
	private String yxbz;                             // 有效标志(Y/N)
	private String czrq;//操作日期

	public JsKpsqMx() {
	}

	//获取开票申请登记序号
	public String getKpsqmxDjxh() {
		return this.kpsqmxDjxh;
	}

	//设置开票申请登记序号
	public void setKpsqmxDjxh(String kpsqmxDjxh) {
		this.kpsqmxDjxh=kpsqmxDjxh;
	}

	//获取开票申请登记序号(SEQ_QD_DJXH)
	public String getKpsqDjxh() {
		return this.kpsqDjxh;
	}

	//设置开票申请登记序号(SEQ_QD_DJXH)
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh=kpsqDjxh;
	}

	//获取开票申请明细分类代码
	public String getKpsqMxflDm() {
		return this.kpsqMxflDm;
	}

	//设置开票申请明细分类代码
	public void setKpsqMxflDm(String kpsqMxflDm) {
		this.kpsqMxflDm=kpsqMxflDm;
	}

	//获取清单登记序号
	public String getYwDjxh() {
		return this.ywDjxh;
	}

	//设置清单登记序号
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh=ywDjxh;
	}

	//获取申请开票金额
	public Double getSqKpje() {
		return this.sqKpje;
	}

	//设置申请开票金额
	public void setSqKpje(Double sqKpje) {
		this.sqKpje=sqKpje;
	}

	//获取备注说明
	public String getBzsm() {
		return this.bzsm;
	}

	//设置备注说明
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	public String getCzrq() {
		return czrq;
	}

	public void setCzrq(String czrq) {
		this.czrq = czrq;
	}
}