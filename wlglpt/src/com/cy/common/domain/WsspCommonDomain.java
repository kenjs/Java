package com.cy.common.domain;


import java.util.ArrayList;
import java.util.List;

import com.cy.dzgl.domain.QyWsSpZbDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * T��������domain
 * @author HJH
 */

public class WsspCommonDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wsspxh;                           // �����������(SEQ_WS_SPXH)
	private String spxh;//�������
	
	private String spjg;//�������
	private String spyj;//�������
	private String spjUrl;//������
	
	private String sprJdxh;//�����˽ڵ����
	private String sprCzyDjxh;//������
	
	private String spjgjgDm;//���������������
	private String spjgjgmc;//����������������
	private String spjdsm;//�����ڵ�˵��
	private String spJgbm;//��������
	private String spjgmc;//������������
	private String gwDjxh;//��λ�Ǽ����
	private String gwmc;//��λ����
	private String wsspms;//��������ģʽ
	private String wsspmsmc;//��������ģʽ
	
	private boolean judgeBz;//�����־,true,��������
	private boolean sendBz;//���ͱ�־,true,������
	private boolean backBz;//�˻ر�־,true,�����˻�
	private boolean saveBz;//�����־,true,������
	
	private List<DmbGgDomain> sprList;//������list
	
	private List<WssplzDomain> wssplzList;//������list
	
	private QyWsSpZbDomain wsSpZbDomain;//�����ӱ�
	
	private List<String> checkboxs;//
	
	private boolean nextWssp;//������һ����,true
	
	private String rtnCode;
	
	private String wsDm;//�������  
	private String wsXmflDjxh;//������Ŀ����Ǽ����
	private String ywDjxh;//������ҵ�����
	private String fsCzyDjxh;//���Ͳ���Ա�Ǽ����
	private String wssplzszxh;//����������ת�������
	private String oldWsspxh;//����������ţ��� ����������Ϣ�ģ��� ��Ϊ�գ�����Ϊ��
	private String jgbm;//��������


	public WsspCommonDomain() {
	}


	public String getWsspxh() {
		return wsspxh;
	}


	public void setWsspxh(String wsspxh) {
		this.wsspxh = wsspxh;
	}


	public String getSpjg() {
		return spjg;
	}


	public void setSpjg(String spjg) {
		this.spjg = spjg;
	}


	public String getSpjUrl() {
		return spjUrl;
	}


	public void setSpjUrl(String spjUrl) {
		this.spjUrl = spjUrl;
	}


	public String getSpyj() {
		return spyj;
	}


	public void setSpyj(String spyj) {
		this.spyj = spyj;
	}


	public String getSpxh() {
		return spxh;
	}


	public void setSpxh(String spxh) {
		this.spxh = spxh;
	}


	public String getSprJdxh() {
		return sprJdxh;
	}


	public void setSprJdxh(String sprJdxh) {
		this.sprJdxh = sprJdxh;
	}


	public String getSprCzyDjxh() {
		return sprCzyDjxh;
	}


	public void setSprCzyDjxh(String sprCzyDjxh) {
		this.sprCzyDjxh = sprCzyDjxh;
	}


	public String getSpjdsm() {
		return spjdsm;
	}


	public void setSpjdsm(String spjdsm) {
		this.spjdsm = spjdsm;
	}


	public String getSpJgbm() {
		return spJgbm;
	}


	public void setSpJgbm(String spJgbm) {
		this.spJgbm = spJgbm;
	}


	public String getGwDjxh() {
		return gwDjxh;
	}


	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh = gwDjxh;
	}


	public String getWsspms() {
		return wsspms;
	}


	public void setWsspms(String wsspms) {
		this.wsspms = wsspms;
	}


	public boolean isJudgeBz() {
		return judgeBz;
	}


	public void setJudgeBz(boolean judgeBz) {
		this.judgeBz = judgeBz;
	}


	public String getGwmc() {
		return gwmc;
	}


	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}


	public String getSpjgmc() {
		return spjgmc;
	}


	public void setSpjgmc(String spjgmc) {
		this.spjgmc = spjgmc;
	}


	public boolean isSendBz() {
		return sendBz;
	}


	public void setSendBz(boolean sendBz) {
		this.sendBz = sendBz;
	}


	public List<DmbGgDomain> getSprList() {
		if(null==sprList)
			sprList=new ArrayList<DmbGgDomain>();
		return sprList;
	}


	public void setSprList(List<DmbGgDomain> sprList) {
		this.sprList = sprList;
	}


	public List<WssplzDomain> getWssplzList() {
		if(null==wssplzList)
			wssplzList=new ArrayList<WssplzDomain>();
		return wssplzList;
	}


	public void setWssplzList(List<WssplzDomain> wssplzList) {
		this.wssplzList = wssplzList;
	}


	public boolean isBackBz() {
		return backBz;
	}


	public void setBackBz(boolean backBz) {
		this.backBz = backBz;
	}


	public boolean isSaveBz() {
		return saveBz;
	}


	public void setSaveBz(boolean saveBz) {
		this.saveBz = saveBz;
	}


	public String getWsspmsmc() {
		return wsspmsmc;
	}


	public void setWsspmsmc(String wsspmsmc) {
		this.wsspmsmc = wsspmsmc;
	}


	public QyWsSpZbDomain getWsSpZbDomain() {
		if(wsSpZbDomain==null){
			wsSpZbDomain=new QyWsSpZbDomain();
		}
		return wsSpZbDomain;
	}


	public void setWsSpZbDomain(QyWsSpZbDomain wsSpZbDomain) {
		this.wsSpZbDomain = wsSpZbDomain;
	}


	public List<String> getCheckboxs() {
		if(null==checkboxs)
			checkboxs=new ArrayList<String>();
		return checkboxs;
	}


	public void setCheckboxs(List<String> checkboxs) {
		this.checkboxs = checkboxs;
	}


	public boolean isNextWssp() {
		return nextWssp;
	}


	public void setNextWssp(boolean nextWssp) {
		this.nextWssp = nextWssp;
	}


	public String getRtnCode() {
		return rtnCode;
	}


	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}


	public String getFsCzyDjxh() {
		return fsCzyDjxh;
	}


	public void setFsCzyDjxh(String fsCzyDjxh) {
		this.fsCzyDjxh = fsCzyDjxh;
	}


	public String getOldWsspxh() {
		return oldWsspxh;
	}


	public void setOldWsspxh(String oldWsspxh) {
		this.oldWsspxh = oldWsspxh;
	}


	public String getWsDm() {
		return wsDm;
	}


	public void setWsDm(String wsDm) {
		this.wsDm = wsDm;
	}


	public String getWssplzszxh() {
		return wssplzszxh;
	}


	public void setWssplzszxh(String wssplzszxh) {
		this.wssplzszxh = wssplzszxh;
	}


	public String getWsXmflDjxh() {
		return wsXmflDjxh;
	}


	public void setWsXmflDjxh(String wsXmflDjxh) {
		this.wsXmflDjxh = wsXmflDjxh;
	}


	public String getYwDjxh() {
		return ywDjxh;
	}


	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh = ywDjxh;
	}


	public String getJgbm() {
		return jgbm;
	}


	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}


	public String getSpjgjgDm() {
		return spjgjgDm;
	}


	public void setSpjgjgDm(String spjgjgDm) {
		this.spjgjgDm = spjgjgDm;
	}


	public String getSpjgjgmc() {
		return spjgjgmc;
	}


	public void setSpjgjgmc(String spjgjgmc) {
		this.spjgjgmc = spjgjgmc;
	}


}