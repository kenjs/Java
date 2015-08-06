package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_TYD_WFHXX is created by tools.
 * @author HJH
 */

public class HyTydWfhxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wfhDjxh;                          // 未发货登记序号(SEQ_FHMX_DJXH)
	private String hwztDm;                           // 货物状态代码(仅包括未提/未发)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 货物明细序号
	private Double hwSl;                             // 货物_数量
	private Double hwZl;                             // 货物_重量
	private Double hwTj;                             // 货物_体积
	private String fhrDjxh;                          // 发货人_登记序号
	private String fhrMc;                            // 发货人_名称
	private String fhrDz;                            // 发货人_地址
	private String fhrLxr;                           // 发货人_联系人
	private String fhrLxdh;                          // 发货人_联系电话
	private String fhrXzqhDm;                        // 发货人_行政区划代码
	private String pcbz;                             // 派车标志(Y/N)
	private String pcDjxh;                           // 派车登记序号
	private String yxbz;                             // 有效标志(Y/N)

	public HyTydWfhxx() {
	}

	//获取未发货登记序号(SEQ_FHMX_DJXH)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置未发货登记序号(SEQ_FHMX_DJXH)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取货物状态代码(仅包括未提/未发)
	public String getHwztDm() {
		return this.hwztDm;
	}

	//设置货物状态代码(仅包括未提/未发)
	public void setHwztDm(String hwztDm) {
		this.hwztDm=hwztDm;
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

	//获取货物_数量
	public Double getHwSl() {
		return this.hwSl;
	}

	//设置货物_数量
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//获取货物_重量
	public Double getHwZl() {
		return this.hwZl;
	}

	//设置货物_重量
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//获取货物_体积
	public Double getHwTj() {
		return this.hwTj;
	}

	//设置货物_体积
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	//获取发货人_登记序号
	public String getFhrDjxh() {
		return this.fhrDjxh;
	}

	//设置发货人_登记序号
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh=fhrDjxh;
	}

	//获取发货人_名称
	public String getFhrMc() {
		return this.fhrMc;
	}

	//设置发货人_名称
	public void setFhrMc(String fhrMc) {
		this.fhrMc=fhrMc;
	}

	//获取发货人_地址
	public String getFhrDz() {
		return this.fhrDz;
	}

	//设置发货人_地址
	public void setFhrDz(String fhrDz) {
		this.fhrDz=fhrDz;
	}

	//获取发货人_联系人
	public String getFhrLxr() {
		return this.fhrLxr;
	}

	//设置发货人_联系人
	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr=fhrLxr;
	}

	//获取发货人_联系电话
	public String getFhrLxdh() {
		return this.fhrLxdh;
	}

	//设置发货人_联系电话
	public void setFhrLxdh(String fhrLxdh) {
		this.fhrLxdh=fhrLxdh;
	}

	//获取发货人_行政区划代码
	public String getFhrXzqhDm() {
		return this.fhrXzqhDm;
	}

	//设置发货人_行政区划代码
	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm=fhrXzqhDm;
	}

	//获取派车标志(Y/N)
	public String getPcbz() {
		return this.pcbz;
	}

	//设置派车标志(Y/N)
	public void setPcbz(String pcbz) {
		this.pcbz=pcbz;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
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