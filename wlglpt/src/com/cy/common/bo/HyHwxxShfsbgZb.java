package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_HWXX_SHFSBG_ZB is created by tools.
 * @author HJH
 */

public class HyHwxxShfsbgZb  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String shbgDjxh;                         // 送货变更-登记序号
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 派车货物序号(未发货登记序号)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物明细序号)
	private Double bspsf;                            // 收入_运输费
	private Double zcHdf;                            // 货到付
	private Double zcThf;                            // 提货付
	private String bz;                           // 备注
	private String ssJgbm;                           // 所属机构
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public HyHwxxShfsbgZb() {
	}

	//获取送货变更-登记序号
	public String getShbgDjxh() {
		return this.shbgDjxh;
	}

	//设置送货变更-登记序号
	public void setShbgDjxh(String shbgDjxh) {
		this.shbgDjxh=shbgDjxh;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取派车货物序号(未发货登记序号)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置派车货物序号(未发货登记序号)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
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

	//获取收入_运输费
	public Double getBspsf() {
		return this.bspsf;
	}

	//设置收入_运输费
	public void setBspsf(Double bspsf) {
		this.bspsf=bspsf;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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

	public Double getZcHdf() {
		return zcHdf;
	}

	public void setZcHdf(Double zcHdf) {
		this.zcHdf = zcHdf;
	}

	public Double getZcThf() {
		return zcThf;
	}

	public void setZcThf(Double zcThf) {
		this.zcThf = zcThf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}