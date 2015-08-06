package com.cy.jcgl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

public class JcFcQkTjDomain extends BaseBusinessDomain {
	private static final long serialVersionUID = 1L;
	private String sjJgbm;
	private String rqq;
	private String rqz;
	private String xl;
	private String month;
	private String fcSl;
	private String sXj;
	private String xf;
	private  String df;
	private String hf;
	private String cSj;
	private String th;
	private String ys;
	private String ps;
	private String 	thZb;
	private String psZb;
	private String ml;
	private String mll;
	
	public String getRqz() {
		return rqz;
	}
	public void setRqz(String rqz) {
		this.rqz = rqz;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getFcSl() {
		return fcSl;
	}
	public void setFcSl(String fcSl) {
		this.fcSl = fcSl;
	}
	public String getsXj() {
		return sXj;
	}
	public void setsXj(String sXj) {
		this.sXj = sXj;
	}
	public String getcSj() {
		return cSj;
	}
	public void setcSj(String cSj) {
		this.cSj = cSj;
	}
	public String getTh() {
		return th;
	}
	public void setTh(String th) {
		this.th = th;
	}
	public String getYs() {
		return ys;
	}
	public void setYs(String ys) {
		this.ys = ys;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public String getThZb() {
		return thZb;
	}
	public void setThZb(String thZb) {
		this.thZb = thZb;
	}
	public String getPsZb() {
		return psZb;
	}
	public void setPsZb(String psZb) {
		this.psZb = psZb;
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
	public String getSjJgbm() {
		return sjJgbm;
	}
	public void setSjJgbm(String sjJgbm) {
		this.sjJgbm = sjJgbm;
	}
	public String getRqq() {
		return rqq;
	}
	public void setRqq(String rqq) {
		this.rqq = rqq;
	}

	
	
	private String zl;
	private String tj;
	
	private List<BaseBusinessDomain> dataList;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
