package com.cy.common.bo;
import java.io.Serializable;
import java.sql.Date;

/**
 * The persistent class For XYJS_ZB_YJDZ is created by tools.
 * @author XIAY
 */

public class XyjsZbYjdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // 对帐登记序号(SEQ_DZ_DJXH)
	private String ssJgbm;                           // 所属机构
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 派车货物序号(未发货登记序号)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 货物明细序号
	private String zrbmDm;                           // 转入部门代码
	private String zrbmDjxh;                         // 转入部门登记序号
	private Double jsYj;                             // 结算_未结
	private Double dzje;                             // 对账金额
	private String dzrCzyDjxh;                       // 对帐人
	private String dzrq;                               // 对帐日期
	private String dzJgbm;                           // 对帐部门
	private String dzbz;							  // 对账标志
	private String yxbz;                             // 有效标志(Y/N)
	private String dzCybz;                           // 对帐差异标志(Y/N)
	private Double dzcyje;                           // 对帐差异金额
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号

	public XyjsZbYjdz() {
	}

	//获取对帐登记序号(SEQ_DZ_DJXH)
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//设置对帐登记序号(SEQ_DZ_DJXH)
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取派车货物序号(未发货登记序号)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置派车货物序号(未发货登记序号)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取货物明细序号
	public String getXh() {
		return this.xh;
	}

	//设置货物明细序号
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取转入部门代码
	public String getZrbmDm() {
		return this.zrbmDm;
	}

	//设置转入部门代码
	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm=zrbmDm;
	}

	//获取转入部门登记序号
	public String getZrbmDjxh() {
		return this.zrbmDjxh;
	}

	//设置转入部门登记序号
	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh=zrbmDjxh;
	}

	//获取结算_未结
	public Double getJsYj() {
		return this.jsYj;
	}

	//设置结算_未结
	public void setJsYj(Double jsYj) {
		this.jsYj=jsYj;
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

	//获取标志
	public String getDzbz() {
		return dzbz;
	}

	//设置对账标志
	public void setDzbz(String dzbz) {
		this.dzbz = dzbz;
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
}