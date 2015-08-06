package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_GZR is created by tools.
 * @author HJH
 */

public class BgGzr  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // 机构编码
	private String rq;                               // 日期(YYYY-MM-DD)
	private String gzrDm;                            // 工作日代码
	private String weekdayDm;                        // 星期几代码
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public BgGzr() {
	}

	//获取机构编码
	public String getJgbm() {
		return this.jgbm;
	}

	//设置机构编码
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//获取日期(YYYY-MM-DD)
	public String getRq() {
		return this.rq;
	}

	//设置日期(YYYY-MM-DD)
	public void setRq(String rq) {
		this.rq=rq;
	}

	//获取工作日代码
	public String getGzrDm() {
		return this.gzrDm;
	}

	//设置工作日代码
	public void setGzrDm(String gzrDm) {
		this.gzrDm=gzrDm;
	}

	//获取星期几代码
	public String getWeekdayDm() {
		return this.weekdayDm;
	}

	//设置星期几代码
	public void setWeekdayDm(String weekdayDm) {
		this.weekdayDm=weekdayDm;
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
}