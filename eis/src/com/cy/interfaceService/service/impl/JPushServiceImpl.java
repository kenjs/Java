package com.cy.interfaceService.service.impl;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.cy.interfaceService.bo.PushLogInfo;
import com.cy.interfaceService.dao.BaiduPushDao;
import com.cy.interfaceService.domain.DriverUserInfo;
import com.cy.interfaceService.service.JPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.NotificationEmitter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haoy on 2015/1/5.
 */
@Service("jPushService")
public class JPushServiceImpl implements JPushService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Resource
    private BaiduPushDao baiduPushDao;

    private String appKey;
    private String masterSecret;

    @Value("#{propertiesReader['jpush.appKey']}")
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Value("#{propertiesReader['jpush.masterSecret']}")
    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    @Override
    public int sendPush(PushLogInfo logInfo) throws Exception {
        logInfo.setReturnedValue("0");
        logInfo.setTagName("");

        baiduPushDao.addPushLogInfo(logInfo);

        int id = logInfo.getId();

        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);

        PushPayload payload;
        PushResult result;
        Map<String, String> map;
        int type = logInfo.getType();

        if (type == 0) {
            throw new IllegalArgumentException("请选择推送方式");
        }

        switch (type) {
            case 1:
                logInfo.setTagName("all");
                payload = buildPushObject_android_all_alertWithTitle(logInfo.getPushTitle(), logInfo.getPushContent());
                break;
            case 2:
                logInfo.setTagName(logInfo.getTag());
                payload = buildPushObject_android_tag_alertWithTitle(logInfo.getPushTitle(), logInfo.getPushContent(), logInfo.getTag());
                break;
            case 3:
                DriverUserInfo info = baiduPushDao.queryDriverUserInfoById(logInfo.getDriverId());
                logInfo.setTagName("one");
                logInfo.setDriverId(logInfo.getDriverId());

                map = new HashMap<String, String>();
                map.put("logId", id + "");
                map.put("pushType", logInfo.getJumpType());
                map.put("id", logInfo.getTarId());
                map.put("companyName", logInfo.getCompanyName());
                map.put("webUserId", logInfo.getWebUserId());

                payload = buildPushObject_android_rid_alertWithTitle(logInfo.getPushTitle(), logInfo.getPushContent(), info.getRegistrationId(), map);
                break;
            case 4:
                logInfo.setTagName("one");
                logInfo.setDriverId(logInfo.getDriverId());

                map = new HashMap<String, String>();
                map.put("logId", id + "");
                map.put("pushType", logInfo.getJumpType());
                map.put("id", logInfo.getTarId());
                map.put("companyName", logInfo.getCompanyName());
                map.put("webUserId", logInfo.getWebUserId());

                payload = buildPushObject_android_alias_alertWithTitle(logInfo.getPushTitle(), logInfo.getPushContent(), logInfo.getDriverId(), map);
                break;
            default:
                payload = buildPushObject_android_all_alertWithTitle(logInfo.getPushTitle(), logInfo.getPushContent());
                break;
        }

        try {
            result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

            logInfo.setReturnedValue(result.toString());
            baiduPushDao.updatePushLogInfoById(logInfo);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
        return id;
    }

    private PushPayload buildPushObject_android_all_alertWithTitle(String title, String msg) {
        return PushPayload
                .newBuilder()
                .setNotification(Notification
                        .newBuilder()
                        .addPlatformNotification(AndroidNotification
                                .newBuilder()
                                .setTitle(title)
                                .setAlert(msg)
                                .setBuilderId(1)
                                .build())
                        .build())
                .setPlatform(Platform
                        .android())
                .setAudience(Audience
                        .all())
                .build();
    }

    private PushPayload buildPushObject_android_tag_alertWithTitle(String title, String msg, String tag) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(
                        Notification.newBuilder()
                        .addPlatformNotification(
                                AndroidNotification
                                        .newBuilder()
                                        .setTitle(title)
                                        .setAlert(msg)
                                        .setBuilderId(1)
                                        .build())
                        .build())
                .build();
    }

    private PushPayload buildPushObject_android_rid_alertWithTitle(String title, String msg, String alias, Map<String, String> map) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform
                        .android())
                .setAudience(Audience
                        .registrationId(alias))
                .setNotification(Notification
                        .newBuilder()
                        .setAlert(msg)
                        .addPlatformNotification(AndroidNotification
                                .newBuilder()
                                .setTitle(title)
                                .addExtras(map)
                                .setBuilderId(1)
                                .build())
                        .build())
                .build();
    }

    private PushPayload buildPushObject_android_alias_alertWithTitle(String title, String msg, String alias, Map<String, String> map) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform
                        .android())
                .setAudience(Audience
                        .alias(alias))
                .setNotification(Notification
                        .newBuilder()
                        .setAlert(msg)
                        .addPlatformNotification(AndroidNotification
                                .newBuilder()
                                .setTitle(title)
                                .addExtras(map)
                                .setBuilderId(1)
                                .build())
                        .build())
                .build();
    }

    private PushPayload buildPushObject_android_and_ios() {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform
                        .android())
                .setAudience(Audience
                        .tag("快到网"))
                .setNotification(Notification
                        .newBuilder()
                        .setAlert("alert content")
                        .addPlatformNotification(AndroidNotification
                                .newBuilder()
                                .setTitle("快到网")
                                .build())
                        .addPlatformNotification(IosNotification
                                .newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value")
                                .build())
                        .build())
                .build();
    }
}
