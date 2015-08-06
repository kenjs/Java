package com.cy.swp.domain;

import com.cy.swp.bo.BaseBo;
import com.cy.swp.bo.MarketingUserInfo;

import java.util.List;

/**
 * 司机客户资料表domain
 * Created by wyh on 2014/12/9.
 */
public class MarketingDriverInfoDomain extends BaseDomain {
    private static final long serialVersionUID = 7068596300319681389L;
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
    private String realLevel;//实时客户等级(ABCD，默认值D)
    private String category;//客户类别(1 2 3 4 5)
    private String lastContactDate;//最近联系时间
    private String nextContactDate;//下次联系时间
    private Integer allocateStatus;//分配状态 0 待分配 1 分配中 2 已分配(默认值0)
    private Integer assisterId;//营销专员id(当分配状态为1或2时，此字段不能为空)
    private Integer distributerId;//分配者id(指有权限进行客户分配的营销人员id)
    private String distributTime;//分配时间
    private String oftenCity1;//常跑城市一
    private String oftenCity2;//常跑城市二
    private String oftenCity3;//常跑城市三
    private String oftenCity4;//常跑城市四
    private String oftenCity5;//常跑城市五
    private String oftenCity6;//常跑城市六
    private String createTime;//创建时间
    private String lastModifyTime;//最后修改时间
    private Integer deleteFlag;//0 未删除 1 已删除
    private Integer regThroughAssist;//是否营销人员发展的注册用户：0 否 1 是
    private Integer authThroughAssist;//是否营销人员发展的注册用户：0 否 1 是
    private Integer phoneValid;//号码是否有效：0 无效 1 有效
    private Integer hasPurpose;//是否有意向 0 无意向 1 有意向
    private String qqNumber;//QQ号码

    private List<MarketingDriverInfoDomain> list;//司机客户资料list
    private List<MarketingUserInfo> marketingUserlist;//营销人员list
    private String driverIds;//司机ids
    private String assisterName;//营销专员名称
    private String joinGroup;//营销专员所属组： 0 未分组1 营销一组 2 营销二组 3 营销三组
    private String distributerName;//分配者名称
    private String lastUserTime;//最近使用时间
    private String user15DayNum;//近15天使用次数
    private String lastLocation;//最近定位地点
    private String lastLocationTime;//最近定位时间
    private String customerType;//客户状态
    private String assisterApplyIds;//客户分配申请表ids
    private String noCustomType;//未联系客户查询类型（1所有客户 2预约客户）
    private Integer noCustomMonth;//未联系客户中所有客户查询未联系月数
    private String startNextDate;//预约查询开始时间
    private String endNextDate;//预约查询截至时间
    private String listHtml;//司机客户资料listhtml
    private String pageHtml;//分页html
    private Integer assisterIdQuery;//营销专员id页面查询查询条件
    private Integer optRegister;//查询条件 0未注册 1已注册
    private String reghtTime;//注册时间


    private Integer transactionCount;//交易笔数
    private String identityLicenseNum;//身份证
    private String driversLicense;//驾驶证路径
    private String drivingLicense;//行驶证路径

    public String getReghtTime() {
        return reghtTime;
    }

    public void setReghtTime(String reghtTime) {
        this.reghtTime = reghtTime;
    }

    public Integer getOptRegister() {
        return optRegister;
    }

    public void setOptRegister(Integer optRegister) {
        this.optRegister = optRegister;
    }

    public Integer getAssisterIdQuery() {
        return assisterIdQuery;
    }

    public void setAssisterIdQuery(Integer assisterIdQuery) {
        this.assisterIdQuery = assisterIdQuery;
    }


    public String getListHtml() {
        if(listHtml == null){
            listHtml = "";
        }
        return listHtml;
    }

    public void setListHtml(String listHtml) {
        this.listHtml = listHtml;
    }

    public String getPageHtml() {
        if(pageHtml == null){
            pageHtml = "";
        }
        return pageHtml;
    }

    public void setPageHtml(String pageHtml) {
        this.pageHtml = pageHtml;
    }

