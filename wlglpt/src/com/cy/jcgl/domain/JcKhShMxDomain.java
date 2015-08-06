package com.cy.jcgl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

public class JcKhShMxDomain extends BaseBusinessDomain {
	private static final long serialVersionUID = 1L;
	private String sjJgbm;
	private String rqq;
	private String rqz;
	private String fhrDjxh;
	private String hwMc;
	private String ddbh;
	private String xdrq;
	private String sl;
	private String zl;
	private String tj;
	private String xj;
	private String xf;
	private String df;
	private String hf;
	private String sfd;
	private String mdd;
	private String fhrMc;
	private String jgMc;
	public String getJgMc() {
		return jgMc;
	}
	public void setJgMc(String jgMc) {
		this.jgMc = jgMc;
	}
	public String getFhrMc() {
		return fhrMc;
	}
	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}
	public String getFhrDjxh() {
		return fhrDjxh;
	}
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}
	public String getRqq() {
		return rqq;
	}
	public void setRqq(String rqq) {
		this.rqq = rqq;
	}
	public String getHwMc() {
		return hwMc;
	}
	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getXdrq() {
		return xdrq;
	}
	public void setXdrq(String xdrq) {
		this.xdrq = xdrq;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getTj() {
		return tj;
	}
	public void setTj(String tj) {
		this.tj = tj;
	}
	public String getXj() {
		return xj;
	}
	public void setXj(String xj) {
		this.xj = xj;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getDf() {
		return df;
	}
	public void setDf(String df) {
		this.df = df;
	}
	public String getHf() {
		return hf;
	}
	public void setHf(String hf) {
		this.hf = hf;
	}
	public String getSfd() {
		return sfd;
	}
	public void setSfd(String sfd) {
		this.sfd = sfd;
	}
	public String getMdd() {
		return mdd;
	}
	public void setMdd(String mdd) {
		this.mdd = mdd;
	}
	public String getRqz() {
		return rqz;
	}
	public void setRqz(String rqz) {
		this.rqz = rqz;
	}
	private List<BaseBusinessDomain> dataList;
	public String getSjJgbm() {
		return sjJgbm;
	}
	public void setSjJgbm(String sjJgbm) {
		this.sjJgbm = sjJgbm;
	}
	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}
	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
}
