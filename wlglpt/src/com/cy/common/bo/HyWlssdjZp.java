package com.cy.common.bo;

import java.io.Serializable;

/**
 * @author FWC
 */

public class HyWlssdjZp implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String zpscxh;                     //����
	private String wlssDjXh;                   //������ʧ�Ǽ����
	private String zpdz;                       //��Ƭ�����ַ
	private String xgsj;                       //�޸�ʱ��
	private String zpmc;                       //��Ƭչʾ����
	public String getZpscxh() {
		return zpscxh;
	}
	public void setZpscxh(String zpscxh) {
		this.zpscxh = zpscxh;
	}
	public String getWlssDjXh() {
		return wlssDjXh;
	}
	public void setWlssDjXh(String wlssDjXh) {
		this.wlssDjXh = wlssDjXh;
	}
	public String getZpdz() {
		return zpdz;
	}
	public void setZpdz(String zpdz) {
		this.zpdz = zpdz;
	}
	public String getXgsj() {
		return xgsj;
	}
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	public String getZpmc() {
		return zpmc;
	}
	public void setZpmc(String zpmc) {
		this.zpmc = zpmc;
	}

}
