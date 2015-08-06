package com.cy.driver.domain;

import org.apache.commons.lang.StringUtils;

/**
 * 货源domain
 * @date 2014-5-30
 * @author haoyong
 *
 */
public class OrderCargoInfoDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6350323539694309402L;

	private String id;//主键（自增、初始值1）
	private String cargoName;//货物名称
	private String cargoType;//货物类型
	private String cargoWeight;//重量（货物）
	private String cargoCubage;//体积（货物）
	private String requestCarLength;//车型要求（车长）
	private String requestCarPlateType;//板-平板、高低板
	private String requestCarBarType;//车型要求（车 栏）
	private String freight;//发布运费价格
	private String requestStartTime;//要求装货时间
	private String requestEndTime;//要求到货时间
	private String startProvince;//装货地-省
	private String startCity;//装货地-市
	private String startCounty;//装货地-县
	private String startTown;//装货地-自定义地址
	private String endProvince;//卸货地-省
	private String endCity;//卸货地-市
	private String endCounty;//卸货地-县
	private String endTown;//卸货地-自定义地址
	private String contactName;//联系人
	private String contactMobilephone;//手机
	private String contactTelephone;//固定电话
	private String remark;//备注
	private String deletedFlag;//删除状态
	private String deployUserid;//发布用户ID
	private String modifyUserid;//修改用户ID
	private String companyId;//企业ID
	private String cargoFlag;//货源状态
	private String cargoFlagTime;//状态修改时间
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	
	private String companyName;//企业名称
	private int collectNum;		//关注次数
	private int assessGoneNum;	//货源点评货已走次数
	private int assessExistNum;	//货源点评货还在次数
	private String isMyAttention = "n";  //是否是我关注的货源

	/**  找货的查询条件  **/

	private String driverId;//司机ID

	private String startTime; //时间起止
	private String endTime;

	private String minCarLength;//车长区间
	private String maxCarLength;

	private String minLoad;//载重区间
	private String maxLoad;

	private String carType;//车型

	private int way;	//查找貨源和附近貨源區別（1-找貨，0-附近）

	private int fromSize;//分页条件
	private int listSize;

	private String cargoIds;	//货源ID，排重用


	public OrderCargoInfoDomain() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCargoName() {
        if (cargoName == null) {
            return "";
        }
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getCargoType() {
        if (cargoType == null) {
            return "";
        }
		return cargoType;
	}

	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}

	public String getCargoWeight() {
        if (StringUtils.isBlank(cargoWeight)) {
            return "0";
        }
		return cargoWeight;
	}

	public void setCargoWeight(String cargoWeight) {
		this.cargoWeight = cargoWeight;
	}

	public String getCargoCubage() {
        if (StringUtils.isBlank(cargoCubage)) {
            return "0";
        }
		return cargoCubage;
	}

	public void setCargoCubage(String cargoCubage) {
		this.cargoCubage = cargoCubage;
	}

	public String getRequestCarLength() {
        if (requestCarLength == null) {
            return "";
        }
		return requestCarLength;
	}

	public void setRequestCarLength(String requestCarLength) {
		this.requestCarLength = requestCarLength;
	}

	public String getRequestCarPlateType() {
        if (requestCarPlateType == null) {
            return "";
        }
		return requestCarPlateType;
	}

	public void setRequestCarPlateType(String requestCarPlateType) {
		this.requestCarPlateType = requestCarPlateType;
	}

	public String getRequestCarBarType() {
        if (requestCarBarType == null) {
            return "";
        }
		return requestCarBarType;
	}

	public void setRequestCarBarType(String requestCarBarType) {
		this.requestCarBarType = requestCarBarType;
	}

	public String getFreight() {
        if (freight == null) {
            return "";
        }
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getRequestStartTime() {
        if (requestStartTime == null) {
            return "";
        }
		return requestStartTime;
	}

	public void setRequestStartTime(String requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public String getRequestEndTime() {
        if (requestEndTime == null) {
            return "";
        }
		return requestEndTime;
	}

	public void setRequestEndTime(String requestEndTime) {
		this.requestEndTime = requestEndTime;
	}

	public String getStartProvince() {
        if (startProvince == null) {
            return "";
        }
		return startProvince;
	}

	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}

	public String getStartCity() {
        if (startCity == null) {
            return "";
        }
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getStartCounty() {
        if (startCounty == null) {
            return "";
        }
		return startCounty;
	}

	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}

	public String getStartTown() {
        if (startTown == null) {
            return "";
        }
		return startTown;
	}

	public void setStartTown(String startTown) {
		this.startTown = startTown;
	}

	public String getEndProvince() {
        if (endProvince == null) {
            return "";
        }
		return endProvince;
	}

	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}

	public String getEndCity() {
        if (endCity == null) {
            return "";
        }
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public String getEndCounty() {
        if (endCounty == null) {
            return "";
        }
		return endCounty;
	}

	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}

	public String getEndTown() {
        if (endTown == null) {
            return "";
        }
		return endTown;
	}

	public void setEndTown(String endTown) {
		this.endTown = endTown;
	}

	public String getContactName() {
        if (contactName == null) {
            return "";
        }
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobilephone() {
        if (contactMobilephone == null) {
            return "";
        }
		return contactMobilephone;
	}

	public void setContactMobilephone(String contactMobilephone) {
		this.contactMobilephone = contactMobilephone;
	}

	public String getContactTelephone() {
        if (contactTelephone == null) {
            return "";
        }
        return contactTelephone;
	}

	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}

	public String getRemark() {
        if (remark == null) {
            return "";
        }
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getDeployUserid() {
		return deployUserid;
	}

	public void setDeployUserid(String deployUserid) {
		this.deployUserid = deployUserid;
	}

	public String getModifyUserid() {
        if (modifyUserid == null) {
            return "";
        }
		return modifyUserid;
	}

	public void setModifyUserid(String modifyUserid) {
		this.modifyUserid = modifyUserid;
	}

	public String getCompanyId() {
        if (companyId == null) {
            return "";
        }
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCargoFlag() {
        if (cargoFlag == null) {
            return "";
        }
		return cargoFlag;
	}

	public void setCargoFlag(String cargoFlag) {
		this.cargoFlag = cargoFlag;
	}

	public String getCargoFlagTime() {
        if (cargoFlagTime == null) {
            return "";
        }
		return cargoFlagTime;
	}

	public void setCargoFlagTime(String cargoFlagTime) {
		this.cargoFlagTime = cargoFlagTime;
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

	public String getCompanyName() {
        if (companyName == null) {
            return "";
        }
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getAssessGoneNum() {
		return assessGoneNum;
	}

	public void setAssessGoneNum(int assessGoneNum) {
		this.assessGoneNum = assessGoneNum;
	}

	public int getAssessExistNum() {
		return assessExistNum;
	}

	public void setAssessExistNum(int assessExistNum) {
		this.assessExistNum = assessExistNum;
	}

	public String getIsMyAttention() {
		return isMyAttention;
	}

	public void setIsMyAttention(String isMyAttention) {
		this.isMyAttention = isMyAttention;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMinCarLength() {
		return minCarLength;
	}

	public void setMinCarLength(String minCarLength) {
		this.minCarLength = minCarLength;
	}

	public String getMaxCarLength() {
		return maxCarLength;
	}

	public void setMaxCarLength(String maxCarLength) {
		this.maxCarLength = maxCarLength;
	}

	public String getMinLoad() {
		return minLoad;
	}

	public void setMinLoad(String minLoad) {
		this.minLoad = minLoad;
	}

	public String getMaxLoad() {
		return maxLoad;
	}

	public void setMaxLoad(String maxLoad) {
		this.maxLoad = maxLoad;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public int getFromSize() {
		return fromSize;
	}

	public void setFromSize(int fromSize) {
		this.fromSize = fromSize;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public String getCargoIds() {
		return cargoIds;
	}

	public void setCargoIds(String cargoIds) {
		this.cargoIds = cargoIds;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}
}
