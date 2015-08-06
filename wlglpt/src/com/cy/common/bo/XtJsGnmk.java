package com.cy.common.bo;


import java.io.Serializable;

/**
 * The persistent class For XT_JS_GNMK is created by tools.
 * @author HJH
 */

public class XtJsGnmk  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsDm;                             // 角色代码
	private String gnmkDm;                           // 功能模块代码
	private String xtml;           					 //系统目录
	public String getXtml() {
		return xtml;
	}

	public void setXtml(String xtml) {
		this.xtml = xtml;
	}

	public XtJsGnmk() {
	}

	//获取角色代码
	public String getJsDm() {
		return this.jsDm;
	}

	//设置角色代码
	public void setJsDm(String jsDm) {
		this.jsDm=jsDm;
	}

	//获取功能模块代码
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	//设置功能模块代码
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm=gnmkDm;
	}
}