package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_DDDL is created by tools.
 * @author HJH
 */

public class HyWlssdjMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wlssDjxh;                         // ������ʧ�Ǽ����(SEQ_WLSS_DJXH)
	private String xh;                           	 // ���
	private String wlssyyWhXh;                       // ������ʧԭ�����
	private String wlssclfsDm;                       // ������ʧ����ʽ����
	private String je;                             	 // ���
	private String pcygCzyDjxh;
	private String yxbz;
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
	public String getPcygCzyDjxh() {
		return pcygCzyDjxh;
	}
	public void setPcygCzyDjxh(String pcygCzyDjxh) {
		this.pcygCzyDjxh = pcygCzyDjxh;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	
}