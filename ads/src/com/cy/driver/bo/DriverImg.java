package com.cy.driver.bo;

import java.io.Serializable;

/**
 * Created by haoy on 2015/1/21.
 */
public class DriverImg implements Serializable {
    private int id;
    private int driverId;
    private String imgPath; //司机信息图片路径
    private int imgType;    //司机信息图片类型 （1 身份证正面,2 身份证反面,3 驾驶证,4 行驶证,5 营运证,6 头像）
    private int submitType; //认证状态（0未提交1已提交2 认证未通过 3 认证已通过）
    private String submitTime;  //认证时间

    public DriverImg() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public int getSubmitType() {
        return submitType;
    }

    public void setSubmitType(int submitType) {
        this.submitType = submitType;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
}
