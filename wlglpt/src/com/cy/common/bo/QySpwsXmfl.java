package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_SPWS_XMFL is created by tools.
 * @author HJH
 */

public class QySpwsXmfl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xmflDjxh;                         // 项目分类登记序号(SEQ_XMFL_DJXH)
	private String jgbm;                             // 机构编码
	private String wsDm;                             // 文书代码
	private String xmflmc;                           // 项目分类名称
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public QySpwsXmfl() {
	}

	//获取项目分类登记序号(SEQ_XMFL_DJXH)
	public String getXmflDjxh() {
		return this.xmflDjxh;
	}

	//设置项目分类登记序号(SEQ_XMFL_DJXH)
	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh=xmflDjxh;
	}

	//获取机构编码
	public String getJgbm() {
		return this.jgbm;
	}

	//设置机构编码
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//获取文书代码
	public String getWsDm() {
		return this.wsDm;
	}

	//设置文书代码
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//获取项目分类名称
	public String getXmflmc() {
		return this.xmflmc;
	}

	//设置项目分类名称
	public void setXmflmc(String xmflmc) {
		this.xmflmc=xmflmc;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
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