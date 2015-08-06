package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For QY_SPWS_SPLCSZ_ZB is created by tools.
 * @author HJH
 */

public class QySpwsSplcszZb  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long splcSzxh;                         // ���������������
	private String jdxh;                             // �ڵ����(��1��ʼ����)
	private String spjdsm;//�����ڵ�˵��
	private String spjgjbDm;//���������������
	private String spJgbm;                           // ������������(����)
	private String gwDjxh;                           // ������λ�Ǽ����
	private String sphjsm;                           // ��������˵��
	private String spyjl;                            // ���������
	private String spqm;                             // ����ǩ��
	private String yxzsBz;                           // ���������־(Y/N)
	private String zstj;                             // ��������(�ɴ���������,������#��ͷ)
	private Double spsx;                             // ����ʱ��(��)
	private String gzrbz;                            // �����ձ�־(1 �����գ�2 ��Ȼ��)
	private Double qzxs;                             // Ȩ��ϵ��
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����
	private String fsbz;//��ͬ�������������(Y/N)

	public QySpwsSplcszZb() {
	}

	//��ȡ���������������
	public Long getSplcSzxh() {
		return this.splcSzxh;
	}

	//�������������������
	public void setSplcSzxh(Long splcSzxh) {
		this.splcSzxh=splcSzxh;
	}

	//��ȡ�ڵ����(��1��ʼ����)
	public String getJdxh() {
		return this.jdxh;
	}

	//���ýڵ����(��1��ʼ����)
	public void setJdxh(String jdxh) {
		this.jdxh=jdxh;
	}

	//��ȡ������������(����)
	public String getSpJgbm() {
		return this.spJgbm;
	}

	//����������������(����)
	public void setSpJgbm(String spJgbm) {
		this.spJgbm=spJgbm;
	}

	//��ȡ������λ�Ǽ����
	public String getGwDjxh() {
		return this.gwDjxh;
	}

	//����������λ�Ǽ����
	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh=gwDjxh;
	}

	//��ȡ��������˵��
	public String getSphjsm() {
		return this.sphjsm;
	}

	//������������˵��
	public void setSphjsm(String sphjsm) {
		this.sphjsm=sphjsm;
	}

	//��ȡ���������
	public String getSpyjl() {
		return this.spyjl;
	}

	//�������������
	public void setSpyjl(String spyjl) {
		this.spyjl=spyjl;
	}

	//��ȡ����ǩ��
	public String getSpqm() {
		return this.spqm;
	}

	//��������ǩ��
	public void setSpqm(String spqm) {
		this.spqm=spqm;
	}

	//��ȡ���������־(Y/N)
	public String getYxzsBz() {
		return this.yxzsBz;
	}

	//�������������־(Y/N)
	public void setYxzsBz(String yxzsBz) {
		this.yxzsBz=yxzsBz;
	}

	//��ȡ��������(�ɴ���������,������#��ͷ)
	public String getZstj() {
		return this.zstj;
	}

	//������������(�ɴ���������,������#��ͷ)
	public void setZstj(String zstj) {
		this.zstj=zstj;
	}

	//��ȡ����ʱ��(��)
	public Double getSpsx() {
		return this.spsx;
	}

	//��������ʱ��(��)
	public void setSpsx(Double spsx) {
		this.spsx=spsx;
	}

	//��ȡ�����ձ�־(1 �����գ�2 ��Ȼ��)
	public String getGzrbz() {
		return this.gzrbz;
	}

	//���ù����ձ�־(1 �����գ�2 ��Ȼ��)
	public void setGzrbz(String gzrbz) {
		this.gzrbz=gzrbz;
	}

	//��ȡȨ��ϵ��
	public Double getQzxs() {
		return this.qzxs;
	}

	//����Ȩ��ϵ��
	public void setQzxs(Double qzxs) {
		this.qzxs=qzxs;
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
	public Date getCjrq() {
		return this.cjrq;
	}

	//���ô�������
	public void setCjrq(Date cjrq) {
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
	public Date getXgrq() {
		return this.xgrq;
	}

	//�����޸�����
	public void setXgrq(Date xgrq) {
		this.xgrq=xgrq;
	}

	public String getSpjdsm() {
		return spjdsm;
	}

	public void setSpjdsm(String spjdsm) {
		this.spjdsm = spjdsm;
	}

	public String getSpjgjbDm() {
		return spjgjbDm;
	}

	public void setSpjgjbDm(String spjgjbDm) {
		this.spjgjbDm = spjgjbDm;
	}

	public String getFsbz() {
		return fsbz;
	}

	public void setFsbz(String fsbz) {
		this.fsbz = fsbz;
	}
}