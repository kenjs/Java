package com.cy.jcgl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * 
 */

public class JcYxsjbDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String pcdh;
	private String pcDjxh;
	private String clhm;
	private String pcrq;
	private String zsr;
	private String ysf;
	private String thf;
	private String df;
	private String xf;
	private String hf;
	private String psf;
	private String wlssSr;
	private String wlssZc;
	private String lr;
	private String hk;
	private String hdbh;
	private String khmc;
	private String khDjxh;
	private String ml;
	private String mll;
	private String ssjgMc;
	private String xdrq;
	private String mdd;
	private String ddbh;
	private String zzc; // ×ÜÖ§³ö
	private String ssJgbm;
	private String fcrqS;
	private String fcrqZ;
	
	private int pageXh;
	private String orderStr;
	
	private String fhrDjxh;
	private String fhrMc;
	
	private List<BaseBusinessDomain> dataList;
	
	public String getClhm() {
		return clhm;
	}
	public void setClhm(String clhm) {
		this.clhm = clhm;
	}
	public String getPcrq() {
		return pcrq;
	}
	public void setPcrq(String pcrq) {
		this.pcrq = pcrq;
	}
	public String getZsr() {
		return zsr;
	}
	public void setZsr(String zsr) {
		this.zsr = zsr;
	}
	public String getYsf() {
		return ysf;
	}
	public void setYsf(String ysf) {
		this.ysf = ysf;
	}
	public String getThf() {
		return thf;
	}
	public void setThf(String thf) {
		this.thf = thf;
	}
	public String getPsf() {	
		return psf;
	}
	public void setPsf(String psf) {
		this.psf = psf;
	}
	public String getWlssSr() {
		return wlssSr;
	}
	public void setWlssSr(String wlssSr) {
		this.wlssSr = wlssSr;
	}
	public String getWlssZc() {	
		return wlssZc;
	}
	public void setWlssZc(String wlssZc) {
		this.wlssZc = wlssZc;
	}
	public String getLr() {
		return lr;
	}
	public void setLr(String lr) {
		this.lr = lr;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getFcrqS() {
		return fcrqS;
	}
	public void setFcrqS(String fcrqS) {
		this.fcrqS = fcrqS;
	}
	public String getFcrqZ() {
		return fcrqZ;
	}
	public void setFcrqZ(String fcrqZ) {
		this.fcrqZ = fcrqZ;
	}
	public List<BaseBusinessDomain> getDataList() {
		if(dataList == null){
			dataList = new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}
	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
	public int getPageXh() {
		return pageXh;
	}
	public void setPageXh(int pageXh) {
		this.pageXh = pageXh;
	}
	public String getPcdh() {
		return pcdh;
	}
	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}
	public String getPcDjxh() {
		return pcDjxh;
	}
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh = pcDjxh;
	}
	public String getHk() {
		return hk;
	}
	public void setHk(String hk) {
		this.hk = hk;
	}
	public String getHdbh() {
		return hdbh;
	}
	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	public String getKhDjxh() {
		return khDjxh;
	}
	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}
	public String getMl() {
		return ml;
	}
	public void setMl(String ml) {
		this.ml = ml;
	}
	public String getMll() {
		return mll;
	}
	public void setMll(String mll) {
		this.mll = mll;
	}
	public String getSsjgMc() {
		return ssjgMc;
	}
	public void setSsjgMc(String ssjgMc) {
		this.ssjgMc = ssjgMc;
	}
	public String getXdrq() {
		return xdrq;
	}
	public void setXdrq(String xdrq) {
		this.xdrq = xdrq;
	}
	public String getMdd() {
		return mdd;
	}
	public void setMdd(String mdd) {
		this.mdd = mdd;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getFhrDjxh() {
		return fhrDjxh;
	}
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}
	public String getFhrMc() {
		return fhrMc;
	}
	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}
	public String getDf() {
		return df;
	}
	public void setDf(String df) {
		this.df = df;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getHf() {
		return hf;
	}
	public void setHf(String hf) {
		this.hf = hf;
	}
	public String getOrderStr() {
		return orderStr;
	}
	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
	public String getZzc() {
		return zzc;
	}
	public void setZzc(String zzc) {
		this.zzc = zzc;
	}

}
