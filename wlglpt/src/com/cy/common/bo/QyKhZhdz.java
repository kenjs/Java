package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_KH_ZHDZ is created by tools.
 * @author HJH
 */

public class QyKhZhdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zhdzDjxh;                         // 
	private String ssJgbm;                           // 机构编码(SEQ_JG_DJXH)
	private String khDjxh;                           // 
	private String xzqhDm;                           // 行政区划代码
	private String xxdz;                             // 详细地址
	private String pyqc;                             // 地址拼音全称
	private String pyjc;                             // 地址拼音简称
	private String yb;                               // 邮编
	private String lxr;                              // 联系人
	private String lxrYddh;                          // 联系人移动电话
	private String lxrGddh;                          // 联系人固定电话
	private String qtlxdh;                           // 其他联系电话
	private String bz;                               // 备注
	private String djJgbm;                           // 登记机构编码
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public QyKhZhdz() {
	}

	//获取
	public String getZhdzDjxh() {
		return this.zhdzDjxh;
	}

	//设置
	public void setZhdzDjxh(String zhdzDjxh) {
		this.zhdzDjxh=zhdzDjxh;
	}

	//获取机构编码(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置机构编码(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取行政区划代码
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//设置行政区划代码
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//获取名称
	public String getXxdz() {
		return this.xxdz;
	}

	//设置名称
	public void setXxdz(String xxdz) {
		this.xxdz=xxdz;
	}

	//获取拼音全称
	public String getPyqc() {
		return this.pyqc;
	}

	//设置拼音全称
	public void setPyqc(String pyqc) {
		this.pyqc=pyqc;
	}

	//获取拼音简称
	public String getPyjc() {
		return this.pyjc;
	}

	//设置拼音简称
	public void setPyjc(String pyjc) {
		this.pyjc=pyjc;
	}

	//获取邮编
	public String getYb() {
		return this.yb;
	}

	//设置邮编
	public void setYb(String yb) {
		this.yb=yb;
	}

	//获取简称
	public String getLxr() {
		return this.lxr;
	}

	//设置简称
	public void setLxr(String lxr) {
		this.lxr=lxr;
	}

	//获取拼音全称
	public String getLxrYddh() {
		return this.lxrYddh;
	}

	//设置拼音全称
	public void setLxrYddh(String lxrYddh) {
		this.lxrYddh=lxrYddh;
	}

	//获取拼音简称
	public String getLxrGddh() {
		return this.lxrGddh;
	}

	//设置拼音简称
	public void setLxrGddh(String lxrGddh) {
		this.lxrGddh=lxrGddh;
	}

	//获取地址
	public String getQtlxdh() {
		return this.qtlxdh;
	}

	//设置地址
	public void setQtlxdh(String qtlxdh) {
		this.qtlxdh=qtlxdh;
	}

	//获取
	public String getBz() {
		return this.bz;
	}

	//设置
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//设置
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//获取
	public String getDjrq() {
		return this.djrq;
	}

	//设置
	public void setDjrq(String djrq) {
		this.djrq=djrq;
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