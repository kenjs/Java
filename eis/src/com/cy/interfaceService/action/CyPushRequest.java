package com.cy.interfaceService.action;

import com.cy.interfaceService.bo.PushLogInfo;
import com.cy.interfaceService.dao.BaiduPushDao;
import com.cy.interfaceService.domain.DriverUserInfo;
import com.cy.interfaceService.domain.JsonResponse;
import com.cy.interfaceService.service.BaiduPushMessageService;
import com.cy.interfaceService.service.JPushService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HY on 2015/1/8.
 */
@Scope("prototype")
@Controller("cyPushRequest")
public class CyPushRequest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private BaiduPushDao baiduPushDao;

    @Resource
    private JPushService jPushService;

    @Resource
    private BaiduPushMessageService baiduPushMessageService;

    @RequestMapping(value = "/cySendPush")
    @ResponseBody
    public JsonResponse sendPush(PushLogInfo pushLogInfo) {
        JsonResponse response = null;
        try {
            int type = pushLogInfo.getPushChannel();
            DriverUserInfo driverUserInfo;
            if (type == 1) {
                driverUserInfo = baiduPushDao.queryDriverUserInfoById(pushLogInfo.getDriverId());
                if (driverUserInfo != null) {
                    String appVersion = driverUserInfo.getAppVersion();

                    if (StringUtils.isNoneBlank(appVersion) && appVersion.contains(".")) {
                        String[] indexs = appVersion.split("\\.");
                        if (indexs.length >= 3) {
                            boolean flag = false;
                            if (Integer.parseInt(indexs[0]) < 2) {
                                flag = true;
                            } else if (Integer.parseInt(indexs[1]) < 2) {
                                flag = true;
                            } else if (Integer.parseInt(indexs[2]) < 5) {
                                flag = true;
                            }
                            if (flag) {
                                type = 2;
                                if (pushLogInfo.getType() == 3 || pushLogInfo.getType() == 4) {
                                    pushLogInfo.setType(1);
                                } else {
                                    pushLogInfo.setType(2);
                                }
                            }
                        }
                    }
                }
            }

            if (type == 1) {
                int key = jPushService.sendPush(pushLogInfo);
                return JsonResponse.sendJsonResponse("1", "推送成功", key);
            } else if (type == 2){
                if (pushLogInfo.getType() == 1) {
                    response = baiduPushMessageService.pushUnicastMessage(pushLogInfo);
                } else if (pushLogInfo.getType() == 2) {
                    response = baiduPushMessageService.pushBroadcastMessage(pushLogInfo);
                }
                return response;
            }
            return JsonResponse.sendJsonResponse("0", "推送失败");
        } catch (Exception e) {
            log.error("推送失败-" + e.getMessage());
            return JsonResponse.sendJsonResponse("0", "推送失败，错误信息：" + e.getMessage());
        }

    }
}
