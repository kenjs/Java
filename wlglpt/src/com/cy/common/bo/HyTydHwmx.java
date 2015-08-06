package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_TYD_HWMX is created by tools.
 * @author HJH
 */

public class HyTydHwmx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
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
	private String yxbz;                             // 有效标志(Y/N)
	private String hwflDm;                           // 货物分类代码
	private Double jsSl;                             // 结算数量
	private String jsJldwDm;                         // 结算计量单位
	private String jsJldwFlDm;                       // 结算计量单位分类代码
	private String hdbh;                             // 回单编号(多个已逗号分隔)
	private Double srHj;                             // 收入_小计
	private Double srYj;                             // 收入_月结
	private Double srXf;                             // 收入_现付
	private Double srHdf;                            // 收入_货到付
	private Double srThf;                            // 收入_提货付
	private Double srHf;                             // 收入_回单付
	private Double srHk;                             // 收入_回扣
	private String fhrDjxh;                          // 发货人_登记序号
	private String fhrMc;                            // 发货人_名称
	private String fhrDz;                            // 发货人_地址
	private String fhrLxr;                           // 发货人_联系人
	private String fhrLxdh;                          // 发货人_联系电话
	private String fhrXzqhDm;                        // 发货人_行政区划代码
	private String shrDjxh;                          // 收货人_登记序号
	private String shrMc;                            // 收货人_名称
	private String shrDz;                            // 收货人_地址
	private String shrLxr;                           // 收货人_联系人
	private String shrLxdh;                          // 收货人_联系电话
	private String shrXzqhDm;                        // 收货人_行政区划代码
	private String yqFhrq;                           // 要求发货日期
	private String yqDdrq;                           // 要求到达日期
	private String shfsDm;                           // 收货方式代码
	private String thflDm;                           // 提货分类代码
	private String ykjsfsDm;                         // 余款结算方式代码
	private String yjjsfsDm;                         // 运价结算方式代码
	private String psbz;                             // 配送标志(Y/N)
	private String hwhh;                             // 货物货号
	
	private Double srYsf; //运输费
	private Double srBjf; //保价费
	private Double srPsf; //配送费
	private Double  fySmjz; //声明价值
	private Double fyDshk;						//	代收货款
	private Double sl;    //税率
	private String kpbz;  //开票标志 
	private String sfhdBz;  //是否回单标志 Y/N
	
	public HyTydHwmx() {
	}

	//获取订单登记序号(SEQ_DD_DJXH)
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号(SEQ_DD_DJXH)
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

	public String getHwflDm() {
		return hwflDm;
	}

	public void setHwflDm(String hwflDm) {
		this.hwflDm = hwflDm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	public Double getJsSl() {
		return jsSl;
	}

	public void setJsSl(Double jsSl) {
		this.jsSl = jsSl;
	}

	public String getJsJldwDm() {
		return jsJldwDm;
	}

	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm = jsJldwDm;
	}

	public String getJsJldwFlDm() {
		return jsJldwFlDm;
	}

	public void setJsJldwFlDm(String jsJldwFlDm) {
		this.jsJldwFlDm = jsJldwFlDm;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}

	public Double getSrHj() {
		return srHj;
	}

	public void setSrHj(Double srHj) {
		this.srHj = srHj;
	}

	public Double getSrYj() {
		return srYj;
	}

	public void setSrYj(Double srYj) {
		this.srYj = srYj;
	}

	public Double getSrXf() {
		return srXf;
	}

	public void setSrXf(Double srXf) {
		this.srXf = srXf;
	}

	public Double getSrHdf() {
		return srHdf;
	}

	public void setSrHdf(Double srHdf) {
		this.srHdf = srHdf;
	}

	public Double getSrThf() {
		return srThf;
	}

	public void setSrThf(Double srThf) {
		this.srThf = srThf;
	}

	public Double getSrHf() {
		return srHf;
	}

	public void setSrHf(Double srHf) {
		this.srHf = srHf;
	}

	public Double getSrHk() {
		return srHk;
	}

	public void setSrHk(Double srHk) {
		this.srHk = srHk;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getFhrDz() {
		return fhrDz;
	}

	public void setFhrDz(String fhrDz) {
		this.fhrDz = fhrDz;
	}

	public String getFhrLxr() {
		return fhrLxr;
	}

	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr = fhrLxr;
	}

	public String getFhrLxdh() {
		return fhrLxdh;
	}

	public void setFhrLxdh(String fhrLxdh) {
		this.fhrLxdh = fhrLxdh;
	}

	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}

	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}

	public String getShrDjxh() {
		return shrDjxh;
	}

	public void setShrDjxh(String shrDjxh) {
		this.shrDjxh = shrDjxh;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public String getShrLxr() {
		return shrLxr;
	}

	public void setShrLxr(String shrLxr) {
		this.shrLxr = shrLxr;
	}

	public String getShrLxdh() {
		return shrLxdh;
	}

	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh = shrLxdh;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public String getYqFhrq() {
		return yqFhrq;
	}

	public void setYqFhrq(String yqFhrq) {
		this.yqFhrq = yqFhrq;
	}

	public String getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(String yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public String getShfsDm() {
		return shfsDm;
	}

	public void setShfsDm(String shfsDm) {
		this.shfsDm = shfsDm;
	}

	public String getThflDm() {
		return thflDm;
	}

	public void setThflDm(String thflDm) {
		this.thflDm = thflDm;
	}

	public String getYkjsfsDm() {
		return ykjsfsDm;
	}

	public void setYkjsfsDm(String ykjsfsDm) {
		this.ykjsfsDm = ykjsfsDm;
	}

	public String getYjjsfsDm() {
		return yjjsfsDm;
	}

	public void setYjjsfsDm(String yjjsfsDm) {
		this.yjjsfsDm = yjjsfsDm;
	}

	public String getPsbz() {
		return psbz;
	}

	public void setPsbz(String psbz) {
		this.psbz = psbz;
	}

	public Double getSrYsf() {
		return srYsf;
	}

	public void setSrYsf(Double srYsf) {
		this.srYsf = srYsf;
	}

	public Double getSrBjf() {
		return srBjf;
	}

	public void setSrBjf(Double srBjf) {
		this.srBjf = srBjf;
	}

	public Double getSrPsf() {
		return srPsf;
	}

	public void setSrPsf(Double srPsf) {
		this.srPsf = srPsf;
	}

	public Double getFySmjz() {
		return fySmjz;
	}

	public void setFySmjz(Double fySmjz) {
		this.fySmjz = fySmjz;
	}

	public Double getFyDshk() {
		return fyDshk;
	}

	public void setFyDshk(Double fyDshk) {
		this.fyDshk = fyDshk;
	}

	public Double getSl() {
		return sl;
	}

	public void setSl(Double sl) {
		this.sl = sl;
	}

	public String getKpbz() {
		return kpbz;
	}

	public void setKpbz(String kpbz) {
		this.kpbz = kpbz;
	}

	public String getSfhdBz() {
		return sfhdBz;
	}

	public void setSfhdBz(String sfhdBz) {
		this.sfhdBz = sfhdBz;
	}

	public String getHwhh() {
		return hwhh;
	}

	public void setHwhh(String hwhh) {
		this.hwhh = hwhh;
	}
}