package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_KH_HWXX is created by tools.
 * @author HJH
 */

public class QyKhHwxh  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwxhDjxh;
	private String ssJgbm;                           // ��������(SEQ_JG_DJXH)
	private String khDjxh;                           // �ͻ��Ǽ����
	private String hwDjxh;                           // ����Ǽ����
	private String xhmc;                             // ����
	private String xhjc;                             // ���
	private String xhqc;                             // ȫ��
	private String pyqc;                             // ƴ��ȫ��
	private String pyjc;                             // ƴ�����
	private String cdJldwDm;                         // 
	private Double cd;                               // �绰
	private Double kd;                               // �ʱ�
	private Double gd;                               // ������
	private String bzJldwDm;                         // ��ַ
	private Double bzJsHsbl;                         // 
	private Double bzCbHsbl;                         // 
	private String jsJldwDm;                         // ��ַ
	private String cbJldwDm;                         // ��ַ
	private String bz;                               // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String slJldwDm;						//����
	private String zlJldwDm;  						//����		
	private String tjJldwDm;                        //���
	public String getSlJldwDm() {
		return slJldwDm;
	}

	public void setSlJldwDm(String slJldwDm) {
		this.slJldwDm = slJldwDm;
	}

	public String getTjJldwDm() {
		return tjJldwDm;
	}

	public void setTjJldwDm(String tjJldwDm) {
		this.tjJldwDm = tjJldwDm;
	}

	public String getZlJldwDm() {
		return zlJldwDm;
	}

	public void setZlJldwDm(String zlJldwDm) {
		this.zlJldwDm = zlJldwDm;
	}

	public QyKhHwxh() {
	}

	//��ȡ
	public String getHwDjxh() {
		return this.hwDjxh;
	}

	//����
	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh=hwDjxh;
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

	//��ȡ
	public String getCdJldwDm() {
		return this.cdJldwDm;
	}

	//����
	public void setCdJldwDm(String cdJldwDm) {
		this.cdJldwDm=cdJldwDm;
	}

	//��ȡ�绰
	public Double getCd() {
		return this.cd;
	}

	//���õ绰
	public void setCd(Double cd) {
		this.cd=cd;
	}

	//��ȡ�ʱ�
	public Double getKd() {
		return this.kd;
	}

	//�����ʱ�
	public void setKd(Double kd) {
		this.kd=kd;
	}

	//��ȡ������
	public Double getGd() {
		return this.gd;
	}

	//���ø�����
	public void setGd(Double gd) {
		this.gd=gd;
	}

	//��ȡ��ַ
	public String getBzJldwDm() {
		return this.bzJldwDm;
	}

	//���õ�ַ
	public void setBzJldwDm(String bzJldwDm) {
		this.bzJldwDm=bzJldwDm;
	}

	//��ȡ
	public Double getBzJsHsbl() {
		return this.bzJsHsbl;
	}

	//����
	public void setBzJsHsbl(Double bzJsHsbl) {
		this.bzJsHsbl=bzJsHsbl;
	}

	//��ȡ
	public Double getBzCbHsbl() {
		return this.bzCbHsbl;
	}

	//����
	public void setBzCbHsbl(Double bzCbHsbl) {
		this.bzCbHsbl=bzCbHsbl;
	}

	//��ȡ��ַ
	public String getJsJldwDm() {
		return this.jsJldwDm;
	}

	//���õ�ַ
	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm=jsJldwDm;
	}

	//��ȡ��ַ
	public String getCbJldwDm() {
		return this.cbJldwDm;
	}

	//���õ�ַ
	public void setCbJldwDm(String cbJldwDm) {
		this.cbJldwDm=cbJldwDm;
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

	public String getHwxhDjxh() {
		return hwxhDjxh;
	}

	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh = hwxhDjxh;
	}

	public String getXhjc() {
		return xhjc;
	}

	public void setXhjc(String xhjc) {
		this.xhjc = xhjc;
	}

	public String getXhmc() {
		return xhmc;
	}

	public void setXhmc(String xhmc) {
		this.xhmc = xhmc;
	}

	public String getXhqc() {
		return xhqc;
	}

	public void setXhqc(String xhqc) {
		this.xhqc = xhqc;
	}
}