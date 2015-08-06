package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_DDDL is created by tools.
 * @author HJH
 */

public class WlSsYy  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String whXh;                           // 行政区划代码
	private String ssJgbm;                           // 行政区划名称
	private String ssJgbmMc;                           // 行政区划简称
	private String ssyy;                           // 行政区划全称
	private String sm;                         // 行政区划级别代码
	private String yxbz;                         // 上级行政区划代码
	                            // 有效标志(Y/N)
                       // 行政大区代码
	private String cjrCzyDjxh;
	private String xgrCzyDjxh;
	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}
	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}
	private String cjrMc;							 //	行政大区名称
	private String cjrq;                       // 行政区划级别分类编码
	private String xgrMc;                             // 拼音全称
	private String xgrq;
	public String getWhXh() {
		return whXh;
	}
	public void setWhXh(String whXh) {
		this.whXh = whXh;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getSsJgbmMc() {
		return ssJgbmMc;
	}
	public void setSsJgbmMc(String ssJgbmMc) {
		this.ssJgbmMc = ssJgbmMc;
	}
	public String getSsyy() {
		return ssyy;
	}
	public void setSsyy(String ssyy) {
		this.ssyy = ssyy;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getCjrMc() {
		return cjrMc;
	}
	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	public String getXgrMc() {
		return xgrMc;
	}
	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}
	public String getXgrq() {
		return xgrq;
	}
	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}    
}