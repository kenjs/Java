package com.cy.hygl.domain;


import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The DOMAIN class FOR HY_WLSSDJ is created by tools.
 * @author HJH
 */

public class HyWlssdjMxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wlssDjxh;                         // ������ʧ�Ǽ����(SEQ_WLSS_DJXH)
	private String xh;                           	 // ���
	private String wlssyyWhXh;                       // ������ʧԭ�����
	private String wlssclfsDm;                       // ������ʧ����ʽ����
	private String je;                             	 // ���
	private String pcygCzyDjxh;
	private String yxbz;
	
	private String wlssyy;
	private String wlssclfsMc;
	private String pcygCzyMc;
	public String getWlssDjxh() {
		return wlssDjxh;
	}
	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh = wlssDjxh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getWlssyyWhXh() {
		return wlssyyWhXh;
	}
	public void setWlssyyWhXh(String wlssyyWhXh) {
		this.wlssyyWhXh = wlssyyWhXh;
	}
	public String getWlssclfsDm() {
		return wlssclfsDm;
	}
	public void setWlssclfsDm(String wlssclfsDm) {
		this.wlssclfsDm = wlssclfsDm;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getWlssyy() {
		return wlssyy;
	}
	public void setWlssyy(String wlssyy) {
		this.wlssyy = wlssyy;
	}
	public String getWlssclfsMc() {
		return wlssclfsMc;
	}
	public void setWlssclfsMc(String wlssclfsMc) {
		this.wlssclfsMc = wlssclfsMc;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getPcygCzyDjxh() {
		return pcygCzyDjxh;
	}
	public void setPcygCzyDjxh(String pcygCzyDjxh) {
		this.pcygCzyDjxh = pcygCzyDjxh;
	}
	public String getPcygCzyMc() {
		return pcygCzyMc;
	}
	public void setPcygCzyMc(String pcygCzyMc) {
		this.pcygCzyMc = pcygCzyMc;
	}
	
}
