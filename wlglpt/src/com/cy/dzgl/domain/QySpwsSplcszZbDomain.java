package com.cy.dzgl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR QY_SPWS_SPLCSZ_ZB is created by tools.
 * @author HJH
 */

public class QySpwsSplcszZbDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long splcSzxh;                         // ���������������
	private String jdxh;                             // �ڵ����(��1��ʼ����)
	private String spjdsm;//�����ڵ�˵��
	private String spjgjbDm;//���������������
	private String spjgjbMc;//����������������
	private String spJgbm;                           // ������������(����)
	private String spJgmc;
	private String gwDjxh;                           // ������λ�Ǽ����
	private String gwMc;
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

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String fsbz;//��ͬ�������������(Y/N)
	private String dwDm;//��λ
	private String dwMc;//��λ����

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	

	public QySpwsSplcszZbDomain() {
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

	public String getSpJgmc() {
		return spJgmc;
	}

	public void setSpJgmc(String spJgmc) {
		this.spJgmc = spJgmc;
	}

	public String getGwMc() {
		return gwMc;
	}

	public void setGwMc(String gwMc) {
		this.gwMc = gwMc;
	}

	//��ȡ�޸�����
	public Date getXgrq() {
		return this.xgrq;
	}

	//�����޸�����
	public void setXgrq(Date xgrq) {
		this.xgrq=xgrq;
	}

	public String getCjrMc() {
		return this.cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getXgrMc() {
		return this.xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
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

	public String getSpjgjbMc() {
		return spjgjbMc;
	}

	public void setSpjgjbMc(String spjgjbMc) {
		this.spjgjbMc = spjgjbMc;
	}

	public String getFsbz() {
		return fsbz;
	}

	public void setFsbz(String fsbz) {
		this.fsbz = fsbz;
	}

	public String getDwDm() {
		return dwDm;
	}

	public void setDwDm(String dwDm) {
		this.dwDm = dwDm;
	}

	public String getDwMc() {
		return dwMc;
	}

	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}
}
