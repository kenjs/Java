package com.cy.swp.domain;

/**
 *
 * 货源匹配司机、司机信息类
 *
 * Created by nixianjing on 15/1/7.
 */
public class MatchingDriverInfoDomain extends BaseDomain {


    private Integer id;//司机表id
    private String code;//司机手机号码
    private String name;//司机名称
    private String carNumber;//车牌号
    private Integer locadate;//定位时间和当前时间相差多少时间（小时）
    private String markType;//匹配规则类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLocadate() {
        return locadate;
    }

    public void setLocadate(Integer locadate) {
        this.locadate = locadate;
    }

    public String getMarkType() {
        return markType;
    }

    public void setMarkType(String markType) {
        this.markType = markType;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
