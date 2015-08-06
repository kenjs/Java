package com.cy.driver.domain;

public class UserDriverAssessInfoDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5042201955750108762L;

	private String driverId;
	private String cargoId;
	private String transactionId;
	private String tradeEvaluateScore;
	private String assess;
	private String userId;
	private String name;
	private String companyId;
	private String mobilephone;
	private String companyName;
	private String contactName;
	private String contactTelephone;
	
	public UserDriverAssessInfoDomain() {
		super();
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getCargoId() {
		return cargoId;
	}
	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTradeEvaluateScore() {
		return tradeEvaluateScore;
	}
	public void setTradeEvaluateScore(String tradeEvaluateScore) {
		this.tradeEvaluateScore = tradeEvaluateScore;
	}
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactTelephone() {
		return contactTelephone;
	}
	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}
	
}
