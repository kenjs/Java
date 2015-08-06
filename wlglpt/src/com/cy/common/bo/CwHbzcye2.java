package com.cy.common.bo;
import java.io.Serializable;

/**
 * 后改名为  财务-货币资产-变动记录 cw—hbzc—bdjl
 * The persistent class For CW_HBZCYE2 is created by tools.
 * @author HJH
 */

public class CwHbzcye2  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwbdDjxh;                         // 财务变动登记序号(SEQ_CW_DJXH)
	private String ssJgbm;                           // 所属机构
	private String zcflDm;                           // 资产分类代码
	private String yhCshDjxh;                        // 银行初始化登记序号
	private Double je;                               // 金额
	private String sm;                               // 说明
	private String jbrCzyDjxh;                       // 经办人
	private String rq;                               // 日期
	private String djJgbm;                           // 部门
	private String bz;
	private String ywxh;
	private String yxbz;
	
	public CwHbzcye2() {
	}

	//获取财务变动登记序号(SEQ_CW_DJXH)
	public String getCwbdDjxh() {
		return this.cwbdDjxh;
	}

	//设置财务变动登记序号(SEQ_CW_DJXH)
	public void setCwbdDjxh(String cwbdDjxh) {
		this.cwbdDjxh=cwbdDjxh;
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

	//获取说明
	public String getSm() {
		return this.sm;
	}

	//设置说明
	public void setSm(String sm) {
		this.sm=sm;
	}

	//获取经办人
	public String getJbrCzyDjxh() {
		return this.jbrCzyDjxh;
	}

	//设置经办人
	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh=jbrCzyDjxh;
	}

	//获取日期
	public String getRq() {
		return this.rq;
	}

	//设置日期
	public void setRq(String rq) {
		this.rq=rq;
	}

	//获取部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置部门
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getYwxh() {
		return ywxh;
	}

	public void setYwxh(String ywxh) {
		this.ywxh = ywxh;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
}