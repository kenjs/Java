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

	private String gnmkDm; // 功能模块代码

	private String gnmkMc; // 功能模块名称

	private String gnmkBz; // 功能模块备注

	private String url; // URL

	private String urlHelp; // URL_HELP

	private String parmbz; // 参数标志

	private String xybz; // 选用标志

	private String yxbz; // 有效标志

	private String cjrDm; // 创建人

	private String cjrq; // 创建日期

	private String xgrDm; // 修改人

	private String xgrq; // 修改日期

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

	// 获取功能模块代码
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	// 设置功能模块代码
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm = gnmkDm;
	}

	// 获取功能模块名称
	public String getGnmkMc() {
		return this.gnmkMc;
	}

	// 设置功能模块名称
	public void setGnmkMc(String gnmkMc) {
		this.gnmkMc = gnmkMc;
	}

	// 获取功能模块备注
	public String getGnmkBz() {
		return this.gnmkBz;
	}

	// 设置功能模块备注
	public void setGnmkBz(String gnmkBz) {
		this.gnmkBz = gnmkBz;
	}

	// 获取URL
	public String getUrl() {
		return this.url;
	}

	// 设置URL
	public void setUrl(String url) {
		this.url = url;
	}

	// 获取URL_HELP
	public String getUrlHelp() {
		return this.urlHelp;
	}

	// 设置URL_HELP
	public void setUrlHelp(String urlHelp) {
		this.urlHelp = urlHelp;
	}

	// 获取参数标志
	public String getParmbz() {
		return this.parmbz;
	}

	// 设置参数标志
	public void setParmbz(String parmbz) {
		this.parmbz = parmbz;
	}

	// 获取选用标志
	public String getXybz() {
		return this.xybz;
	}

	// 设置选用标志
	public void setXybz(String xybz) {
		this.xybz = xybz;
	}

	// 获取有效标志
	public String getYxbz() {
		return this.yxbz;
	}

	// 设置有效标志
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	// 获取创建人
	public String getCjrDm() {
		return this.cjrDm;
	}

	// 设置创建人
	public void setCjrDm(String cjrDm) {
		this.cjrDm = cjrDm;
	}

	// 获取创建日期
	public String getCjrq() {
		return this.cjrq;
	}

	// 设置创建日期
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	// 获取修改人
	public String getXgrDm() {
		return this.xgrDm;
	}

	// 设置修改人
	public void setXgrDm(String xgrDm) {
		this.xgrDm = xgrDm;
	}

	// 获取修改日期
	public String getXgrq() {
		return this.xgrq;
	}

	// 设置修改日期
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