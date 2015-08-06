package com.cy.common.bo;

import java.io.Serializable;

/**
 * @author FWC
 */

public class HyWlssdjZp implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String zpscxh;                     //主键
	private String wlssDjXh;                   //物流损失登记序号
	private String zpdz;                       //照片保存地址
	private String xgsj;                       //修改时间
	private String zpmc;                       //照片展示名称
	public String getZpscxh() {
		return zpscxh;
	}
	public void setZpscxh(String zpscxh) {
		this.zpscxh = zpscxh;
	}
	public String getWlssDjXh() {
		return wlssDjXh;
	}
	public void setWlssDjXh(String wlssDjXh) {
		this.wlssDjXh = wlssDjXh;
	}
	public String getZpdz() {
		return zpdz;
	}
	public void setZpdz(String zpdz) {
		this.zpdz = zpdz;
	}
	public String getXgsj() {
		return xgsj;
	}
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	public String getZpmc() {
		return zpmc;
	}
	public void setZpmc(String zpmc) {
		this.zpmc = zpmc;
	}

}
