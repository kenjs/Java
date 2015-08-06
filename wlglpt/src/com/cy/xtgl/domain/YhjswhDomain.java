package com.cy.xtgl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The DOMAIN class .
 * @author 
 */

public class YhjswhDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	private String czyDjxh;
	private String mc;
	private String jbdm;
	private String jsDm;
	private String jsJc;
	private String jsMc;
	private String selBz;
	private String jsDjxh;
	private String qyZcxh;
	private String gsbm;
	private String czyDjxh_jsDixh;
	private String selJsDms;
	private String bmMc;
	private String gwMc;
	private String sjMc;
	private String dwDm;
	
	private String dwDjxh;
	private String ssJgbm;
	
	public String getSjMc() {
		return sjMc;
	}
	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}
	public String getBmMc() {
		return bmMc;
	}
	public void setBmMc(String bmMc) {
		this.bmMc = bmMc;
	}
	public String getGwMc() {
		return gwMc;
	}
	public void setGwMc(String gwMc) {
		this.gwMc = gwMc;
	}
	private List jsMcList;
	private List dataList;
	
	public String getCzyDjxh() {
		return czyDjxh;
	}
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh = czyDjxh;
	}
	public List getDataList() {
		if(dataList == null) {
			dataList = new ArrayList();
		}
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public List getJsMcList() {
		if(jsMcList == null){
			jsMcList = new ArrayList();
		}
		return jsMcList;
	}
	public void setJsMcList(List jsMcList) {
		this.jsMcList = jsMcList;
	}
	public String getJsJc() {
		return jsJc;
	}
	public void setJsJc(String jsJc) {
		this.jsJc = jsJc;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getJsDjxh() {
		return jsDjxh;
	}
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh = jsDjxh;
	}
	public String getQyZcxh() {
		return qyZcxh;
	}
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh = qyZcxh;
	}
	public String getJbdm() {
		return jbdm;
	}
	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}
	public String getGsbm() {
		return gsbm;
	}
	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}
	public String getJsDm() {
		return jsDm;
	}
	public void setJsDm(String jsDm) {
		this.jsDm = jsDm;
	}
	public String getJsMc() {
		return jsMc;
	}
	public void setJsMc(String jsMc) {
		this.jsMc = jsMc;
	}
	public String getSelBz() {
		return selBz;
	}
	public void setSelBz(String selBz) {
		this.selBz = selBz;
	}
	public String getSelJsDms() {
		return selJsDms;
	}
	public void setSelJsDms(String selJsDms) {
		this.selJsDms = selJsDms;
	}
	public String getCzyDjxh_jsDixh() {
		return czyDjxh_jsDixh;
	}
	public void setCzyDjxh_jsDixh(String czyDjxh_jsDixh) {
		this.czyDjxh_jsDixh = czyDjxh_jsDixh;
	}
	public String getDwDm() {
		return dwDm;
	}
	public void setDwDm(String dwDm) {
		this.dwDm = dwDm;
	}
	public String getDwDjxh() {
		return dwDjxh;
	}
	public void setDwDjxh(String dwDjxh) {
		this.dwDjxh = dwDjxh;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	
}