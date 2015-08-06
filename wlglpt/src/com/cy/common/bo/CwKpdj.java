package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_KPDJ is created by tools.
 * @author HJH
 */

public class CwKpdj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpDjxh;                           // 开票登记序号(SEQ_KP_DJXH)
	private String kpsqDjxh;                         // 开票申请登记序号(SEQ_QD_DJXH)
	private String khDjxh;                           // 客户登记序号
	private String fpdm;                             // 备注
	private String fphm;                             // 需要审批标志(Y/N)
	private String kprCzyDjxh;                       // 
	private String kprq;                             // 开票方式代码
	private Double kpje;                             // 已开发票号码
	private Double sl;                               // 
	private Double se;                               // 税额
	private String zfbz;                             // 
	private String hzbz;                             // 
	private String lzFpdm;                           // 
	private String lzFphm;                           // 
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public CwKpdj() {
	}

	//获取开票登记序号(SEQ_KP_DJXH)
	public String getKpDjxh() {
		return this.kpDjxh;
	}

	//设置开票登记序号(SEQ_KP_DJXH)
	public void setKpDjxh(String kpDjxh) {
		this.kpDjxh=kpDjxh;
	}

	//获取开票申请登记序号(SEQ_QD_DJXH)
	public String getKpsqDjxh() {
		return this.kpsqDjxh;
	}

	//设置开票申请登记序号(SEQ_QD_DJXH)
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh=kpsqDjxh;
	}

	//获取客户登记序号
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置客户登记序号
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取备注
	public String getFpdm() {
		return this.fpdm;
	}

	//设置备注
	public void setFpdm(String fpdm) {
		this.fpdm=fpdm;
	}

	//获取需要审批标志(Y/N)
	public String getFphm() {
		return this.fphm;
	}

	//设置需要审批标志(Y/N)
	public void setFphm(String fphm) {
		this.fphm=fphm;
	}

	//获取
	public String getKprCzyDjxh() {
		return this.kprCzyDjxh;
	}

	//设置
	public void setKprCzyDjxh(String kprCzyDjxh) {
		this.kprCzyDjxh=kprCzyDjxh;
	}

	//获取开票方式代码
	public String getKprq() {
		return this.kprq;
	}

	//设置开票方式代码
	public void setKprq(String kprq) {
		this.kprq=kprq;
	}

	//获取已开发票号码
	public Double getKpje() {
		return this.kpje;
	}

	//设置已开发票号码
	public void setKpje(Double kpje) {
		this.kpje=kpje;
	}

	//获取
	public Double getSl() {
		return this.sl;
	}

	//设置
	public void setSl(Double sl) {
		this.sl=sl;
	}

	//获取税额
	public Double getSe() {
		return this.se;
	}

	//设置税额
	public void setSe(Double se) {
		this.se=se;
	}

	//获取
	public String getZfbz() {
		return this.zfbz;
	}

	//设置
	public void setZfbz(String zfbz) {
		this.zfbz=zfbz;
	}

	//获取
	public String getHzbz() {
		return this.hzbz;
	}

	//设置
	public void setHzbz(String hzbz) {
		this.hzbz=hzbz;
	}

	//获取
	public String getLzFpdm() {
		return this.lzFpdm;
	}

	//设置
	public void setLzFpdm(String lzFpdm) {
		this.lzFpdm=lzFpdm;
	}

	//获取
	public String getLzFphm() {
		return this.lzFphm;
	}

	//设置
	public void setLzFphm(String lzFphm) {
		this.lzFphm=lzFphm;
	}

	//获取登记部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置登记部门
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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