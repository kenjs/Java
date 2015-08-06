package com.cy.interfaceService.bo;

import java.io.Serializable;

/**
 * Created by haoy on 2014/12/22.
 */
public class PushLogInfo implements Serializable {
    private int id; //
    private String tagName;//ALL 所有用户 ONE 单个用户  其他自定义的tag_name
    private String driverId;//司机id
    private String pushTitle;//推送标题
    private String pushContent;//推送内容
    private String pushTime;//推送时间
    private String returnedValue;//第三方推送服务原始返回值
    private String isClicked;//用户是否有点击
    private String clickedTime;//用户点击时间
    private String baiduChannelId;//百度云推送channelId
    private String baiduUserId;//百度云推送userId
    private int eventFrom; //1 营销平台 2 Android Server 3 Web网站 4 经管系统

    private int pushChannel = 1;    //推送服务渠道

    private String tarId; //跳转对象ID
    private String jumpType;  //跳转方式
    private String companyName; //企业名称
    private String webUserId;

    private int logId;

    private int type;

    /** 极光 **/
    private String alias;   //设备别名
    private String tag;     //组名
    private String registrationId;

    public PushLogInfo() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public String getReturnedValue() {
        return returnedValue;
    }

    public void setReturnedValue(String returnedValue) {
        this.returnedValue = returnedValue;
    }

    public String getIsClicked() {
        return isClicked;
    }

    public void setIsClicked(String isClicked) {
        this.isClicked = isClicked;
    }

    public String getClickedTime() {
        return clickedTime;
    }

    public void setClickedTime(String clickedTime) {
        this.clickedTime = clickedTime;
    }

    public String getBaiduChannelId(String baiduChannelId) {
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

    public String getBaiduChannelId() {
        return baiduChannelId;
    }

    public int getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(int eventFrom) {
        this.eventFrom = eventFrom;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getPushChannel() {
        return pushChannel;
    }

    public void setPushChannel(int pushChannel) {
        this.pushChannel = pushChannel;
    }

    public String getTarId() {
        return tarId;
    }

    public void setTarId(String tarId) {
        this.tarId = tarId;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }
}
