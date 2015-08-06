package com.cy.common.bo;




import java.io.Serializable;

/**
 * The persistent class For BG_QDQT is created by tools.
 * @author HJH
 */

public class BgQdqt  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String bgDjxh;                           // 办公登记序号(SEQ_BG_DJXH)
	private String czyDjxh;                          // 操作员登记序号
	private String rq;                               // 日期(YYYY-MM-DD)
	private String qdqtDm;                           // 签到签退代码
	private String yQdqtSj;                          // 应签到签退时间
	private String sjQdqtSj;                         // 实际签到签退时间
	private String cdztbz;                           // 迟到早退标志(Y/N)
	private Double cdztsj;                           // 迟到早退时间(分钟)

	public BgQdqt() {
	}

	//获取办公登记序号(SEQ_BG_DJXH)
	public String getBgDjxh() {
		return this.bgDjxh;
	}

	//设置办公登记序号(SEQ_BG_DJXH)
	public void setBgDjxh(String bgDjxh) {
		this.bgDjxh=bgDjxh;
	}

	//获取操作员登记序号
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置操作员登记序号
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//获取日期(YYYY-MM-DD)
	public String getRq() {
		return this.rq;
	}

	//设置日期(YYYY-MM-DD)
	public void setRq(String rq) {
		this.rq=rq;
	}

	//获取签到签退代码
	public String getQdqtDm() {
		return this.qdqtDm;
	}

	//设置签到签退代码
	public void setQdqtDm(String qdqtDm) {
		this.qdqtDm=qdqtDm;
	}

	//获取应签到签退时间
	public String getYQdqtSj() {
		return this.yQdqtSj;
	}

	//设置应签到签退时间
	public void setYQdqtSj(String yQdqtSj) {
		this.yQdqtSj=yQdqtSj;
	}

	//获取实际签到签退时间
	public String getSjQdqtSj() {
		return this.sjQdqtSj;
	}

	//设置实际签到签退时间
	public void setSjQdqtSj(String sjQdqtSj) {
		this.sjQdqtSj=sjQdqtSj;
	}

	//获取迟到早退标志(Y/N)
	public String getCdztbz() {
		return this.cdztbz;
	}

	//设置迟到早退标志(Y/N)
	public void setCdztbz(String cdztbz) {
		this.cdztbz=cdztbz;
	}

	//获取迟到早退时间(分钟)
	public Double getCdztsj() {
		return this.cdztsj;
	}

	//设置迟到早退时间(分钟)
	public void setCdztsj(Double cdztsj) {
		this.cdztsj=cdztsj;
	}
}