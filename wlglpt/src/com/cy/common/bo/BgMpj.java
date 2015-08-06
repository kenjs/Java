package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_MPJ is created by tools.
 * @author HJH
 */

public class BgMpj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String bgDjxh;                           // 办公登记序号(SEQ_BG_DJXH)
	private String czyDjxh;                          // 操作员登记序号
	private String xm;                               // 姓名
	private String zw;                               // 职务
	private String gs;                               // 公司
	private String dz;                               // 地址
	private String dh;                               // 电话
	private String cz;                               // 传真
	private String sj;                               // 手机
	private String wz;                               // 网址
	private String yb;                               // 邮编
	private String dy;                               // 电邮
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public BgMpj() {
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

	//获取姓名
	public String getXm() {
		return this.xm;
	}

	//设置姓名
	public void setXm(String xm) {
		this.xm=xm;
	}

	//获取职务
	public String getZw() {
		return this.zw;
	}

	//设置职务
	public void setZw(String zw) {
		this.zw=zw;
	}

	//获取公司
	public String getGs() {
		return this.gs;
	}

	//设置公司
	public void setGs(String gs) {
		this.gs=gs;
	}

	//获取地址
	public String getDz() {
		return this.dz;
	}

	//设置地址
	public void setDz(String dz) {
		this.dz=dz;
	}

	//获取电话
	public String getDh() {
		return this.dh;
	}

	//设置电话
	public void setDh(String dh) {
		this.dh=dh;
	}

	//获取传真
	public String getCz() {
		return this.cz;
	}

	//设置传真
	public void setCz(String cz) {
		this.cz=cz;
	}

	//获取手机
	public String getSj() {
		return this.sj;
	}

	//设置手机
	public void setSj(String sj) {
		this.sj=sj;
	}

	//获取网址
	public String getWz() {
		return this.wz;
	}

	//设置网址
	public void setWz(String wz) {
		this.wz=wz;
	}

	//获取邮编
	public String getYb() {
		return this.yb;
	}

	//设置邮编
	public void setYb(String yb) {
		this.yb=yb;
	}

	//获取电邮
	public String getDy() {
		return this.dy;
	}

	//设置电邮
	public void setDy(String dy) {
		this.dy=dy;
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