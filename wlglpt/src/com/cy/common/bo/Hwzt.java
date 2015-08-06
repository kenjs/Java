package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_DDDL is created by tools.
 * @author HJH
 */

public class Hwzt  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwztDjxh;					//货物自提登记序号
	private String wfhDjxh;						//未发货登记序号(SEQ_WFH_DJXH)
	private String ddDjxh;						//订单登记序号
	private String xh;							//序号(货物明细序号)
	private String jbrCzyDjxh;					//经办人
	private String thrq;						//提货日期
	private String bzsm;						//备注说明
	private String thbz;						//自提标志(Y 已提/N未提)
	private String yxbz;						//有效标志(Y/N)
	private String cjrCzyDjxh;					//创建人
	private String cjrq;						//创建日期
	private String xgrCzyDjxh;					//修改人
	private String xgrq;						//修改日期
	private String thrMc;						//提货人名称
	private String thrLxdh;						//提货人联系电话
	private String thrSfzh;						//提货人身份证号
	public String getBzsm() {
		return bzsm;
	}
	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
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
	public String getDdDjxh() {
		return ddDjxh;
	}
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}
	public String getHwztDjxh() {
		return hwztDjxh;
	}
	public void setHwztDjxh(String hwztDjxh) {
		this.hwztDjxh = hwztDjxh;
	}
	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}
	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}
	public String getThbz() {
		return thbz;
	}
	public void setThbz(String thbz) {
		this.thbz = thbz;
	}
	public String getThrq() {
		return thrq;
	}
	public void setThrq(String thrq) {
		this.thrq = thrq;
	}
	public String getWfhDjxh() {
		return wfhDjxh;
	}
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getThrLxdh() {
		return thrLxdh;
	}
	public void setThrLxdh(String thrLxdh) {
		this.thrLxdh = thrLxdh;
	}
	public String getThrMc() {
		return thrMc;
	}
	public void setThrMc(String thrMc) {
		this.thrMc = thrMc;
	}
	public String getThrSfzh() {
		return thrSfzh;
	}
	public void setThrSfzh(String thrSfzh) {
		this.thrSfzh = thrSfzh;
	}
	

}