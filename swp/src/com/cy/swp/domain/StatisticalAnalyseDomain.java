package com.cy.swp.domain;

/**
 * 统计分析记录数据
 * Created by wyh on 2015/1/6.
 */
public class StatisticalAnalyseDomain extends BaseDomain {
    private static final long serialVersionUID = 6104539026881106261L;

    private Integer marketingUserId;//营销平台用户id
    private String name;//营销平台用户名称
    private Integer position;//营销平台用户 职务 0 高层管理 1 经理 2 主管 3 组长 4 专员
    private Integer joinGroup;//营销平台用户 所属组： 0 未分组1 营销一组 2 营销二组 3 营销三组
    private Integer customerNum;//客户数量
    private Integer callOutNum;//呼出数量
    private Integer validPhoneNum;//有效电话量
    private Integer driverRegNum;//司机注册量
    private Integer driverAuthNum;//司机认证量

    public Integer getMarketingUserId() {
        return marketingUserId;
    }

    public void setMarketingUserId(Integer marketingUserId) {
        this.marketingUserId = marketingUserId;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getJoinGroup() {
        return joinGroup;
    }

    public void setJoinGroup(Integer joinGroup) {
        this.joinGroup = joinGroup;
    }

    public Integer getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(Integer customerNum) {
        this.customerNum = customerNum;
    }

    public Integer getCallOutNum() {
        return callOutNum;
    }

    public void setCallOutNum(Integer callOutNum) {
        this.callOutNum = callOutNum;
    }

    public Integer getValidPhoneNum() {
        return validPhoneNum;
    }

    public void setValidPhoneNum(Integer validPhoneNum) {
        this.validPhoneNum = validPhoneNum;
    }

    public Integer getDriverRegNum() {
        return driverRegNum;
    }

    public void setDriverRegNum(Integer driverRegNum) {
        this.driverRegNum = driverRegNum;
    }

    public Integer getDriverAuthNum() {
        return driverAuthNum;
    }

    public void setDriverAuthNum(Integer driverAuthNum) {
        this.driverAuthNum = driverAuthNum;
    }
}
