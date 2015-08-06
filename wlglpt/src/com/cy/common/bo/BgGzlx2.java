package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For BG_GZLX2 is created by tools.
 * @author anq
 */

public class BgGzlx2  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long jstxXh;                           // 即时通讯序号(SEQ_GZLX_XH)
	private String nr;                               // 内容
	private String fsrCzyDjxh;                       // 发送人
	private Date fsrq;                             // 发送日期
	private String czyDjxh;                          // 接收人
	private String xtyhflDm;                         // 系统用户分类代码
	private String jsbz;                             // 接收标志(Y/N)
	private Date jsrq;                             // 接收日期

	public BgGzlx2() {
	}

	//获取即时通讯序号(SEQ_GZLX_XH)
	public Long getJstxXh() {
		return this.jstxXh;
	}

	//设置即时通讯序号(SEQ_GZLX_XH)
	public void setJstxXh(Long jstxXh) {
		this.jstxXh=jstxXh;
	}

	//获取内容
	public String getNr() {
		return this.nr;
	}

	//设置内容
	public void setNr(String nr) {
		this.nr=nr;
	}

	//获取发送人
	public String getFsrCzyDjxh() {
		return this.fsrCzyDjxh;
	}

	//设置发送人
	public void setFsrCzyDjxh(String fsrCzyDjxh) {
		this.fsrCzyDjxh=fsrCzyDjxh;
	}

	//获取发送日期
	public Date getFsrq() {
		return this.fsrq;
	}

	//设置发送日期
	public void setFsrq(Date fsrq) {
		this.fsrq=fsrq;
	}

	//获取接收人
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置接收人
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//获取系统用户分类代码
	public String getXtyhflDm() {
		return this.xtyhflDm;
	}

	//设置系统用户分类代码
	public void setXtyhflDm(String xtyhflDm) {
		this.xtyhflDm=xtyhflDm;
	}

	//获取接收标志(Y/N)
	public String getJsbz() {
		return this.jsbz;
	}

	//设置接收标志(Y/N)
	public void setJsbz(String jsbz) {
		this.jsbz=jsbz;
	}

	//获取接收日期
	public Date getJsrq() {
		return this.jsrq;
	}

	//设置接收日期
	public void setJsrq(Date jsrq) {
		this.jsrq=jsrq;
	}
}