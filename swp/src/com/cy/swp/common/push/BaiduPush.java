package com.cy.swp.common.push;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.cy.swp.common.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaiduPush {

    private static final Logger log = LoggerFactory.getLogger(BaiduPush.class);
// -------------------------- STATIC METHODS --------------------------

    /**
     * 为android手机推送单播通知
     *
     * @param channelId通道Id
     * @param userId        用户Id
     *                      title 推送标题
     *                      description 描述信息
     * @author WJL
     */
    public static boolean pushUnicastNotification(Long channelId, String userId, String title, String description) {
        // 1. 设置developer平台的ApiKey/SecretKey
        ChannelKeyPair pair = new ChannelKeyPair(Constants.apiKey, Constants.secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            public void onHandle(YunLogEvent event) {
                log.info(event.getMessage());
            }
        });

        try {
            // 4. 创建请求类对象
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setMessageType(1);
            request.setDeviceType(3);
            request.setChannelId(channelId);
            request.setUserId(userId);
            request.setMessage("{\"title\":\"" + title + "\",\"description\":\"" + description + "\"}");
            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);
            if (response.getSuccessAmount() == 1) {
                return true;
            }
            return false;
        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            return false;
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            log.info(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            return false;
        }
    }

    /**
     * 为android手机推送广播播通知
     *
     * @author WJL
     * title 推送标题
     * description 描述信息
     */
    public static boolean pushBroadcastNotification(String title, String description) {
        // 1. 设置developer平台的ApiKey/SecretKey
        ChannelKeyPair pair = new ChannelKeyPair(Constants.apiKey, Constants.secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            public void onHandle(YunLogEvent event) {
                log.info(event.getMessage());
            }
        });

        try {
            // 4. 创建请求类对象
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            request.setMessageType(1);
            request.setDeviceType(3);
            request.setMessage("{\"title\":\"" + title + "\",\"description\":\"" + description + "\"}");

            // 5. 调用pushMessage接口
            PushBroadcastMessageResponse response = channelClient
                    .pushBroadcastMessage(request);
            if (response.getSuccessAmount() == 1) {
                return true;
            }
            return false;
        } catch (ChannelClientException e) {
            return false;
        } catch (ChannelServerException e) {
            return false;
        }
    }
}
