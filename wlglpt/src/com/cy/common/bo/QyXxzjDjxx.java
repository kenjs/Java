package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_XXZJ_DJXX is created by tools.
 * @author HJH
 */

public class QyXxzjDjxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xxzjDjxh;                         // ��Ϣ�н�Ǽ����(SEQ_ZY_DJXH)
	private String ssJgbm;                           // ��������
	private String xxzjmc;                           // ��Ϣ�н�����
	private String xxzjjc;                           // ��Ϣ�н���
	private String pyqc;                             // ƴ��ȫ��
	private String pyjc;                             // ƴ�����
	private String xzqhDm;                           // ������������
	private String dz;                               // ��ַ
	private String dh;                               // �绰
	private String yb;                               // �ʱ�
	private String fzr;                              // ������
	private String xxzjQybm;                         // ��Ϣ�н���ҵ����
	private String bz;                               // ��ע
	private String djJgbm;                           // �Ǽǲ���
	private String djrCzyDjxh;                       // �Ǽ���
	private String djrq;                             // �Ǽ�����
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public QyXxzjDjxx() {
	}

	//��ȡ��Ϣ�н�Ǽ����(SEQ_ZY_DJXH)
	public String getXxzjDjxh() {
		return this.xxzjDjxh;
	}

	//������Ϣ�н�Ǽ����(SEQ_ZY_DJXH)
	public void setXxzjDjxh(String xxzjDjxh) {
		this.xxzjDjxh=xxzjDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ��Ϣ�н�����
	public String getXxzjmc() {
		return this.xxzjmc;
	}

	//������Ϣ�н�����
	public void setXxzjmc(String xxzjmc) {
		this.xxzjmc=xxzjmc;
	}

	//��ȡ��Ϣ�н���
	public String getXxzjjc() {
		return this.xxzjjc;
	}

	//������Ϣ�н���
	public void setXxzjjc(String xxzjjc) {
		this.xxzjjc=xxzjjc;
	}

	//��ȡƴ��ȫ��
	public String getPyqc() {
		return this.pyqc;
	}

	//����ƴ��ȫ��
	public void setPyqc(String pyqc) {
		this.pyqc=pyqc;
	}

	//��ȡƴ�����
	public String getPyjc() {
		return this.pyjc;
	}

	//����ƴ�����
	public void setPyjc(String pyjc) {
		this.pyjc=pyjc;
	}

	//��ȡ������������
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//����������������
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//��ȡ��ַ
	public String getDz() {
		return this.dz;
	}

	//���õ�ַ
	public void setDz(String dz) {
		this.dz=dz;
	}

	//��ȡ�绰
	public String getDh() {
		return this.dh;
	}

	//���õ绰
	public void setDh(String dh) {
		this.dh=dh;
	}

	//��ȡ�ʱ�
	public String getYb() {
		return this.yb;
	}

	//�����ʱ�
	public void setYb(String yb) {
		this.yb=yb;
	}

	//��ȡ������
	public String getFzr() {
		return this.fzr;
	}

	//���ø�����
	public void setFzr(String fzr) {
		this.fzr=fzr;
	}

	//��ȡ��Ϣ�н���ҵ����
	public String getXxzjQybm() {
		return this.xxzjQybm;
	}

	//������Ϣ�н���ҵ����
	public void setXxzjQybm(String xxzjQybm) {
		this.xxzjQybm=xxzjQybm;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ�Ǽ���
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//���õǼ���
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ�Ǽ�����
	public String getDjrq() {
		return this.djrq;
	}

	//���õǼ�����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
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