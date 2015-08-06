package com.cy.dctms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 货源表
 * 
 * @author zdy
 * 
 */
public class OrderCargoInfo implements Serializable {
	
	private static final long serialVersionUID = 8617332575268144232L;
	
	private String id;
	private String cargoName;// 货物名称
	private String cargoType;// 货物类型
	private double cargoWeight;// 货物重量
	private double cargoCubage;// 货物体积
	private String requestCarLength;// 车型要求（车长）
	private String requestCarBarType;// 车型要求（车 栏）
	private String requestCarPlateType;//板-平板、高低板
	private double freight;// 发布运费价格
	private Date requestStartTime;// 要求装货时间
	private Date requestEndTime;// 要求到货时间
	private String startProvince;// 装货地-省
	private String startCity;// 装货地-市
	private String startCounty;// 装货地-县
	private String startTown;// 装货地-自定义地址
	private String endProvince;// 卸货-省
	private String endCity;// 卸货-市
	private String endCounty;// 卸货-县
	private String endTown;// 卸货地-自定义地址
	private String contactName;// 联系人
	private String contactMobilephone;// 手机
	private String contactTelephone;// 固定电话
	private String remark;// 备注
	private String deployUserid;// 发布用户ID
	private String modifyUserid;// 修改用户ID
	private String companyId;// 企业ID
	private String cargoFlag;// 货源状态
	private String deletedFlag;// 删除状态
	private Date cargoFlagTime;// 状态修改时间
	private Date createTime;// 创建时间
	private Date modifyTime;// 修改时间

	private String companyName;//企业名称
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	}

	public double getCargoWeight() {
		return cargoWeight;
	}

	public void setCargoWeight(double cargoWeight) {
		this.cargoWeight = cargoWeight;
	}

	public double getCargoCubage() {
		return cargoCubage;
	}

	public void setCargoCubage(double cargoCubage) {
		this.cargoCubage = cargoCubage;
	}

	public String getRequestCarLength() {
		return requestCarLength;
	}

	public void setRequestCarLength(String requestCarLength) {
		this.requestCarLength = requestCarLength;
	}

	public String getRequestCarBarType() {
		return requestCarBarType;
	}

	public void setRequestCarBarType(String requestCarBarType) {
		this.requestCarBarType = requestCarBarType;
	}

	public double getFreight() {
		return freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public Date getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(Date requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public Date getRequestEndTime() {
		return requestEndTime;
	}

	public void setRequestEndTime(Date requestEndTime) {
		this.requestEndTime = requestEndTime;
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

	public String getStartCounty() {
		return startCounty;
	}

	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}

	public String getStartTown() {
		return startTown;
	}

	public void setStartTown(String startTown) {
		this.startTown = startTown;
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

	public String getEndCounty() {
		return endCounty;
	}

	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}

	public String getEndTown() {
		return endTown;
	}

	public void setEndTown(String endTown) {
		this.endTown = endTown;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobilephone() {
		return contactMobilephone;
	}

	public void setContactMobilephone(String contactMobilephone) {
		this.contactMobilephone = contactMobilephone;
	}

	public String getContactTelephone() {
		return contactTelephone;
	}

	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeployUserid() {
		return deployUserid;
	}

	public void setDeployUserid(String deployUserid) {
		this.deployUserid = deployUserid;
	}

	public String getModifyUserid() {
		return modifyUserid;
	}

	public void setModifyUserid(String modifyUserid) {
		this.modifyUserid = modifyUserid;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCargoFlag() {
		return cargoFlag;
	}

	public void setCargoFlag(String cargoFlag) {
		this.cargoFlag = cargoFlag;
	}

	public Date getCargoFlagTime() {
		return cargoFlagTime;
	}

	public void setCargoFlagTime(Date cargoFlagTime) {
		this.cargoFlagTime = cargoFlagTime;
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

	public String getRequestCarPlateType() {
		return requestCarPlateType;
	}

	public void setRequestCarPlateType(String requestCarPlateType) {
		this.requestCarPlateType = requestCarPlateType;
	}

	public String getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
