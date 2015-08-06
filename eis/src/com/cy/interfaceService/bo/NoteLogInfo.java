package com.cy.interfaceService.bo;

import java.io.Serializable;

/**
 * Created by haoy on 2014/10/16.
 */
public class NoteLogInfo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
    private String phoneNum;        //手机号码
    private int sendStart;          //发送状态
    private String returnedValue;   //短信返回值
    private String requestIp;       //访问Ip
    private String  sendTime;       //发送时间
    private String createTime;      //创建时间

    private String kind;               //短信性质：1 验证码短信 2 系统通知 3  短信营销 4  电话营销 5  车配匹配
    private String channelType;     //短信通道

    private String content;         //短信内容
    
    private String companyId;	    //短信发送结算公司ID
    private int eventFrom;          //1 营销平台 2 Android Server 3 Web网站 4 经管系统

    private String sendOutType;    //短信发送对象（0企业，1司机）

    public NoteLogInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getSendStart() {
        return sendStart;
    }

    public void setSendStart(int sendStart) {
        this.sendStart = sendStart;
    }

    public String getReturnedValue() {
        return returnedValue;
    }

    public void setReturnedValue(String returnedValue) {
        this.returnedValue = returnedValue;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(int eventFrom) {
        this.eventFrom = eventFrom;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSendOutType() {
        return sendOutType;
    }

    public void setSendOutType(String sendOutType) {
        this.sendOutType = sendOutType;
    }
}
