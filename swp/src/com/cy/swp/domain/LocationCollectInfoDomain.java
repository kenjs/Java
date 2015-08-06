package com.cy.swp.domain;

import java.util.List;

/**
 * 车辆位置历史信息表Domain
 *
 * @author zdy
 */
public class LocationCollectInfoDomain extends BaseDomain {
    private static final long serialVersionUID = 8617332575268144232L;
    private String id;
    private String driverId;//车源ID
    private String longitude;//经度
    private String latitude;//纬度
    private String province;//省
    private String city;//市
    private String county;//区县
    private String town;//位置-乡镇、街道(包含其它)
    private String location;//完整位置信息
    private String collectTime;//位置采集时间
    private String createTime;//创建时间
    private String html;//存放返回html页面值


    private List<LocationCollectInfoDomain> list;

    public List<LocationCollectInfoDomain> getList() {
        return list;
    }

    public void setList(List<LocationCollectInfoDomain> list) {
        this.list = list;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }




}
