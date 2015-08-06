package com.cy.common.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR XT_XTML_GNMK_CZTS is created by tools.
 * @author HJH
 */

public class XtXtmlGnmkCztsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long xtmlXh;                             // 系统目录序号
	private String gnmkDm;                           // 功能模块代码
	private String czts;                             // 操作提示
	private String xtmlMc;							 //系统目录名称
	private String gnmkMc;							 //功能模块名称
	
	private Long xgXtmlXh;							 //修改时从父页面传到子页面的系统目录序号，修改时用
	
	private String xgGnmkDm;							 //修改从父页面传到子页面的功能模块代码，修改时用
	
	List<XtXtmlGnmkCztsDomain> domainList; 			 //结果列表
	
	List<XtXtmlDomain> xtXtmlList; 			 		 //系统目录下拉列表
	
	List<XtGnmkDomain> xtGnmkList; 			 		 //系统功能模块下拉列表

	public XtXtmlGnmkCztsDomain() {
	}

	//获取系统目录序号
	public Long getXtmlXh() {
		return this.xtmlXh;
	}

	//设置系统目录序号
	public void setXtmlXh(Long xtmlXh) {
		this.xtmlXh=xtmlXh;
	}

	//获取功能模块代码
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	//设置功能模块代码
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm=gnmkDm;
	}

	//获取操作提示
	public String getCzts() {
		return this.czts;
	}

	//设置操作提示
	public void setCzts(String czts) {
		this.czts=czts;
	}

	public List<XtXtmlGnmkCztsDomain> getDomainList() {
		if (null == domainList) {
			domainList = new ArrayList<XtXtmlGnmkCztsDomain>();
		}
		return domainList;
	}

	public void setDomainList(List<XtXtmlGnmkCztsDomain> domainList) {
		this.domainList = domainList;
	}

	public List<XtGnmkDomain> getXtGnmkList() {
		if (null == xtGnmkList) {
			xtGnmkList = new ArrayList<XtGnmkDomain>();
		}
		return xtGnmkList;
	}

	public void setXtGnmkList(List<XtGnmkDomain> xtGnmkList) {
		this.xtGnmkList = xtGnmkList;
	}

	public List<XtXtmlDomain> getXtXtmlList() {
		if (null == xtXtmlList) {
			xtXtmlList = new ArrayList<XtXtmlDomain>();
		}
		return xtXtmlList;
	}

	public void setXtXtmlList(List<XtXtmlDomain> xtXtmlList) {
		this.xtXtmlList = xtXtmlList;
	}

	public String getXtmlMc() {
		return xtmlMc;
	}

	public void setXtmlMc(String xtmlMc) {
		this.xtmlMc = xtmlMc;
	}

	public String getGnmkMc() {
		return gnmkMc;
	}

	public void setGnmkMc(String gnmkMc) {
		this.gnmkMc = gnmkMc;
	}

	public Long getXgXtmlXh() {
		return xgXtmlXh;
	}

	public void setXgXtmlXh(Long xgXtmlXh) {
		this.xgXtmlXh = xgXtmlXh;
	}

	public String getXgGnmkDm() {
		return xgGnmkDm;
	}

	public void setXgGnmkDm(String xgGnmkDm) {
		this.xgGnmkDm = xgGnmkDm;
	}
}