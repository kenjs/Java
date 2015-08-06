package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PC_HWQS_MX is created by tools.
 * @author HJH
 */

public class HyPcHwqsMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwqsDjxh;                         // 
	private String pcDjxh;                           // 
	private String wfhDjxh;                          // 
	private String ddDjxh;                           // 
	private String xh;                               // 
	private Double hwQsSl;                           // 
	private Double hwQsZl;                           // 
	private Double hwQsTj;                           // 
	private String bz;                               //
	private Date qsrq;
	private String qsrCzyDjxh;

	public HyPcHwqsMx() {
	}

	//获取
	public String getHwqsDjxh() {
		return this.hwqsDjxh;
	}

	//设置
	public void setHwqsDjxh(String hwqsDjxh) {
		this.hwqsDjxh=hwqsDjxh;
	}

	//获取
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取
	public String getXh() {
		return this.xh;
	}

	//设置
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取
	public Double getHwQsSl() {
		return this.hwQsSl;
	}

	//设置
	public void setHwQsSl(Double hwQsSl) {
		this.hwQsSl=hwQsSl;
	}

	//获取
	public Double getHwQsZl() {
		return this.hwQsZl;
	}

	//设置
	public void setHwQsZl(Double hwQsZl) {
		this.hwQsZl=hwQsZl;
	}

	//获取
	public Double getHwQsTj() {
		return this.hwQsTj;
	}

	//设置
	public void setHwQsTj(Double hwQsTj) {
		this.hwQsTj=hwQsTj;
	}

	//获取
	public String getBz() {
		return this.bz;
	}

	//设置
	public void setBz(String bz) {
		this.bz=bz;
	}

	public Date getQsrq() {
		return qsrq;
	}

	public void setQsrq(Date qsrq) {
		this.qsrq = qsrq;
	}

	public String getQsrCzyDjxh() {
		return qsrCzyDjxh;
	}

	public void setQsrCzyDjxh(String qsrCzyDjxh) {
		this.qsrCzyDjxh = qsrCzyDjxh;
	}
}