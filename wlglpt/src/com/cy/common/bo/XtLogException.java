package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For XT_LOG_EXCEPTION is created by tools.
 * @author HJH
 */

public class XtLogException  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long logXh;                              // 日志序号(SEQ_LOG_XH)
	private String xtyhflDm;						 //系统用户分类代码
	private String czyDjXh;                          // 操作员序号
	private Long xtmlXh;							 //系统目录序号
	private String gnmkDm;							 //功能模块代码
	private String action;                           // 操作URL
	private String cssj;                             // 产生时间
	private String cwxx;                             // 错误信息	
	private String serverip;                          // serverip
	private String serverport;                          // serverport
	private String qtxx;						      //其他信息


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


	//获取日志序号(SEQ_LOG_XH)
	public Long getLogXh() {
		return this.logXh;
	}

	//设置日志序号(SEQ_LOG_XH)
	public void setLogXh(Long logXh) {
		this.logXh=logXh;
	}

	public String getXtyhflDm() {
		return xtyhflDm;
	}


	public void setXtyhflDm(String xtyhflDm) {
		this.xtyhflDm = xtyhflDm;
	}


	//获取产生时间
	public String getCssj() {
		return this.cssj;
	}

	//设置产生时间
	public void setCssj(String cssj) {
		this.cssj=cssj;
	}

	//获取错误信息
	public String getCwxx() {
		return this.cwxx;
	}

	//设置错误信息
	public void setCwxx(String cwxx) {
		this.cwxx=cwxx;
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