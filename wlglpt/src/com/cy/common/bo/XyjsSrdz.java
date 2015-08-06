package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For XYJS_SRDZ is created by tools.
 * @author HJH
 */

public class XyjsSrdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // 对帐登记序号(SEQ_DZ_DJXH)
	private String ywDjxh;                           // 结算登记序号
	private String ywMxXh;                           // 业务明细序号
	private String ssJgbm;                           // 所属机构
	private Double jsJe;                             // 结算_未结
	private Double dzje;                             // 
	private String dzrCzyDjxh;                       // 对帐人
	private Date dzrq;                             // 对帐日期
	private String dzJgbm;                           // 对帐部门
	private String yxbz;                             // 有效标志(Y/N)
	private String dzCybz;                           // 对帐差异标志(Y/N)
	private Double dzcyje;                           // 对帐差异金额
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String qdDjxh;                           // 清单登记序号
	private String bz;

	public XyjsSrdz() {
	}

	//获取对帐登记序号(SEQ_DZ_DJXH)
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//设置对帐登记序号(SEQ_DZ_DJXH)
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}

	//获取结算登记序号
	public String getYwDjxh() {
		return this.ywDjxh;
	}

	//设置结算登记序号
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh=ywDjxh;
	}

	//获取业务明细序号
	public String getYwMxXh() {
		return this.ywMxXh;
	}

	//设置业务明细序号
	public void setYwMxXh(String ywMxXh) {
		this.ywMxXh=ywMxXh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取结算_未结
	public Double getJsJe() {
		return this.jsJe;
	}

	//设置结算_未结
	public void setJsJe(Double jsJe) {
		this.jsJe=jsJe;
	}

	//获取
	public Double getDzje() {
		return this.dzje;
	}

	//设置
	public void setDzje(Double dzje) {
		this.dzje=dzje;
	}

	//获取对帐人
	public String getDzrCzyDjxh() {
		return this.dzrCzyDjxh;
	}

	//设置对帐人
	public void setDzrCzyDjxh(String dzrCzyDjxh) {
		this.dzrCzyDjxh=dzrCzyDjxh;
	}

	//获取对帐日期
	public Date getDzrq() {
		return this.dzrq;
	}

	//设置对帐日期
	public void setDzrq(Date dzrq) {
		this.dzrq=dzrq;
	}

	//获取对帐部门
	public String getDzJgbm() {
		return this.dzJgbm;
	}

	//设置对帐部门
	public void setDzJgbm(String dzJgbm) {
		this.dzJgbm=dzJgbm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取对帐差异标志(Y/N)
	public String getDzCybz() {
		return this.dzCybz;
	}

	//设置对帐差异标志(Y/N)
	public void setDzCybz(String dzCybz) {
		this.dzCybz=dzCybz;
	}

	//获取对帐差异金额
	public Double getDzcyje() {
		return this.dzcyje;
	}

	//设置对帐差异金额
	public void setDzcyje(Double dzcyje) {
		this.dzcyje=dzcyje;
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

	//获取清单登记序号
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//设置清单登记序号
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}