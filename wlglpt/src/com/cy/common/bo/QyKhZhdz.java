package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_KH_ZHDZ is created by tools.
 * @author HJH
 */

public class QyKhZhdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zhdzDjxh;                         // 
	private String ssJgbm;                           // ��������(SEQ_JG_DJXH)
	private String khDjxh;                           // 
	private String xzqhDm;                           // ������������
	private String xxdz;                             // ��ϸ��ַ
	private String pyqc;                             // ��ַƴ��ȫ��
	private String pyjc;                             // ��ַƴ�����
	private String yb;                               // �ʱ�
	private String lxr;                              // ��ϵ��
	private String lxrYddh;                          // ��ϵ���ƶ��绰
	private String lxrGddh;                          // ��ϵ�˹̶��绰
	private String qtlxdh;                           // ������ϵ�绰
	private String bz;                               // ��ע
	private String djJgbm;                           // �Ǽǻ�������
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public QyKhZhdz() {
	}

	//��ȡ
	public String getZhdzDjxh() {
		return this.zhdzDjxh;
	}

	//����
	public void setZhdzDjxh(String zhdzDjxh) {
		this.zhdzDjxh=zhdzDjxh;
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
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ������������
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//����������������
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//��ȡ����
	public String getXxdz() {
		return this.xxdz;
	}

	//��������
	public void setXxdz(String xxdz) {
		this.xxdz=xxdz;
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

	//��ȡ�ʱ�
	public String getYb() {
		return this.yb;
	}

	//�����ʱ�
	public void setYb(String yb) {
		this.yb=yb;
	}

	//��ȡ���
	public String getLxr() {
		return this.lxr;
	}

	//���ü��
	public void setLxr(String lxr) {
		this.lxr=lxr;
	}

	//��ȡƴ��ȫ��
	public String getLxrYddh() {
		return this.lxrYddh;
	}

	//����ƴ��ȫ��
	public void setLxrYddh(String lxrYddh) {
		this.lxrYddh=lxrYddh;
	}

	//��ȡƴ�����
	public String getLxrGddh() {
		return this.lxrGddh;
	}

	//����ƴ�����
	public void setLxrGddh(String lxrGddh) {
		this.lxrGddh=lxrGddh;
	}

	//��ȡ��ַ
	public String getQtlxdh() {
		return this.qtlxdh;
	}

	//���õ�ַ
	public void setQtlxdh(String qtlxdh) {
		this.qtlxdh=qtlxdh;
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
}