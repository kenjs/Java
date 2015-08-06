package com.cy.common.bo;




import java.io.Serializable;

/**
 * The persistent class For QY_ZZJG is created by tools.
 * @author HJH
 */

public class QyZzjg  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // 机构编码(SEQ_JG_DJXH)
	private String mc;                               // 名称
	private String jc;                               // 简称
	private String jglbDm;                           // 机构类别代码
	private String jcbm;                             // 级次编码
	private String jbdm;                             // 级别代码
	private String sjJgbm;                           // 上级机构编码
	private String qyZcxh;                           // 企业注册序号(总公司时不能为空)
	private String dz;                               // 地址
	private String dh;                               // 电话
	private String yb;                               // 邮编
	private String fzr;                              // 负责人
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String pyjx;                             // 拼音简写
	private String pyqp;                             // 拼音全拼

	public QyZzjg() {
	}

	//获取机构编码(SEQ_JG_DJXH)
	public String getJgbm() {
		return this.jgbm;
	}

	//设置机构编码(SEQ_JG_DJXH)
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//获取名称
	public String getMc() {
		return this.mc;
	}

	//设置名称
	public void setMc(String mc) {
		this.mc=mc;
	}

	//获取简称
	public String getJc() {
		return this.jc;
	}

	//设置简称
	public void setJc(String jc) {
		this.jc=jc;
	}

	//获取机构类别代码
	public String getJglbDm() {
		return this.jglbDm;
	}

	//设置机构类别代码
	public void setJglbDm(String jglbDm) {
		this.jglbDm=jglbDm;
	}

	//获取级次编码
	public String getJcbm() {
		return this.jcbm;
	}

	//设置级次编码
	public void setJcbm(String jcbm) {
		this.jcbm=jcbm;
	}

	//获取级别代码
	public String getJbdm() {
		return this.jbdm;
	}

	//设置级别代码
	public void setJbdm(String jbdm) {
		this.jbdm=jbdm;
	}

	//获取上级机构编码
	public String getSjJgbm() {
		return this.sjJgbm;
	}

	//设置上级机构编码
	public void setSjJgbm(String sjJgbm) {
		this.sjJgbm=sjJgbm;
	}

	//获取企业注册序号(总公司时不能为空)
	public String getQyZcxh() {
		return this.qyZcxh;
	}

	//设置企业注册序号(总公司时不能为空)
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh=qyZcxh;
	}

	//获取地址
	public String getDz() {
		return this.dz;
	}

	//设置地址
	public void setDz(String dz) {
		this.dz=dz;
	}

	//获取电话
	public String getDh() {
		return this.dh;
	}

	//设置电话
	public void setDh(String dh) {
		this.dh=dh;
	}

	//获取邮编
	public String getYb() {
		return this.yb;
	}

	//设置邮编
	public void setYb(String yb) {
		this.yb=yb;
	}

	//获取负责人
	public String getFzr() {
		return this.fzr;
	}

	//设置负责人
	public void setFzr(String fzr) {
		this.fzr=fzr;
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

	//获取
	public String getPyjx() {
		return this.pyjx;
	}

	//设置
	public void setPyjx(String pyjx) {
		this.pyjx=pyjx;
	}

	//获取
	public String getPyqp() {
		return this.pyqp;
	}

	//设置
	public void setPyqp(String pyqp) {
		this.pyqp=pyqp;
	}
}