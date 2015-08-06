package com.cy.dzgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_WS_SP_ZB is created by tools.
 * @author HJH
 */

public class QyWsSpZbDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wsSpxh;                           // �����������(SEQ_WS_SPXH)
	private String spxh;                             // �������(��1��ʼ����)
	private String fsthbz;                           // �����˻ر�־(1 ����,2 �˻�)
	private String fsrCzyDjxh;                       // ������
	private String fsrq;                             // ��������
	private String jdxh;                             // �����˽ڵ����
	private String spJgbm;                           // ������������(����)
	private String gwDjxh;                           // ������λ�Ǽ����
	private String spyjl;                            // ���������
	private String spqm;                             // ����ǩ��
	private String yxzsBz;                           // ���������־(Y/N)
	private String zstj;                             // ��������(�ɴ���������,������#��ͷ)
	private Double qzxs;                             // Ȩ��ϵ��
	private String spjzsj;                           // ������ֹʱ��
	private String sprCzyDjxh;                       // ������
	private String sprmc;                       // ������
	private String sprq;                             // ��������
	private String spjg;                             // �������(1 ͬ�⣬2 ��ͬ��)
	private String spyj;                             // �������
	private String cqbz;                             // ���ڱ�־(Y/N)
	private String spbz;                             // ������־(Y ������/N δ����)
	private String fsbz;                             // ��ͬ�������������(Y/N)

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QyWsSpZbDomain() {
	}
	
	public QyWsSpZbDomain(String wsSpxh,String spxh) {
		this.wsSpxh=wsSpxh;
		this.spxh=spxh;
	}

	//��ȡ�����������(SEQ_WS_SPXH)
	public String getWsSpxh() {
		return this.wsSpxh;
	}

	//���������������(SEQ_WS_SPXH)
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh=wsSpxh;
	}

	//��ȡ�������(��1��ʼ����)
	public String getSpxh() {
		return this.spxh;
	}

	//�����������(��1��ʼ����)
	public void setSpxh(String spxh) {
		this.spxh=spxh;
	}

	//��ȡ�����˻ر�־(1 ����,2 �˻�)
	public String getFsthbz() {
		return this.fsthbz;
	}

	//���÷����˻ر�־(1 ����,2 �˻�)
	public void setFsthbz(String fsthbz) {
		this.fsthbz=fsthbz;
	}

	//��ȡ������
	public String getFsrCzyDjxh() {
		return this.fsrCzyDjxh;
	}

	//���÷�����
	public void setFsrCzyDjxh(String fsrCzyDjxh) {
		this.fsrCzyDjxh=fsrCzyDjxh;
	}

	//��ȡ��������
	public String getFsrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.fsrq);
		}
		catch(Exception e){
			return this.fsrq;
		}
	}

	//���÷�������
	public void setFsrq(String fsrq) {
		this.fsrq=fsrq;
	}

	//��ȡ�����˽ڵ����
	public String getJdxh() {
		return this.jdxh;
	}

	//���������˽ڵ����
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

	//��ȡȨ��ϵ��
	public Double getQzxs() {
		return this.qzxs;
	}

	//����Ȩ��ϵ��
	public void setQzxs(Double qzxs) {
		this.qzxs=qzxs;
	}

	//��ȡ������ֹʱ��
	public String getSpjzsj() {
		try{
			return SysDateUtil.getYyyyMmdd(this.spjzsj);
		}
		catch(Exception e){
			return this.spjzsj;
		}
	}

	//����������ֹʱ��
	public void setSpjzsj(String spjzsj) {
		this.spjzsj=spjzsj;
	}

	//��ȡ������
	public String getSprCzyDjxh() {
		return this.sprCzyDjxh;
	}

	//����������
	public void setSprCzyDjxh(String sprCzyDjxh) {
		this.sprCzyDjxh=sprCzyDjxh;
	}

	//��ȡ��������
	public String getSprq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.sprq);
		}
		catch(Exception e){
			return this.sprq;
		}
	}

	//������������
	public void setSprq(String sprq) {
		this.sprq=sprq;
	}

	//��ȡ�������(1 ͬ�⣬2 ��ͬ��)
	public String getSpjg() {
		return this.spjg;
	}

	//�����������(1 ͬ�⣬2 ��ͬ��)
	public void setSpjg(String spjg) {
		this.spjg=spjg;
	}

	//��ȡ�������
	public String getSpyj() {
		return this.spyj;
	}

	//�����������
	public void setSpyj(String spyj) {
		this.spyj=spyj;
	}

	//��ȡ���ڱ�־(Y/N)
	public String getCqbz() {
		return this.cqbz;
	}

	//���ó��ڱ�־(Y/N)
	public void setCqbz(String cqbz) {
		this.cqbz=cqbz;
	}

	//��ȡ������־(Y ������/N δ����)
	public String getSpbz() {
		return this.spbz;
	}

	//����������־(Y ������/N δ����)
	public void setSpbz(String spbz) {
		this.spbz=spbz;
	}

	//��ȡ��ͬ�������������(Y/N)
	public String getFsbz() {
		return this.fsbz;
	}

	//���ò�ͬ�������������(Y/N)
	public void setFsbz(String fsbz) {
		this.fsbz=fsbz;
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

	public String getSprmc() {
		return sprmc;
	}

	public void setSprmc(String sprmc) {
		this.sprmc = sprmc;
	}
}
