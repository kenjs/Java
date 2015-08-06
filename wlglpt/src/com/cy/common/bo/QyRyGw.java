package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_RY_GW is created by tools.
 * @author HJH
 */

public class QyRyGw  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String czyDjxh;                          // 操作员登记序号(QY_RYDJ.CZY_DJXH)
	private String gwDjxh;                           // 岗位登记序号
	private String ssJgbm;                           // 所属机构编码(部门)
	private String zjbz;                             // 主兼标志(Y主要部门/N兼职部门)
	private String qxJgbm;                           // 权限机构编码(数据权限)

	public QyRyGw() {
	}

	//获取操作员登记序号(QY_RYDJ.CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置操作员登记序号(QY_RYDJ.CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//获取岗位登记序号
	public String getGwDjxh() {
		return this.gwDjxh;
	}

	//设置岗位登记序号
	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh=gwDjxh;
	}

	//获取所属机构编码(部门)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构编码(部门)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取主兼标志(Y主要部门/N兼职部门)
	public String getZjbz() {
		return this.zjbz;
	}

	//设置主兼标志(Y主要部门/N兼职部门)
	public void setZjbz(String zjbz) {
		this.zjbz=zjbz;
	}

	//获取权限机构编码(数据权限)
	public String getQxJgbm() {
		return this.qxJgbm;
	}

	//设置权限机构编码(数据权限)
	public void setQxJgbm(String qxJgbm) {
		this.qxJgbm=qxJgbm;
	}
}