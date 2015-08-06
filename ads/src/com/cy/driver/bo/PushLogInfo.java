package com.cy.driver.bo;

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

    public PushLogInfo() {
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
}
