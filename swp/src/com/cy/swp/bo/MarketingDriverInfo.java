package com.cy.swp.bo;

import java.util.Date;

/**
 * 司机客户资料表
 * Created by wyh on 2014/12/9.
 */
public class MarketingDriverInfo extends BaseBo {
    private static final long serialVersionUID = -9056343161726691150L;
    private Integer id;
    private String name;//司机姓名
    private String mobilePhone;//手机号码
    private Integer markInvalidNums;//主手机号码标注无效的次数
    private String mobilePhone2;//第二手机号码
    private String mobilePhone3;//第三手机号码
    private Integer driverId;//注册司机id(当司机注册成功后，此id关联起来)
    private String carNumber;//车牌号(默认值"")
    private String carLength;//车长(默认值"")
    private String carPlateType;//板-平板、高低板(默认值"")
    private String carBarType;//栏-高栏、低栏(默认值"")
    private String carWeight;//运力-吨位(默认值"")
    private String carCubage;//运力-体积(默认值"")
    private String carTypes;//车型（车长、车宽、板、栏、运力等合起来）
    private String realLevel;//实时客户等级(ABCD，默认值D)private String carTypes;//车型（车长、车宽、板、栏、运力等合起来）
    private String category;//客户类别(1 2 3 4 5)
    private Date lastContactDate;//最近联系时间
    private Date nextContactDate;//下次联系时间
    private Integer allocateStatus;//分配状态 0 待分配 1 分配中 2 已分配(默认值0)
    private Integer assisterId;//营销专员id(当分配状态为1或2时，此字段不能为空)
    private Integer distributerId;//分配者id(指有权限进行客户分配的营销人员id)
    private Date distributTime;//分配时间
    private String oftenCity1;//常跑城市一
    private String oftenCity2;//常跑城市二
    private String oftenCity3;//常跑城市三
    private String oftenCity4;//常跑城市四
    private String oftenCity5;//常跑城市五
    private String oftenCity6;//常跑城市六
    private Date createTime;//创建时间
    private Date lastModifyTime;//最后修改时间
    private Integer deleteFlag;//0 未删除 1 已删除
    private Integer regThroughAssist;//是否营销人员发展的注册用户：0 否 1 是
    private Integer authThroughAssist;//是否营销人员发展的注册用户：0 否 1 是
    private Integer phoneValid;//号码是否有效：0 无效 1 有效
    private Integer hasPurpose;//是否有意向 0 无意向 1 有意向
    private String qqNumber;//QQ号码

    public Integer getMarkInvalidNums() {
        return markInvalidNums;
    }

    public void setMarkInvalidNums(Integer markInvalidNums) {
        this.markInvalidNums = markInvalidNums;
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

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public Integer getRegThroughAssist() {
        return regThroughAssist;
    }

    public void setRegThroughAssist(Integer regThroughAssist) {
        this.regThroughAssist = regThroughAssist;
    }

    public Integer getAuthThroughAssist() {
        return authThroughAssist;
    }

    public void setAuthThroughAssist(Integer authThroughAssist) {
        this.authThroughAssist = authThroughAssist;
    }

    public Integer getPhoneValid() {
        return phoneValid;
    }

    public void setPhoneValid(Integer phoneValid) {
        this.phoneValid = phoneValid;
    }

    public Integer getHasPurpose() {
        return hasPurpose;
    }

    public void setHasPurpose(Integer hasPurpose) {
        this.hasPurpose = hasPurpose;
    }

    public Integer getDistributerId() {
        return distributerId;
    }

    public void setDistributerId(Integer distributerId) {
        this.distributerId = distributerId;
    }

    public Date getDistributTime() {
        return distributTime;
    }

    public void setDistributTime(Date distributTime) {
        this.distributTime = distributTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
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

    public String getRealLevel() {
        return realLevel;
    }

    public void setRealLevel(String realLevel) {
        this.realLevel = realLevel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getLastContactDate() {
        return lastContactDate;
    }

    public void setLastContactDate(Date lastContactDate) {
        this.lastContactDate = lastContactDate;
    }

    public Date getNextContactDate() {
        return nextContactDate;
    }

    public void setNextContactDate(Date nextContactDate) {
        this.nextContactDate = nextContactDate;
    }

    public Integer getAllocateStatus() {
        return allocateStatus;
    }

    public void setAllocateStatus(Integer allocateStatus) {
        this.allocateStatus = allocateStatus;
    }

    public Integer getAssisterId() {
        return assisterId;
    }

    public String getOftenCity6() {
        return oftenCity6;
    }

    public void setOftenCity6(String oftenCity6) {
        this.oftenCity6 = oftenCity6;
    }

    public void setAssisterId(Integer assisterId) {
        this.assisterId = assisterId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(String carTypes) {
        this.carTypes = carTypes;
    }

}
