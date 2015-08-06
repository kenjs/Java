package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For XT_LOG_ACTION is created by tools.
 * @author HJH
 */

public class XtLogAction  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long logXh;                              // 日志序号(SEQ_LOG_XH)
	private String xtyhflDm;						 //系统用户分类代码
	private String czyDjXh;                          // 操作员序号
	private Long xtmlXh;                             //系统目录序号
	private String gnmkDm;	                         //功能模块代码
	private String action;                           // 操作URL
	private String kssj;                             // 开始时间
	private String jssj;                             // 结束时间
	private Double timeUsed;                         // 用时(秒)
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

	//获取日志序号(SEQ_LOG_XH)
	public Long getLogXh() {
		return this.logXh;
	}

	//设置日志序号(SEQ_LOG_XH)
	public void setLogXh(Long logXh) {
		this.logXh=logXh;
	}

	//获取操作员序号
	public String getCzyDjXh() {
		return this.czyDjXh;
	}

	//设置操作员序号
	public void setCzyDjXh(String czyDjXh) {
		this.czyDjXh=czyDjXh;
	}

	//获取操作URL
	public String getAction() {
		return this.action;
	}

	//设置操作URL
	public void setAction(String action) {
		this.action=action;
	}

	//获取开始时间
	public String getKssj() {
		return this.kssj;
	}

	//设置开始时间
	public void setKssj(String kssj) {
		this.kssj=kssj;
	}

	//获取结束时间
	public String getJssj() {
		return this.jssj;
	}

	//设置结束时间
	public void setJssj(String jssj) {
		this.jssj=jssj;
	}

	//获取用时(秒)
	public Double getTimeUsed() {
		return this.timeUsed;
	}

	//设置用时(秒)
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