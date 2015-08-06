package com.cy.jcgl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.hygl.domain.HyClgzDomain;
import com.cy.hygl.domain.HyTydglDomain;
import com.cy.hygl.domain.HyWlSsDjGlDomain;

/**
 * The DOMAIN class FOR HY_HW_DDXX is created by tools.
 * @author LYY
 */

public class JcTydglDomain  extends HyTydglDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String pcDjxh;
	private String wlssDjxh;						//物流损失登记序号
	private String xtcs20201;						//派车调度成本是否需要审批
	private List<JcPcxxglDomain> tydPcxxList;
	private List<HyClgzDomain> clgzList;
	private List<JcYfZfxxDomain> yfList;
	private List<HyWlSsDjGlDomain> wlssList;		//货物对应的物流损失
	private List<HyWlSsDjGlDomain> wlssMxList;		//物流损失明细


	public List<JcYfZfxxDomain> getYfList() {
		if(yfList==null){
			yfList=new ArrayList<JcYfZfxxDomain>();
		}
		return yfList;
	}

	public void setYfList(List<JcYfZfxxDomain> yfList) {
		this.yfList = yfList;
	}

	public List<HyClgzDomain> getClgzList() {
		if(clgzList==null){
			clgzList=new ArrayList<HyClgzDomain>();
		}
		return clgzList;
	}

	public void setClgzList(List<HyClgzDomain> clgzList) {
		this.clgzList = clgzList;
	}

	public List<JcPcxxglDomain> getTydPcxxList() {
		if (tydPcxxList == null) {
			tydPcxxList = new ArrayList<JcPcxxglDomain>();
		}
		return tydPcxxList;
	}

	public void setTydPcxxList(List<JcPcxxglDomain> tydPcxxList) {
		this.tydPcxxList = tydPcxxList;
	}

	public String getXtcs20201() {
		return xtcs20201;
	}

	public void setXtcs20201(String xtcs20201) {
		this.xtcs20201 = xtcs20201;
	}

	public String getPcDjxh() {
		return pcDjxh;
	}

	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh = pcDjxh;
	}

	public List<HyWlSsDjGlDomain> getWlssList() {
		if (wlssList == null) {
			wlssList = new ArrayList<HyWlSsDjGlDomain>();
		}		
		return wlssList;
	}

	public void setWlssList(List<HyWlSsDjGlDomain> wlssList) {
		this.wlssList = wlssList;
	}

	public List<HyWlSsDjGlDomain> getWlssMxList() {
		if (wlssMxList == null) {
			wlssMxList = new ArrayList<HyWlSsDjGlDomain>();
		}	
		return wlssMxList;
	}

	public void setWlssMxList(List<HyWlSsDjGlDomain> wlssMxList) {
		this.wlssMxList = wlssMxList;
	}

	public String getWlssDjxh() {
		return wlssDjxh;
	}

	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh = wlssDjxh;
	}
	
	
	
}
