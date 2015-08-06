package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PC_HWQS is created by tools.
 * @author HJH
 */

public class HyPcHwqs  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwqsDjxh;                         // 物流签收登记序号(SEQ_HWQS_DJXH)
	private String pcDjxh;                           // 派车登记序号
	private Date qsrq;                             // 签收日期
	private String qsrCzyDjxh;                       // 签收人
	private String bz;                               // 备注
	private String newDdDjxh;                        // 新订单登记序号
	private String djJgbm;                           // 派车部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期

	public HyPcHwqs() {
	}

	//获取物流签收登记序号(SEQ_HWQS_DJXH)
	public String getHwqsDjxh() {
		return this.hwqsDjxh;
	}

	//设置物流签收登记序号(SEQ_HWQS_DJXH)
	public void setHwqsDjxh(String hwqsDjxh) {
		this.hwqsDjxh=hwqsDjxh;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取签收日期
	public Date getQsrq() {
		return this.qsrq;
	}

	//设置签收日期
	public void setQsrq(Date qsrq) {
		this.qsrq=qsrq;
	}

	//获取签收人
	public String getQsrCzyDjxh() {
		return this.qsrCzyDjxh;
	}

	//设置签收人
	public void setQsrCzyDjxh(String qsrCzyDjxh) {
		this.qsrCzyDjxh=qsrCzyDjxh;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取新订单登记序号
	public String getNewDdDjxh() {
		return this.newDdDjxh;
	}

	//设置新订单登记序号
	public void setNewDdDjxh(String newDdDjxh) {
		this.newDdDjxh=newDdDjxh;
	}

	//获取派车部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置派车部门
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
	public Date getCjrq() {
		return this.cjrq;
	}

	//设置创建日期
	public void setCjrq(Date cjrq) {
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
	public Date getXgrq() {
		return this.xgrq;
	}

	//设置修改日期
	public void setXgrq(Date xgrq) {
		this.xgrq=xgrq;
	}
}