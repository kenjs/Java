package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_RY_JS is created by tools.
 * @author HJH
 */

public class QyRyJs  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String czyDjxh;                          // 操作员登记序号(QY_RYDJ.CZY_DJXH)
	private String jsDjxh;                           // 角色登记序号(SEQ_JS_DJXH)

	public QyRyJs() {
	}

	//获取操作员登记序号(QY_RYDJ.CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置操作员登记序号(QY_RYDJ.CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//获取角色登记序号(SEQ_JS_DJXH)
	public String getJsDjxh() {
		return this.jsDjxh;
	}

	//设置角色登记序号(SEQ_JS_DJXH)
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh=jsDjxh;
	}
}