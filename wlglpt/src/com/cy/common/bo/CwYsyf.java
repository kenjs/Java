package com.cy.common.bo;

import java.io.Serializable;

public class CwYsyf  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ysyfDjxh ;		//应收应付登记序号(SEQ_YSYF_DJXH)
	private String yfjsfDm ;		//运费结算方代码
	private String yfjsfDjxh ;		//运费结算方登记序号
	private String kmdlDm ;			//科目大类代码
	private String kmxlDm ;			//科目小类代码
	private String zdyKmzlDm ;		//自定义科目子类代码
	private String ysyflyDm ;		//应收应付来源代码
	private String ywDjxh ;			//业务登记序号(视具体业务对应不同表)
	private String csrq ;			//产生日期
	private String ysyfztDm ;		//应收应付状态代码
	private String ysfJe ;			//应收付金额
	private String yisfJe ;			//已收付金额
	private String wsfJe ;			//未收付金额
	private String sm ;				//说明
	private String yxbz ;			//有效标志(Y/N)
	private String djJgbm ;			//登记部门
	private String ssJgbm ;			//所属机构
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getDjJgbm() {
		return djJgbm;
	}
	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}
	public String getKmdlDm() {
		return kmdlDm;
	}
	public void setKmdlDm(String kmdlDm) {
		this.kmdlDm = kmdlDm;
	}
	public String getKmxlDm() {
		return kmxlDm;
	}
	public void setKmxlDm(String kmxlDm) {
		this.kmxlDm = kmxlDm;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getWsfJe() {
		return wsfJe;
	}
	public void setWsfJe(String wsfJe) {
		this.wsfJe = wsfJe;
	}
	public String getYfjsfDjxh() {
		return yfjsfDjxh;
	}
	public void setYfjsfDjxh(String yfjsfDjxh) {
		this.yfjsfDjxh = yfjsfDjxh;
	}
	public String getYfjsfDm() {
		return yfjsfDm;
	}
	public void setYfjsfDm(String yfjsfDm) {
		this.yfjsfDm = yfjsfDm;
	}
	public String getYisfJe() {
		return yisfJe;
	}
	public void setYisfJe(String yisfJe) {
		this.yisfJe = yisfJe;
	}
	public String getYsfJe() {
		return ysfJe;
	}
	public void setYsfJe(String ysfJe) {
		this.ysfJe = ysfJe;
	}
	public String getYsyfDjxh() {
		return ysyfDjxh;
	}
	public void setYsyfDjxh(String ysyfDjxh) {
		this.ysyfDjxh = ysyfDjxh;
	}
	public String getYsyflyDm() {
		return ysyflyDm;
	}
	public void setYsyflyDm(String ysyflyDm) {
		this.ysyflyDm = ysyflyDm;
	}
	public String getYsyfztDm() {
		return ysyfztDm;
	}
	public void setYsyfztDm(String ysyfztDm) {
		this.ysyfztDm = ysyfztDm;
	}
	public String getYwDjxh() {
		return ywDjxh;
	}
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh = ywDjxh;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getZdyKmzlDm() {
		return zdyKmzlDm;
	}
	public void setZdyKmzlDm(String zdyKmzlDm) {
		this.zdyKmzlDm = zdyKmzlDm;
	}

}
