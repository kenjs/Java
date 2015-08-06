package com.cy.common.bo;

import java.io.Serializable;
/**
 *  THE BO FOR 系统-系统参数-设置信息
 * @author Administrator
 *
 */
public class QyXtCs implements Serializable{
	private static final long serialVersionUID = 1L;
	private String szdw;                             //设置单位
	private String csxh;                             //参数序号
	private String cslbDm;                           //参数类别代码
	private String csz;                              //参数值
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	public String getSzdw() {
		return szdw;
	}
	public void setSzdw(String szdw) {
		this.szdw = szdw;
	}
	public String getCsxh() {
		return csxh;
	}
	public void setCsxh(String csxh) {
		this.csxh = csxh;
	}
	public String getCslbDm() {
		return cslbDm;
	}
	public void setCslbDm(String cslbDm) {
		this.cslbDm = cslbDm;
	}
	public String getCsz() {
		return csz;
	}
	public void setCsz(String csz) {
		this.csz = csz;
	}
	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}
	public String getXgrq() {
		return xgrq;
	}
	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}
	
}
