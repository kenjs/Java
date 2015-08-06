package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_GZLX is created by tools.
 * @author HJH
 */

public class BgGzlx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gzlxXh;                           // ������ϵ���(SEQ_GZLX_XH)
	private String zt;                               // ����
	private String nr;                               // ����
	private String bcbzDm;                           // �����־����
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public BgGzlx() {
	}

	//��ȡ������ϵ���(SEQ_GZLX_XH)
	public String getGzlxXh() {
		return this.gzlxXh;
	}

	//���ù�����ϵ���(SEQ_GZLX_XH)
	public void setGzlxXh(String gzlxXh) {
		this.gzlxXh=gzlxXh;
	}

	//��ȡ����
	public String getZt() {
		return this.zt;
	}

	//��������
	public void setZt(String zt) {
		this.zt=zt;
	}

	//��ȡ�����־����
	public String getBcbzDm() {
		return this.bcbzDm;
	}

	//���ñ����־����
	public void setBcbzDm(String bcbzDm) {
		this.bcbzDm=bcbzDm;
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

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}
}