package com.cy.dctms.common.domain;

import java.util.List;

/**
*��ѯ�����
* wjl
*/
public class DriverSurviveRateDomain extends BaseDomain{
private static final long serialVersionUID = 1L;
    private String id;  // ID
    private String queryTime;  // ����
    private String registerCount;  // ��ע������
    private String foreRegisterCount;  // ǰע������
    private String surviveCount;  // �����
    private String surviveRate;  // �����
    private String activeCount;  // ��Ծ��
    private String activeRate;  // ��Ծ��
    private String goodFindNum;  // �һ�����
    private String appointNum;  // ԤԼ����
    private String phoneCallNum;  // �绰��������
    private String createTime;  // ����ʱ��
    //����
    private List<DriverSurviveRateDomain> dataList;
    private String queryTimeQ;//������
    private String queryTimeZ;//����ֹ
    private String registerTotalCount;  // ��ע��������
    private String goodFindTotalNum;  // �һ��ܴ���
    private String appointTotalNum;  // ԤԼ������
    private String phoneCallTotalNum;  // �绰����������
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