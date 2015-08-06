package com.cy.dcts.common.bo;

import java.io.Serializable;

public class DictInfo  implements Serializable {

	private static final long serialVersionUID = 3312190358905755378L;

	
	private String code;
	private String name;
	private String parentCode;
	
	public DictInfo() {
		super();
	}

	public DictInfo(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	public DictInfo(String code, String name, String parentCode) {
		super();
		this.code = code;
		this.name = name;
		this.parentCode = parentCode;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getParentCode() {
		return parentCode;
	}


	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}
