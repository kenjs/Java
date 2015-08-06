package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_FYXM_WH is created by tools.
 * @author HJH
 */

public class CwFyxmWh  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String ssJgbm;                           // 所属机构(总公司)
	private String fylbCwDjxh;                       // 费用类别_财务登记序号
	private String fyxmMc;                           // 费用项目名称
	private String splcXmflDjxh;                     // 审批流程_项目分类登记序号
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public CwFyxmWh() {
	}

	//获取财务登记序号(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//设置财务登记序号(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//获取所属机构(总公司)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构(总公司)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取费用类别_财务登记序号
	public String getFylbCwDjxh() {
		return this.fylbCwDjxh;
	}

	//设置费用类别_财务登记序号
	public void setFylbCwDjxh(String fylbCwDjxh) {
		this.fylbCwDjxh=fylbCwDjxh;
	}

	//获取费用项目名称
	public String getFyxmMc() {
		return this.fyxmMc;
	}

	//设置费用项目名称
	public void setFyxmMc(String fyxmMc) {
		this.fyxmMc=fyxmMc;
	}

	//获取审批流程_项目分类登记序号
	public String getSplcXmflDjxh() {
		return this.splcXmflDjxh;
	}

	//设置审批流程_项目分类登记序号
	public void setSplcXmflDjxh(String splcXmflDjxh) {
		this.splcXmflDjxh=splcXmflDjxh;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取创建人
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//设置创建人
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//获取创建日期
	public String getCjrq() {
		return this.cjrq;
	}

	//设置创建日期
	public void setCjrq(String cjrq) {
		this.cjrq=cjrq;
	}

	//获取修改人
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//设置修改人
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//获取修改日期
	public String getXgrq() {
		return this.xgrq;
	}

	//设置修改日期
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}
}