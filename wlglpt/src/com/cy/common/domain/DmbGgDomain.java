package com.cy.common.domain;

import com.cy.framework.domain.BaseDomain;

/**
 * The DOMAIN class FOR DM_SWJG is created by tools.
 * @author HJH
 */

public class DmbGgDomain  extends BaseDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dm;
	private String mc;
	
	private String parDm;
	private String openFlag;
	
	private String defaultFlag = ""; //Ä¬ÈÏÖµ
	
	public String getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getParDm() {
		return parDm;
	}
	public void setParDm(String parDm) {
		this.parDm = parDm;
	}
	public String getOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}
    
}