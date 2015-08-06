package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_KHYSGL is created by tools.
 * @author HJH
 */

public class CwKhysgl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String srDjxh;                           // 收入登记序号(SEQ_SR_DJXH)
	private String khDjxh;                           // 应收应付登记序号
	private String je;                               // 运费结算方代码
	private String yxbz;                             // 运费结算方登记序号
	private String djrDjxh;                          // 付款方名称
	private String djrq;                             // 
	private String djJgbm;                           // 日期
	private String ssSsjg;                           // 
	private String xgrDjxh;                          // 
	private String xgrq;                             // 
	private String khMc;
	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public CwKhysgl() {
	}

	//获取收入登记序号(SEQ_SR_DJXH)
	public String getSrDjxh() {
		return this.srDjxh;
	}

	//设置收入登记序号(SEQ_SR_DJXH)
	public void setSrDjxh(String srDjxh) {
		this.srDjxh=srDjxh;
	}

	//获取应收应付登记序号
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置应收应付登记序号
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取运费结算方代码
	

	//获取运费结算方登记序号
	public String getYxbz() {
		return this.yxbz;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	//设置运费结算方登记序号
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取付款方名称
	public String getDjrDjxh() {
		return this.djrDjxh;
	}

	//设置付款方名称
	public void setDjrDjxh(String djrDjxh) {
		this.djrDjxh=djrDjxh;
	}

	//获取
	public String getDjrq() {
		return this.djrq;
	}

	//设置
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//获取日期
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置日期
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取
	public String getSsSsjg() {
		return this.ssSsjg;
	}

	//设置
	public void setSsSsjg(String ssSsjg) {
		this.ssSsjg=ssSsjg;
	}

	//获取
	public String getXgrDjxh() {
		return this.xgrDjxh;
	}

	//设置
	public void setXgrDjxh(String xgrDjxh) {
		this.xgrDjxh=xgrDjxh;
	}

	//获取
	public String getXgrq() {
		return this.xgrq;
	}

	//设置
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}
}