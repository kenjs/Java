package com.cy.common.domain;

import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The DOMAIN class FOR XT_XTCS is created by tools.
 * @author HJH
 */

public class XtXtcsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String csxh;                             // 参数编码(规则：首位为参数类别代码)
	private String csmc;                             // 参数名称
	private String sysm;                             // 备注说明
	private String csmrz;                            // 参数默认值
	private String cslbDm;                           // 参数类别代码
	private String yxbz;                             // 有效标志(Y/N)
	
	
	private String csz1;								 //参数值（显示）
	private String csz2;								 //参数值（显示）
	
	private String oldDlmm;							//登录密码
	private String newDlmm;							//登录密码


	public XtXtcsDomain() {
	}

	//获取参数编码(规则：首位为参数类别代码)
	public String getCsxh() {
		return this.csxh;
	}

	//设置参数编码(规则：首位为参数类别代码)
	public void setCsxh(String csxh) {
		this.csxh=csxh;
	}

	//获取参数名称
	public String getCsmc() {
		return this.csmc;
	}

	//设置参数名称
	public void setCsmc(String csmc) {
		this.csmc=csmc;
	}

	//获取备注说明
	public String getSysm() {
		return this.sysm;
	}

	//设置备注说明
	public void setSysm(String sysm) {
		this.sysm=sysm;
	}

	//获取参数默认值
	public String getCsmrz() {
		return this.csmrz;
	}

	//设置参数默认值
	public void setCsmrz(String csmrz) {
		this.csmrz=csmrz;
	}

	//获取参数类别代码
	public String getCslbDm() {
		return this.cslbDm;
	}

	//设置参数类别代码
	public void setCslbDm(String cslbDm) {
		this.cslbDm=cslbDm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	public String getCsz1() {
		return csz1;
	}

	public void setCsz1(String csz1) {
		this.csz1 = csz1;
	}

	public String getCsz2() {
		return csz2;
	}

	public void setCsz2(String csz2) {
		this.csz2 = csz2;
	}

	public String getNewDlmm() {
		return newDlmm;
	}

	public void setNewDlmm(String newDlmm) {
		this.newDlmm = newDlmm;
	}

	public String getOldDlmm() {
		return oldDlmm;
	}

	public void setOldDlmm(String oldDlmm) {
		this.oldDlmm = oldDlmm;
	}

	
}