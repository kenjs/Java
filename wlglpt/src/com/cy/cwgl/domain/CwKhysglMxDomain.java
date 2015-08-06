package com.cy.cwgl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_KHYSGL_MX is created by tools.
 * @author HJH
 */

public class CwKhysglMxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String srDjxh;                           // ����Ǽ����(SEQ_SR_DJXH)
	private String mxXh;                             // Ӧ��Ӧ���Ǽ����
	private String khmc;                             // �˷ѽ��㷽�Ǽ����
	private String je;                               // ���
	private String zffsDm;                           // ֧����ʽ����
	private String zcflDm;                           // �ʲ��������
	private String yhCshDjxh;                        // ���г�ʼ���Ǽ����
	private String yhhdh;                            // ���лص���
	private String jbrCzyDjxh;                       // ������
	private String jbrq;                             // 
	private String bz;                               // ��ע
	private String yxbz;                             // ��Ч��־(Y/N)
	private String czxfBz;
	private String zffsMc;
	private String zcflMc;
	private String jbrCzyDjmc;
	private String yhzh;
	private String yhmc;
	private String ysyfDjxh;
	public String getYsyfDjxh() {
		return ysyfDjxh;
	}

	public void setYsyfDjxh(String ysyfDjxh) {
		this.ysyfDjxh = ysyfDjxh;
	}

	public String getZffsMc() {
		return zffsMc;
	}

	public void setZffsMc(String zffsMc) {
		this.zffsMc = zffsMc;
	}

	public String getZcflMc() {
		return zcflMc;
	}

	public void setZcflMc(String zcflMc) {
		this.zcflMc = zcflMc;
	}

	public String getJbrCzyDjmc() {
		return jbrCzyDjmc;
	}

	public void setJbrCzyDjmc(String jbrCzyDjmc) {
		this.jbrCzyDjmc = jbrCzyDjmc;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getCzxfBz() {
		return czxfBz;
	}

	public void setCzxfBz(String czxfBz) {
		this.czxfBz = czxfBz;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public CwKhysglMxDomain() {
	}

	//��ȡ����Ǽ����(SEQ_SR_DJXH)
	public String getSrDjxh() {
		return this.srDjxh;
	}

	//��������Ǽ����(SEQ_SR_DJXH)
	public void setSrDjxh(String srDjxh) {
		this.srDjxh=srDjxh;
	}

	//��ȡӦ��Ӧ���Ǽ����
	public String getMxXh() {
		return this.mxXh;
	}

	//����Ӧ��Ӧ���Ǽ����
	public void setMxXh(String mxXh) {
		this.mxXh=mxXh;
	}

	//��ȡ�˷ѽ��㷽�Ǽ����
	public String getKhmc() {
		return this.khmc;
	}

	//�����˷ѽ��㷽�Ǽ����
	public void setKhmc(String khmc) {
		this.khmc=khmc;
	}

	//��ȡ���
	

	//��ȡ֧����ʽ����
	public String getZffsDm() {
		return this.zffsDm;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	//����֧����ʽ����
	public void setZffsDm(String zffsDm) {
		this.zffsDm=zffsDm;
	}

	//��ȡ�ʲ��������
	public String getZcflDm() {
		return this.zcflDm;
	}

	//�����ʲ��������
	public void setZcflDm(String zcflDm) {
		this.zcflDm=zcflDm;
	}

	//��ȡ���г�ʼ���Ǽ����
	public String getYhCshDjxh() {
		return this.yhCshDjxh;
	}

	//�������г�ʼ���Ǽ����
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh=yhCshDjxh;
	}

	//��ȡ���лص���
	public String getYhhdh() {
		return this.yhhdh;
	}

	//�������лص���
	public void setYhhdh(String yhhdh) {
		this.yhhdh=yhhdh;
	}

	//��ȡ������
	public String getJbrCzyDjxh() {
		return this.jbrCzyDjxh;
	}

	//���þ�����
	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh=jbrCzyDjxh;
	}

	//��ȡ
	public String getJbrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.jbrq);
		}
		catch(Exception e){
			return this.jbrq;
		}
	}

	//����
	public void setJbrq(String jbrq) {
		this.jbrq=jbrq;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
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
}
