package com.cy.driver.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MarketingDriverInfo implements Serializable{

	private int id;					//
	private String mobilePhone;		//主手机号码
	private String driverId;		//注册司机id(当司机注册成功后，此id关联起来)
	private String name;			//司机姓名
	private String mobilePhone2;	//第二手机号码
	private String mobilePhone3;	//第三手机号码
	private int marInvalidNums;		//主手机号码标注无效的次数
	private String carNumber;		//车牌号
	private String carLength;		//车长
	private String carPlateType;	//板-平板、高低板
	private String carBarType;		//栏-高栏、低栏
	private String carWeight;		//运力-吨位
	private String carCubage;		//运力-体积
	private String carTypes;		//车型（车长、板、栏、吨，方等合起来）
	private String realLevel;		//实时客户等级(ABCD)
	private int category;			//客户类别(1 2 3 4 5)
	private String lastContactDate;	//最近联系时间
	private String nextContactDate;	//下次联系时间
	private int allocateStatus;		//分配状态 0 待分配 1 分配中 2 已分配
	private String assisterId;		//营销专员id(当分配状态为1或2时，此字段不能为空)
	private String distributerId;	//分配者id(指有权限进行客户分配的营销人员id)
	private String distributTime;	//分配时间
	private String oftenCity1;		//常跑城市一
	private String oftenCity2;		//常跑城市二
	private String oftenCity3;		//常跑城市三
	private String oftenCity4;		//常跑城市四
	private String oftenCity5;		//常跑城市五
	private String oftenCity6;		//常跑城市六
	private String createTime;		//创建时间
	private String lastModifyTime;	//最后修改时间
	private int deleteFlag;			//0 未删除 1 已删除
	private int regThroughAssist;	//是否营销人员发展的注册用户：0 否 1 是

	private List<MarketingDriverLine> list;

	public MarketingDriverInfo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone2() {
		return mobilePhone2;
	}

	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	public String getMobilePhone3() {
		return mobilePhone3;
	}

	public void setMobilePhone3(String mobilePhone3) {
		this.mobilePhone3 = mobilePhone3;
	}

	public int getMarInvalidNums() {
		return marInvalidNums;
	}

	public void setMarInvalidNums(int marInvalidNums) {
		this.marInvalidNums = marInvalidNums;
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

	public String getCarTypes() {
		return carTypes;
	}

	public void setCarTypes(String carTypes) {
		this.carTypes = carTypes;
	}

	public String getRealLevel() {
		return realLevel;
	}

	public void setRealLevel(String realLevel) {
		this.realLevel = realLevel;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getLastContactDate() {
		return lastContactDate;
	}

	public void setLastContactDate(String lastContactDate) {
		this.lastContactDate = lastContactDate;
	}

	public String getNextContactDate() {
		return nextContactDate;
	}

	public void setNextContactDate(String nextContactDate) {
		this.nextContactDate = nextContactDate;
	}

	public int getAllocateStatus() {
		return allocateStatus;
	}

	public void setAllocateStatus(int allocateStatus) {
		this.allocateStatus = allocateStatus;
	}

	public String getAssisterId() {
		return assisterId;
	}

	public void setAssisterId(String assisterId) {
		this.assisterId = assisterId;
	}

	public String getDistributerId() {
		return distributerId;
	}

	public void setDistributerId(String distributerId) {
		this.distributerId = distributerId;
	}

	public String getDistributTime() {
		return distributTime;
	}

	public void setDistributTime(String distributTime) {
		this.distributTime = distributTime;
	}

	public String getOftenCity1() {
		return oftenCity1;
	}

	public void setOftenCity1(String oftenCity1) {
		this.oftenCity1 = oftenCity1;
	}

	public String getOftenCity2() {
		return oftenCity2;
	}

	public void setOftenCity2(String oftenCity2) {
		this.oftenCity2 = oftenCity2;
	}

	public String getOftenCity3() {
		return oftenCity3;
	}

	public void setOftenCity3(String oftenCity3) {
		this.oftenCity3 = oftenCity3;
	}

	public String getOftenCity4() {
		return oftenCity4;
	}

	public void setOftenCity4(String oftenCity4) {
		this.oftenCity4 = oftenCity4;
	}

	public String getOftenCity5() {
		return oftenCity5;
	}

	public void setOftenCity5(String oftenCity5) {
		this.oftenCity5 = oftenCity5;
	}

	public String getOftenCity6() {
		return oftenCity6;
	}

	public void setOftenCity6(String oftenCity6) {
		this.oftenCity6 = oftenCity6;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<MarketingDriverLine> getList() {
		if (list == null)
			list = new ArrayList<MarketingDriverLine>();
		return list;
	}

	public void setList(List<MarketingDriverLine> list) {
		this.list = list;
	}

	public int getRegThroughAssist() {
		return regThroughAssist;
	}

	public void setRegThroughAssist(int regThroughAssist) {
		this.regThroughAssist = regThroughAssist;
	}
}
