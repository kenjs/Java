package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_SPWS_SPLCSZ is created by tools.
 * @author HJH
 */

public class QySpwsSplcsz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long splcSzxh;                         // 审批流程设置序号(SEQ_SPLC_SZXH)
	private String ssJgbm;                           // 所属机构编码(部门)
	private String wsDm;                             // 文书代码
	private String xmflDjxh;                         // 项目分类登记序号
	private String splc;                             // 审批流程
	private Double zssx;                             // 终审时限(天)
	private String gzrbz;                            // 工作日标志(1 工作日，2 自然日)
	private String qzxsbz;                           // 权重系数标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public QySpwsSplcsz() {
	}

	//获取审批流程设置序号(SEQ_SPLC_SZXH)
	public Long getSplcSzxh() {
		return this.splcSzxh;
	}

	//设置审批流程设置序号(SEQ_SPLC_SZXH)
	public void setSplcSzxh(Long splcSzxh) {
		this.splcSzxh=splcSzxh;
	}

	//获取所属机构编码(部门)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构编码(部门)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取文书代码
	public String getWsDm() {
		return this.wsDm;
	}

	//设置文书代码
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//获取项目分类登记序号
	public String getXmflDjxh() {
		return this.xmflDjxh;
	}

	//设置项目分类登记序号
	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh=xmflDjxh;
	}

	//获取审批流程
	public String getSplc() {
		return this.splc;
	}

	//设置审批流程
	public void setSplc(String splc) {
		this.splc=splc;
	}

	//获取终审时限(天)
	public Double getZssx() {
		return this.zssx;
	}

	//设置终审时限(天)
	public void setZssx(Double zssx) {
		this.zssx=zssx;
	}

	//获取工作日标志(1 工作日，2 自然日)
	public String getGzrbz() {
		return this.gzrbz;
	}

	//设置工作日标志(1 工作日，2 自然日)
	public void setGzrbz(String gzrbz) {
		this.gzrbz=gzrbz;
	}

	//获取权重系数标志(Y/N)
	public String getQzxsbz() {
		return this.qzxsbz;
	}

	//设置权重系数标志(Y/N)
	public void setQzxsbz(String qzxsbz) {
		this.qzxsbz=qzxsbz;
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