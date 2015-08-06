package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_WSDYSZ is created by tools.
 * @author HJH
 */

public class QyWsdysz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String whXh;                             // 维护序号(SEQ_ZY_DJXH)
	private String ssJgbm;                           // 所属机构(当前所属公司)
	private String wsDm;                             // 文书代码，1001:托运单
	private Double leftMargin;                       // 左边距
	private Double topMargin;                        // 上边距
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public QyWsdysz() {
	}

	//获取维护序号(SEQ_ZY_DJXH)
	public String getWhXh() {
		return this.whXh;
	}

	//设置维护序号(SEQ_ZY_DJXH)
	public void setWhXh(String whXh) {
		this.whXh=whXh;
	}

	//获取所属机构(当前所属公司)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构(当前所属公司)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取文书代码，1001:托运单
	public String getWsDm() {
		return this.wsDm;
	}

	//设置文书代码，1001:托运单
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//获取左边距
	public Double getLeftMargin() {
		return this.leftMargin;
	}

	//设置左边距
	public void setLeftMargin(Double leftMargin) {
		this.leftMargin=leftMargin;
	}

	//获取上边距
	public Double getTopMargin() {
		return this.topMargin;
	}

	//设置上边距
	public void setTopMargin(Double topMargin) {
		this.topMargin=topMargin;
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