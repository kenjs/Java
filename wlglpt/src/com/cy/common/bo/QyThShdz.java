package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_TH_SHDZ is created by tools.
 * @author HJH
 */

public class QyThShdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String whXh;                             // �����ͺ�ά�����(SEQ_ZY_DJXH)
	private String ssJgbm;                           // ��������
	private String dz;                               // ��ַ
	private String dh;                               // ��ϵ�绰
	private String xzqhDm;                           // ������������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String xgrCzyDjxh;                       // �޸���
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String fzr;                              // ������
	private String xgrq;                             // �޸�����

	public QyThShdz() {
	}

	//��ȡ�����ͺ�ά�����(SEQ_ZY_DJXH)
	public String getWhXh() {
		return this.whXh;
	}

	//���ó����ͺ�ά�����(SEQ_ZY_DJXH)
	public void setWhXh(String whXh) {
		this.whXh=whXh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ��ַ
	public String getDz() {
		return this.dz;
	}

	//���õ�ַ
	public void setDz(String dz) {
		this.dz=dz;
	}

	//��ȡ��ϵ�绰
	public String getDh() {
		return this.dh;
	}

	//������ϵ�绰
	public void setDh(String dh) {
		this.dh=dh;
	}

	//��ȡ������������
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//����������������
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ�޸���
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//�����޸���
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
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

	//��ȡ������
	public String getFzr() {
		return this.fzr;
	}

	//���ø�����
	public void setFzr(String fzr) {
		this.fzr=fzr;
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