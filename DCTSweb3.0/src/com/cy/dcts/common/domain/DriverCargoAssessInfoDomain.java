package com.cy.dcts.common.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.constants.Constants;

public class DriverCargoAssessInfoDomain extends BaseDomain{
 private static final long serialVersionUID = 8617332575268144232L;
	private String id;//主键Id
	private String driverId;//司机ID
	private String cargoId;//货物ID
	private String type;//0：货已走，1：货还在
	private String assessInfo;//点评内容（备注）
	private String  createTime;//点评时间
	
	private String name;//司机姓名
	private String code;//司机电话
	private String carNumber;//司机车牌号
	
	private String typeVal;//点评类型对应的值
	private List<DriverCargoAssessInfoDomain> list;
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
		if(StringUtils.isEmpty(type)){
			return;
		}
		try {
			if(Constants.CARGO_EXPORTED_TYPE_KEY.equals(type)){
				this.setTypeVal(Constants.CARGO_EXPORTED_TYPE_VALUE);
			}else if(Constants.CARGO_EXIST_TYPE_KEY.equals(type)){
				this.setTypeVal(Constants.CARGO_EXIST_TYPE_VALUE);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public String getAssessInfo() {
		return assessInfo;
	}
	public void setAssessInfo(String assessInfo) {
		this.assessInfo = assessInfo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<DriverCargoAssessInfoDomain> getList() {
		return list;
	}
	public void setList(List<DriverCargoAssessInfoDomain> list) {
		this.list = list;
	}
	public String getTypeVal() {
		return typeVal;
	}
	public void setTypeVal(String typeVal) {
		this.typeVal = typeVal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCargoId() {
		return cargoId;
	}
	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}
	
}
