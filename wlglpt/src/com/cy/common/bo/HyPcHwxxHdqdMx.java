package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_PC_HWXX_HDQD_MX is created by tools.
 * @author HJH
 */

public class HyPcHwxxHdqdMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hdqdDjxh;                         // 回单清单登记序号
	private String hdDjxh;                           // 回单登记序号
	private String jszt;                             // 接收状态(0 初始，1 发送，2 接收，3 退回)
	private String fsGsbm;                           // 发送公司编码
	private String jsGsbm;                           // 接收公司编码
	private String dqztbz;
	
	public HyPcHwxxHdqdMx() {
	}

	//获取回单清单登记序号
	public String getHdqdDjxh() {
		return this.hdqdDjxh;
	}

	//设置回单清单登记序号
	public void setHdqdDjxh(String hdqdDjxh) {
		this.hdqdDjxh=hdqdDjxh;
	}

	//获取回单登记序号
	public String getHdDjxh() {
		return this.hdDjxh;
	}

	//设置回单登记序号
	public void setHdDjxh(String hdDjxh) {
		this.hdDjxh=hdDjxh;
	}

	//获取接收状态(0 初始，1 发送，2 接收，3 退回)
	public String getJszt() {
		return this.jszt;
	}

	//设置接收状态(0 初始，1 发送，2 接收，3 退回)
	public void setJszt(String jszt) {
		this.jszt=jszt;
	}

	//获取发送公司编码
	public String getFsGsbm() {
		return this.fsGsbm;
	}

	//设置发送公司编码
	public void setFsGsbm(String fsGsbm) {
		this.fsGsbm=fsGsbm;
	}

	//获取接收公司编码
	public String getJsGsbm() {
		return this.jsGsbm;
	}

	//设置接收公司编码
	public void setJsGsbm(String jsGsbm) {
		this.jsGsbm=jsGsbm;
	}

	public String getDqztbz() {
		return dqztbz;
	}

	public void setDqztbz(String dqztbz) {
		this.dqztbz = dqztbz;
	}
}