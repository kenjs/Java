package com.cy.dctms.common.bo;

import java.util.Date;
import java.util.List;
public class SendMessagerLog {
    private Long id;  // ID
    private String sendNumber;  // 发送号码
    private String content;  // 发送内容
    private String title;  // 标题
    private Integer sendState;  // 发送状态(0成功、1失败)
    private String resultValue;  // 发送返回值
    private Date sendTime;  // 发送时间
    private Integer sendType;  // 发送类型(0审核成功、1审核失败、2群发、3自定义)
    private Long managerId;  // 管理员ID
    private Integer origin;  // 来源(0司机、1物流企业)
    private Integer channelType;  // 通道类别(0短信，1邮件，2推送)
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSendNumber() {
        return sendNumber;
    }
    public void setSendNumber(String sendNumber) {
        this.sendNumber = sendNumber;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getSendState() {
        return sendState;
    }
    public void setSendState(Integer sendState) {
        this.sendState = sendState;
    }
    public String getResultValue() {
        return resultValue;
    }
    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public Integer getSendType() {
        return sendType;
    }
    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }
    public Long getManagerId() {
        return managerId;
    }
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
    public Integer getOrigin() {
        return origin;
    }
    public void setOrigin(Integer origin) {
        this.origin = origin;
    }
    public Integer getChannelType() {
        return channelType;
    }
    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }
}