package com.cy.dcts.common.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.util.SysToolsUtil;

public class DriverUserAssessInfoDomain extends BaseDomain{
	private static final long serialVersionUID = 8617332575268144232L;
	private String id;
	private String cargoId;//货源ID
	private String driverId;//司机Id
	private String transactionId;//交易订单ID (20140704)
	private String userId;//用户id（企业用户）
	private String assessEvaluateScore;//总评分
	private String assess;//评语
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	
	private String assessEvaluateValue;//总评分对应的value值
	private String name;//司机姓名
	private String cargoName;// 货物名称
	private String cargoType;// 货物类型
	private String startProvince;// 装货地-省
	private String startCity;// 装货地-市
	private String endProvince;// 卸货-省
	private String endCity;// 卸货-市
	private String cargoTypeVal;//货物类型对应的值
	
	private String satisfactory;//好评
	private String arial;//中评
	private String noSatisfactory;//差评
	private List<DriverUserAssessInfoDomain> list;
	private String menuAId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAssessEvaluateScore() {
		return assessEvaluateScore;
	}
	public void setAssessEvaluateScore(String assessEvaluateScore) {
		this.assessEvaluateScore = assessEvaluateScore;
		if(SysToolsUtil.isNullOrEmpty(assessEvaluateScore)){
			return;
		}
		try {
			if(assessEvaluateScore.equals(Constants.EVALUATE_SATISFACTORY_KEY)){
				this.setAssessEvaluateValue(Constants.EVALUATE_SATISFACTORY_VALUE);
			}else if(assessEvaluateScore.equals(Constants.EVALUATE_ARIAL_KEY)){
				this.setAssessEvaluateValue(Constants.EVALUATE_ARIAL_VALUE);
			}else if(assessEvaluateScore.equals(Constants.EVALUATE_NOSATISFACTORY_KEY)){
				this.setAssessEvaluateValue(Constants.EVALUATE_NOSATISFACTORY_VALUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getCargoType() {
		return cargoType;
	}
	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
		if(StringUtils.isEmpty(cargoType)){
			return;
		}
		try {
			if(Constants.CARGO_TYPE_HEVAY_KEY.equals(cargoType)){
				this.setCargoTypeVal(Constants.CARGO_TYPE_HEVAY_VALUE);
			}else if(Constants.CARGO_TYPE_BULKY_KEY.equals(cargoType)) {
				this.setCargoTypeVal(Constants.CARGO_TYPE_BULKY_VALUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getStartProvince() {
		return startProvince;
	}
	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getEndProvince() {
		return endProvince;
	}
	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public List<DriverUserAssessInfoDomain> getList() {
		return list;
	}
	public void setList(List<DriverUserAssessInfoDomain> list) {
		this.list = list;
	}
	public String getMenuAId() {
		return menuAId;
	}
	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}
	public String getAssessEvaluateValue() {
		return assessEvaluateValue;
	}
	public void setAssessEvaluateValue(String assessEvaluateValue) {
		this.assessEvaluateValue = assessEvaluateValue;
	}
	public String getSatisfactory() {
		return satisfactory;
	}
	public void setSatisfactory(String satisfactory) {
		this.satisfactory = satisfactory;
	}
	public String getArial() {
		return arial;
	}
	public void setArial(String arial) {
		this.arial = arial;
	}
	public String getNoSatisfactory() {
		return noSatisfactory;
	}
	public void setNoSatisfactory(String noSatisfactory) {
		this.noSatisfactory = noSatisfactory;
	}
	public String getCargoTypeVal() {
		return cargoTypeVal;
	}
	public void setCargoTypeVal(String cargoTypeVal) {
		this.cargoTypeVal = cargoTypeVal;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
