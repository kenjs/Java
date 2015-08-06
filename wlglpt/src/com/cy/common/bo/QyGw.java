package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_GW is created by tools.
 * @author HJH
 */

public class QyGw  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gwDjxh;                           // 岗位编码(SEQ_GW_DJXH或DM_GW.GW_DM)
	private String gwMc;                             // 岗位名称
	private String gwJc;                             // 岗位简称
	private String bzsm;                             // 备注说明
	private String lybz;                             // 来源标志(Y企业创建/N岗位代码)
	private Long gwDm;                               // 岗位代码
	private Long ssJgbm;                             // 所属机构编码(部门)
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private Long cjrCzyDjxh;                         // 创建人
	private String cjrq;                             // 创建日期
	private Long xgrCzyDjxh;                         // 修改人
	private String xgrq;                             // 修改日期

	public QyGw() {
	}

	//获取岗位编码(SEQ_GW_DJXH或DM_GW.GW_DM)
	public String getGwDjxh() {
		return this.gwDjxh;
	}

	//设置岗位编码(SEQ_GW_DJXH或DM_GW.GW_DM)
	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh=gwDjxh;
	}

	//获取岗位名称
	public String getGwMc() {
		return this.gwMc;
	}

	//设置岗位名称
	public void setGwMc(String gwMc) {
		this.gwMc=gwMc;
	}

	//获取岗位简称
	public String getGwJc() {
		return this.gwJc;
	}

	//设置岗位简称
	public void setGwJc(String gwJc) {
		this.gwJc=gwJc;
	}

	//获取备注说明
	public String getBzsm() {
		return this.bzsm;
	}

	//设置备注说明
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//获取来源标志(Y企业创建/N岗位代码)
	public String getLybz() {
		return this.lybz;
	}

	//设置来源标志(Y企业创建/N岗位代码)
	public void setLybz(String lybz) {
		this.lybz=lybz;
	}

	//获取岗位代码
	public Long getGwDm() {
		return this.gwDm;
	}

	//设置岗位代码
	public void setGwDm(Long gwDm) {
		this.gwDm=gwDm;
	}

	//获取所属机构编码(部门)
	public Long getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构编码(部门)
	public void setSsJgbm(Long ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取启用标志(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//设置启用标志(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
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
	public Long getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//设置创建人
	public void setCjrCzyDjxh(Long cjrCzyDjxh) {
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
	public Long getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//设置修改人
	public void setXgrCzyDjxh(Long xgrCzyDjxh) {
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