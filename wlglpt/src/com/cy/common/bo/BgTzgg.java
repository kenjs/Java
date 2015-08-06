package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_TZGG is created by tools.
 * @author HJH
 */

public class BgTzgg  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String tzggXh;                           // ֪ͨ�������(SEQ_TZGG_XH)
	private String jgbm;                             // ��������
	private String fbrq;                             // ��������(YYYY-MM-DD)
	private String bcztDm;                           // ����״̬����
	private String zt;                               // ����
	private String nr;                               // ����
	private String xjgxbz;                           // �¼������־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public BgTzgg() {
	}

	//��ȡ֪ͨ�������(SEQ_TZGG_XH)
	public String getTzggXh() {
		return this.tzggXh;
	}

	//����֪ͨ�������(SEQ_TZGG_XH)
	public void setTzggXh(String tzggXh) {
		this.tzggXh=tzggXh;
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ��������(YYYY-MM-DD)
	public String getFbrq() {
		return this.fbrq;
	}

	//���÷�������(YYYY-MM-DD)
	public void setFbrq(String fbrq) {
		this.fbrq=fbrq;
	}

	//��ȡ����״̬����
	public String getBcztDm() {
		return this.bcztDm;
	}

	//���ñ���״̬����
	public void setBcztDm(String bcztDm) {
		this.bcztDm=bcztDm;
	}

	//��ȡ����
	public String getZt() {
		return this.zt;
	}

	//��������
	public void setZt(String zt) {
		this.zt=zt;
	}

	//��ȡ����
	public String getNr() {
		return this.nr;
	}

	//��������
	public void setNr(String nr) {
		this.nr=nr;
	}

	//��ȡ�¼������־(Y/N)
	public String getXjgxbz() {
		return this.xjgxbz;
	}

	//�����¼������־(Y/N)
	public void setXjgxbz(String xjgxbz) {
		this.xjgxbz=xjgxbz;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ������
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//��ȡ��������
	public String getCjrq() {
		return this.cjrq;
	}

	//���ô�������
	public void setCjrq(String cjrq) {
		this.cjrq=cjrq;
	}

	//��ȡ�޸���
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//�����޸���
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//��ȡ�޸�����
	public String getXgrq() {
		return this.xgrq;
	}

	//�����޸�����
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}
}