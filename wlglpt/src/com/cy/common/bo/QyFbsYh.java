package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_FBS_YH is created by tools.
 * @author HJH
 */

public class QyFbsYh  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String yhDjxh;                           // 
	private String qybm;                             // ��ҵ����
	private String ssJgbm;                           // ��������(SEQ_JG_DJXH)
	private String fbsDjxh;                          // 
	private String mc;                               // ����
	private String zh;                               // �˺�
	private String pwd;                              // ����
	private String dlyzfsDm;                         // ��¼��֤��ʽ����
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrq;                             // ��������
	private String cjrCzyDjxh;                       // ������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public QyFbsYh() {
	}

	//��ȡ
	public String getYhDjxh() {
		return this.yhDjxh;
	}

	//����
	public void setYhDjxh(String yhDjxh) {
		this.yhDjxh=yhDjxh;
	}

	//��ȡ��ҵ����
	public String getQybm() {
		return this.qybm;
	}

	//������ҵ����
	public void setQybm(String qybm) {
		this.qybm=qybm;
	}

	//��ȡ��������(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//���û�������(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ
	public String getFbsDjxh() {
		return this.fbsDjxh;
	}

	//����
	public void setFbsDjxh(String fbsDjxh) {
		this.fbsDjxh=fbsDjxh;
	}

	//��ȡ����
	public String getMc() {
		return this.mc;
	}

	//��������
	public void setMc(String mc) {
		this.mc=mc;
	}

	//��ȡ�˺�
	public String getZh() {
		return this.zh;
	}

	//�����˺�
	public void setZh(String zh) {
		this.zh=zh;
	}

	//��ȡ����
	public String getPwd() {
		return this.pwd;
	}

	//��������
	public void setPwd(String pwd) {
		this.pwd=pwd;
	}

	//��ȡ��¼��֤��ʽ����
	public String getDlyzfsDm() {
		return this.dlyzfsDm;
	}

	//���õ�¼��֤��ʽ����
	public void setDlyzfsDm(String dlyzfsDm) {
		this.dlyzfsDm=dlyzfsDm;
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

	//��ȡ��������
	public String getCjrq() {
		return this.cjrq;
	}

	//���ô�������
	public void setCjrq(String cjrq) {
		this.cjrq=cjrq;
	}

	//��ȡ������
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
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