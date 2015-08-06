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
	private Long logXh; 				// ��־���(SEQ_LOG_XH)
	private String xtyhflDm;	        //ϵͳ�û��������
	private String czyDjXh; 			// ����Ա���
	private String dlsj; 				// ��¼ʱ��
	private String ip; 					// IP��ַ
	private String mac;					//mac��ַ
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

	// ��ȡ��־���(SEQ_LOG_XH)
	public Long getLogXh() {
		return this.logXh;
	}

	// ������־���(SEQ_LOG_XH)
	public void setLogXh(Long logXh) {
		this.logXh = logXh;
	}

	// ��ȡ����Ա���
	public String getCzyDjXh() {
		return this.czyDjXh;
	}

	// ���ò���Ա���
	public void setCzyDjXh(String czyDjXh) {
		this.czyDjXh = czyDjXh;
	}

	// ��ȡ��¼ʱ��
	public String getDlsj() {
		return this.dlsj;
	}

	// ���õ�¼ʱ��
	public void setDlsj(String dlsj) {
		this.dlsj = dlsj;
	}

	// ��ȡIP��ַ
	public String getIp() {
		return this.ip;
	}

	// ����IP��ַ
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