package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_TZGG_YYJL is created by tools.
 * @author HJH
 */

public class BgTzggYyjl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String tzggXh;                           // 通知公告序号
	private String czyDjxh;                          // 操作员登记序号
	private String ckrq;                             // 查看日期(到时分秒)

	public BgTzggYyjl() {
	}

	//获取通知公告序号
	public String getTzggXh() {
		return this.tzggXh;
	}

	//设置通知公告序号
	public void setTzggXh(String tzggXh) {
		this.tzggXh=tzggXh;
	}

	//获取操作员登记序号
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置操作员登记序号
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
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