package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_ZLGX is created by tools.
 * @author HJH
 */

public class BgZlgx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zlgxDjxh;                         // 资料共享登记序号(SEQ_BG_DJXH)
	private String jgbm;                             // 机构编码
	private String fbrq;                             // 发布日期(YYYY-MM-DD)
	private String ly;                               // 来源
	private String zlmc;                             // 资料名称
	private String sm;                               // 资料说明
	private String bcztDm;                           // 保存状态代码
	private String xjgxbz;                           // 下级共享标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public BgZlgx() {
	}

	//获取资料共享登记序号(SEQ_BG_DJXH)
	public String getZlgxDjxh() {
		return this.zlgxDjxh;
	}

	//设置资料共享登记序号(SEQ_BG_DJXH)
	public void setZlgxDjxh(String zlgxDjxh) {
		this.zlgxDjxh=zlgxDjxh;
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

	//获取来源
	public String getLy() {
		return this.ly;
	}

	//设置来源
	public void setLy(String ly) {
		this.ly=ly;
	}

	//获取资料名称
	public String getZlmc() {
		return this.zlmc;
	}

	//设置资料名称
	public void setZlmc(String zlmc) {
		this.zlmc=zlmc;
	}

	//获取资料说明
	public String getSm() {
		return this.sm;
	}

	//设置资料说明
	public void setSm(String sm) {
		this.sm=sm;
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

	public String getBcztDm() {
		return bcztDm;
	}

	public void setBcztDm(String bcztDm) {
		this.bcztDm = bcztDm;
	}
}