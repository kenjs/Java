package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_ZJRB is created by tools.
 * @author HJH
 */

public class CwZjrb  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String ssJgbm;                           // 所属机构
	private Double zrKcXj;                           // 昨日库存现金
	private Double zrKcYh;                           // 昨日库存银行
	private Double zrKcYk;                           // 昨日库存油卡
	private Double kcXj;                             // 库存现金
	private Double kcYh;                             // 库存银行
	private Double kcYk;                             // 库存油卡
	private Double srXj;                             // 收入现金
	private Double srYh;                             // 收入银行
	private Double srYk;                             // 收入油卡
	private Double zcXj;                             // 支出现金
	private Double zcYh;                             // 支出银行
	private Double zcYk;                             // 支出油卡
	private Double kcXjSrz;                          // 库存现金输入值
	private Double kcYhSrz;                          // 库存银行输入值
	private Double srXjSrz;                          // 收入现金输入值
	private Double srYhSrz;                          // 收入银行输入值
	private Double zcXjSrz;                          // 支出现金输入值
	private Double zcYhSrz;                          // 支出银行输入值
	private String rq;                               // 日期
	private Double xfSrXj;                           // 现付收入现金
	private Double dfSrXj;                           // 到付收回现金
	private Double yjSrXj;                           // 月结收回现金
	private Double dshkSrXj;                         // 代收货款收入现金
	private Double ysSrXj;                           // 预收收入现金
	private Double qtSrXj;                           // 其他收入现金
	private Double xfSrYh;                           // 现付收入银行
	private Double dfSrYh;                           // 到付收回银行
	private Double yjSrYh;                           // 月结收回银行
	private Double dshkSrYh;                         // 代收货款收入银行
	private Double ysSrYh;                           // 预收收入银行
	private Double qtSrYh;                           // 其他收入银行
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	public CwZjrb() {
	}

	//获取财务登记序号(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//设置财务登记序号(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取昨日库存现金
	public Double getZrKcXj() {
		return this.zrKcXj;
	}

	//设置昨日库存现金
	public void setZrKcXj(Double zrKcXj) {
		this.zrKcXj=zrKcXj;
	}

	//获取昨日库存银行
	public Double getZrKcYh() {
		return this.zrKcYh;
	}

	//设置昨日库存银行
	public void setZrKcYh(Double zrKcYh) {
		this.zrKcYh=zrKcYh;
	}

	//获取昨日库存油卡
	public Double getZrKcYk() {
		return this.zrKcYk;
	}

	//设置昨日库存油卡
	public void setZrKcYk(Double zrKcYk) {
		this.zrKcYk=zrKcYk;
	}

	//获取库存现金
	public Double getKcXj() {
		return this.kcXj;
	}

	//设置库存现金
	public void setKcXj(Double kcXj) {
		this.kcXj=kcXj;
	}

	//获取库存银行
	public Double getKcYh() {
		return this.kcYh;
	}

	//设置库存银行
	public void setKcYh(Double kcYh) {
		this.kcYh=kcYh;
	}

	//获取库存油卡
	public Double getKcYk() {
		return this.kcYk;
	}

	//设置库存油卡
	public void setKcYk(Double kcYk) {
		this.kcYk=kcYk;
	}

	//获取收入现金
	public Double getSrXj() {
		return this.srXj;
	}

	//设置收入现金
	public void setSrXj(Double srXj) {
		this.srXj=srXj;
	}

	//获取收入银行
	public Double getSrYh() {
		return this.srYh;
	}

	//设置收入银行
	public void setSrYh(Double srYh) {
		this.srYh=srYh;
	}

	//获取收入油卡
	public Double getSrYk() {
		return this.srYk;
	}

	//设置收入油卡
	public void setSrYk(Double srYk) {
		this.srYk=srYk;
	}

	//获取支出现金
	public Double getZcXj() {
		return this.zcXj;
	}

	//设置支出现金
	public void setZcXj(Double zcXj) {
		this.zcXj=zcXj;
	}

	//获取支出银行
	public Double getZcYh() {
		return this.zcYh;
	}

	//设置支出银行
	public void setZcYh(Double zcYh) {
		this.zcYh=zcYh;
	}

	//获取支出油卡
	public Double getZcYk() {
		return this.zcYk;
	}

	//设置支出油卡
	public void setZcYk(Double zcYk) {
		this.zcYk=zcYk;
	}

	//获取库存现金输入值
	public Double getKcXjSrz() {
		return this.kcXjSrz;
	}

	//设置库存现金输入值
	public void setKcXjSrz(Double kcXjSrz) {
		this.kcXjSrz=kcXjSrz;
	}

	//获取库存银行输入值
	public Double getKcYhSrz() {
		return this.kcYhSrz;
	}

	//设置库存银行输入值
	public void setKcYhSrz(Double kcYhSrz) {
		this.kcYhSrz=kcYhSrz;
	}

	//获取收入现金输入值
	public Double getSrXjSrz() {
		return this.srXjSrz;
	}

	//设置收入现金输入值
	public void setSrXjSrz(Double srXjSrz) {
		this.srXjSrz=srXjSrz;
	}

	//获取收入银行输入值
	public Double getSrYhSrz() {
		return this.srYhSrz;
	}

	//设置收入银行输入值
	public void setSrYhSrz(Double srYhSrz) {
		this.srYhSrz=srYhSrz;
	}

	//获取支出现金输入值
	public Double getZcXjSrz() {
		return this.zcXjSrz;
	}

	//设置支出现金输入值
	public void setZcXjSrz(Double zcXjSrz) {
		this.zcXjSrz=zcXjSrz;
	}

	//获取支出银行输入值
	public Double getZcYhSrz() {
		return this.zcYhSrz;
	}

	//设置支出银行输入值
	public void setZcYhSrz(Double zcYhSrz) {
		this.zcYhSrz=zcYhSrz;
	}

	//获取日期
	public String getRq() {
		return this.rq;
	}

	//设置日期
	public void setRq(String rq) {
		this.rq=rq;
	}

	//获取现付收入现金
	public Double getXfSrXj() {
		return this.xfSrXj;
	}

	//设置现付收入现金
	public void setXfSrXj(Double xfSrXj) {
		this.xfSrXj=xfSrXj;
	}

	//获取到付收回现金
	public Double getDfSrXj() {
		return this.dfSrXj;
	}

	//设置到付收回现金
	public void setDfSrXj(Double dfSrXj) {
		this.dfSrXj=dfSrXj;
	}

	//获取月结收回现金
	public Double getYjSrXj() {
		return this.yjSrXj;
	}

	//设置月结收回现金
	public void setYjSrXj(Double yjSrXj) {
		this.yjSrXj=yjSrXj;
	}

	//获取代收货款收入现金
	public Double getDshkSrXj() {
		return this.dshkSrXj;
	}

	//设置代收货款收入现金
	public void setDshkSrXj(Double dshkSrXj) {
		this.dshkSrXj=dshkSrXj;
	}

	//获取预收收入现金
	public Double getYsSrXj() {
		return this.ysSrXj;
	}

	//设置预收收入现金
	public void setYsSrXj(Double ysSrXj) {
		this.ysSrXj=ysSrXj;
	}

	//获取其他收入现金
	public Double getQtSrXj() {
		return this.qtSrXj;
	}

	//设置其他收入现金
	public void setQtSrXj(Double qtSrXj) {
		this.qtSrXj=qtSrXj;
	}

	//获取现付收入银行
	public Double getXfSrYh() {
		return this.xfSrYh;
	}

	//设置现付收入银行
	public void setXfSrYh(Double xfSrYh) {
		this.xfSrYh=xfSrYh;
	}

	//获取到付收回银行
	public Double getDfSrYh() {
		return this.dfSrYh;
	}

	//设置到付收回银行
	public void setDfSrYh(Double dfSrYh) {
		this.dfSrYh=dfSrYh;
	}

	//获取月结收回银行
	public Double getYjSrYh() {
		return this.yjSrYh;
	}

	//设置月结收回银行
	public void setYjSrYh(Double yjSrYh) {
		this.yjSrYh=yjSrYh;
	}

	//获取代收货款收入银行
	public Double getDshkSrYh() {
		return this.dshkSrYh;
	}

	//设置代收货款收入银行
	public void setDshkSrYh(Double dshkSrYh) {
		this.dshkSrYh=dshkSrYh;
	}

	//获取预收收入银行
	public Double getYsSrYh() {
		return this.ysSrYh;
	}

	//设置预收收入银行
	public void setYsSrYh(Double ysSrYh) {
		this.ysSrYh=ysSrYh;
	}

	//获取其他收入银行
	public Double getQtSrYh() {
		return this.qtSrYh;
	}

	//设置其他收入银行
	public void setQtSrYh(Double qtSrYh) {
		this.qtSrYh=qtSrYh;
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