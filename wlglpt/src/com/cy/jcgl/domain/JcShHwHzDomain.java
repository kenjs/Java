package com.cy.jcgl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

public class JcShHwHzDomain extends BaseBusinessDomain {
	private static final long serialVersionUID = 1L;
	private String sjJgbm;
	private String tjRq;
	public String getTjRq() {
		return tjRq;
	}
	public void setTjRq(String tjRq) {
		this.tjRq = tjRq;
	}

	private String fhrDjxh;
	private String tjDate;
	private String khMc;
	private String byZl;
	private String byTj;
	private String byXj;
	private String byXf;
	private String byDj;
	private  String byHf;
	private String bnZl;
	private String bnTj;
	private String bnXj;
	private String bnXf;
	private String bnDj;
	private String bnHf;
	private String bySl;
	private String bnSl;
	private String jgMc;
	private String fhrMc;
	public String getFhrMc() {
		return fhrMc;
	}
	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}
	public String getJgMc() {
		return jgMc;
	}
	public void setJgMc(String jgMc) {
		this.jgMc = jgMc;
	}
	public String getBySl() {
		return bySl;
	}
	public void setBySl(String bySl) {
		this.bySl = bySl;
	}
	public String getBnSl() {
		return bnSl;
	}
	public void setBnSl(String bnSl) {
		this.bnSl = bnSl;
	}
	public String getFhrDjxh() {
		return fhrDjxh;
	}
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}
	
	
	
	public String getTjDate() {
		return tjDate;
	}
	public void setTjDate(String tjDate) {
		this.tjDate = tjDate;
	}
	public String getKhMc() {
		return khMc;
	}
	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}
	public String getByZl() {
		return byZl;
	}
	public void setByZl(String byZl) {
		this.byZl = byZl;
	}
	public String getByTj() {
		return byTj;
	}
	public void setByTj(String byTj) {
		this.byTj = byTj;
	}
	public String getByXj() {
		return byXj;
	}
	public void setByXj(String byXj) {
		this.byXj = byXj;
	}
	public String getByXf() {
		return byXf;
	}
	public void setByXf(String byXf) {
		this.byXf = byXf;
	}
	public String getByDj() {
		return byDj;
	}
	public void setByDj(String byDj) {
		this.byDj = byDj;
	}
	public String getByHf() {
		return byHf;
	}
	public void setByHf(String byHf) {
		this.byHf = byHf;
	}
	public String getBnZl() {
		return bnZl;
	}
	public void setBnZl(String bnZl) {
		this.bnZl = bnZl;
	}
	public String getBnTj() {
		return bnTj;
	}
	public void setBnTj(String bnTj) {
		this.bnTj = bnTj;
	}
	public String getBnXj() {
		return bnXj;
	}
	public void setBnXj(String bnXj) {
		this.bnXj = bnXj;
	}
	public String getBnXf() {
		return bnXf;
	}
	public void setBnXf(String bnXf) {
		this.bnXf = bnXf;
	}
	public String getBnDj() {
		return bnDj;
	}
	public void setBnDj(String bnDj) {
		this.bnDj = bnDj;
	}
	public String getBnHf() {
		return bnHf;
	}
	public void setBnHf(String bnHf) {
		this.bnHf = bnHf;
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
