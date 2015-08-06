package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For XT_LOG_EXCEPTION is created by tools.
 * @author HJH
 */

public class XtLogException  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long logXh;                              // ��־���(SEQ_LOG_XH)
	private String xtyhflDm;						 //ϵͳ�û��������
	private String czyDjXh;                          // ����Ա���
	private Long xtmlXh;							 //ϵͳĿ¼���
	private String gnmkDm;							 //����ģ�����
	private String action;                           // ����URL
	private String cssj;                             // ����ʱ��
	private String cwxx;                             // ������Ϣ	
	private String serverip;                          // serverip
	private String serverport;                          // serverport
	private String qtxx;						      //������Ϣ


	public XtLogException() {
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

	public String getXtyhflDm() {
		return xtyhflDm;
	}


	public void setXtyhflDm(String xtyhflDm) {
		this.xtyhflDm = xtyhflDm;
	}


	//��ȡ����ʱ��
	public String getCssj() {
		return this.cssj;
	}

	//���ò���ʱ��
	public void setCssj(String cssj) {
		this.cssj=cssj;
	}

	//��ȡ������Ϣ
	public String getCwxx() {
		return this.cwxx;
	}

	//���ô�����Ϣ
	public void setCwxx(String cwxx) {
		this.cwxx=cwxx;
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


	public String getQtxx() {
		return qtxx;
	}


	public void setQtxx(String qtxx) {
		this.qtxx = qtxx;
	}
}