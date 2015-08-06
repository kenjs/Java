package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_DDDL is created by tools.
 * @author HJH
 */

public class HyWlssdjMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wlssDjxh;                         // 物流损失登记序号(SEQ_WLSS_DJXH)
	private String xh;                           	 // 序号
	private String wlssyyWhXh;                       // 物流损失原因代码
	private String wlssclfsDm;                       // 物流损失处理方式代码
	private String je;                             	 // 金额
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