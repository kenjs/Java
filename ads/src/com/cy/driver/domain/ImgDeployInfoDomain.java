package com.cy.driver.domain;

/**
 * Created by haoy on 2014/12/5.
 */
public class ImgDeployInfoDomain {
    private int id;
    private int orderNum;   //排序字段
    private String imgName;//图片文件名
    private String imgPath;//图片文件路径
    private String imgFilemd5;//图片文件MD5
    private double width;//最佳宽度
    private double height;//最佳高度
    private String useBeginDate;//使用开始日期
    private String useEndDate;//使用结束日期
    private int toApplication;//作用于哪个系统  1 app 2 web
    private int useFor;//用途(app中用1打头，web中用2打头)： 101 APP客户端首页轮播图片 201 web首页轮播广告位
    private String clickResponse;
    private String createTime;
    private String modifyTime;
    private int deleteFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgFilemd5() {
        return imgFilemd5;
    }

    public void setImgFilemd5(String imgFilemd5) {
        this.imgFilemd5 = imgFilemd5;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getUseBeginDate() {
        return useBeginDate;
    }

    public void setUseBeginDate(String useBeginDate) {
        this.useBeginDate = useBeginDate;
    }

    public String getUseEndDate() {
        return useEndDate;
    }

    public void setUseEndDate(String useEndDate) {
        this.useEndDate = useEndDate;
    }

    public int getToApplication() {
        return toApplication;
    }

    public void setToApplication(int toApplication) {
        this.toApplication = toApplication;
    }

    public int getUseFor() {
        return useFor;
    }

    public void setUseFor(int useFor) {
        this.useFor = useFor;
    }

    public String getClickResponse() {
        return clickResponse;
    }

    public void setClickResponse(String clickResponse) {
        this.clickResponse = clickResponse;
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

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public ImgDeployInfoDomain() {

    }
}
