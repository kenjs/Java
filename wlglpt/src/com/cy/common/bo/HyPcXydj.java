package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PC_XYDJ is created by tools.
 * @author HJH
 */

public class HyPcXydj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // 派车登记序号(SEQ_PC_DJXH)
	private String xyh;                              // 协议号
	private Double yfHj;                             // 运费变更_总运费
	private Double yfYfyf;                           // 运费变更_预付运费
	private Double yfYj;                             // 运费变更_押金
	private Double yfXxf;                            // 运费变更_信息费
	private Double yfSjs;                            // 运费变更_司机收
	private Double yfHdyf;                           // 运费变更_货到运费
	private Double yfHdf;                            // 运费变更_回单付
	private String bz;                               // 备注
	private String ywyCzyDjxh;                       // 业务员
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号

	public HyPcXydj() {
	}

	//获取派车登记序号(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取协议号
	public String getXyh() {
		return this.xyh;
	}

	//设置协议号
	public void setXyh(String xyh) {
		this.xyh=xyh;
	}

	//获取运费变更_总运费
	public Double getYfHj() {
		return this.yfHj;
	}

	//设置运费变更_总运费
	public void setYfHj(Double yfHj) {
		this.yfHj=yfHj;
	}

	//获取运费变更_预付运费
	public Double getYfYfyf() {
		return this.yfYfyf;
	}

	//设置运费变更_预付运费
	public void setYfYfyf(Double yfYfyf) {
		this.yfYfyf=yfYfyf;
	}

	//获取运费变更_押金
	public Double getYfYj() {
		return this.yfYj;
	}

	//设置运费变更_押金
	public void setYfYj(Double yfYj) {
		this.yfYj=yfYj;
	}

	//获取运费变更_信息费
	public Double getYfXxf() {
		return this.yfXxf;
	}

	//设置运费变更_信息费
	public void setYfXxf(Double yfXxf) {
		this.yfXxf=yfXxf;
	}

	//获取运费变更_司机收
	public Double getYfSjs() {
		return this.yfSjs;
	}

	//设置运费变更_司机收
	public void setYfSjs(Double yfSjs) {
		this.yfSjs=yfSjs;
	}

	//获取运费变更_货到运费
	public Double getYfHdyf() {
		return this.yfHdyf;
	}

	//设置运费变更_货到运费
	public void setYfHdyf(Double yfHdyf) {
		this.yfHdyf=yfHdyf;
	}

	//获取运费变更_回单付
	public Double getYfHdf() {
		return this.yfHdf;
	}

	//设置运费变更_回单付
	public void setYfHdf(Double yfHdf) {
		this.yfHdf=yfHdf;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取业务员
	public String getYwyCzyDjxh() {
		return this.ywyCzyDjxh;
	}

	//设置业务员
	public void setYwyCzyDjxh(String ywyCzyDjxh) {
		this.ywyCzyDjxh=ywyCzyDjxh;
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

	//获取需要审批标志(Y/N)
	public String getSpbz() {
		return this.spbz;
	}

	//设置需要审批标志(Y/N)
	public void setSpbz(String spbz) {
		this.spbz=spbz;
	}

	//获取文书审批状态代码
	public String getWsspztDm() {
		return this.wsspztDm;
	}

	//设置文书审批状态代码
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm=wsspztDm;
	}

	//获取文书审批序号
	public String getWsSpxh() {
		return this.wsSpxh;
	}

	//设置文书审批序号
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh=wsSpxh;
	}
}