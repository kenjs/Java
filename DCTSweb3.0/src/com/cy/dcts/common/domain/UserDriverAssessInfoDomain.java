package com.cy.dcts.common.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.util.SysToolsUtil;
/**
 * 货主对司机评价domain
 * @author haoyong
 *
 */
public class UserDriverAssessInfoDomain extends BaseDomain{

	private static final long serialVersionUID = 3514102264610117675L;
	private String id;
	private String driverId;//司机ID
	private String cargoId;//货物ID
	private String transactionId;//交易订单ID (20140704)
	private String userId;//评价人id
	private String arriverEvaluateScore;//到达速度（评分）
	private String serveEvaluateScore;//司机服务态度（评分）
	private String tradeEvaluateScore;//交易总评价
	private String assess;//用户填写评语
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	
	private String tradeEvaluateValue;//交易总评价对应的value值
	private String name;// 用户姓名
	private String cargoName;// 货物名称
	private String cargoType;// 货物类型
	private String startProvince;// 装货地-省
	private String startCity;// 装货地-市
	private String endProvince;// 卸货-省
	private String endCity;// 卸货-市
	private String cargoTypeVal;//货物类型对应的值
	
	private String companyName;//企业名称
	
	
	private List<UserDriverAssessInfoDomain> list;
	private String menuAId; 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getArriverEvaluateScore() {
		return arriverEvaluateScore;
	}
	public void setArriverEvaluateScore(String arriverEvaluateScore) {
		this.arriverEvaluateScore = arriverEvaluateScore;
	}
	public String getServeEvaluateScore() {
		return serveEvaluateScore;
	}
	public void setServeEvaluateScore(String serveEvaluateScore) {
		this.serveEvaluateScore = serveEvaluateScore;
	}
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
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
	public UserDriverAssessInfoDomain() {
		super();
	}
	public String getTradeEvaluateScore() {
		return tradeEvaluateScore;
	}
	public void setTradeEvaluateScore(String tradeEvaluateScore) {
		this.tradeEvaluateScore = tradeEvaluateScore;
		if(SysToolsUtil.isNullOrEmpty(tradeEvaluateScore)){
			return;
		}
		try {
			if(tradeEvaluateScore.equals(Constants.EVALUATE_SATISFACTORY_KEY)){
				this.setTradeEvaluateValue(Constants.EVALUATE_SATISFACTORY_VALUE);
			}else if(tradeEvaluateScore.equals(Constants.EVALUATE_ARIAL_KEY)){
				this.setTradeEvaluateValue(Constants.EVALUATE_ARIAL_VALUE);
			}else if(tradeEvaluateScore.equals(Constants.EVALUATE_NOSATISFACTORY_KEY)){
				this.setTradeEvaluateValue(Constants.EVALUATE_NOSATISFACTORY_VALUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<UserDriverAssessInfoDomain> getList() {
		return list;
	}
	public void setList(List<UserDriverAssessInfoDomain> list) {
		this.list = list;
	}
	public String getMenuAId() {
		return menuAId;
	}
	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}
	public String getTradeEvaluateValue() {
		return tradeEvaluateValue;
	}
	public void setTradeEvaluateValue(String tradeEvaluateValue) {
		this.tradeEvaluateValue = tradeEvaluateValue;
	}
	public String getCargoTypeVal() {
		return cargoTypeVal;
	}
	public void setCargoTypeVal(String cargoTypeVal) {
		this.cargoTypeVal = cargoTypeVal;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
