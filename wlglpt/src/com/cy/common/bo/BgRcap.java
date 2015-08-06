package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_RCAP is created by tools.
 * @author HJH
 */

public class BgRcap  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String bgDjxh;                           // 办公登记序号(SEQ_BG_DJXH)
	private String czyDjxh;                          // 操作员登记序号
	private String rq;                               // 日期(YYYY-MM-DD)
	private String nr;                               // 内容
	private String dxFsxh;                           // 短信发送序号
	private String ckbz;                             // 查看标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public BgRcap() {
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

	//获取内容
	public String getNr() {
		return this.nr;
	}

	//设置内容
	public void setNr(String nr) {
		this.nr=nr;
	}

	//获取短信发送序号
	public String getDxFsxh() {
		return this.dxFsxh;
	}

	//设置短信发送序号
	public void setDxFsxh(String dxFsxh) {
		this.dxFsxh=dxFsxh;
	}

	//获取查看标志(Y/N)
	public String getCkbz() {
		return this.ckbz;
	}

	//设置查看标志(Y/N)
	public void setCkbz(String ckbz) {
		this.ckbz=ckbz;
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