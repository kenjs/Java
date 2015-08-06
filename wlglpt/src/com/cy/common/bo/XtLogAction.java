package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For XT_LOG_ACTION is created by tools.
 * @author HJH
 */

public class XtLogAction  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long logXh;                              // ��־���(SEQ_LOG_XH)
	private String xtyhflDm;						 //ϵͳ�û��������
	private String czyDjXh;                          // ����Ա���
	private Long xtmlXh;                             //ϵͳĿ¼���
	private String gnmkDm;	                         //����ģ�����
	private String action;                           // ����URL
	private String kssj;                             // ��ʼʱ��
	private String jssj;                             // ����ʱ��
	private Double timeUsed;                         // ��ʱ(��)
	private String sessionId;                          // SESSION
	private String serverip;                         // serverip
	private String serverport;                       // serverport
	private String qtxx;


	public XtLogAction() {
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

	//��ȡ��־���(SEQ_LOG_XH)
	public Long getLogXh() {
		return this.logXh;
	}

	//������־���(SEQ_LOG_XH)
	public void setLogXh(Long logXh) {
		this.logXh=logXh;
	}

	//��ȡ����Ա���
	public String getCzyDjXh() {
		return this.czyDjXh;
	}

	//���ò���Ա���
	public void setCzyDjXh(String czyDjXh) {
		this.czyDjXh=czyDjXh;
	}

	//��ȡ����URL
	public String getAction() {
		return this.action;
	}

	//���ò���URL
	public void setAction(String action) {
		this.action=action;
	}

	//��ȡ��ʼʱ��
	public String getKssj() {
		return this.kssj;
	}

	//���ÿ�ʼʱ��
	public void setKssj(String kssj) {
		this.kssj=kssj;
	}

	//��ȡ����ʱ��
	public String getJssj() {
		return this.jssj;
	}

	//���ý���ʱ��
	public void setJssj(String jssj) {
		this.jssj=jssj;
	}

	//��ȡ��ʱ(��)
	public Double getTimeUsed() {
		return this.timeUsed;
	}

	//������ʱ(��)
	public void setTimeUsed(Double timeUsed) {
		this.timeUsed=timeUsed;
	}

	public String getXtyhflDm() {
		return xtyhflDm;
	}

	public void setXtyhflDm(String xtyhflDm) {
		this.xtyhflDm = xtyhflDm;
	}

	public Long getXtmlXh() {
		return xtmlXh;
	}

	public void setXtmlXh(Long xtmlXh) {
		this.xtmlXh = xtmlXh;
	}

	public String getGnmkDm() {
		return gnmkDm;
	}

	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm = gnmkDm;
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