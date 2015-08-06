package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_SRDZ_QD_MX is created by tools.
 * @author HJH
 */

public class JsSrdzQdMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // �嵥�Ǽ����(SEQ_QD_DJXH)
	private String ywDjxh;                           // ҵ��Ǽ����
	private String ywMxXh;							 // ҵ����ϸ���
	private String ywLydm;							 // ҵ����Դ���� 1��������ˣ�2�����õǼǣ�3��������ʧ
	private String czRq;          					//��������           

	public JsSrdzQdMx() {
	}

	//��ȡ�嵥�Ǽ����(SEQ_QD_DJXH)
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//�����嵥�Ǽ����(SEQ_QD_DJXH)
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	public String getYwDjxh() {
		return ywDjxh;
	}

	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh = ywDjxh;
	}

	public String getYwMxXh() {
		return ywMxXh;
	}

	public void setYwMxXh(String ywMxXh) {
		this.ywMxXh = ywMxXh;
	}

	public String getYwLydm() {
		return ywLydm;
	}

	public void setYwLydm(String ywLydm) {
		this.ywLydm = ywLydm;
	}

	public String getCzRq() {
		return czRq;
	}

	public void setCzRq(String czRq) {
		this.czRq = czRq;
	}
}