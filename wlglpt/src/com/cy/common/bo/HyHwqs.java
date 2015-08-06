package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_HWQS is created by tools.
 * @author HJH
 */

public class HyHwqs  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwqsDjxh;                         // 货物签收登记序号
	private String pcDjxh;                           // 派车登记序号
	private String wfhdjxh;                          // 未发货登记序号
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号（货物明细序号）
	private String qsrq;                             // 签收日期
	private String qsrmc;                            // 签收人名称
	private String lxdh;                             // 联系电话
	private String sfzh;                             // 身份证号
	private String bz;                               // 备注说明
	private String yxbz;                             // 有效标志
	private String cjr;                              // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public HyHwqs() {
	}

	//获取货物签收登记序号
	public String getHwqsDjxh() {
		return this.hwqsDjxh;
	}

	//设置货物签收登记序号
	public void setHwqsDjxh(String hwqsDjxh) {
		this.hwqsDjxh=hwqsDjxh;
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
	public String getWfhdjxh() {
		return this.wfhdjxh;
	}

	//设置未发货登记序号
	public void setWfhdjxh(String wfhdjxh) {
		this.wfhdjxh=wfhdjxh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取序号（货物明细序号）
	public String getXh() {
		return this.xh;
	}

	//设置序号（货物明细序号）
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取签收日期
	public String getQsrq() {
		return this.qsrq;
	}

	//设置签收日期
	public void setQsrq(String qsrq) {
		this.qsrq=qsrq;
	}

	//获取签收人名称
	public String getQsrmc() {
		return this.qsrmc;
	}

	//设置签收人名称
	public void setQsrmc(String qsrmc) {
		this.qsrmc=qsrmc;
	}

	//获取联系电话
	public String getLxdh() {
		return this.lxdh;
	}

	//设置联系电话
	public void setLxdh(String lxdh) {
		this.lxdh=lxdh;
	}

	//获取身份证号
	public String getSfzh() {
		return this.sfzh;
	}

	//设置身份证号
	public void setSfzh(String sfzh) {
		this.sfzh=sfzh;
	}

	//获取备注说明
	public String getBz() {
		return this.bz;
	}

	//设置备注说明
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取有效标志
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取创建人
	public String getCjr() {
		return this.cjr;
	}

	//设置创建人
	public void setCjr(String cjr) {
		this.cjr=cjr;
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
}