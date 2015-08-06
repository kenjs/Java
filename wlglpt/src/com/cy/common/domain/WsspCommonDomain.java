package com.cy.common.domain;


import java.util.ArrayList;
import java.util.List;

import com.cy.dzgl.domain.QyWsSpZbDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * T文书审批domain
 * @author HJH
 */

public class WsspCommonDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wsspxh;                           // 文书审批序号(SEQ_WS_SPXH)
	private String spxh;//审批序号
	
	private String spjg;//审批结果
	private String spyj;//审批意见
	private String spjUrl;//审批件
	
	private String sprJdxh;//审批人节点序号
	private String sprCzyDjxh;//审批人
	
	private String spjgjgDm;//审批机构级别代码
	private String spjgjgmc;//审批机构级别名称
	private String spjdsm;//审批节点说明
	private String spJgbm;//审批机构
	private String spjgmc;//审批机构名称
	private String gwDjxh;//岗位登记序号
	private String gwmc;//岗位名称
	private String wsspms;//文书审批模式
	private String wsspmsmc;//文书审批模式
	
	private boolean judgeBz;//终审标志,true,允许终审
	private boolean sendBz;//发送标志,true,允许发送
	private boolean backBz;//退回标志,true,允许退回
	private boolean saveBz;//保存标志,true,允许保存
	
	private List<DmbGgDomain> sprList;//审批人list
	
	private List<WssplzDomain> wssplzList;//审批人list
	
	private QyWsSpZbDomain wsSpZbDomain;//审批子表
	
	private List<String> checkboxs;//
	
	private boolean nextWssp;//查找下一审批,true
	
	private String rtnCode;
	
	private String wsDm;//文书代码  
	private String wsXmflDjxh;//文书项目分类登记序号
	private String ywDjxh;//审批件业务序号
	private String fsCzyDjxh;//发送操作员登记序号
	private String wssplzszxh;//文书审批流转设置序号
	private String oldWsspxh;//文书审批序号，若 已有审批信息的，则 不为空，否则为空
	private String jgbm;//机构编码


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