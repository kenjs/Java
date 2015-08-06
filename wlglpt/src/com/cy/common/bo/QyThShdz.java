package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_TH_SHDZ is created by tools.
 * @author HJH
 */

public class QyThShdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String whXh;                             // 车辆型号维护序号(SEQ_ZY_DJXH)
	private String ssJgbm;                           // 所属机构
	private String dz;                               // 地址
	private String dh;                               // 联系电话
	private String xzqhDm;                           // 行政区划代码
	private String yxbz;                             // 有效标志(Y/N)
	private String xgrCzyDjxh;                       // 修改人
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String fzr;                              // 负责人
	private String xgrq;                             // 修改日期

	public QyThShdz() {
	}

	//获取车辆型号维护序号(SEQ_ZY_DJXH)
	public String getWhXh() {
		return this.whXh;
	}

	//设置车辆型号维护序号(SEQ_ZY_DJXH)
	public void setWhXh(String whXh) {
		this.whXh=whXh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取地址
	public String getDz() {
		return this.dz;
	}

	//设置地址
	public void setDz(String dz) {
		this.dz=dz;
	}

	//获取联系电话
	public String getDh() {
		return this.dh;
	}

	//设置联系电话
	public void setDh(String dh) {
		this.dh=dh;
	}

	//获取行政区划代码
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//设置行政区划代码
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取修改人
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//设置修改人
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
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

	//获取负责人
	public String getFzr() {
		return this.fzr;
	}

	//设置负责人
	public void setFzr(String fzr) {
		this.fzr=fzr;
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