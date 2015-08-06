package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_HBZCYE is created by tools.
 * @author HJH
 */

public class CwHbzcye  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String ssJgbm;                           // 所属机构
	private String zcflDm;                           // 资产分类代码
	private String yhCshDjxh;                        // 银行初始化登记序号
	private Double je;                               // 金额
	private String yxbz;
	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public CwHbzcye() {
	}

	//获取财务登记序号(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//设置财务登记序号(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取资产分类代码
	public String getZcflDm() {
		return this.zcflDm;
	}

	//设置资产分类代码
	public void setZcflDm(String zcflDm) {
		this.zcflDm=zcflDm;
	}

	//获取银行初始化登记序号
	public String getYhCshDjxh() {
		return this.yhCshDjxh;
	}

	//设置银行初始化登记序号
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh=yhCshDjxh;
	}

	//获取金额
	public Double getJe() {
		return this.je;
	}

	//设置金额
	public void setJe(Double je) {
		this.je=je;
	}
}