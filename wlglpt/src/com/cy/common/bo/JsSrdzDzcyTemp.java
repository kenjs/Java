package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_SRDZ_DZCY_TEMP is created by tools.
 * @author HJH
 */

public class JsSrdzDzcyTemp  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // 对账登记序号
	private String xh;                               // 序号
	private Double dzcyje;                           // 对帐差异金额
	private String dzcyyyDm;                         // 对帐差异原因代码
	private String dzcyClfsDm;                       // 对帐差异处理方式代码
	private String bz;                               // 备注说明
	//private String preJsDjxh;                        // 二次结算登记序号
	private String xcJsDjxh;//下次_结算登记序号
	private String wlssDjxh;                         // 物流损失登记序号
	private String rq;                               // 日期

	public JsSrdzDzcyTemp() {
	}

	//获取序号
	public String getXh() {
		return this.xh;
	}

	//设置序号
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取对帐差异金额
	public Double getDzcyje() {
		return this.dzcyje;
	}

	//设置对帐差异金额
	public void setDzcyje(Double dzcyje) {
		this.dzcyje=dzcyje;
	}

	//获取对帐差异原因代码
	public String getDzcyyyDm() {
		return this.dzcyyyDm;
	}

	//设置对帐差异原因代码
	public void setDzcyyyDm(String dzcyyyDm) {
		this.dzcyyyDm=dzcyyyDm;
	}

	//获取对帐差异处理方式代码
	public String getDzcyClfsDm() {
		return this.dzcyClfsDm;
	}

	//设置对帐差异处理方式代码
	public void setDzcyClfsDm(String dzcyClfsDm) {
		this.dzcyClfsDm=dzcyClfsDm;
	}

	//获取备注说明
	public String getBz() {
		return this.bz;
	}

	//设置备注说明
	public void setBz(String bz) {
		this.bz=bz;
	}

/*	//获取二次结算登记序号
	public String getPreJsDjxh() {
		return this.preJsDjxh;
	}

	//设置二次结算登记序号
	public void setPreJsDjxh(String preJsDjxh) {
		this.preJsDjxh=preJsDjxh;
	}*/

	//获取物流损失登记序号
	public String getWlssDjxh() {
		return this.wlssDjxh;
	}

	//设置物流损失登记序号
	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh=wlssDjxh;
	}

	//获取日期
	public String getRq() {
		return this.rq;
	}

	//设置日期
	public void setRq(String rq) {
		this.rq=rq;
	}

	public String getDzDjxh() {
		return dzDjxh;
	}

	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh = dzDjxh;
	}

	public String getXcJsDjxh() {
		return xcJsDjxh;
	}

	public void setXcJsDjxh(String xcJsDjxh) {
		this.xcJsDjxh = xcJsDjxh;
	}
}