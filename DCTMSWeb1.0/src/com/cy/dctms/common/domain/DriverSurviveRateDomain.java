package com.cy.dctms.common.domain;

import java.util.List;

/**
*查询存活率
* wjl
*/
public class DriverSurviveRateDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ID
    private String queryTime;  // 日期
    private String registerCount;  // 新注册数量
    private String foreRegisterCount;  // 前注册数量
    private String surviveCount;  // 存活量
    private String surviveRate;  // 存活率
    private String activeCount;  // 活跃量
    private String activeRate;  // 活跃率
    private String goodFindNum;  // 找货次数
    private String appointNum;  // 预约条数
    private String phoneCallNum;  // 电话拨打数量
    private String createTime;  // 创建时间
    //冗余
    private List<DriverSurviveRateDomain> dataList;
    private String queryTimeQ;//日期起
    private String queryTimeZ;//日期止
    private String registerTotalCount;  // 新注册总数量
    private String goodFindTotalNum;  // 找货总次数
    private String appointTotalNum;  // 预约总条数
    private String phoneCallTotalNum;  // 电话拨打总数量
    public String getQueryTimeQ() {
		return queryTimeQ;
	}
	public void setQueryTimeQ(String queryTimeQ) {
		this.queryTimeQ = queryTimeQ;
	}
	public String getRegisterTotalCount() {
		return registerTotalCount;
	}
	public void setRegisterTotalCount(String registerTotalCount) {
		this.registerTotalCount = registerTotalCount;
	}
	public String getGoodFindTotalNum() {
		return goodFindTotalNum;
	}
	public void setGoodFindTotalNum(String goodFindTotalNum) {
		this.goodFindTotalNum = goodFindTotalNum;
	}
	public String getAppointTotalNum() {
		return appointTotalNum;
	}
	public void setAppointTotalNum(String appointTotalNum) {
		this.appointTotalNum = appointTotalNum;
	}
	public String getPhoneCallTotalNum() {
		return phoneCallTotalNum;
	}
	public void setPhoneCallTotalNum(String phoneCallTotalNum) {
		this.phoneCallTotalNum = phoneCallTotalNum;
	}
	public String getQueryTimeZ() {
		return queryTimeZ;
	}
	public void setQueryTimeZ(String queryTimeZ) {
		this.queryTimeZ = queryTimeZ;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getQueryTime() {
        return queryTime;
    }
    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }
    public String getRegisterCount() {
        return registerCount;
    }
    public void setRegisterCount(String registerCount) {
        this.registerCount = registerCount;
    }
    public String getForeRegisterCount() {
        return foreRegisterCount;
    }
    public void setForeRegisterCount(String foreRegisterCount) {
        this.foreRegisterCount = foreRegisterCount;
    }
    public String getSurviveCount() {
        return surviveCount;
    }
    public void setSurviveCount(String surviveCount) {
        this.surviveCount = surviveCount;
    }
    public String getSurviveRate() {
        return surviveRate;
    }
    public void setSurviveRate(String surviveRate) {
        this.surviveRate = surviveRate;
    }
    public String getActiveCount() {
        return activeCount;
    }
    public void setActiveCount(String activeCount) {
        this.activeCount = activeCount;
    }
    public String getActiveRate() {
        return activeRate;
    }
    public void setActiveRate(String activeRate) {
        this.activeRate = activeRate;
    }
    public String getGoodFindNum() {
        return goodFindNum;
    }
    public void setGoodFindNum(String goodFindNum) {
        this.goodFindNum = goodFindNum;
    }
    public String getAppointNum() {
        return appointNum;
    }
    public void setAppointNum(String appointNum) {
        this.appointNum = appointNum;
    }
    public String getPhoneCallNum() {
        return phoneCallNum;
    }
    public void setPhoneCallNum(String phoneCallNum) {
        this.phoneCallNum = phoneCallNum;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public List<DriverSurviveRateDomain> getDataList() {    	return dataList;    }    public void setDataList(List<DriverSurviveRateDomain> dataList) {    	this.dataList = dataList;    }}