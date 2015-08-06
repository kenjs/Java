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

	private String jbdm; // �������

	private String jgjc; // �������

	private String czyDjxh; // �����˵Ǽ����

	private String czyMc; // ����������

	private String fbsDjxh; // �ְ��̵Ǽ����

	private String fbsjc; // �ְ��̼��
	
	private String qyryTree;//��ҵ�ڲ���Ա
	private String fbsryTree;//�ְ�����Ա
	
	private String sjJbdm;//�ϼ��������

	
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