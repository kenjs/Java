package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For XYJS_SRDZ_QD is created by tools.
 * @author HJH
 */

public class XyjsSrdzQd  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // 清单登记序号(SEQ_QD_DJXH)
	private String xyDjxh;                           // 客户登记序号
	private String qdmc;                             // 清单名称
	private String fylbDm;
	private String dzqdHzfsDm;                       // 对帐清单汇总方式代码
	private Double heJe;                             // 合计金额
	private Double dzJe;
	private Double dzcyJe;
	private Double yfJe;                             // 已收金额
	private Double wfJe;                             // 未收金额
	private Double ysqKpje;                          // 已申请开票金额
	private Double wsqKpje;                          // 未申请开票金额
	private String djrCzyDjxh;                       // 登记人
	private Date djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String zt;
	private String bz;
	
	public XyjsSrdzQd() {
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
	public String getXyDjxh() {
		return this.xyDjxh;
	}

	//设置客户登记序号
	public void setXyDjxh(String xyDjxh) {
		this.xyDjxh=xyDjxh;
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

	//获取已收金额
	public Double getYfJe() {
		return this.yfJe;
	}

	//设置已收金额
	public void setYfJe(Double yfJe) {
		this.yfJe=yfJe;
	}

	//获取未收金额
	public Double getWfJe() {
		return this.wfJe;
	}

	//设置未收金额
	public void setWfJe(Double wfJe) {
		this.wfJe=wfJe;
	}

	//获取已申请开票金额
	public Double getYsqKpje() {
		return this.ysqKpje;
	}

	//设置已申请开票金额
	public void setYsqKpje(Double ysqKpje) {
		this.ysqKpje=ysqKpje;
	}

	//获取未申请开票金额
	public Double getWsqKpje() {
		return this.wsqKpje;
	}

	//设置未申请开票金额
	public void setWsqKpje(Double wsqKpje) {
		this.wsqKpje=wsqKpje;
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
	public Date getDjrq() {
		return this.djrq;
	}

	//设置登记日期
	public void setDjrq(Date djrq) {
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

	public String getFylbDm() {
		return fylbDm;
	}

	public void setFylbDm(String fylbDm) {
		this.fylbDm = fylbDm;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}

	public String getWsspztDm() {
		return wsspztDm;
	}

	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm = wsspztDm;
	}

	public String getWsSpxh() {
		return wsSpxh;
	}

	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh = wsSpxh;
	}

	public Double getDzJe() {
		return dzJe;
	}

	public void setDzJe(Double dzJe) {
		this.dzJe = dzJe;
	}

	public Double getDzcyJe() {
		return dzcyJe;
	}

	public void setDzcyJe(Double dzcyJe) {
		this.dzcyJe = dzcyJe;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}