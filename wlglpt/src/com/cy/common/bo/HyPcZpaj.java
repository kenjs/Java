package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_PC_ZPAJ is created by tools.
 * @author HJH
 */

public class HyPcZpaj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ajDjxh;                           // 
	private String pcDjxh;                           // 派车登记序号(SEQ_PC_DJXH)
	private byte[] ajzp;                             // 派车单号
	private String bz;                               // 备注
	private String pcrCzyDjxh;                       // 派车人
	private String pcrq;                             // 派车日期
	private String pcJgbm;                           // 派车部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	
	private String zpdz;                             //照片地址

	public String getZpdz() {
		return zpdz;
	}

	public void setZpdz(String zpdz) {
		this.zpdz = zpdz;
	}

	public HyPcZpaj() {
	}

	//获取
	public String getAjDjxh() {
		return this.ajDjxh;
	}

	//设置
	public void setAjDjxh(String ajDjxh) {
		this.ajDjxh=ajDjxh;
	}

	//获取派车登记序号(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取派车单号
	public byte[] getAjzp() {
		return this.ajzp;
	}

	//设置派车单号
	public void setAjzp(byte[] ajzp) {
		this.ajzp=ajzp;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取派车人
	public String getPcrCzyDjxh() {
		return this.pcrCzyDjxh;
	}

	//设置派车人
	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh=pcrCzyDjxh;
	}

	//获取派车日期
	public String getPcrq() {
		return this.pcrq;
	}

	//设置派车日期
	public void setPcrq(String pcrq) {
		this.pcrq=pcrq;
	}

	//获取派车部门
	public String getPcJgbm() {
		return this.pcJgbm;
	}

	//设置派车部门
	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm=pcJgbm;
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