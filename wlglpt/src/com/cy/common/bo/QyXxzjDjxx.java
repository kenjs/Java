package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_XXZJ_DJXX is created by tools.
 * @author HJH
 */

public class QyXxzjDjxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xxzjDjxh;                         // 信息中介登记序号(SEQ_ZY_DJXH)
	private String ssJgbm;                           // 所属机构
	private String xxzjmc;                           // 信息中介名称
	private String xxzjjc;                           // 信息中介简称
	private String pyqc;                             // 拼音全称
	private String pyjc;                             // 拼音简称
	private String xzqhDm;                           // 行政区划代码
	private String dz;                               // 地址
	private String dh;                               // 电话
	private String yb;                               // 邮编
	private String fzr;                              // 负责人
	private String xxzjQybm;                         // 信息中介企业编码
	private String bz;                               // 备注
	private String djJgbm;                           // 登记部门
	private String djrCzyDjxh;                       // 登记人
	private String djrq;                             // 登记日期
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public QyXxzjDjxx() {
	}

	//获取信息中介登记序号(SEQ_ZY_DJXH)
	public String getXxzjDjxh() {
		return this.xxzjDjxh;
	}

	//设置信息中介登记序号(SEQ_ZY_DJXH)
	public void setXxzjDjxh(String xxzjDjxh) {
		this.xxzjDjxh=xxzjDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取信息中介名称
	public String getXxzjmc() {
		return this.xxzjmc;
	}

	//设置信息中介名称
	public void setXxzjmc(String xxzjmc) {
		this.xxzjmc=xxzjmc;
	}

	//获取信息中介简称
	public String getXxzjjc() {
		return this.xxzjjc;
	}

	//设置信息中介简称
	public void setXxzjjc(String xxzjjc) {
		this.xxzjjc=xxzjjc;
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

	//获取行政区划代码
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//设置行政区划代码
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
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

	//获取信息中介企业编码
	public String getXxzjQybm() {
		return this.xxzjQybm;
	}

	//设置信息中介企业编码
	public void setXxzjQybm(String xxzjQybm) {
		this.xxzjQybm=xxzjQybm;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取登记部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置登记部门
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取登记人
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//设置登记人
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//获取登记日期
	public String getDjrq() {
		return this.djrq;
	}

	//设置登记日期
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