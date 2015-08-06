package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_GZSJ is created by tools.
 * @author HJH
 */

public class BgGzsj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // 机构编码
	private String yxqQ;                             // 有效期起
	private String yxqZ;                             // 有效期止
	private String amSbsjS;                          // 上午上班时间-时
	private String amSbsjF;                          // 上午上班时间-分
	private String pmSbsjS;                          // 下午下班时间-时
	private String pmSbsjF;                          // 下午下班时间-分
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public BgGzsj() {
	}

	//获取机构编码
	public String getJgbm() {
		return this.jgbm;
	}

	//设置机构编码
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//获取有效期起
	public String getYxqQ() {
		return this.yxqQ;
	}

	//设置有效期起
	public void setYxqQ(String yxqQ) {
		this.yxqQ=yxqQ;
	}

	//获取有效期止
	public String getYxqZ() {
		return this.yxqZ;
	}

	//设置有效期止
	public void setYxqZ(String yxqZ) {
		this.yxqZ=yxqZ;
	}

	//获取上午上班时间-时
	public String getAmSbsjS() {
		return this.amSbsjS;
	}

	//设置上午上班时间-时
	public void setAmSbsjS(String amSbsjS) {
		this.amSbsjS=amSbsjS;
	}

	//获取上午上班时间-分
	public String getAmSbsjF() {
		return this.amSbsjF;
	}

	//设置上午上班时间-分
	public void setAmSbsjF(String amSbsjF) {
		this.amSbsjF=amSbsjF;
	}

	//获取下午下班时间-时
	public String getPmSbsjS() {
		return this.pmSbsjS;
	}

	//设置下午下班时间-时
	public void setPmSbsjS(String pmSbsjS) {
		this.pmSbsjS=pmSbsjS;
	}

	//获取下午下班时间-分
	public String getPmSbsjF() {
		return this.pmSbsjF;
	}

	//设置下午下班时间-分
	public void setPmSbsjF(String pmSbsjF) {
		this.pmSbsjF=pmSbsjF;
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