package com.cy.dctms.common.bo;

import java.util.Date;
import java.util.List;
public class SendMessagerLog {
    private Long id;  // ID
    private String sendNumber;  // ���ͺ���
    private String content;  // ��������
    private String title;  // ����
    private Integer sendState;  // ����״̬(0�ɹ���1ʧ��)
    private String resultValue;  // ���ͷ���ֵ
    private Date sendTime;  // ����ʱ��
    private Integer sendType;  // ��������(0��˳ɹ���1���ʧ�ܡ�2Ⱥ����3�Զ���)
    private Long managerId;  // ����ԱID
    private Integer origin;  // ��Դ(0˾����1������ҵ)
    private Integer channelType;  // ͨ�����(0���ţ�1�ʼ���2����)
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