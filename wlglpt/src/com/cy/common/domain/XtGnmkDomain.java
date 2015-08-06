package com.cy.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR XT_GNMK is created by tools.
 * 
 * @author HJH
 */

public class XtGnmkDomain extends BaseBusinessDomain {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String gnmkDm; // ����ģ�����

	private String gnmkMc; // ����ģ������

	private String gnmkBz; // ����ģ�鱸ע

	private String url; // URL

	private String urlHelp; // URL_HELP

	private String parmbz; // ������־

	private String xybz; // ѡ�ñ�־

	private String yxbz; // ��Ч��־

	private String cjrDm; // ������

	private String cjrq; // ��������

	private String xgrDm; // �޸���

	private String xgrq; // �޸�����

	private String node;

	private String xtflDm;

	private String pxxh;

	private String sjMenuDm;

	private String gnmkPath;

	private List<XtGnmkDomain> domainList;

	public List<XtGnmkDomain> getDomainList() {
		if (domainList == null) {
			domainList = new ArrayList<XtGnmkDomain>();
		}
		return domainList;
	}

	public void setDomainList(List<XtGnmkDomain> domainList) {
		this.domainList = domainList;
	}

	public XtGnmkDomain() {
	}

	// ��ȡ����ģ�����
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	// ���ù���ģ�����
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm = gnmkDm;
	}

	// ��ȡ����ģ������
	public String getGnmkMc() {
		return this.gnmkMc;
	}

	// ���ù���ģ������
	public void setGnmkMc(String gnmkMc) {
		this.gnmkMc = gnmkMc;
	}

	// ��ȡ����ģ�鱸ע
	public String getGnmkBz() {
		return this.gnmkBz;
	}

	// ���ù���ģ�鱸ע
	public void setGnmkBz(String gnmkBz) {
		this.gnmkBz = gnmkBz;
	}

	// ��ȡURL
	public String getUrl() {
		return this.url;
	}

	// ����URL
	public void setUrl(String url) {
		this.url = url;
	}

	// ��ȡURL_HELP
	public String getUrlHelp() {
		return this.urlHelp;
	}

	// ����URL_HELP
	public void setUrlHelp(String urlHelp) {
		this.urlHelp = urlHelp;
	}

	// ��ȡ������־
	public String getParmbz() {
		return this.parmbz;
	}

	// ���ò�����־
	public void setParmbz(String parmbz) {
		this.parmbz = parmbz;
	}

	// ��ȡѡ�ñ�־
	public String getXybz() {
		return this.xybz;
	}

	// ����ѡ�ñ�־
	public void setXybz(String xybz) {
		this.xybz = xybz;
	}

	// ��ȡ��Ч��־
	public String getYxbz() {
		return this.yxbz;
	}

	// ������Ч��־
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	// ��ȡ������
	public String getCjrDm() {
		return this.cjrDm;
	}

	// ���ô�����
	public void setCjrDm(String cjrDm) {
		this.cjrDm = cjrDm;
	}

	// ��ȡ��������
	public String getCjrq() {
		return this.cjrq;
	}

	// ���ô�������
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	// ��ȡ�޸���
	public String getXgrDm() {
		return this.xgrDm;
	}

	// �����޸���
	public void setXgrDm(String xgrDm) {
		this.xgrDm = xgrDm;
	}

	// ��ȡ�޸�����
	public String getXgrq() {
		return this.xgrq;
	}

	// �����޸�����
	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}

	public String getGnmkPath() {
		return gnmkPath;
	}

	public void setGnmkPath(String gnmkPath) {
		this.gnmkPath = gnmkPath;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getPxxh() {
		return pxxh;
	}

	public void setPxxh(String pxxh) {
		this.pxxh = pxxh;
	}

	public String getSjMenuDm() {
		return sjMenuDm;
	}

	public void setSjMenuDm(String sjMenuDm) {
		this.sjMenuDm = sjMenuDm;
	}

	public String getXtflDm() {
		return xtflDm;
	}

	public void setXtflDm(String xtflDm) {
		this.xtflDm = xtflDm;
	}
}