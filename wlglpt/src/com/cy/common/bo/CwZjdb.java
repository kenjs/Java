package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_ZJDBGL is created by tools.
 * @author HJH
 */

public class CwZjdb  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zjdbDjxh;                         // 资金调拨登记序号(SEQ_CW_DJXH)
	private String rq;                               // 计划日期
	private String dcDwDjxh;                         // 调出单位登记序号
	private String zcflDm;
	private String yhCshDjxh;
	private Double je;                               // 调拨金额	
	private String bz;                               // 备注说明
	private String drDwDjxh;                         // 调入单位登记序号
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	public String getZjdbDjxh() {
		return zjdbDjxh;
	}
	public void setZjdbDjxh(String zjdbDjxh) {
		this.zjdbDjxh = zjdbDjxh;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getDcDwDjxh() {
		return dcDwDjxh;
	}
	public void setDcDwDjxh(String dcDwDjxh) {
		this.dcDwDjxh = dcDwDjxh;
	}
	public String getZcflDm() {
		return zcflDm;
	}
	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}
	public String getYhCshDjxh() {
		return yhCshDjxh;
	}
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getDrDwDjxh() {
		return drDwDjxh;
	}
	public void setDrDwDjxh(String drDwDjxh) {
		this.drDwDjxh = drDwDjxh;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}
	public String getXgrq() {
		return xgrq;
	}
	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}
	public String getSpbz() {
		return spbz;
	}
	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}
	public String getWsspztDm() {
		return wsspztDm;
	}
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm = wsspztDm;
	}
	public String getWsSpxh() {
		return wsSpxh;
	}
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh = wsSpxh;
	}

}