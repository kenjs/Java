package com.cy.dctms.common.domain;

import java.util.List;

/**
*司机信息
* wjl
*/
public class DriverUserInfoDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // 主键（自增、初始值1）
    private String code;  // 登录帐号(手机号)
    private String password;  // 登录密码(MD5)
    private String name;  // 司机姓名
    private String carNumber;  // 车辆牌照号码
    private String freezeFlag;  // 冻结标志（0:正常，1:冻结)
    private String auditFlag;  // 审核标志（0:审核中,1:通过,-1未通过）
    private String telephone;  // 联系电话
    private String identityLicenseNum;  // 身份证
    private String operatingLicense;  // 营运证路径
    private String driversLicense;  // 驾驶证路径
    private String drivingLicense;  // 行驶证路径
    private String carLength;  // 车长
    private String carWeight;  // 运力-吨位
    private String carCubage;  // 运力-体积
    private String carPlateType;  // 板-平板、高低板
    private String carBarType;  // 栏-高栏、低栏
    private String carTypes;  // 车型（车长、板、栏、吨，方等合起来）
    private String remark;  // 备注
    private String deleteFlag;  // 删除标志（1:已删除,0:未删除）
    private String carStateType;  // 当前运营状态（默认1求货、2满载、3休息）
    private String createTime;  // 创建时间
    private String modifyTime;  // 修改时间
    private String headPortrait;  // 用户头像
    private String identityLicenseNumFront;  // 司机身份证正面照片
    private String identityLicenseNumContrary;  // 司机身份证反面照片
    private String newOrOldAppUser;  // 新老用户标识（默认0（0新用户，1老用户））
    private String baiduChannelId;  // 百度云推送channelId
    private String baiduUserId;  // 百度云推送userId
    private String submitType;   //审核提交标示
    
    //冗余字段
    private List<DriverUserInfoDomain> dataList;
    private String commitAuditTimeQ;
    private String commitAuditTimeZ;
    private List<String> reason;   //推送理由
    private String location; //司机完整位置
    private String lastTime; //最后定位时间
    private String registerTimeQ;//注册时间起
    private String registerTimeZ;//注册时间止
    private String currentLocationTimeQ;//当前位置时间起
    private String currentLocationTimeZ;//当前位置时间止
    private String accumulateTransactionCount;//交易累积数量
    private String todayTransactionCount;//今天交易数量
    private String passOrderCount;//成交订单量
    private String orderingCount;//被订车数量
    private String orderingRate;//被订车率
    private String orderingTotalCount;//被订车数量
    private String driverLine;//运营线路
    private String driverBusinessLine;//预约线路
	private String queryTime;//查询日期
	private String queryTimeQ;//查询日期起
	private String queryTimeZ;//查询日期止
	private String registerCount;//注册数量 
	private String goodFindNum;  // 找货总次数
	private String phoneCallNum;  // 电话拨打总数量
    private String quoteCount;//报价次数
    private String passOrderRate;//通过订单率
    private String isPushAll;//是否推送所有人，推送所有人为1，否则为0
    private String deleteOrModifyFlag;//删除还是修改标志0为删除，1为修改。
    private String deleteReason;//删除理由
    private List<String> idList;//id列表。用于批量发送信息时候，拿着id列表去查信息
	public String getDeleteReason() {
		return deleteReason;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public String getIsPushAll() {
		return isPushAll;
	}
	public String getDeleteOrModifyFlag() {
		return deleteOrModifyFlag;
	}
	public void setDeleteOrModifyFlag(String deleteOrModifyFlag) {
		this.deleteOrModifyFlag = deleteOrModifyFlag;
	}
	public void setIsPushAll(String isPushAll) {
		this.isPushAll = isPushAll;
	}
	public String getPassOrderRate() {
		return passOrderRate;
	}
	public void setPassOrderRate(String passOrderRate) {
		this.passOrderRate = passOrderRate;
	}
	public String getGoodFindNum() {
		return goodFindNum;
	}
	public void setGoodFindNum(String goodFindNum) {
		this.goodFindNum = goodFindNum;
	}
	public String getPhoneCallNum() {
		return phoneCallNum;
	}
	public void setPhoneCallNum(String phoneCallNum) {
		this.phoneCallNum = phoneCallNum;
	}
	public String getQuoteCount() {
		return quoteCount;
	}
	public void setQuoteCount(String quoteCount) {
		this.quoteCount = quoteCount;
	}
	public String getOrderingRate() {
		return orderingRate;
	}
	public void setOrderingRate(String orderingRate) {
		this.orderingRate = orderingRate;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getQueryTimeQ() {
		return queryTimeQ;
	}
	public void setQueryTimeQ(String queryTimeQ) {
		this.queryTimeQ = queryTimeQ;
	}
	public String getQueryTimeZ() {
		return queryTimeZ;
	}
	public void setQueryTimeZ(String queryTimeZ) {
		this.queryTimeZ = queryTimeZ;
	}
	public String getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(String registerCount) {
		this.registerCount = registerCount;
	}
	public String getRegisterTimeQ() {
		return registerTimeQ;
	}
	public String getDriverLine() {
		return driverLine;
	}
	public void setDriverLine(String driverLine) {
		this.driverLine = driverLine;
	}
	public String getDriverBusinessLine() {
		return driverBusinessLine;
	}
	public void setDriverBusinessLine(String driverBusinessLine) {
		this.driverBusinessLine = driverBusinessLine;
	}
	public void setRegisterTimeQ(String registerTimeQ) {
		this.registerTimeQ = registerTimeQ;
	}
	public String getRegisterTimeZ() {
		return registerTimeZ;
	}
	public void setRegisterTimeZ(String registerTimeZ) {
		this.registerTimeZ = registerTimeZ;
	}
	public String getOrderingTotalCount() {
		return orderingTotalCount;
	}
	public void setOrderingTotalCount(String orderingTotalCount) {
		this.orderingTotalCount = orderingTotalCount;
	}
	public String getPassOrderCount() {
		return passOrderCount;
	}
	public void setPassOrderCount(String passOrderCount) {
		this.passOrderCount = passOrderCount;
	}
	public String getOrderingCount() {
		return orderingCount;
	}
	public void setOrderingCount(String orderingCount) {
		this.orderingCount = orderingCount;
	}
	public String getCurrentLocationTimeQ() {
		return currentLocationTimeQ;
	}
	public void setCurrentLocationTimeQ(String currentLocationTimeQ) {
		this.currentLocationTimeQ = currentLocationTimeQ;
	}
	public String getCurrentLocationTimeZ() {
		return currentLocationTimeZ;
	}
	public void setCurrentLocationTimeZ(String currentLocationTimeZ) {
		this.currentLocationTimeZ = currentLocationTimeZ;
	}
	public List<String> getReason() {
		return reason;
	}
	public void setReason(List<String> reason) {
		this.reason = reason;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getAccumulateTransactionCount() {
		return accumulateTransactionCount;
	}
	public void setAccumulateTransactionCount(String accumulateTransactionCount) {
		this.accumulateTransactionCount = accumulateTransactionCount;
	}
	public String getTodayTransactionCount() {
		return todayTransactionCount;
	}
	public void setTodayTransactionCount(String todayTransactionCount) {
		this.todayTransactionCount = todayTransactionCount;
	}
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	public String getCommitAuditTimeQ() {
		return commitAuditTimeQ;
	}
	public void setCommitAuditTimeQ(String commitAuditTimeQ) {
		this.commitAuditTimeQ = commitAuditTimeQ;
	}
	public String getCommitAuditTimeZ() {
		return commitAuditTimeZ;
	}
	public void setCommitAuditTimeZ(String commitAuditTimeZ) {
		this.commitAuditTimeZ = commitAuditTimeZ;
	}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public String getFreezeFlag() {
        return freezeFlag;
    }
    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag;
    }
    public String getAuditFlag() {
        return auditFlag;
    }
    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getIdentityLicenseNum() {
        return identityLicenseNum;
    }
    public void setIdentityLicenseNum(String identityLicenseNum) {
        this.identityLicenseNum = identityLicenseNum;
    }
    public String getOperatingLicense() {
        return operatingLicense;
    }
    public void setOperatingLicense(String operatingLicense) {
        this.operatingLicense = operatingLicense;
    }
    public String getDriversLicense() {
        return driversLicense;
    }
    public void setDriversLicense(String driversLicense) {
        this.driversLicense = driversLicense;
    }
    public String getDrivingLicense() {
        return drivingLicense;
    }
    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
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
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public String getCarStateType() {
        return carStateType;
    }
    public void setCarStateType(String carStateType) {
        this.carStateType = carStateType;
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
    public String getHeadPortrait() {
        return headPortrait;
    }
    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
    public String getIdentityLicenseNumFront() {
        return identityLicenseNumFront;
    }
    public void setIdentityLicenseNumFront(String identityLicenseNumFront) {
        this.identityLicenseNumFront = identityLicenseNumFront;
    }
    public String getIdentityLicenseNumContrary() {
        return identityLicenseNumContrary;
    }
    public void setIdentityLicenseNumContrary(String identityLicenseNumContrary) {
        this.identityLicenseNumContrary = identityLicenseNumContrary;
    }
    public String getNewOrOldAppUser() {
        return newOrOldAppUser;
    }
    public void setNewOrOldAppUser(String newOrOldAppUser) {
        this.newOrOldAppUser = newOrOldAppUser;
    }
    public String getBaiduChannelId() {
        return baiduChannelId;
    }
    public void setBaiduChannelId(String baiduChannelId) {
        this.baiduChannelId = baiduChannelId;
    }
    public String getBaiduUserId() {
        return baiduUserId;
    }
    public void setBaiduUserId(String baiduUserId) {
        this.baiduUserId = baiduUserId;
    }
    public List<DriverUserInfoDomain> getDataList() {    
    	return dataList;  
    	}  
    public void setDataList(List<DriverUserInfoDomain> dataList) { 
    	this.dataList = dataList;   
    	}
    }