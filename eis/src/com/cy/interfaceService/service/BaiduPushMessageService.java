package com.cy.interfaceService.service;

import com.cy.interfaceService.bo.PushLogInfo;
import com.cy.interfaceService.domain.JsonResponse;

/**
 * Created by haoy on 2014/12/16.
 */
public interface BaiduPushMessageService {

    /**
     * 单播消息推送 (消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
     * 必须指定手 机端的ChannelId， 手机端的UserId
     * @param pushLogInfo
     * @return
     */
    public JsonResponse pushUnicastMessage(PushLogInfo pushLogInfo) throws Exception;

    /**
     * 广播消息推送(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
     * @param pushLogInfo
     * @return
     */
    public JsonResponse pushBroadcastMessage(PushLogInfo pushLogInfo) throws Exception;
}
