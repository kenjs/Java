package com.cy.zygl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;

public class QyDzcyyyDomain extends BaseBusinessDomain{
	private static final long serialVersionUID = 1L;
	private String whXh;
	private String ssJgbm;
	private String dzcyyy;
	private String sm;
	private String yxbz;
	private String cjrCzyDjxh;
	private String cjrq;
	private String xgrCzyDjxh;
	private String xgrq;
	
	private String cjrMc;
	private String xgrMc;
	
	private List<BaseBusinessDomain> dataList; 		 //≤È—Ø¡–±Ì
	public String getWhXh() {
		return whXh;
	}
	public void setWhXh(String whXh) {
		this.whXh = whXh;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getDzcyyy() {
		return dzcyyy;
	}
	public void setDzcyyy(String dzcyyy) {
		this.dzcyyy = dzcyyy;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}
	public String getCjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}
	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}
	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}
	public String getCjrMc() {
		return cjrMc;
	}
	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}
	public String getXgrMc() {
		return xgrMc;
	}
	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
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
