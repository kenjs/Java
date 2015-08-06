package com.cy.common.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * 物流管理平台公用COMMONDOMAIN
 */

public class WlglptCommonDomain  extends BaseBusinessDomain {
	private static final long serialVersionUID = 1L;

	private String ywid;     // 作为参数传进来的用于代表级联业务的ID
	private String paramdm;  //作为参数传进来的值
	private String mc;
	private String containQbBz;//包含 “---清选择---” 选项
	
	private String currentObjName;
	private String currentObjId;
	private String defaultValue;
	private String mcContainDmBz;
	private List dataList;
	
	public List getDataList() {
		if(dataList==null){
			dataList = new ArrayList();
		}
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public String getContainQbBz() {
		return containQbBz;
	}
	public void setContainQbBz(String containQbBz) {
		this.containQbBz = containQbBz;
	}
	public String getParamdm() {
		return paramdm;
	}
	public void setParamdm(String paramdm) {
		this.paramdm = paramdm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getCurrentObjName() {
		return currentObjName;
	}
	public void setCurrentObjName(String currentObjName) {
		this.currentObjName = currentObjName;
	}
	public String getCurrentObjId() {
		return currentObjId;
	}
	public void setCurrentObjId(String currentObjId) {
		this.currentObjId = currentObjId;
	}
	public String getMcContainDmBz() {
		return mcContainDmBz;
	}
	public void setMcContainDmBz(String mcContainDmBz) {
		this.mcContainDmBz = mcContainDmBz;
	}
	public String getDefaultValue() {
		if(StringUtils.isBlank(defaultValue)){
			defaultValue = "";
		}
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getYwid() {
		return ywid;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
	} 

}