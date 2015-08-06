package com.cy.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * 
 * @author hel
 */

public class RyxzCommonDomain extends BaseBusinessDomain {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String jbdm; // 级别代码

	private String jgjc; // 机构简称

	private String czyDjxh; // 操作人登记序号

	private String czyMc; // 操作人名称

	private String fbsDjxh; // 分包商登记序号

	private String fbsjc; // 分包商简称
	
	private String qyryTree;//企业内部人员
	private String fbsryTree;//分包商人员
	
	private String sjJbdm;//上级级别代码

	
	private List<RyxzCommonDomain> domainList;

	public RyxzCommonDomain() {
	}
	public List<RyxzCommonDomain> getDomainList() {
		if (domainList == null) {
			domainList = new ArrayList<RyxzCommonDomain>();
		}
		return domainList;
	}

	public void setDomainList(List<RyxzCommonDomain> domainList) {
		this.domainList = domainList;
	}
	public String getCzyDjxh() {
		return czyDjxh;
	}
	public String getCzyMc() {
		return czyMc;
	}
	public String getFbsDjxh() {
		return fbsDjxh;
	}
	public String getFbsjc() {
		return fbsjc;
	}
	public String getJbdm() {
		return jbdm;
	}
	public String getJgjc() {
		return jgjc;
	}
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh = czyDjxh;
	}
	public void setCzyMc(String czyMc) {
		this.czyMc = czyMc;
	}
	public void setFbsDjxh(String fbsDjxh) {
		this.fbsDjxh = fbsDjxh;
	}
	public void setFbsjc(String fbsjc) {
		this.fbsjc = fbsjc;
	}
	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}
	public void setJgjc(String jgjc) {
		this.jgjc = jgjc;
	}
	public String getFbsryTree() {
		return fbsryTree;
	}
	public String getQyryTree() {
		return qyryTree;
	}
	public void setFbsryTree(String fbsryTree) {
		this.fbsryTree = fbsryTree;
	}
	public void setQyryTree(String qyryTree) {
		this.qyryTree = qyryTree;
	}
	public String getSjJbdm() {
		return sjJbdm;
	}
	public void setSjJbdm(String sjJbdm) {
		this.sjJbdm = sjJbdm;
	}



	
}