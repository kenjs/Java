package com.cy.common.bo;

import java.io.Serializable;

public class YysDmUserBcxx implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long czryDm;                           // 操作人员代码(YHBZ=Y为CTAIS中的CZRY_DM,否则SEQ_CZY_XH)
	private String czryMc;                           // 操作人员名称
	private String name;                             // 登录用户名
	private String password;                         // 登录密码
	private String yhsm;                             // 用户说明
	private String yhbz;                             // CTAIS用户标志(Y/N)
	private String swjgDm;                           // 税务机关代码
	private String cjglybz;                          // 超级管理员标志(Y/N)
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)

	public YysDmUserBcxx() {
	}

	

	public Long getCzryDm() {
		return czryDm;
	}



	public void setCzryDm(Long czryDm) {
		this.czryDm = czryDm;
	}



	public String getCzryMc() {
		return this.czryMc;
	}

	public void setCzryMc(String czryMc) {
		this.czryMc = czryMc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQybz() {
		return this.qybz;
	}

	public void setQybz(String qybz) {
		this.qybz = qybz;
	}

	

	public String getSwjgDm() {
		return this.swjgDm;
	}

	public void setSwjgDm(String swjgDm) {
		this.swjgDm = swjgDm;
	}

	public String getYhbz() {
		return this.yhbz;
	}

	public void setYhbz(String yhbz) {
		this.yhbz = yhbz;
	}


	public String getCjglybz() {
		return cjglybz;
	}

	public void setCjglybz(String cjglybz) {
		this.cjglybz = cjglybz;
	}

	public String getYhsm() {
		return this.yhsm;
	}

	public void setYhsm(String yhsm) {
		this.yhsm = yhsm;
	}

	public String getYxbz() {
		return this.yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
}