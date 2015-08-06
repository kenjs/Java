package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_HWXX_SHFSBG is created by tools.
 * @author HJH
 */

public class HyHwxxShfsbg  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String shbgDjxh;                         // 送货变更-登记序号
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物明细序号)
	private Double srHj;                             // 收入_小计
	private Double srYj;                             // 收入_月结
	private Double srXf;                             // 收入_现付
	private Double srHdf;                            // 收入_货到付
	private Double srThf;                            // 收入_提货付
	private Double srHf;                             // 收入_回单付
	private Double srHk;                             // 收入_回扣
	private Double srBjf;                            // 收入_保价费
	private Double srPsf;                            // 收入_配送费
	private Double srYsf;                            // 收入_运输费
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期

	public HyHwxxShfsbg() {
	}

	//获取送货变更-登记序号
	public String getShbgDjxh() {
		return this.shbgDjxh;
	}

	//设置送货变更-登记序号
	public void setShbgDjxh(String shbgDjxh) {
		this.shbgDjxh=shbgDjxh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取序号(货物明细序号)
	public String getXh() {
		return this.xh;
	}

	//设置序号(货物明细序号)
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取收入_小计
	public Double getSrHj() {
		return this.srHj;
	}

	//设置收入_小计
	public void setSrHj(Double srHj) {
		this.srHj=srHj;
	}

	//获取收入_月结
	public Double getSrYj() {
		return this.srYj;
	}

	//设置收入_月结
	public void setSrYj(Double srYj) {
		this.srYj=srYj;
	}

	//获取收入_现付
	public Double getSrXf() {
		return this.srXf;
	}

	//设置收入_现付
	public void setSrXf(Double srXf) {
		this.srXf=srXf;
	}

	//获取收入_货到付
	public Double getSrHdf() {
		return this.srHdf;
	}

	//设置收入_货到付
	public void setSrHdf(Double srHdf) {
		this.srHdf=srHdf;
	}

	//获取收入_提货付
	public Double getSrThf() {
		return this.srThf;
	}

	//设置收入_提货付
	public void setSrThf(Double srThf) {
		this.srThf=srThf;
	}

	//获取收入_回单付
	public Double getSrHf() {
		return this.srHf;
	}

	//设置收入_回单付
	public void setSrHf(Double srHf) {
		this.srHf=srHf;
	}

	//获取收入_回扣
	public Double getSrHk() {
		return this.srHk;
	}

	//设置收入_回扣
	public void setSrHk(Double srHk) {
		this.srHk=srHk;
	}

	//获取收入_保价费
	public Double getSrBjf() {
		return this.srBjf;
	}

	//设置收入_保价费
	public void setSrBjf(Double srBjf) {
		this.srBjf=srBjf;
	}

	//获取收入_配送费
	public Double getSrPsf() {
		return this.srPsf;
	}

	//设置收入_配送费
	public void setSrPsf(Double srPsf) {
		this.srPsf=srPsf;
	}

	//获取收入_运输费
	public Double getSrYsf() {
		return this.srYsf;
	}

	//设置收入_运输费
	public void setSrYsf(Double srYsf) {
		this.srYsf=srYsf;
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
}