package com.cy.interfaceService.service.impl;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.*;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.cy.interfaceService.bo.PushLogInfo;
import com.cy.interfaceService.dao.BaiduPushDao;
import com.cy.interfaceService.domain.DriverUserInfo;
import com.cy.interfaceService.domain.JsonResponse;
import com.cy.interfaceService.service.BaiduPushMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  百度云消息推送
 * 参数：device_type => 1: web 2: pc 3:android 4:ios 5:wp
 * 		message_type = 0 (默认为0) {0:消息,1:通知}
 * 		通知：{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}
 * 		apiKey： 应用标识，终端上的绑定和服务端推送消息时都要用到。
 * 		secretKey： 应用私钥，服务端推送消息时用到。
 * 		app id ： 应用ID，就是百度开发者中心的应用基本信息中的应用ID。客户端绑定调用返回值中可获得。
 * 		channel id ： 推送通道ID，通常指一个终端，如一台android系统手机。客户端绑定调用返回值中可获得。
 * 		user id ： 应用的用户ID，一个应用在多个端，可以都属于同一用户（即对应一个userid）。
 * 					user id和channel id配合可以唯一指定一个应用的特定终端。如果应用不是基于百度账户的账户体系，
 * 					单独用user就通常指定了一个应用的特定终端。客户端绑定调用返回值中可获得。
 * Created by haoy on 2014/12/16.
 */
@Service("baiduPushMessageService")
public class BaiduPushMessageServiceImpl implements BaiduPushMessageService {

    private Logger log = LoggerFactory.getLogger(getClass());

    // 1. 设置developer平台的ApiKey/SecretKey
    private String apiKey;
    private String secretKey;

    @Resource
    private BaiduPushDao baiduPushDao;


    /**
     * 单播消息推送 (消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
     * 必须指定手 机端的ChannelId， 手机端的UserId
     * @param pushLogInfo
     * @return
     */
    @Override
    public JsonResponse pushUnicastMessage(PushLogInfo pushLogInfo) throws Exception {
        if (StringUtils.isBlank(pushLogInfo.getDriverId())
                || StringUtils.isBlank(pushLogInfo.getPushTitle())
                || StringUtils.isBlank(pushLogInfo.getPushContent())) {
            return JsonResponse.sendJsonResponse("0", "参数错误，请检查");
        }

        String status = "0";
        String content = "推送成功";

        pushLogInfo.setTagName("one");

        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);
        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                log.info(event.getMessage());
            }
        });

        DriverUserInfo driverUserInfo = baiduPushDao.queryDriverUserInfoById(pushLogInfo.getDriverId());

        long channelId = 0;
        String userId = "";
        if (driverUserInfo != null) {
            String  baiduChannelId = driverUserInfo.getBaiduChannelId();
            if (baiduChannelId != null && baiduChannelId.trim().length() > 0) {
                channelId = Long.parseLong(baiduChannelId);
            }
            userId = driverUserInfo.getBaiduUserId();
            pushLogInfo.setBaiduChannelId(baiduChannelId);
            pushLogInfo.setBaiduUserId(userId);
        }

        pushLogInfo.setReturnedValue("0");

        baiduPushDao.addPushLogInfo(pushLogInfo);

        int logId = pushLogInfo.getId();

        String customContent = "{\"id\":\"" + pushLogInfo.getTarId() +
                "\",\"pushType\":\"" + pushLogInfo.getJumpType() +
                "\",\"companyName\":\"" + pushLogInfo.getCompanyName() +
                "\",\"logId\":\"" + logId + "\",\"webUserId\":" + pushLogInfo.getWebUserId() + "}";


        String message = "{\"title\":\"" + pushLogInfo.getPushTitle()
                +"\"," + "\"description\":\"" + pushLogInfo.getPushContent() +"\","
                + "\"custom_content\":"+ customContent +"}";

        try {
            // 4. 创建请求类对象
            // 手机端的ChannelId， 手机端的UserId
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(3);// device_type => 1: web 2: pc 3:android
            // 4:ios 5:wp
            request.setChannelId(channelId);
            request.setUserId(userId);

            request.setMessageType(1);//message_type = 0 (默认为0) {0:消息,1:通知}
            request.setMessage(message);

            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

            status = response.getSuccessAmount() + "";
            log.info("push amount:" + response.getSuccessAmount());

            pushLogInfo = new PushLogInfo();
            pushLogInfo.setId(logId);
            pushLogInfo.setReturnedValue(status);

            baiduPushDao.updatePushLogInfoById(pushLogInfo);
        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            log.error(e.getMessage());
            content = e.getMessage();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            content = e.getMessage();
            log.error(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
        return JsonResponse.sendJsonResponse(status, content, logId);
    }

    /**
     * 广播消息推送(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
     * @param pushLogInfo
     * @return
     */
    @Override
    public JsonResponse pushBroadcastMessage(PushLogInfo pushLogInfo) throws Exception {
        if (StringUtils.isBlank(pushLogInfo.getPushTitle())
                || StringUtils.isBlank(pushLogInfo.getPushContent())) {
            return JsonResponse.sendJsonResponse("0", "参数错误，请检查");
        }

        String status = "0";
        String content = "推送成功";

        pushLogInfo.setTagName("all");

        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);;
        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                log.info(event.getMessage());
            }
        });

        pushLogInfo.setReturnedValue("0");

        baiduPushDao.addPushLogInfo(pushLogInfo);

        int logId = pushLogInfo.getId();

        String message = "{\"title\":\"" + pushLogInfo.getPushTitle() +"\"," + "\"description\":\"" + pushLogInfo.getPushContent() +"\"}";


        try {

            // 4. 创建请求类对象
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android
            // 4:ios 5:wp

            request.setMessageType(1);
            request.setMessage(message);

            // 5. 调用pushMessage接口
            PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);

            // 6. 认证推送成功
            status = response.getSuccessAmount() + "";
            log.info("push amount:" + response.getSuccessAmount());

            pushLogInfo = new PushLogInfo();
            pushLogInfo.setId(logId);
            pushLogInfo.setReturnedValue(status);

            baiduPushDao.updatePushLogInfoById(pushLogInfo);
        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            content = e.getMessage();
            log.error(e.getMessage());
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            content = e.getMessage();
            log.error(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
        return JsonResponse.sendJsonResponse(status, content, logId);
    }

    @Value("#{propertiesReader['baiduyun.apiKey']}")
    public void setBdApiKey(String bdApiKey) {
        apiKey = bdApiKey;
    }

    @Value("#{propertiesReader['baiduyun.secretKey']}")
    public void setBdSecretKey (String bdSecretKey) {
        secretKey = bdSecretKey;
    }
}
