package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PC_HD is created by tools.
 * @author HJH
 */

public class HyPcHddj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hdDjxh;
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 派车货物序号(未发货登记序号)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物明细序号)
	private Double szHwSl;                           // 实装_货物_数量
	private Double szHwZl;                           // 实装_货物_重量
	private Double szHwTj;                           // 实装_货物_体积
	private Date yqDdrq;                           // 要求到达日期
	private String shfsDm;                           // 收货方式代码
	private Double szJsSl;                           // 实装_结算数量
	private String hdbh;                             // 回单编号(多个已逗号分隔)
	private Date hdjsrq;                           // 回单接收日期
	private String bz;                               // 备注
	private String spbz;
	private String yxbz;
	private String ssJgbm;
	private String djrCzyDjxh;
	private String djrq;
	public HyPcHddj() {
	}

	public String getHdDjxh() {
		return hdDjxh;
	}

	public void setHdDjxh(String hdDjxh) {
		this.hdDjxh = hdDjxh;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取未发货登记序号
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置未发货登记序号
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取回单编号
	public String getHdbh() {
		return this.hdbh;
	}

	//设置回单编号
	public void setHdbh(String hdbh) {
		this.hdbh=hdbh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取序号(货物或计划单明细序号)
	public String getXh() {
		return this.xh;
	}

	//设置序号(货物或计划单明细序号)
	public void setXh(String xh) {
		this.xh=xh;
	}

	public Double getSzHwSl() {
		return szHwSl;
	}

	public void setSzHwSl(Double szHwSl) {
		this.szHwSl = szHwSl;
	}

	public Double getSzHwZl() {
		return szHwZl;
	}

	public void setSzHwZl(Double szHwZl) {
		this.szHwZl = szHwZl;
	}

	public Double getSzHwTj() {
		return szHwTj;
	}

	public void setSzHwTj(Double szHwTj) {
		this.szHwTj = szHwTj;
	}

	public Date getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(Date yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public String getShfsDm() {
		return shfsDm;
	}

	public void setShfsDm(String shfsDm) {
		this.shfsDm = shfsDm;
	}

	public Double getSzJsSl() {
		return szJsSl;
	}

	public void setSzJsSl(Double szJsSl) {
		this.szJsSl = szJsSl;
	}

	public Date getHdjsrq() {
		return hdjsrq;
	}

	public void setHdjsrq(Date hdjsrq) {
		this.hdjsrq = hdjsrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}

	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}

	public String getDjrq() {
		return djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

}