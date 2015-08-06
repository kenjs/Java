package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_HBZC_CSH is created by tools.
 * @author HJH
 */

public class CwHbzcCsh  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cshDjxh;                          // ��ʼ���Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������
	private String zcflDm;                           // �ʲ��������
	private String yhmc;                             // ��������
	private String hm;                               // �û���
	private String zh;                               // �˺�
	private Double csje;                             // ��ʼ���
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public CwHbzcCsh() {
	}

	//��ȡ��ʼ���Ǽ����(SEQ_CW_DJXH)
	public String getCshDjxh() {
		return this.cshDjxh;
	}

	//���ó�ʼ���Ǽ����(SEQ_CW_DJXH)
	public void setCshDjxh(String cshDjxh) {
		this.cshDjxh=cshDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�ʲ��������
	public String getZcflDm() {
		return this.zcflDm;
	}

	//�����ʲ��������
	public void setZcflDm(String zcflDm) {
		this.zcflDm=zcflDm;
	}

	//��ȡ��������
	public String getYhmc() {
		return this.yhmc;
	}

	//������������
	public void setYhmc(String yhmc) {
		this.yhmc=yhmc;
	}

	//��ȡ�û���
	public String getHm() {
		return this.hm;
	}

	//�����û���
	public void setHm(String hm) {
		this.hm=hm;
	}

	//��ȡ�˺�
	public String getZh() {
		return this.zh;
	}

	//�����˺�
	public void setZh(String zh) {
		this.zh=zh;
	}

	//��ȡ��ʼ���
	public Double getCsje() {
		return this.csje;
	}

	//���ó�ʼ���
	public void setCsje(Double csje) {
		this.csje=csje;
	}

	//��ȡ���ñ�־(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//�������ñ�־(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
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