package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PZ is created by tools.
 * @author HJH
 */

public class HyPz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pzDjxh;                           // 配载登记序号(SEQ_PZ_DJXH)
	private String hzJgbm;                           // 货站机构编码
	private String clxhwhDjxh;                       // 车辆型号维护序号
	private Double clCz;                             // 车辆_承重(吨)
	private Double clTj;                             // 车辆_体积(方)
	private Double clCd;                             // 车辆_长度(米)
	private Double clKd;                             // 车辆_宽度(米)
	private Double clGd;                             // 车辆_高度(米)
	private Double pzCz;                             // 配载_承重(吨)
	private Double pzTj;                             // 配载_体积(方)
	private Double pzCd;                             // 配载_长度(米)
	private Double pzKd;                             // 配载_宽度(米)
	private Double pzGd;                             // 配载_高度(米)
	private Double pzsr;                             // 配载收入
	private Double pzcb;                             // 配载预计成本
	private Double pzpsf;                            // 配载预计配送费
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期

	public HyPz() {
	}

	//获取配载登记序号(SEQ_PZ_DJXH)
	public String getPzDjxh() {
		return this.pzDjxh;
	}

	//设置配载登记序号(SEQ_PZ_DJXH)
	public void setPzDjxh(String pzDjxh) {
		this.pzDjxh=pzDjxh;
	}

	//获取货站机构编码
	public String getHzJgbm() {
		return this.hzJgbm;
	}

	//设置货站机构编码
	public void setHzJgbm(String hzJgbm) {
		this.hzJgbm=hzJgbm;
	}

	//获取车辆型号维护序号
	public String getClxhwhDjxh() {
		return this.clxhwhDjxh;
	}

	//设置车辆型号维护序号
	public void setClxhwhDjxh(String clxhwhDjxh) {
		this.clxhwhDjxh=clxhwhDjxh;
	}

	//获取车辆_承重(吨)
	public Double getClCz() {
		return this.clCz;
	}

	//设置车辆_承重(吨)
	public void setClCz(Double clCz) {
		this.clCz=clCz;
	}

	//获取车辆_体积(方)
	public Double getClTj() {
		return this.clTj;
	}

	//设置车辆_体积(方)
	public void setClTj(Double clTj) {
		this.clTj=clTj;
	}

	//获取车辆_长度(米)
	public Double getClCd() {
		return this.clCd;
	}

	//设置车辆_长度(米)
	public void setClCd(Double clCd) {
		this.clCd=clCd;
	}

	//获取车辆_宽度(米)
	public Double getClKd() {
		return this.clKd;
	}

	//设置车辆_宽度(米)
	public void setClKd(Double clKd) {
		this.clKd=clKd;
	}

	//获取车辆_高度(米)
	public Double getClGd() {
		return this.clGd;
	}

	//设置车辆_高度(米)
	public void setClGd(Double clGd) {
		this.clGd=clGd;
	}

	//获取配载_承重(吨)
	public Double getPzCz() {
		return this.pzCz;
	}

	//设置配载_承重(吨)
	public void setPzCz(Double pzCz) {
		this.pzCz=pzCz;
	}

	//获取配载_体积(方)
	public Double getPzTj() {
		return this.pzTj;
	}

	//设置配载_体积(方)
	public void setPzTj(Double pzTj) {
		this.pzTj=pzTj;
	}

	//获取配载_长度(米)
	public Double getPzCd() {
		return this.pzCd;
	}

	//设置配载_长度(米)
	public void setPzCd(Double pzCd) {
		this.pzCd=pzCd;
	}

	//获取配载_宽度(米)
	public Double getPzKd() {
		return this.pzKd;
	}

	//设置配载_宽度(米)
	public void setPzKd(Double pzKd) {
		this.pzKd=pzKd;
	}

	//获取配载_高度(米)
	public Double getPzGd() {
		return this.pzGd;
	}

	//设置配载_高度(米)
	public void setPzGd(Double pzGd) {
		this.pzGd=pzGd;
	}

	//获取配载收入
	public Double getPzsr() {
		return this.pzsr;
	}

	//设置配载收入
	public void setPzsr(Double pzsr) {
		this.pzsr=pzsr;
	}

	//获取配载预计成本
	public Double getPzcb() {
		return this.pzcb;
	}

	//设置配载预计成本
	public void setPzcb(Double pzcb) {
		this.pzcb=pzcb;
	}

	//获取配载预计配送费
	public Double getPzpsf() {
		return this.pzpsf;
	}

	//设置配载预计配送费
	public void setPzpsf(Double pzpsf) {
		this.pzpsf=pzpsf;
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