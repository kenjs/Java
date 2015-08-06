package com.cy.common.bo;

import java.io.Serializable;

/**
 * The persistent class For XT_LOG_LOGIN is created by tools.
 * 
 * @author HJH
 */

public class XtLogLogin implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long logXh; 				// 日志序号(SEQ_LOG_XH)
	private String xtyhflDm;	        //系统用户分类代码
	private String czyDjXh; 			// 操作员序号
	private String dlsj; 				// 登录时间
	private String ip; 					// IP地址
	private String mac;					//mac地址
	private String sessionId; 			// SESSION
	private String serverip; 			// serverip
	private String serverport; 			// serverport
	private String qtxx;

	public XtLogLogin() {

	}

	public String getServerip() {
		return serverip;
	}

	public void setServerip(String serverip) {
		this.serverip = serverip;
	}

	public String getServerport() {
		return serverport;
	}

	public void setServerport(String serverport) {
		this.serverport = serverport;
	}

	// 获取日志序号(SEQ_LOG_XH)
	public Long getLogXh() {
		return this.logXh;
	}

	// 设置日志序号(SEQ_LOG_XH)
	public void setLogXh(Long logXh) {
		this.logXh = logXh;
	}

	// 获取操作员序号
	public String getCzyDjXh() {
		return this.czyDjXh;
	}

	// 设置操作员序号
	public void setCzyDjXh(String czyDjXh) {
		this.czyDjXh = czyDjXh;
	}

	// 获取登录时间
	public String getDlsj() {
		return this.dlsj;
	}

	// 设置登录时间
	public void setDlsj(String dlsj) {
		this.dlsj = dlsj;
	}

	// 获取IP地址
	public String getIp() {
		return this.ip;
	}

	// 设置IP地址
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getXtyhflDm() {
		return xtyhflDm;
	}

	public void setXtyhflDm(String xtyhflDm) {
		this.xtyhflDm = xtyhflDm;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getQtxx() {
		return qtxx;
	}

	public void setQtxx(String qtxx) {
		this.qtxx = qtxx;
	}

}