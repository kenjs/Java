package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_SRDZ is created by tools.
 * @author HJH
 */

public class JsSrdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // 对帐登记序号(SEQ_DZ_DJXH)
	private String dzfsDm;                           // 对帐方式代码
	private String jsDjxh;                           // 结算登记序号
	private String ssJgbm;                           // 所属机构
	private Double jsSr;                             // 结算_收入
	private Double jsYj;                             // 结算_已结
	private Double jsWj;                             // 结算_未结
	private Double dzje;							 // 对账金额
	private String dzrCzyDjxh;                       // 对帐人
	private String dzrq;                             // 对帐日期
	private String dzJgbm;                           // 对帐部门
	private String yxbz;                             // 有效标志(Y/N)
	private String dzCybz;                           // 对帐差异标志(Y/N)
	private Double dzcyje;                           // 对帐差异金额
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String qdDjxh;                           // 清单登记序号

	public JsSrdz() {
	}

	//获取对帐登记序号(SEQ_DZ_DJXH)
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//设置对帐登记序号(SEQ_DZ_DJXH)
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}

	//获取对帐方式代码
	public String getDzfsDm() {
		return this.dzfsDm;
	}

	//设置对帐方式代码
	public void setDzfsDm(String dzfsDm) {
		this.dzfsDm=dzfsDm;
	}

	//获取结算登记序号
	public String getJsDjxh() {
		return this.jsDjxh;
	}

	//设置结算登记序号
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh=jsDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取结算_收入
	public Double getJsSr() {
		return this.jsSr;
	}

	//设置结算_收入
	public void setJsSr(Double jsSr) {
		this.jsSr=jsSr;
	}

	//获取结算_已结
	public Double getJsYj() {
		return this.jsYj;
	}

	//设置结算_已结
	public void setJsYj(Double jsYj) {
		this.jsYj=jsYj;
	}

	//获取结算_未结
	public Double getJsWj() {
		return this.jsWj;
	}

	//设置结算_未结
	public void setJsWj(Double jsWj) {
		this.jsWj=jsWj;
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
	public String getDzrq() {
		return this.dzrq;
	}

	//设置对帐日期
	public void setDzrq(String dzrq) {
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

	public Double getDzje() {
		return dzje;
	}

	public void setDzje(Double dzje) {
		this.dzje = dzje;
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
}