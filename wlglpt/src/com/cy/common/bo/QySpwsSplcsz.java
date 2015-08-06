package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_SPWS_SPLCSZ is created by tools.
 * @author HJH
 */

public class QySpwsSplcsz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long splcSzxh;                         // ���������������(SEQ_SPLC_SZXH)
	private String ssJgbm;                           // ������������(����)
	private String wsDm;                             // �������
	private String xmflDjxh;                         // ��Ŀ����Ǽ����
	private String splc;                             // ��������
	private Double zssx;                             // ����ʱ��(��)
	private String gzrbz;                            // �����ձ�־(1 �����գ�2 ��Ȼ��)
	private String qzxsbz;                           // Ȩ��ϵ����־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public QySpwsSplcsz() {
	}

	//��ȡ���������������(SEQ_SPLC_SZXH)
	public Long getSplcSzxh() {
		return this.splcSzxh;
	}

	//�������������������(SEQ_SPLC_SZXH)
	public void setSplcSzxh(Long splcSzxh) {
		this.splcSzxh=splcSzxh;
	}

	//��ȡ������������(����)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//����������������(����)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�������
	public String getWsDm() {
		return this.wsDm;
	}

	//�����������
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//��ȡ��Ŀ����Ǽ����
	public String getXmflDjxh() {
		return this.xmflDjxh;
	}

	//������Ŀ����Ǽ����
	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh=xmflDjxh;
	}

	//��ȡ��������
	public String getSplc() {
		return this.splc;
	}

	//������������
	public void setSplc(String splc) {
		this.splc=splc;
	}

	//��ȡ����ʱ��(��)
	public Double getZssx() {
		return this.zssx;
	}

	//��������ʱ��(��)
	public void setZssx(Double zssx) {
		this.zssx=zssx;
	}

	//��ȡ�����ձ�־(1 �����գ�2 ��Ȼ��)
	public String getGzrbz() {
		return this.gzrbz;
	}

	//���ù����ձ�־(1 �����գ�2 ��Ȼ��)
	public void setGzrbz(String gzrbz) {
		this.gzrbz=gzrbz;
	}

	//��ȡȨ��ϵ����־(Y/N)
	public String getQzxsbz() {
		return this.qzxsbz;
	}

	//����Ȩ��ϵ����־(Y/N)
	public void setQzxsbz(String qzxsbz) {
		this.qzxsbz=qzxsbz;
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