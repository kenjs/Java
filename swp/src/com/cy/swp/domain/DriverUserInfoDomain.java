package com.cy.swp.domain;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cy.swp.common.constants.Constants;


public class DriverUserInfoDomain extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -776351885092812258L;

	private int id;//主键（自增、初始值1）
	private String code;//登录帐号(手机号)
	private String password;//登录密码(MD5)
	private String name;//司机姓名
	private String carNumber;//车辆牌照号码
	private String freezeFlag;//冻结标志（0:正常，1:冻结)
	private String auditFlag;//审核标志（0:审核中,1:通过,-1未通过）
	private String telephone;//联系电话
	private String identityLicenseNum;//身份证
	private String operatingLicense;//营运证路径
    private String driversLicense;//驾驶证路径
	private String drivingLicense;//行驶证路径
    private String carLength;//车长
	private String carWeight;//运力-吨位
	private String carCubage;//运力-体积
	private String carPlateType;//板-平板、高低板
	private String carBarType;//栏-高栏、低栏
	private String carTypes;//车型（车长、板、栏、运力等合起来)
	private String remark;//备注
	private String deleteFlag;//删除标志（1:已删除,0:未删除）
	private String carStateType;//当前运营状态（默认1求货、2满载、3休息）
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	private String headPortrait;//用户头像 
	private String identityLicenseNumFront;//司机身份证正面照片
	private String identityLicenseNumContrary;//司机身份证反面照片
	private String newOrOldAppUser;//新老用户标识（默认0（0新用户，1老用户））
	private String baiduChannelId;
	private String baiduUserId;
	private String submitType;//审核提交标识（0未提交1已提交2审核未通过3审核以通过）
	
	private String cargoId;//货源Id
	private String startProvince;//开始省
	private String startCity;//开始市
	private String startCounty;//开始区
	private String endProvince;//结束省
	private String endCity;//结束市
	private String endCounty;//结束区
	private String lastLocation;// 当前位置
	private String driverLine;//路线
	
	private String assistId;//协助Id
	
	private String contactDriverCounts;//联系司机的次数
	private String contactDriverVal;//是否联系司机
	private String driverReply;//司机回复结果
	private String driverReplyVal;//司机回复结果值
	private String lastTime;//最后定位时间
    private Integer countDriverLine;//司机运营线路计数
    private Integer countMonAct;//t_dayrecord_driver_active按天统计司机存活与活跃记录表中的记录数
    private String usedRecentTime;//司机最近使用时间
    private String user15DayNum;//近15天使用次数

    public String getUsedRecentTime() {
        return usedRecentTime;
    }

    public void setUsedRecentTime(String usedRecentTime) {
        this.usedRecentTime = usedRecentTime;
    }

    public String getUser15DayNum() {
        return user15DayNum;
    }

    public void setUser15DayNum(String user15DayNum) {
        this.user15DayNum = user15DayNum;
    }

    public Integer getCountDriverLine() {
        return countDriverLine;
    }

    public void setCountDriverLine(Integer countDriverLine) {
        this.countDriverLine = countDriverLine;
    }

    public Integer getCountMonAct() {
        return countMonAct;
    }

    public void setCountMonAct(Integer countMonAct) {
        this.countMonAct = countMonAct;
    }

    public String getHtml1() {
		return html1;
	}

	public void setHtml1(String html1) {
		this.html1 = html1;
	}

	public String getHtml2() {
		return html2;
	}

	public void setHtml2(String html2) {
		this.html2 = html2;
	}

	private String menuAId;
	
	private List<DriverUserInfoDomain> list;

	private String html1;
	private String html2;
	
	public DriverUserInfoDomain() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	public String getCargoId() {
		return cargoId;
	}
	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
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
	public String getLastLocation() {
		return lastLocation;
	}
	public void setLastLocation(String lastLocation) {
		this.lastLocation = lastLocation;
	}
	public List<DriverUserInfoDomain> getList() {
		return list;
	}
	public void setList(List<DriverUserInfoDomain> list) {
		this.list = list;
	}
	public String getMenuAId() {
		return menuAId;
	}
	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}
	public String getDriverLine() {
		return driverLine;
	}
	public void setDriverLine(String driverLine) {
		this.driverLine = driverLine;
	}
	public String getAssistId() {
		return assistId;
	}
	public void setAssistId(String assistId) {
		this.assistId = assistId;
	}
	public String getContactDriverCounts() {
		return contactDriverCounts;
	}
	public void setContactDriverCounts(String contactDriverCounts) {
		this.contactDriverCounts = contactDriverCounts;
		if (StringUtils.isEmpty(contactDriverCounts)) {
            return;
        }
        try {
        	if(Constants.NOT_CONTACTED_KEY.equals(contactDriverCounts)){
    			this.setContactDriverVal(Constants.NOT_CONTACTED_VALUE);
    		}else{
    			this.setContactDriverVal(Constants.ALREADY_CONTACTED_VALUE);
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}
	public String getDriverReply() {
		return driverReply;
	}
	public void setDriverReply(String driverReply) {
		this.driverReply = driverReply;
		if (StringUtils.isEmpty(driverReply)) {
            return;
        }
        try {
        	if(Constants.DRIVER_REPLYRESULT_INTENTION_KEY.equals(driverReply)){
    			this.setDriverReplyVal(Constants.DRIVER_REPLYRESULT_INTENTION_VALUE);
    		}else if(Constants.DRIVER_REPLYRESULT_NOT_INTENTION_KEY.equals(driverReply)){
    			this.setDriverReplyVal(Constants.DRIVER_REPLYRESULT_NOT_INTENTION_VALUE);
    		}else if(Constants.DRIVER_REPLYRESULT_UNKNOWN_KEY.equals(driverReply)){
    			this.setDriverReplyVal(Constants.DRIVER_REPLYRESULT_UNKNOWN_VALUE);
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	public String getContactDriverVal() {
		return contactDriverVal;
	}
	public void setContactDriverVal(String contactDriverVal) {
		this.contactDriverVal = contactDriverVal;
	}
	public String getDriverReplyVal() {
		return driverReplyVal;
	}
	public void setDriverReplyVal(String driverReplyVal) {
		this.driverReplyVal = driverReplyVal;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	
	
}
