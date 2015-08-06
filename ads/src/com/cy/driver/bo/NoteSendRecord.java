package com.cy.driver.bo;

import java.io.Serializable;

/**
 * Created by haoy on 2015/1/23.
 */
public class NoteSendRecord implements Serializable {
    private int id;//主键（自增长）
    private int eventFrom;//0 不区分子系统 1 营销平台 2 快到网网站 3 app服务端 4 经管系统  5 调度服务
    private int noteSendedId;//短信日志记录表主键(pk-t_note_log_info.id)
    private int type;//此短信接收方的类型（0 企业，1 司机）
    private String telephone;//短信接收者手机号码
    private String content;//短信内容
    private int returnStatus;//短信发送返回状态
    private String remark;//备注(被发送对象的号码保存到该字段)
    private int useFor;
    private String createTime;//创建时间

    public NoteSendRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(int eventFrom) {
        this.eventFrom = eventFrom;
    }

    public int getNoteSendedId() {
        return noteSendedId;
    }

    public void setNoteSendedId(int noteSendedId) {
        this.noteSendedId = noteSendedId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(int returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUseFor() {
        return useFor;
    }

    public void setUseFor(int useFor) {
        this.useFor = useFor;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
