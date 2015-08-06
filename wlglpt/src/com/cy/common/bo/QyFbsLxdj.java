package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_FBS_LXDJ is created by tools.
 * @author HJH
 */

public class QyFbsLxdj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String lxDjxh;                           // 
	private String ssJgbm;                           // 机构编码(SEQ_JG_DJXH)
	private String fbsDjxh;                          // 
	private String lxmc;                             // 名称
	private String lxjc;                             // 
	private String pyqc;                             // 拼音全称
	private String pyjc;                             // 拼音简称
	private String sfdXzqhDm;                        // 
	private String mddXzqhDm;                        // 行政区划代码
	private String bz;                               // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public QyFbsLxdj() {
	}

	//获取
	public String getLxDjxh() {
		return this.lxDjxh;
	}

	//设置
	public void setLxDjxh(String lxDjxh) {
		this.lxDjxh=lxDjxh;
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
	public String getLxmc() {
		return this.lxmc;
	}

	//设置名称
	public void setLxmc(String lxmc) {
		this.lxmc=lxmc;
	}

	//获取
	public String getLxjc() {
		return this.lxjc;
	}

	//设置
	public void setLxjc(String lxjc) {
		this.lxjc=lxjc;
	}

	//获取拼音全称
	public String getPyqc() {
		return this.pyqc;
	}

	//设置拼音全称
	public void setPyqc(String pyqc) {
		this.pyqc=pyqc;
	}

	//获取拼音简称
	public String getPyjc() {
		return this.pyjc;
	}

	//设置拼音简称
	public void setPyjc(String pyjc) {
		this.pyjc=pyjc;
	}

	//获取
	public String getSfdXzqhDm() {
		return this.sfdXzqhDm;
	}

	//设置
	public void setSfdXzqhDm(String sfdXzqhDm) {
		this.sfdXzqhDm=sfdXzqhDm;
	}

	//获取行政区划代码
	public String getMddXzqhDm() {
		return this.mddXzqhDm;
	}

	//设置行政区划代码
	public void setMddXzqhDm(String mddXzqhDm) {
		this.mddXzqhDm=mddXzqhDm;
	}

	//获取
	public String getBz() {
		return this.bz;
	}

	//设置
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//设置
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//获取
	public String getDjrq() {
		return this.djrq;
	}

	//设置
	public void setDjrq(String djrq) {
		this.djrq=djrq;
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