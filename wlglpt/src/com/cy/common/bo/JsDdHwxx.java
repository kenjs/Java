package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_DD_HWXX is created by tools.
 * @author HJH
 */

public class JsDdHwxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsDjxh;                           // 结算登记序号(SEQ_JS_DJXH)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 货物明细序号
	private String hwmc;                             // 货物名称
	private String hwDjxh;                           // 货物登记序号
	private String hwxhDjxh;                         // 货物型号登记序号
	private String hwBzHldwDm;                       // 货物_包装_计量单位
	private Double hwSl;                             // 货物_数量
	private String hwSlJldwDm;                       // 货物_数量_计量单位
	private Double hwZl;                             // 货物_重量
	private String hwZlJldwDm;                       // 货物_重量_计量单位
	private Double hwTj;                             // 货物_体积
	private String hwTjJldwDm;                       // 货物_体积_计量单位
	private Double jsSl;                             // 结算数量
	private String jsJldwDm;                         // 结算计量单位
	private String jsJldwFlDm;                       // 结算计量单位分类代码
	private String hdbh;                             // 回单编号
	private String sfdXzqhDm;                        // 始发地_行政区划代码
	private String mddXzqhDm;                        // 目的地_行政区划代码
	private Double dzSr;                             // 对帐_收入
	private Double dzYj;                             // 对帐_已结
	private Double dzWj;                             // 对帐_未结
	private String yxbz;                             // 有效标志(Y/N)
	private String dcjsbz;                           // 多次结算标志(Y/N)
	private String qcDzDjxh;                         // 前次对帐登记序号
	private String dzztDm;                           // 对帐状态代码
	private String dzDjxh;                           // 对帐登记序号

	public JsDdHwxx() {
	}

	//获取结算登记序号(SEQ_JS_DJXH)
	public String getJsDjxh() {
		return this.jsDjxh;
	}

	//设置结算登记序号(SEQ_JS_DJXH)
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh=jsDjxh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取货物明细序号
	public String getXh() {
		return this.xh;
	}

	//设置货物明细序号
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取货物名称
	public String getHwmc() {
		return this.hwmc;
	}

	//设置货物名称
	public void setHwmc(String hwmc) {
		this.hwmc=hwmc;
	}

	//获取货物登记序号
	public String getHwDjxh() {
		return this.hwDjxh;
	}

	//设置货物登记序号
	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh=hwDjxh;
	}

	//获取货物型号登记序号
	public String getHwxhDjxh() {
		return this.hwxhDjxh;
	}

	//设置货物型号登记序号
	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh=hwxhDjxh;
	}

	//获取货物_包装_计量单位
	public String getHwBzHldwDm() {
		return this.hwBzHldwDm;
	}

	//设置货物_包装_计量单位
	public void setHwBzHldwDm(String hwBzHldwDm) {
		this.hwBzHldwDm=hwBzHldwDm;
	}

	//获取货物_数量
	public Double getHwSl() {
		return this.hwSl;
	}

	//设置货物_数量
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//获取货物_数量_计量单位
	public String getHwSlJldwDm() {
		return this.hwSlJldwDm;
	}

	//设置货物_数量_计量单位
	public void setHwSlJldwDm(String hwSlJldwDm) {
		this.hwSlJldwDm=hwSlJldwDm;
	}

	//获取货物_重量
	public Double getHwZl() {
		return this.hwZl;
	}

	//设置货物_重量
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//获取货物_重量_计量单位
	public String getHwZlJldwDm() {
		return this.hwZlJldwDm;
	}

	//设置货物_重量_计量单位
	public void setHwZlJldwDm(String hwZlJldwDm) {
		this.hwZlJldwDm=hwZlJldwDm;
	}

	//获取货物_体积
	public Double getHwTj() {
		return this.hwTj;
	}

	//设置货物_体积
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	//获取货物_体积_计量单位
	public String getHwTjJldwDm() {
		return this.hwTjJldwDm;
	}

	//设置货物_体积_计量单位
	public void setHwTjJldwDm(String hwTjJldwDm) {
		this.hwTjJldwDm=hwTjJldwDm;
	}

	//获取结算数量
	public Double getJsSl() {
		return this.jsSl;
	}

	//设置结算数量
	public void setJsSl(Double jsSl) {
		this.jsSl=jsSl;
	}

	//获取结算计量单位
	public String getJsJldwDm() {
		return this.jsJldwDm;
	}

	//设置结算计量单位
	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm=jsJldwDm;
	}

	//获取结算计量单位分类代码
	public String getJsJldwFlDm() {
		return this.jsJldwFlDm;
	}

	//设置结算计量单位分类代码
	public void setJsJldwFlDm(String jsJldwFlDm) {
		this.jsJldwFlDm=jsJldwFlDm;
	}

	//获取回单编号
	public String getHdbh() {
		return this.hdbh;
	}

	//设置回单编号
	public void setHdbh(String hdbh) {
		this.hdbh=hdbh;
	}

	//获取始发地_行政区划代码
	public String getSfdXzqhDm() {
		return this.sfdXzqhDm;
	}

	//设置始发地_行政区划代码
	public void setSfdXzqhDm(String sfdXzqhDm) {
		this.sfdXzqhDm=sfdXzqhDm;
	}

	//获取目的地_行政区划代码
	public String getMddXzqhDm() {
		return this.mddXzqhDm;
	}

	//设置目的地_行政区划代码
	public void setMddXzqhDm(String mddXzqhDm) {
		this.mddXzqhDm=mddXzqhDm;
	}

	//获取对帐_收入
	public Double getDzSr() {
		return this.dzSr;
	}

	//设置对帐_收入
	public void setDzSr(Double dzSr) {
		this.dzSr=dzSr;
	}

	//获取对帐_已结
	public Double getDzYj() {
		return this.dzYj;
	}

	//设置对帐_已结
	public void setDzYj(Double dzYj) {
		this.dzYj=dzYj;
	}

	//获取对帐_未结
	public Double getDzWj() {
		return this.dzWj;
	}

	//设置对帐_未结
	public void setDzWj(Double dzWj) {
		this.dzWj=dzWj;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取多次结算标志(Y/N)
	public String getDcjsbz() {
		return this.dcjsbz;
	}

	//设置多次结算标志(Y/N)
	public void setDcjsbz(String dcjsbz) {
		this.dcjsbz=dcjsbz;
	}

	//获取前次对帐登记序号
	public String getQcDzDjxh() {
		return this.qcDzDjxh;
	}

	//设置前次对帐登记序号
	public void setQcDzDjxh(String qcDzDjxh) {
		this.qcDzDjxh=qcDzDjxh;
	}

	//获取对帐状态代码
	public String getDzztDm() {
		return this.dzztDm;
	}

	//设置对帐状态代码
	public void setDzztDm(String dzztDm) {
		this.dzztDm=dzztDm;
	}

	//获取对帐登记序号
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//设置对帐登记序号
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}
}