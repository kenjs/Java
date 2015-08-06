package com.cy.common.bo;

import java.io.Serializable;
/**
 *  THE BO FOR 收入登记
 * @author HCM
 *
 */
public class CwSrdj  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String srDjxh;
	private String ysyfDjxh ;		//应收应付登记序号(SEQ_YSYF_DJXH)
	private String yfjsfDm ;		//运费结算方代码
	private String yfjsfDjxh ;		//运费结算方登记序号
	private String fkfmc;
	private String je;
	private String rq;
	private String zffsDm;
	private String zcflDm;
	private String yhCshDjxh;
	private String yhhdh;
	private String jbrCzyDjxh;
	private String bz;
	private String yxbz ;			//有效标志(Y/N)
	private String djrCzyDjxh;
	private String djrq;
	private String djJgbm ;			//登记部门
	private String ssJgbm ;			//所属机构
	public String getSrDjxh() {
		return srDjxh;
	}
	public void setSrDjxh(String srDjxh) {
		this.srDjxh = srDjxh;
	}
	public String getYsyfDjxh() {
		return ysyfDjxh;
	}
	public void setYsyfDjxh(String ysyfDjxh) {
		this.ysyfDjxh = ysyfDjxh;
	}
	public String getYfjsfDm() {
		return yfjsfDm;
	}
	public void setYfjsfDm(String yfjsfDm) {
		this.yfjsfDm = yfjsfDm;
	}
	public String getYfjsfDjxh() {
		return yfjsfDjxh;
	}
	public void setYfjsfDjxh(String yfjsfDjxh) {
		this.yfjsfDjxh = yfjsfDjxh;
	}
	public String getFkfmc() {
		return fkfmc;
	}
	public void setFkfmc(String fkfmc) {
		this.fkfmc = fkfmc;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getZffsDm() {
		return zffsDm;
	}
	public void setZffsDm(String zffsDm) {
		this.zffsDm = zffsDm;
	}
	public String getZcflDm() {
		return zcflDm;
	}
	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}
	public String getYhCshDjxh() {
		return yhCshDjxh;
	}
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}
	public String getYhhdh() {
		return yhhdh;
	}
	public void setYhhdh(String yhhdh) {
		this.yhhdh = yhhdh;
	}
	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}
	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}
	public String getDjrq() {
		return djrq;
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	public String getDjJgbm() {
		return djJgbm;
	}
	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	
}
