package com.cy.common.bo;


import java.io.Serializable;

/**
 * The persistent class For XT_JS_GNMK is created by tools.
 * @author HJH
 */

public class XtJsGnmk  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsDm;                             // ��ɫ����
	private String gnmkDm;                           // ����ģ�����
	private String xtml;           					 //ϵͳĿ¼
	public String getXtml() {
		return xtml;
	}

	public void setXtml(String xtml) {
		this.xtml = xtml;
	}

	public XtJsGnmk() {
	}

	//��ȡ��ɫ����
	public String getJsDm() {
		return this.jsDm;
	}

	//���ý�ɫ����
	public void setJsDm(String jsDm) {
		this.jsDm=jsDm;
	}

	//��ȡ����ģ�����
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	//���ù���ģ�����
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm=gnmkDm;
	}
}