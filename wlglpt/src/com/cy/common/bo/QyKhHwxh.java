package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_KH_HWXX is created by tools.
 * @author HJH
 */

public class QyKhHwxh  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwxhDjxh;
	private String ssJgbm;                           // 机构编码(SEQ_JG_DJXH)
	private String khDjxh;                           // 客户登记序号
	private String hwDjxh;                           // 货物登记序号
	private String xhmc;                             // 名称
	private String xhjc;                             // 简称
	private String xhqc;                             // 全称
	private String pyqc;                             // 拼音全称
	private String pyjc;                             // 拼音简称
	private String cdJldwDm;                         // 
	private Double cd;                               // 电话
	private Double kd;                               // 邮编
	private Double gd;                               // 负责人
	private String bzJldwDm;                         // 地址
	private Double bzJsHsbl;                         // 
	private Double bzCbHsbl;                         // 
	private String jsJldwDm;                         // 地址
	private String cbJldwDm;                         // 地址
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
	private String slJldwDm;						//数量
	private String zlJldwDm;  						//重量		
	private String tjJldwDm;                        //体积
	public String getSlJldwDm() {
		return slJldwDm;
	}

	public void setSlJldwDm(String slJldwDm) {
		this.slJldwDm = slJldwDm;
	}

	public String getTjJldwDm() {
		return tjJldwDm;
	}

	public void setTjJldwDm(String tjJldwDm) {
		this.tjJldwDm = tjJldwDm;
	}

	public String getZlJldwDm() {
		return zlJldwDm;
	}

	public void setZlJldwDm(String zlJldwDm) {
		this.zlJldwDm = zlJldwDm;
	}

	public QyKhHwxh() {
	}

	//获取
	public String getHwDjxh() {
		return this.hwDjxh;
	}

	//设置
	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh=hwDjxh;
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
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
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
	public String getCdJldwDm() {
		return this.cdJldwDm;
	}

	//设置
	public void setCdJldwDm(String cdJldwDm) {
		this.cdJldwDm=cdJldwDm;
	}

	//获取电话
	public Double getCd() {
		return this.cd;
	}

	//设置电话
	public void setCd(Double cd) {
		this.cd=cd;
	}

	//获取邮编
	public Double getKd() {
		return this.kd;
	}

	//设置邮编
	public void setKd(Double kd) {
		this.kd=kd;
	}

	//获取负责人
	public Double getGd() {
		return this.gd;
	}

	//设置负责人
	public void setGd(Double gd) {
		this.gd=gd;
	}

	//获取地址
	public String getBzJldwDm() {
		return this.bzJldwDm;
	}

	//设置地址
	public void setBzJldwDm(String bzJldwDm) {
		this.bzJldwDm=bzJldwDm;
	}

	//获取
	public Double getBzJsHsbl() {
		return this.bzJsHsbl;
	}

	//设置
	public void setBzJsHsbl(Double bzJsHsbl) {
		this.bzJsHsbl=bzJsHsbl;
	}

	//获取
	public Double getBzCbHsbl() {
		return this.bzCbHsbl;
	}

	//设置
	public void setBzCbHsbl(Double bzCbHsbl) {
		this.bzCbHsbl=bzCbHsbl;
	}

	//获取地址
	public String getJsJldwDm() {
		return this.jsJldwDm;
	}

	//设置地址
	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm=jsJldwDm;
	}

	//获取地址
	public String getCbJldwDm() {
		return this.cbJldwDm;
	}

	//设置地址
	public void setCbJldwDm(String cbJldwDm) {
		this.cbJldwDm=cbJldwDm;
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

	public String getHwxhDjxh() {
		return hwxhDjxh;
	}

	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh = hwxhDjxh;
	}

	public String getXhjc() {
		return xhjc;
	}

	public void setXhjc(String xhjc) {
		this.xhjc = xhjc;
	}

	public String getXhmc() {
		return xhmc;
	}

	public void setXhmc(String xhmc) {
		this.xhmc = xhmc;
	}

	public String getXhqc() {
		return xhqc;
	}

	public void setXhqc(String xhqc) {
		this.xhqc = xhqc;
	}
}