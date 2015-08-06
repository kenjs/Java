package com.cy.dctms.common.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nixianjing on 14/12/22.
 */
public class PushSendRecord implements Serializable {

    private Integer id;//
    private Integer eventFrom;//0 不区分子系统,系统级 1 营销平台 2 快到网网站 3 app服务端 4 经管系统'
    private Integer driverId;//司机id
    private Integer pushId;//pk-t_push_log_info.id
    private String pushTitle;//标题
    private String pushContent;//推送内容
    private Integer targetType;//目标类型： 0 货源 1 订单
    private Integer targetId;//目标id
    private Integer returnStatus;//推送接口发送返回状态 0 成功 1 失败
    private Integer useFor;//用途
    private Date createTime;//创建时间

    public String getPushTitle() {
        return pushTitle;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(Integer eventFrom) {
        this.eventFrom = eventFrom;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getPushId() {
        return pushId;
    }

    public void setPushId(Integer pushId) {
        this.pushId = pushId;
    }

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Integer getUseFor() {
        return useFor;
    }

    public void setUseFor(Integer useFor) {
        this.useFor = useFor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
