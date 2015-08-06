package com.cy.swp.domain;

import java.io.Serializable;

import java.util.List;

import com.cy.swp.bo.DictInfo;
import com.cy.swp.common.util.DataDictUtil;


/**
 * 数据字典DOMAIN，存放各数据字典（DataDictUtil.java）LIST对象
 * */
public class DataDictInfoDomain implements Serializable {

	private static final long serialVersionUID = 7553973735794350843L;
	
	private List<DictInfo> carPlateTypeData = DataDictUtil.carPlateTypeData();
	
	private List<DictInfo> carBarTypeData = DataDictUtil.carBarTypeData();
	
	private List<DictInfo> carStateTypeData = DataDictUtil.carStateTypeData();
	
	private List<DictInfo> carLengthData = DataDictUtil.carLengthData();
	
	private List<DictInfo> carWeightData = DataDictUtil.carWeightData();
	
	private List<DictInfo> carCubageData = DataDictUtil.carCubageData();

	private List<DictInfo> cargoTypeData = DataDictUtil.cargoTypeData();
	
	private List<DictInfo> cargoFlagData=DataDictUtil.cargoFlagData();
	
	private List<DictInfo> tradeEvaluateScoreData=DataDictUtil.tradeEvaluateScoreData();
	
	public List<DictInfo> getCarPlateTypeData() {
		return carPlateTypeData;
	}

	public void setCarPlateTypeData(List<DictInfo> carPlateTypeData) {
		this.carPlateTypeData = carPlateTypeData;
	}

	public List<DictInfo> getCarBarTypeData() {
		return carBarTypeData;
	}

	public void setCarBarTypeData(List<DictInfo> carBarTypeData) {
		this.carBarTypeData = carBarTypeData;
	}

	public List<DictInfo> getCarStateTypeData() {
		return carStateTypeData;
	}

	public void setCarStateTypeData(List<DictInfo> carStateTypeData) {
		this.carStateTypeData = carStateTypeData;
	}

	public List<DictInfo> getCarLengthData() {
		return carLengthData;
	}

	public void setCarLengthData(List<DictInfo> carLengthData) {
		this.carLengthData = carLengthData;
	}

	public List<DictInfo> getCarWeightData() {
		return carWeightData;
	}

	public void setCarWeightData(List<DictInfo> carWeightData) {
		this.carWeightData = carWeightData;
	}

	public List<DictInfo> getCarCubageData() {
		return carCubageData;
	}

	public void setCarCubageData(List<DictInfo> carCubageData) {
		this.carCubageData = carCubageData;
	}

	public List<DictInfo> getCargoTypeData() {
		return cargoTypeData;
	}

	public void setCargoTypeData(List<DictInfo> cargoTypeData) {
		this.cargoTypeData = cargoTypeData;
	}

	public List<DictInfo> getCargoFlagData() {
		return cargoFlagData;
	}

	public void setCargoFlagData(List<DictInfo> cargoFlagData) {
		this.cargoFlagData = cargoFlagData;
	}

	public List<DictInfo> getTradeEvaluateScoreData() {
		return tradeEvaluateScoreData;
	}

	public void setTradeEvaluateScoreData(List<DictInfo> tradeEvaluateScoreData) {
		this.tradeEvaluateScoreData = tradeEvaluateScoreData;
	}


}
