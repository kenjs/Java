package com.cy.dcts.common.domain;

import com.cy.dcts.common.constants.Constants;

/**
 * 货源报价表
 * @author nxj
 *
 */
public class QuoteInfoDomain extends BaseDomain {
	
	private static final long serialVersionUID = -4782813124643910118L;
	
	private String id;
	private String cargoId;//货源ID
	private String driverId;//司机ID
	private double quoteFair;//运费报价
	private double cashAdvance;// 预付现金金额
	private double prepaidOilCard; // 预付油卡金额
	private String quoteType;//报价类型
	private String remark;//备注
	private String start;//状态
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	
	private String code;//登录帐号(手机号)
	private String name;//司机姓名
	private String carNumber;//车辆号码
	private String carLength;//车长
	private String carWeight;//运力-吨位
	private String carCubage;//运力-体积
	private String carPlateType;//板-平板、高低板
	private String carBarType;//栏-高栏、低栏
	private String carTypes;//车型（车长、车宽、板、栏、运力等合起来）
	private String lastLocation;// 当前位置
	
	private String quoteTypeVal;//报价类型值
	
	
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
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public double getQuoteFair() {
		return quoteFair;
	}
	public void setQuoteFair(double quoteFair) {
		this.quoteFair = quoteFair;
	}
	public double getCashAdvance() {
		return cashAdvance;
	}
	public void setCashAdvance(double cashAdvance) {
		this.cashAdvance = cashAdvance;
	}
	public double getPrepaidOilCard() {
		return prepaidOilCard;
	}
	public void setPrepaidOilCard(double prepaidOilCard) {
		this.prepaidOilCard = prepaidOilCard;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
		if(this.quoteType==null){
			return;
		}
		try {
			if(Constants.QUOTE_TYPE_VEHICLES_KEY.equals(quoteType)){
				this.setQuoteTypeVal(Constants.QUOTE_TYPE_VEHICLES_VALUE);
			}else if(Constants.QUOTE_TYPE_BY_TON_KEY.equals(quoteType)){
				this.setQuoteTypeVal(Constants.QUOTE_TYPE_BY_TON_VALUE);
			}else if(Constants.QUOTE_TYPE_PRESS_SQUARE_KEY.equals(quoteType)){
				this.setQuoteTypeVal(Constants.QUOTE_TYPE_PRESS_SQUARE_VALUE);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarLength() {
		return carLength;
	}
	public void setCarLength(String carLength) {
		this.carLength = carLength;
	}
	public String getCarWeight() {
		return carWeight;
	}
	public void setCarWeight(String carWeight) {
		this.carWeight = carWeight;
	}
	public String getCarCubage() {
		return carCubage;
	}
	public void setCarCubage(String carCubage) {
		this.carCubage = carCubage;
	}
	public String getCarPlateType() {
		return carPlateType;
	}
	public void setCarPlateType(String carPlateType) {
		this.carPlateType = carPlateType;
	}
	public String getCarBarType() {
		return carBarType;
	}
	public void setCarBarType(String carBarType) {
		this.carBarType = carBarType;
	}
	public String getCarTypes() {
		return carTypes;
	}
	public void setCarTypes(String carTypes) {
		this.carTypes = carTypes;
	}
	public String getLastLocation() {
		return lastLocation;
	}
	public void setLastLocation(String lastLocation) {
		this.lastLocation = lastLocation;
	}
	public String getQuoteTypeVal() {
		return quoteTypeVal;
	}
	public void setQuoteTypeVal(String quoteTypeVal) {
		this.quoteTypeVal = quoteTypeVal;
	}

}
