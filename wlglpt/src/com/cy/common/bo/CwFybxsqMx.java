package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_FYBXSQ_MX is created by tools.
 * @author HJH
 */

public class CwFybxsqMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String xh;                               // 明细序号
	private String fylbCwDjxh;                       // 费用类别_财务登记序号
	private String fyxmCwDjxh;                       // 费用项目_财务登记序号
	private Double fyje;                             // 费用金额
	private Double bxje;                             // 报销金额
	private String bz;                               // 备注
	private String yxbz;                             // 有效标志(Y/N)

	public CwFybxsqMx() {
	}

	//获取财务登记序号(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//设置财务登记序号(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//获取明细序号
	public String getXh() {
		return this.xh;
	}

	//设置明细序号
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取费用类别_财务登记序号
	public String getFylbCwDjxh() {
		return this.fylbCwDjxh;
	}

	//设置费用类别_财务登记序号
	public void setFylbCwDjxh(String fylbCwDjxh) {
		this.fylbCwDjxh=fylbCwDjxh;
	}

	//获取费用项目_财务登记序号
	public String getFyxmCwDjxh() {
		return this.fyxmCwDjxh;
	}

	//设置费用项目_财务登记序号
	public void setFyxmCwDjxh(String fyxmCwDjxh) {
		this.fyxmCwDjxh=fyxmCwDjxh;
	}

	//获取费用金额
	public Double getFyje() {
		return this.fyje;
	}

	//设置费用金额
	public void setFyje(Double fyje) {
		this.fyje=fyje;
	}

	//获取报销金额
	public Double getBxje() {
		return this.bxje;
	}

	//设置报销金额
	public void setBxje(Double bxje) {
		this.bxje=bxje;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}
}