package com.cy.common.domain;


import com.cy.framework.domain.BaseBusinessDomain;

/**
 * T文书审批流转domain
 * @author HJH
 */

public class WssplzDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wsspxh;                           // 文书审批序号(SEQ_WS_SPXH)
	private String spxh;//审批序号
	private String fsthbz;//发送退回标志
	private String fsrmc;//发送人名称
	private String fsrq;//发送日期
	private String spjgmc;//审批机构
	private String spgwmc;//审批岗位
	private String jdxh;//节点序号
	private String qzxs;//权重系数
	private String sprmc;//审批人名称
	private String sprq;//审批日期
	private String spjg;//审批结果
	private String spyj;//审批意见
	private String cqbz;//超期标志
	


	public WssplzDomain() {
	}


	public String getFsthbz() {
		return fsthbz;
	}


	public void setFsthbz(String fsthbz) {
		this.fsthbz = fsthbz;
	}


	public String getFsrmc() {
		return fsrmc;
	}


	public void setFsrmc(String fsrmc) {
		this.fsrmc = fsrmc;
	}


	public String getFsrq() {
		return fsrq;
	}


	public void setFsrq(String fsrq) {
		this.fsrq = fsrq;
	}


	public String getSpjgmc() {
		return spjgmc;
	}


	public void setSpjgmc(String spjgmc) {
		this.spjgmc = spjgmc;
	}


	public String getSpgwmc() {
		return spgwmc;
	}


	public void setSpgwmc(String spgwmc) {
		this.spgwmc = spgwmc;
	}


	public String getJdxh() {
		return jdxh;
	}


	public void setJdxh(String jdxh) {
		this.jdxh = jdxh;
	}


	public String getQzxs() {
		return qzxs;
	}


	public void setQzxs(String qzxs) {
		this.qzxs = qzxs;
	}


	public String getSprmc() {
		return sprmc;
	}


	public void setSprmc(String sprmc) {
		this.sprmc = sprmc;
	}


	public String getSprq() {
		return sprq;
	}


	public void setSprq(String sprq) {
		this.sprq = sprq;
	}


	public String getCqbz() {
		return cqbz;
	}


	public void setCqbz(String cqbz) {
		this.cqbz = cqbz;
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


}