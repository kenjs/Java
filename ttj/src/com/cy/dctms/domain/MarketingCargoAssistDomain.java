package com.cy.dctms.domain;


import com.cy.dctms.common.bo.MarketingCargoAssist;

public class MarketingCargoAssistDomain extends BaseDomain{
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String cargoId;//pk-t_order_cargo_info.id
	private String cargoResult;//货源真实情况：-1 没有这个货 0 未确认 1 货还在 2 货已走
	private String regCompanyId;//已注册企业ID，匹配不到时为空 (pk-t_company_info.id)
	private String marketUserId;//专员用户ID (pk-t_marketing_user_info.id)
	private String cargoInfoFrom;//货源信息来源：1 一点通 2 物流之家
	private String isMatchSuccess;//是否匹配成功
	private String hasTransaction;//是否达成交易，以此货源产生了订单为准
	
	private MarketingCargoAssist[] cargoAssistArray;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCargoId() {
		return cargoId;
	}

	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}

	public String getCargoResult() {
		return cargoResult;
	}

	public void setCargoResult(String cargoResult) {
		this.cargoResult = cargoResult;
	}

	public String getRegCompanyId() {
		return regCompanyId;
	}

	public void setRegCompanyId(String regCompanyId) {
		this.regCompanyId = regCompanyId;
	}

	public String getMarketUserId() {
		return marketUserId;
	}

	public void setMarketUserId(String marketUserId) {
		this.marketUserId = marketUserId;
	}

	public String getCargoInfoFrom() {
		return cargoInfoFrom;
	}

	public void setCargoInfoFrom(String cargoInfoFrom) {
		this.cargoInfoFrom = cargoInfoFrom;
	}

	public String getIsMatchSuccess() {
		return isMatchSuccess;
	}

	public void setIsMatchSuccess(String isMatchSuccess) {
		this.isMatchSuccess = isMatchSuccess;
	}

	public String getHasTransaction() {
		return hasTransaction;
	}

	public void setHasTransaction(String hasTransaction) {
		this.hasTransaction = hasTransaction;
	}

	public MarketingCargoAssist[] getCargoAssistArray() {
		return cargoAssistArray;
	}

	public void setCargoAssistArray(MarketingCargoAssist[] cargoAssistArray) {
		this.cargoAssistArray = cargoAssistArray;
	}
	
}
