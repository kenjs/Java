package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_KPSQ_MX is created by tools.
 * @author HJH
 */

public class JsKpsqMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpsqmxDjxh;                       // ��Ʊ����Ǽ����
	private String kpsqDjxh;                         // ��Ʊ����Ǽ����(SEQ_QD_DJXH)
	private String kpsqMxflDm;                       // ��Ʊ������ϸ�������
	private String ywDjxh;                           // �嵥�Ǽ����
	private Double sqKpje;                           // ���뿪Ʊ���
	private String bzsm;                             // ��ע˵��
	private String yxbz;                             // ��Ч��־(Y/N)
	private String czrq;//��������

	public JsKpsqMx() {
	}

	//��ȡ��Ʊ����Ǽ����
	public String getKpsqmxDjxh() {
		return this.kpsqmxDjxh;
	}

	//���ÿ�Ʊ����Ǽ����
	public void setKpsqmxDjxh(String kpsqmxDjxh) {
		this.kpsqmxDjxh=kpsqmxDjxh;
	}

	//��ȡ��Ʊ����Ǽ����(SEQ_QD_DJXH)
	public String getKpsqDjxh() {
		return this.kpsqDjxh;
	}

	//���ÿ�Ʊ����Ǽ����(SEQ_QD_DJXH)
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh=kpsqDjxh;
	}

	//��ȡ��Ʊ������ϸ�������
	public String getKpsqMxflDm() {
		return this.kpsqMxflDm;
	}

	//���ÿ�Ʊ������ϸ�������
	public void setKpsqMxflDm(String kpsqMxflDm) {
		this.kpsqMxflDm=kpsqMxflDm;
	}

	//��ȡ�嵥�Ǽ����
	public String getYwDjxh() {
		return this.ywDjxh;
	}

	//�����嵥�Ǽ����
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh=ywDjxh;
	}

	//��ȡ���뿪Ʊ���
	public Double getSqKpje() {
		return this.sqKpje;
	}

	//�������뿪Ʊ���
	public void setSqKpje(Double sqKpje) {
		this.sqKpje=sqKpje;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	public String getCzrq() {
		return czrq;
	}

	public void setCzrq(String czrq) {
		this.czrq = czrq;
	}
}