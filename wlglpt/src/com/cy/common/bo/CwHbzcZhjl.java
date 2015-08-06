package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_HBZC_ZHJL is created by tools.
 * @author HJH
 */

public class CwHbzcZhjl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String ssJgbm;                           // 所属机构
	private String oldZcflDm;                        // 原-资产分类代码
	private String oldYhCshDjxh;                     // 原-银行初始化登记序号
	private String newZcflDm;                        // 目标-资产分类代码
	private String newYhCshDjxh;                     // 目标-银行初始化登记序号
	private Double zhje;                             // 转换金额
	private String pzh;                              // 凭证号
	private String bzsm;                             // 备注说明
	private String yxbz;                             // 有效标志(Y/N)
	private String djrCzyDjxh;                       // 登记人
	private String djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门

	public CwHbzcZhjl() {
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

	//获取原-资产分类代码
	public String getOldZcflDm() {
		return this.oldZcflDm;
	}

	//设置原-资产分类代码
	public void setOldZcflDm(String oldZcflDm) {
		this.oldZcflDm=oldZcflDm;
	}

	//获取原-银行初始化登记序号
	public String getOldYhCshDjxh() {
		return this.oldYhCshDjxh;
	}

	//设置原-银行初始化登记序号
	public void setOldYhCshDjxh(String oldYhCshDjxh) {
		this.oldYhCshDjxh=oldYhCshDjxh;
	}

	//获取目标-资产分类代码
	public String getNewZcflDm() {
		return this.newZcflDm;
	}

	//设置目标-资产分类代码
	public void setNewZcflDm(String newZcflDm) {
		this.newZcflDm=newZcflDm;
	}

	//获取目标-银行初始化登记序号
	public String getNewYhCshDjxh() {
		return this.newYhCshDjxh;
	}

	//设置目标-银行初始化登记序号
	public void setNewYhCshDjxh(String newYhCshDjxh) {
		this.newYhCshDjxh=newYhCshDjxh;
	}

	//获取转换金额
	public Double getZhje() {
		return this.zhje;
	}

	//设置转换金额
	public void setZhje(Double zhje) {
		this.zhje=zhje;
	}

	//获取凭证号
	public String getPzh() {
		return this.pzh;
	}

	//设置凭证号
	public void setPzh(String pzh) {
		this.pzh=pzh;
	}

	//获取备注说明
	public String getBzsm() {
		return this.bzsm;
	}

	//设置备注说明
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取登记人
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//设置登记人
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//获取登记日期
	public String getDjrq() {
		return this.djrq;
	}

	//设置登记日期
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//获取登记部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置登记部门
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}
}