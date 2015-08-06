package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_THD is created by tools.
 * @author HJH
 */

public class HyMbTyd  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String mbDjxh;                           // 模版登记序号(SEQ_MB_DJXH)
	private String ssJgbm;                           // 所属机构
	private String mbmc;                             // 模版名称
	private String khDjxh;                           // 客户登记序号
	private String khmc;                             // 
	private String ddflDm;                           // 订单分类代码
	private Date xdrq;                             // 下单日期
	private String yxbz;                             // 有效标志(Y/N)
	private String djrCzyDjxh;                       // 登记人
	private Date djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期
	private Double srHj;                             // 收入_合计
	private Double srYj;                             // 收入_月结
	private Double srXf;                             // 收入_现付
	private Double srHdf;                            // 收入_货到付
	private Double srThf;                            // 收入_提货付
	private Double srHf;                             // 收入_回单付
	private Double srHk;                             // 收入_回扣
	private String bz;                               // 备注

	private Long ddDjxh;                           // 订单登记序号(SEQ_DD_DJXH)

	public HyMbTyd() {
	}

	//获取订单登记序号(SEQ_DD_DJXH)
	public Long getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号(SEQ_DD_DJXH)
	public void setDdDjxh(Long ddDjxh) {
		this.ddDjxh=ddDjxh;
	}


	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取登记人
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//设置登记人
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
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

	//获取修改人
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//设置修改人
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	public String getMbDjxh() {
		return mbDjxh;
	}

	public void setMbDjxh(String mbDjxh) {
		this.mbDjxh = mbDjxh;
	}

	public String getMbmc() {
		return mbmc;
	}

	public void setMbmc(String mbmc) {
		this.mbmc = mbmc;
	}

	public Double getSrHj() {
		return srHj;
	}

	public void setSrHj(Double srHj) {
		this.srHj = srHj;
	}

	public Double getSrYj() {
		return srYj;
	}

	public void setSrYj(Double srYj) {
		this.srYj = srYj;
	}

	public Double getSrXf() {
		return srXf;
	}

	public void setSrXf(Double srXf) {
		this.srXf = srXf;
	}

	public Double getSrHdf() {
		return srHdf;
	}

	public void setSrHdf(Double srHdf) {
		this.srHdf = srHdf;
	}

	public Double getSrThf() {
		return srThf;
	}

	public void setSrThf(Double srThf) {
		this.srThf = srThf;
	}

	public Double getSrHf() {
		return srHf;
	}

	public void setSrHf(Double srHf) {
		this.srHf = srHf;
	}

	public Double getSrHk() {
		return srHk;
	}

	public void setSrHk(Double srHk) {
		this.srHk = srHk;
	}

	public String getDdflDm() {
		return ddflDm;
	}

	public void setDdflDm(String ddflDm) {
		this.ddflDm = ddflDm;
	}

	public String getKhDjxh() {
		return khDjxh;
	}

	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public Date getXdrq() {
		return xdrq;
	}

	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}

	public Date getDjrq() {
		return djrq;
	}

	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}

	public Date getCjrq() {
		return cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	public Date getXgrq() {
		return xgrq;
	}

	public void setXgrq(Date xgrq) {
		this.xgrq = xgrq;
	}

	
}