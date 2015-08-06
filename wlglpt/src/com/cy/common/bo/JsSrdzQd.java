package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_SRDZ_QD is created by tools.
 * @author HJH
 */

public class JsSrdzQd  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // 清单登记序号(SEQ_QD_DJXH)
	private String khDjxh;                           // 客户登记序号
	private String qdmc;                             // 清单名称
	private String dzqdHzfsDm;                       // 对帐清单汇总方式代码
	private Double heJe;                             // 合计金额
	private Double yfJe;                             // 已付金额
	private Double wfJe;                             // 未付金额
	private Double ysqKpJe;                             // 已申请开票金额
	private Double wsqKpJe;                             // 未申请开票金额
	private String djrCzyDjxh;                       // 登记人
	private String djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String sfKpBz;                           //是否开票标志(Y/N)
	private String jsdw;							//结算单位
	public JsSrdzQd() {
	}

	//获取清单登记序号(SEQ_QD_DJXH)
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//设置清单登记序号(SEQ_QD_DJXH)
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	//获取客户登记序号
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置客户登记序号
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取清单名称
	public String getQdmc() {
		return this.qdmc;
	}

	//设置清单名称
	public void setQdmc(String qdmc) {
		this.qdmc=qdmc;
	}

	//获取对帐清单汇总方式代码
	public String getDzqdHzfsDm() {
		return this.dzqdHzfsDm;
	}

	//设置对帐清单汇总方式代码
	public void setDzqdHzfsDm(String dzqdHzfsDm) {
		this.dzqdHzfsDm=dzqdHzfsDm;
	}

	//获取合计金额
	public Double getHeJe() {
		return this.heJe;
	}

	//设置合计金额
	public void setHeJe(Double heJe) {
		this.heJe=heJe;
	}

	//获取已付金额
	public Double getYfJe() {
		return this.yfJe;
	}

	//设置已付金额
	public void setYfJe(Double yfJe) {
		this.yfJe=yfJe;
	}

	//获取未付金额
	public Double getWfJe() {
		return this.wfJe;
	}

	//设置未付金额
	public void setWfJe(Double wfJe) {
		this.wfJe=wfJe;
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

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	public Double getWsqKpJe() {
		return wsqKpJe;
	}

	public void setWsqKpJe(Double wsqKpJe) {
		this.wsqKpJe = wsqKpJe;
	}

	public Double getYsqKpJe() {
		return ysqKpJe;
	}

	public void setYsqKpJe(Double ysqKpJe) {
		this.ysqKpJe = ysqKpJe;
	}

	public String getSfKpBz() {
		return sfKpBz;
	}

	public void setSfKpBz(String sfKpBz) {
		this.sfKpBz = sfKpBz;
	}

	public String getJsdw() {
		return jsdw;
	}

	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
	}
}