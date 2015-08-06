package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.common.domain.DmbGgDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR HY_TYD_WFHXX is created by tools.
 * @author HJH
 */

public class QyZrbmThShdzDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	/********派车检索转入部门地址等信息条件*******/
	private String zrbmDjxh;						
	private String tableName;
	
	private String zrbmDz;
	private String zrbmLxr;
	private String zrbmLxdh;
	private String zrbmXzqhDm;
	private String zrbmXzqhMc;
	
	public QyZrbmThShdzDomain() {
	}

	public String getZrbmDjxh() {
		return zrbmDjxh;
	}

	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh = zrbmDjxh;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getZrbmDz() {
		return zrbmDz;
	}

	public void setZrbmDz(String zrbmDz) {
		this.zrbmDz = zrbmDz;
	}

	public String getZrbmLxr() {
		return zrbmLxr;
	}

	public void setZrbmLxr(String zrbmLxr) {
		this.zrbmLxr = zrbmLxr;
	}

	public String getZrbmLxdh() {
		return zrbmLxdh;
	}

	public void setZrbmLxdh(String zrbmLxdh) {
		this.zrbmLxdh = zrbmLxdh;
	}

	public String getZrbmXzqhDm() {
		return zrbmXzqhDm;
	}

	public void setZrbmXzqhDm(String zrbmXzqhDm) {
		this.zrbmXzqhDm = zrbmXzqhDm;
	}

	public String getZrbmXzqhMc() {
		return zrbmXzqhMc;
	}

	public void setZrbmXzqhMc(String zrbmXzqhMc) {
		this.zrbmXzqhMc = zrbmXzqhMc;
	}

}
