package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_HYXM is created by tools.
 * @author HJH
 */

public class BgHyxm  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hyxmDjxh;                         // 行业新闻登记序号(SEQ_BG_DJXH)
	private String jgbm;                             // 机构编码
	private String fbrq;                             // 发布日期(YYYY-MM-DD)
	private String bcztDm;                           // 保存状态代码
	private String ly;                               // 来源
	private String zt;                               // 主题
	private String nr;                               // 内容
	private String xjgxbz;                           // 下级共享标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public BgHyxm() {
	}

	//获取行业新闻登记序号(SEQ_BG_DJXH)
	public String getHyxmDjxh() {
		return this.hyxmDjxh;
	}

	//设置行业新闻登记序号(SEQ_BG_DJXH)
	public void setHyxmDjxh(String hyxmDjxh) {
		this.hyxmDjxh=hyxmDjxh;
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

	//获取来源
	public String getLy() {
		return this.ly;
	}

	//设置来源
	public void setLy(String ly) {
		this.ly=ly;
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