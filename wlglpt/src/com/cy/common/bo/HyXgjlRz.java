package com.cy.common.bo;

import java.io.Serializable;

public class HyXgjlRz implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String mswz;   //��������
	private String ywId;   //ҵ��ID
	private String lx;     //����
	private String mkmc;   //ģ������
	private String czrCzyDjxh;//������
	private String czrq;   //��������
	
	public HyXgjlRz() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMswz() {
		return mswz;
	}
	public void setMswz(String mswz) {
		this.mswz = mswz;
	}
	public String getYwId() {
		return ywId;
	}
	public void setYwId(String ywId) {
		this.ywId = ywId;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getMkmc() {
		return mkmc;
	}
	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}
	public String getCzrCzyDjxh() {
		return czrCzyDjxh;
	}
	public void setCzrCzyDjxh(String czrCzyDjxh) {
		this.czrCzyDjxh = czrCzyDjxh;
	}
	public String getCzrq() {
		return czrq;
	}
	public void setCzrq(String czrq) {
		this.czrq = czrq;
	}

}
