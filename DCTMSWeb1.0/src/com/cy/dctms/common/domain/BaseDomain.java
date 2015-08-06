package com.cy.dctms.common.domain;

import java.io.Serializable;


public class BaseDomain implements Serializable {

	private static final long serialVersionUID = -653238472375867369L;
	
	PageInfo pageInfo = null;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	

}
