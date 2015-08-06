package com.cy.common.domain;

import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The DOMAIN class FOR XT_XTCS is created by tools.
 * @author HJH
 */

public class XtXtcsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String csxh;                             // ��������(������λΪ����������)
	private String csmc;                             // ��������
	private String sysm;                             // ��ע˵��
	private String csmrz;                            // ����Ĭ��ֵ
	private String cslbDm;                           // ����������
	private String yxbz;                             // ��Ч��־(Y/N)
	
	
	private String csz1;								 //����ֵ����ʾ��
	private String csz2;								 //����ֵ����ʾ��
	
	private String oldDlmm;							//��¼����
	private String newDlmm;							//��¼����


	public XtXtcsDomain() {
	}

	//��ȡ��������(������λΪ����������)
	public String getCsxh() {
		return this.csxh;
	}

	//���ò�������(������λΪ����������)
	public void setCsxh(String csxh) {
		this.csxh=csxh;
	}

	//��ȡ��������
	public String getCsmc() {
		return this.csmc;
	}

	//���ò�������
	public void setCsmc(String csmc) {
		this.csmc=csmc;
	}

	//��ȡ��ע˵��
	public String getSysm() {
		return this.sysm;
	}

	//���ñ�ע˵��
	public void setSysm(String sysm) {
		this.sysm=sysm;
	}

	//��ȡ����Ĭ��ֵ
	public String getCsmrz() {
		return this.csmrz;
	}

	//���ò���Ĭ��ֵ
	public void setCsmrz(String csmrz) {
		this.csmrz=csmrz;
	}

	//��ȡ����������
	public String getCslbDm() {
		return this.cslbDm;
	}

	//���ò���������
	public void setCslbDm(String cslbDm) {
		this.cslbDm=cslbDm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	public String getCsz1() {
		return csz1;
	}

	public void setCsz1(String csz1) {
		this.csz1 = csz1;
	}

	public String getCsz2() {
		return csz2;
	}

	public void setCsz2(String csz2) {
		this.csz2 = csz2;
	}

	public String getNewDlmm() {
		return newDlmm;
	}

	public void setNewDlmm(String newDlmm) {
		this.newDlmm = newDlmm;
	}

	public String getOldDlmm() {
		return oldDlmm;
	}

	public void setOldDlmm(String oldDlmm) {
		this.oldDlmm = oldDlmm;
	}

	
}