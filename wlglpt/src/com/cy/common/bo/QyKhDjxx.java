package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_KH_DJXX is created by tools.
 * @author HJH
 */

public class QyKhDjxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String khDjxh;                           // 
	private String ssJgbm;                           // ��������(SEQ_JG_DJXH)
	private String khmc;                             // ����
	private String khjc;                             // ���
	private String pyqc;                             // ƴ��ȫ��
	private String pyjc;                             // ƴ�����
	private String xzqhDm;                           // ������������
	private String dz;                               // ��ַ
	private String dh;                               // �绰
	private String yb;                               // �ʱ�
	private String fzr;                              // ������
	private String bz;                               // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String khlxDm;							 //�ͻ����ʹ���
	private String ykjsfsDm;                         //�����㷽ʽ����
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String xxgxfsDm;						//��Ϣ����ʽ����
	private String khbm;
	public String getKhbm() {
		return khbm;
	}

	public void setKhbm(String khbm) {
		this.khbm = khbm;
	}

	public QyKhDjxx() {
	}

	//��ȡ
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ��������(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//���û�������(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ����
	public String getKhmc() {
		return this.khmc;
	}

	//��������
	public void setKhmc(String khmc) {
		this.khmc=khmc;
	}

	//��ȡ���
	public String getKhjc() {
		return this.khjc;
	}

	//���ü��
	public void setKhjc(String khjc) {
		this.khjc=khjc;
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

	//��ȡ
	public String getBz() {
		return this.bz;
	}

	//����
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//����
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//����
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ
	public String getDjrq() {
		return this.djrq;
	}

	//����
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

	public String getKhlxDm() {
		return khlxDm;
	}

	public void setKhlxDm(String khlxDm) {
		this.khlxDm = khlxDm;
	}

	public String getYkjsfsDm() {
		return ykjsfsDm;
	}

	public void setYkjsfsDm(String ykjsfsDm) {
		this.ykjsfsDm = ykjsfsDm;
	}

	public String getXxgxfsDm() {
		return xxgxfsDm;
	}

	public void setXxgxfsDm(String xxgxfsDm) {
		this.xxgxfsDm = xxgxfsDm;
	}
}