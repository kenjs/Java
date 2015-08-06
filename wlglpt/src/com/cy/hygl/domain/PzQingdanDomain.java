package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * @author HJH
 */

public class PzQingdanDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String tdh;
	private String khdw;
	private String hwmc;
	private String bz;
	private String js;
	private String tj;
	private String dw;
	private String gsMc;
	public String getGsMc() {
		return gsMc;
	}
	public void setGsMc(String gsMc) {
		this.gsMc = gsMc;
	}
	public String getTdh() {
		return tdh;
	}
	public void setTdh(String tdh) {
		this.tdh = tdh;
	}
	public String getKhdw() {
		return khdw;
	}
	public void setKhdw(String khdw) {
		this.khdw = khdw;
	}
	public String getHwmc() {
		return hwmc;
	}
	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getTj() {
		return tj;
	}
	public void setTj(String tj) {
		this.tj = tj;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	
}
