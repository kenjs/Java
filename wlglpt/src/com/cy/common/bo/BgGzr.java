package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_GZR is created by tools.
 * @author HJH
 */

public class BgGzr  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // ��������
	private String rq;                               // ����(YYYY-MM-DD)
	private String gzrDm;                            // �����մ���
	private String weekdayDm;                        // ���ڼ�����
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public BgGzr() {
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ����(YYYY-MM-DD)
	public String getRq() {
		return this.rq;
	}

	//��������(YYYY-MM-DD)
	public void setRq(String rq) {
		this.rq=rq;
	}

	//��ȡ�����մ���
	public String getGzrDm() {
		return this.gzrDm;
	}

	//���ù����մ���
	public void setGzrDm(String gzrDm) {
		this.gzrDm=gzrDm;
	}

	//��ȡ���ڼ�����
	public String getWeekdayDm() {
		return this.weekdayDm;
	}

	//�������ڼ�����
	public void setWeekdayDm(String weekdayDm) {
		this.weekdayDm=weekdayDm;
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