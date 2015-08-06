package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_ZLGX_FJ is created by tools.
 * @author HJH
 */

public class BgZlgxFj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zlgxDjxh;                         // 资料共享登记序号(SEQ_BG_DJXH)
	private String xh;                               // 序号
	private String fjmc;                             // 附件名称
	private byte[] fjnr;                             // 附件内容
	private String yxbz;                             // 有效标志(Y/N)

	public BgZlgxFj() {
	}

	//获取资料共享登记序号(SEQ_BG_DJXH)
	public String getZlgxDjxh() {
		return this.zlgxDjxh;
	}

	//设置资料共享登记序号(SEQ_BG_DJXH)
	public void setZlgxDjxh(String zlgxDjxh) {
		this.zlgxDjxh=zlgxDjxh;
	}

	//获取序号
	public String getXh() {
		return this.xh;
	}

	//设置序号
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取附件名称
	public String getFjmc() {
		return this.fjmc;
	}

	//设置附件名称
	public void setFjmc(String fjmc) {
		this.fjmc=fjmc;
	}

	//获取附件内容
	public byte[] getFjnr() {
		return this.fjnr;
	}

	//设置附件内容
	public void setFjnr(byte[] fjnr) {
		this.fjnr=fjnr;
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