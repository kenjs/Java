package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_SWDN is created by tools.
 * @author HaoY
 */

public class QySwdn  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ggDjxh;                           // 公共登记序号(SEQ_GG_DJXH)
	private String qyZcxh;                           // 企业注册序号(GL_QYZC.QY_ZCXH)
	private String czyDjxh;                          // 操作员登记序号(QY_RYDJ.CZY_DJXH)
	private String mac;                              // MAC地址
	private String bzsm;                             // 备注说明
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public QySwdn() {
	}

	//获取公共登记序号(SEQ_GG_DJXH)
	public String getGgDjxh() {
		return this.ggDjxh;
	}

	//设置公共登记序号(SEQ_GG_DJXH)
	public void setGgDjxh(String ggDjxh) {
		this.ggDjxh=ggDjxh;
	}

	//获取企业注册序号(GL_QYZC.QY_ZCXH)
	public String getQyZcxh() {
		return this.qyZcxh;
	}

	//设置企业注册序号(GL_QYZC.QY_ZCXH)
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh=qyZcxh;
	}

	//获取操作员登记序号(QY_RYDJ.CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置操作员登记序号(QY_RYDJ.CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//获取MAC地址
	public String getMac() {
		return this.mac;
	}

	//设置MAC地址
	public void setMac(String mac) {
		this.mac=mac;
	}

	//获取备注说明
	public String getBzsm() {
		return this.bzsm;
	}

	//设置备注说明
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
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