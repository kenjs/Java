package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For XYJS_SRDZ_QD_MX is created by tools.
 * @author HJH
 */

public class XyjsSrdzQdMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // �嵥�Ǽ����(SEQ_QD_DJXH)
	private String ywDjxh;                           // ����Ǽ����
	private String ywMxXh;                           // 1�����ͷѣ�2�������3�����ջ���
	private String ywLydm;                           // 1��������ˣ�2�����õǼǣ�3��������ʧ

	public XyjsSrdzQdMx() {
	}

	//��ȡ�嵥�Ǽ����(SEQ_QD_DJXH)
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//�����嵥�Ǽ����(SEQ_QD_DJXH)
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	//��ȡ����Ǽ����
	public String getYwDjxh() {
		return this.ywDjxh;
	}

	//���ý���Ǽ����
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh=ywDjxh;
	}

	//��ȡ1�����ͷѣ�2�������3�����ջ���
	public String getYwMxXh() {
		return this.ywMxXh;
	}

	//����1�����ͷѣ�2�������3�����ջ���
	public void setYwMxXh(String ywMxXh) {
		this.ywMxXh=ywMxXh;
	}

	//��ȡ1��������ˣ�2�����õǼǣ�3��������ʧ
	public String getYwLydm() {
		return this.ywLydm;
	}

	//����1��������ˣ�2�����õǼǣ�3��������ʧ
	public void setYwLydm(String ywLydm) {
		this.ywLydm=ywLydm;
	}
}