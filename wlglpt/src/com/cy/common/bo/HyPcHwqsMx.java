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

	//��ȡ
	public String getHwqsDjxh() {
		return this.hwqsDjxh;
	}

	//����
	public void setHwqsDjxh(String hwqsDjxh) {
		this.hwqsDjxh=hwqsDjxh;
	}

	//��ȡ
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//����
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//��ȡ
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//����
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//��ȡ
	public String getXh() {
		return this.xh;
	}

	//����
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ
	public Double getHwQsSl() {
		return this.hwQsSl;
	}

	//����
	public void setHwQsSl(Double hwQsSl) {
		this.hwQsSl=hwQsSl;
	}

	//��ȡ
	public Double getHwQsZl() {
		return this.hwQsZl;
	}

	//����
	public void setHwQsZl(Double hwQsZl) {
		this.hwQsZl=hwQsZl;
	}

	//��ȡ
	public Double getHwQsTj() {
		return this.hwQsTj;
	}

	//����
	public void setHwQsTj(Double hwQsTj) {
		this.hwQsTj=hwQsTj;
	}

	//��ȡ
	public String getBz() {
		return this.bz;
	}

	//����
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