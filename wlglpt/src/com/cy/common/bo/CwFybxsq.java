package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_FYBXSQ is created by tools.
 * @author HJH
 */

public class CwFybxsq  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String sqrCzyDjxh;                       // 申请人
	private String sqrq;                             // 申请日期
	private String sqBmDjxh;                         // 申请部门
	private String sqDwDjxh;                         // 申请单位
	private String fyjzDwDjxh;                       // 费用记账单位
	private String fyzfDwDjxh;                       // 费用支付单位
	private String fybxje;                           // 费用报销金额合计
	private String bz;                               // 备注
	private String yxbz;                             // 有效标志(Y/N)
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String xmflDjxh;
	private String bxdh;
	public String getBxdh() {
		return bxdh;
	}

	public void setBxdh(String bxdh) {
		this.bxdh = bxdh;
	}

	public String getXmflDjxh() {
		return xmflDjxh;
	}

	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh = xmflDjxh;
	}

	public CwFybxsq() {
	}

	//获取财务登记序号(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//设置财务登记序号(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//获取申请人
	public String getSqrCzyDjxh() {
		return this.sqrCzyDjxh;
	}

	//设置申请人
	public void setSqrCzyDjxh(String sqrCzyDjxh) {
		this.sqrCzyDjxh=sqrCzyDjxh;
	}

	//获取申请日期
	public String getSqrq() {
		return this.sqrq;
	}

	//设置申请日期
	public void setSqrq(String sqrq) {
		this.sqrq=sqrq;
	}

	//获取申请部门
	public String getSqBmDjxh() {
		return this.sqBmDjxh;
	}

	//设置申请部门
	public void setSqBmDjxh(String sqBmDjxh) {
		this.sqBmDjxh=sqBmDjxh;
	}

	//获取申请单位
	public String getSqDwDjxh() {
		return this.sqDwDjxh;
	}

	//设置申请单位
	public void setSqDwDjxh(String sqDwDjxh) {
		this.sqDwDjxh=sqDwDjxh;
	}

	//获取费用记账单位
	public String getFyjzDwDjxh() {
		return this.fyjzDwDjxh;
	}

	//设置费用记账单位
	public void setFyjzDwDjxh(String fyjzDwDjxh) {
		this.fyjzDwDjxh=fyjzDwDjxh;
	}

	//获取费用支付单位
	public String getFyzfDwDjxh() {
		return this.fyzfDwDjxh;
	}

	//设置费用支付单位
	public void setFyzfDwDjxh(String fyzfDwDjxh) {
		this.fyzfDwDjxh=fyzfDwDjxh;
	}

	//获取费用报销金额合计
	public String getFybxje() {
		return this.fybxje;
	}

	//设置费用报销金额合计
	public void setFybxje(String fybxje) {
		this.fybxje=fybxje;
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