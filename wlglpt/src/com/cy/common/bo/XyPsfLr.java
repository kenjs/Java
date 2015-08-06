package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For XY_PSF_LR is created by tools.
 * @author HJH
 */

public class XyPsfLr  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String psfDjxh;                          // 派送费登记序号
	private String sydwJgbm;                         // 上游单位机构编码
	private String zrbmDjxh;                         // 责任部门登记序号
	private String ddDjxh;                           // 订单登记序号
	private String hwmxXh;                           // 货物明细序号
	private String hwMc;                             // 货物名称
	private Double psfy;                             // 派送费用
	private String bz;                               // 备注
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String pcDjxh;                           // 
	private String sfqr;
	private String qrsm;
	private String qrjg;
	private String wfhDjxh;
	public XyPsfLr() {
	}

	//获取派送费登记序号
	public String getPsfDjxh() {
		return this.psfDjxh;
	}

	//设置派送费登记序号
	public void setPsfDjxh(String psfDjxh) {
		this.psfDjxh=psfDjxh;
	}

	//获取上游单位机构编码
	public String getSydwJgbm() {
		return this.sydwJgbm;
	}

	//设置上游单位机构编码
	public void setSydwJgbm(String sydwJgbm) {
		this.sydwJgbm=sydwJgbm;
	}

	//获取责任部门登记序号
	public String getZrbmDjxh() {
		return this.zrbmDjxh;
	}

	//设置责任部门登记序号
	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh=zrbmDjxh;
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
	public String getHwmxXh() {
		return this.hwmxXh;
	}

	//设置货物明细序号
	public void setHwmxXh(String hwmxXh) {
		this.hwmxXh=hwmxXh;
	}

	//获取货物名称
	public String getHwMc() {
		return this.hwMc;
	}

	//设置货物名称
	public void setHwMc(String hwMc) {
		this.hwMc=hwMc;
	}

	//获取派送费用
	public Double getPsfy() {
		return this.psfy;
	}

	//设置派送费用
	public void setPsfy(Double psfy) {
		this.psfy=psfy;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
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

	//获取
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	public String getSfqr() {
		return sfqr;
	}

	public void setSfqr(String sfqr) {
		this.sfqr = sfqr;
	}

	public String getQrsm() {
		return qrsm;
	}

	public void setQrsm(String qrsm) {
		this.qrsm = qrsm;
	}

	public String getQrjg() {
		return qrjg;
	}

	public void setQrjg(String qrjg) {
		this.qrjg = qrjg;
	}

	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}
}