    public Integer getMarkInvalidNums() {
        return markInvalidNums;
    }

    public void setMarkInvalidNums(Integer markInvalidNums) {
        this.markInvalidNums = markInvalidNums;
    }

    public String getMobilePhone2() {
        if(mobilePhone2 == null){
            mobilePhone2 = "";
        }
        return mobilePhone2;
    }

    public void setMobilePhone2(String mobilePhone2) {
        this.mobilePhone2 = mobilePhone2;
    }

    public String getMobilePhone3() {
        if(mobilePhone3 == null){
            mobilePhone3 = "";
        }
        return mobilePhone3;
    }

    public void setMobilePhone3(String mobilePhone3) {
        this.mobilePhone3 = mobilePhone3;
    }

    public Integer getNoCustomMonth() {
        return noCustomMonth;
    }

    public void setNoCustomMonth(Integer noCustomMonth) {
        this.noCustomMonth = noCustomMonth;
    }

    public String getNoCustomType() {
        if(noCustomType == null){
            noCustomType = "";
        }
        return noCustomType;
    }

    public void setNoCustomType(String noCustomType) {
        this.noCustomType = noCustomType;
    }

    public String getStartNextDate() {
        if(startNextDate == null){
            startNextDate = "";
        }
        return startNextDate;
    }

    public void setStartNextDate(String startNextDate) {
        this.startNextDate = startNextDate;
    }

    public String getEndNextDate() {
        if(endNextDate == null){
            endNextDate = "";
        }
        return endNextDate;
    }

    public void setEndNextDate(String endNextDate) {
        this.endNextDate = endNextDate;
    }

    public List<MarketingUserInfo> getMarketingUserlist() {
        return marketingUserlist;
    }

    public void setMarketingUserlist(List<MarketingUserInfo> marketingUserlist) {
        this.marketingUserlist = marketingUserlist;
    }

    public String getAssisterApplyIds() {
        if(assisterApplyIds == null){
            assisterApplyIds = "";
        }
        return assisterApplyIds;
    }

    public void setAssisterApplyIds(String assisterApplyIds) {
        this.assisterApplyIds = assisterApplyIds;
    }

    public String getLastUserTime() {
        if(lastUserTime == null){
            lastUserTime = "";
        }
        return lastUserTime;
    }

    public void setLastUserTime(String lastUserTime) {
        this.lastUserTime = lastUserTime;
    }

    public String getUser15DayNum() {
        if(user15DayNum == null){
            user15DayNum = "";
        }
        return user15DayNum;
    }

    public void setUser15DayNum(String user15DayNum) {
        this.user15DayNum = user15DayNum;
    }

