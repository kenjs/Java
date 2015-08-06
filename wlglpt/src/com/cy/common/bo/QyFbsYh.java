package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_FBS_YH is created by tools.
 * @author HJH
 */

public class QyFbsYh  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String yhDjxh;                           // 
	private String qybm;                             // 企业编码
	private String ssJgbm;                           // 机构编码(SEQ_JG_DJXH)
	private String fbsDjxh;                          // 
	private String mc;                               // 名称
	private String zh;                               // 账号
	private String pwd;                              // 密码
	private String dlyzfsDm;                         // 登录验证方式代码
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrq;                             // 创建日期
	private String cjrCzyDjxh;                       // 创建人
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public QyFbsYh() {
	}

	//获取
	public String getYhDjxh() {
		return this.yhDjxh;
	}

	//设置
	public void setYhDjxh(String yhDjxh) {
		this.yhDjxh=yhDjxh;
	}

	//获取企业编码
	public String getQybm() {
		return this.qybm;
	}

	//设置企业编码
	public void setQybm(String qybm) {
		this.qybm=qybm;
	}

	//获取机构编码(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置机构编码(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取
	public String getFbsDjxh() {
		return this.fbsDjxh;
	}

	//设置
	public void setFbsDjxh(String fbsDjxh) {
		this.fbsDjxh=fbsDjxh;
	}

	//获取名称
	public String getMc() {
		return this.mc;
	}

	//设置名称
	public void setMc(String mc) {
		this.mc=mc;
	}

	//获取账号
	public String getZh() {
		return this.zh;
	}

	//设置账号
	public void setZh(String zh) {
		this.zh=zh;
	}

	//获取密码
	public String getPwd() {
		return this.pwd;
	}

	//设置密码
	public void setPwd(String pwd) {
		this.pwd=pwd;
	}

	//获取登录验证方式代码
	public String getDlyzfsDm() {
		return this.dlyzfsDm;
	}

	//设置登录验证方式代码
	public void setDlyzfsDm(String dlyzfsDm) {
		this.dlyzfsDm=dlyzfsDm;
	}

	//获取启用标志(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//设置启用标志(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取创建日期
	public String getCjrq() {
		return this.cjrq;
	}

	//设置创建日期
	public void setCjrq(String cjrq) {
		this.cjrq=cjrq;
	}

	//获取创建人
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//设置创建人
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
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