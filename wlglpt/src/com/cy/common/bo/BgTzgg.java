package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_TZGG is created by tools.
 * @author HJH
 */

public class BgTzgg  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String tzggXh;                           // 通知公告序号(SEQ_TZGG_XH)
	private String jgbm;                             // 机构编码
	private String fbrq;                             // 发布日期(YYYY-MM-DD)
	private String bcztDm;                           // 保存状态代码
	private String zt;                               // 主题
	private String nr;                               // 内容
	private String xjgxbz;                           // 下级共享标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public BgTzgg() {
	}

	//获取通知公告序号(SEQ_TZGG_XH)
	public String getTzggXh() {
		return this.tzggXh;
	}

	//设置通知公告序号(SEQ_TZGG_XH)
	public void setTzggXh(String tzggXh) {
		this.tzggXh=tzggXh;
	}

	//获取机构编码
	public String getJgbm() {
		return this.jgbm;
	}

	//设置机构编码
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//获取发布日期(YYYY-MM-DD)
	public String getFbrq() {
		return this.fbrq;
	}

	//设置发布日期(YYYY-MM-DD)
	public void setFbrq(String fbrq) {
		this.fbrq=fbrq;
	}

	//获取保存状态代码
	public String getBcztDm() {
		return this.bcztDm;
	}

	//设置保存状态代码
	public void setBcztDm(String bcztDm) {
		this.bcztDm=bcztDm;
	}

	//获取主题
	public String getZt() {
		return this.zt;
	}

	//设置主题
	public void setZt(String zt) {
		this.zt=zt;
	}

	//获取内容
	public String getNr() {
		return this.nr;
	}

	//设置内容
	public void setNr(String nr) {
		this.nr=nr;
	}

	//获取下级共享标志(Y/N)
	public String getXjgxbz() {
		return this.xjgxbz;
	}

	//设置下级共享标志(Y/N)
	public void setXjgxbz(String xjgxbz) {
		this.xjgxbz=xjgxbz;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取创建人
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//设置创建人
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//获取创建日期
	public String getCjrq() {
		return this.cjrq;
	}

	//设置创建日期
	public void setCjrq(String cjrq) {
		this.cjrq=cjrq;
	}

	//获取修改人
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//设置修改人
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//获取修改日期
	public String getXgrq() {
		return this.xgrq;
	}

	//设置修改日期
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}
}