    public String getLastLocation() {
        if(lastLocation == null){
            lastLocation = "";
        }
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public String getLastLocationTime() {
        if(lastLocationTime == null){
            lastLocationTime = "";
        }
        return lastLocationTime;
    }

    public void setLastLocationTime(String lastLocationTime) {
        this.lastLocationTime = lastLocationTime;
    }

    public String getCustomerType() {
        if(customerType == null){
            customerType = "";
        }
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDistributerId() {
        return distributerId;
    }

    public void setDistributerId(Integer distributerId) {
        this.distributerId = distributerId;
    }

    public String getDistributTime() {
        return distributTime;
    }

    public void setDistributTime(String distributTime) {
        this.distributTime = distributTime;
    }

    public List<MarketingDriverInfoDomain> getList() {
        return list;
    }

    public void setList(List<MarketingDriverInfoDomain> list) {
        this.list = list;
    }

    public String getDriverIds() {
        return driverIds;
    }

    public void setDriverIds(String driverIds) {
        this.driverIds = driverIds;
    }

    public String getAssisterName() {
        return assisterName;
    }

    public void setAssisterName(String assisterName) {
        this.assisterName = assisterName;
    }

    public String getJoinGroup() {
        return joinGroup;
    }

    public void setJoinGroup(String joinGroup) {
        this.joinGroup = joinGroup;
    }

    public String getDistributerName() {
        return distributerName;
    }

    public void setDistributerName(String distributerName) {
        this.distributerName = distributerName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        if(name == null){
            name = "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        if(mobilePhone == null){
            mobilePhone = "";
        }
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
        if(carNumber == null){
            carNumber = "";
        }
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarLength() {
        if(carLength == null){
            carLength = "";
        }
        return carLength;
    }

    public void setCarLength(String carLength) {
        this.carLength = carLength;
    }

    public String getCarPlateType() {
        if(carPlateType == null){
            carPlateType = "";
        }
        return carPlateType;
    }

    public void setCarPlateType(String carPlateType) {
        this.carPlateType = carPlateType;
    }

    public String getCarBarType() {
        if(carBarType == null){
            carBarType = "";
        }
        return carBarType;
    }

    public void setCarBarType(String carBarType) {
        this.carBarType = carBarType;
    }

    public String getCarWeight() {
        if(carWeight == null){
            carWeight = "";
        }
        return carWeight;
    }

    public void setCarWeight(String carWeight) {
        this.carWeight = carWeight;
    }

    public String getCarCubage() {
        if(carCubage == null){
            carCubage = "";
        }
        return carCubage;
    }

    public void setCarCubage(String carCubage) {
        this.carCubage = carCubage;
    }

    public String getRealLevel() {
        if(realLevel == null){
            realLevel = "";
        }
        return realLevel;
    }

    public void setRealLevel(String realLevel) {
        this.realLevel = realLevel;
    }

    public String getCategory() {
        if(category == null){
            category = "";
        }
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLastContactDate() {
        if(lastContactDate == null){
            lastContactDate = "";
        }
        return lastContactDate;
    }

    public void setLastContactDate(String lastContactDate) {
        this.lastContactDate = lastContactDate;
    }

    public String getNextContactDate() {
        if(nextContactDate == null){
            nextContactDate = "";
        }
        return nextContactDate;
    }

    public void setNextContactDate(String nextContactDate) {
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

    public void setAssisterId(Integer assisterId) {
        this.assisterId = assisterId;
    }

    public String getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(String carTypes) {
        this.carTypes = carTypes;
    }

    public String getOftenCity6() {
        return oftenCity6;
    }

    public void setOftenCity6(String oftenCity6) {
        this.oftenCity6 = oftenCity6;
    }

    public String getOftenCity1() {
        if(oftenCity1 == null){
            oftenCity1 = "";
        }
        return oftenCity1;
    }

    public void setOftenCity1(String oftenCity1) {
        this.oftenCity1 = oftenCity1;
    }

    public String getOftenCity2() {
        if(oftenCity2 == null){
            oftenCity2 = "";
        }
        return oftenCity2;
    }

    public void setOftenCity2(String oftenCity2) {
        this.oftenCity2 = oftenCity2;
    }

    public String getOftenCity3() {
        if(oftenCity3 == null){
            oftenCity3 = "";
        }
        return oftenCity3;
    }

    public void setOftenCity3(String oftenCity3) {
        this.oftenCity3 = oftenCity3;
    }

    public String getOftenCity4() {
        if(oftenCity4 == null){
            oftenCity4 = "";
        }
        return oftenCity4;
    }

    public void setOftenCity4(String oftenCity4) {
        this.oftenCity4 = oftenCity4;
    }

    public String getOftenCity5() {
        if(oftenCity5 == null){
            oftenCity5 = "";
        }
        return oftenCity5;
    }

    public void setOftenCity5(String oftenCity5) {
        this.oftenCity5 = oftenCity5;
    }

    public String getCreateTime() {
        if(createTime == null){
            createTime = "";
        }
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        if(lastModifyTime == null){
            lastModifyTime = "";
        }
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public String getIdentityLicenseNum() {
        return identityLicenseNum;
    }

    public void setIdentityLicenseNum(String identityLicenseNum) {
        this.identityLicenseNum = identityLicenseNum;
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

    public Integer getRegThroughAssist() {
        return regThroughAssist;
    }

    public void setRegThroughAssist(Integer regThroughAssist) {
        this.regThroughAssist = regThroughAssist;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
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
}
