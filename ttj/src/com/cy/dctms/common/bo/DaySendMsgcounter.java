package com.cy.dctms.common.bo;

import java.io.Serializable;

/**
 * Created by nixianjing on 15/1/7.
 */
public class DaySendMsgcounter implements Serializable {

    private Integer id;//主键
    private Integer targetId;//目标id(企业用户id 或司机id 或营销平台客户id)
    private Integer targetType;//目标类型：0 企业用户 1 已注册司机用户 2 未注册司机用户
    private Integer msgType;//消息类型：0 推送消息 1 短信消息
    private Integer countNums;//当日已发送条数

    public Integer getCountNums() {
        return countNums;
    }

    public void setCountNums(Integer countNums) {
        this.countNums = countNums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }
}
