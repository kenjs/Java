package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_GZLX_JSR is created by tools.
 * @author HJH
 */

public class BgGzlxJsr  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gzlxXh;                           // 工作联系序号(SEQ_GZLX_XH)
	private String czyDjxh;                          // 操作员登记序号
	private Long xtyhflDm;                           // 系统用户分类代码
	private String ckbz;                             // 查看标志(Y/N)
	private String ckrq;                             // 查看日期(到时分秒)

	public BgGzlxJsr() {
	}

	//获取工作联系序号(SEQ_GZLX_XH)
	public String getGzlxXh() {
		return this.gzlxXh;
	}

	//设置工作联系序号(SEQ_GZLX_XH)
	public void setGzlxXh(String gzlxXh) {
		this.gzlxXh=gzlxXh;
	}

	//获取操作员登记序号
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置操作员登记序号
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//获取系统用户分类代码
	public Long getXtyhflDm() {
		return this.xtyhflDm;
	}

	//设置系统用户分类代码
	public void setXtyhflDm(Long xtyhflDm) {
		this.xtyhflDm=xtyhflDm;
	}

	//获取查看标志(Y/N)
	public String getCkbz() {
		return this.ckbz;
	}

	//设置查看标志(Y/N)
	public void setCkbz(String ckbz) {
		this.ckbz=ckbz;
	}

	//获取查看日期(到时分秒)
	public String getCkrq() {
		return this.ckrq;
	}

	//设置查看日期(到时分秒)
	public void setCkrq(String ckrq) {
		this.ckrq=ckrq;
	}
}