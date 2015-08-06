package com.cy.interfaceService.service;

import com.cy.interfaceService.bo.PushLogInfo;

/**
 * Created by haoy on 2015/1/5.
 */
public interface JPushService {

    /**
     * 发送推送(type={1:all,2:tag,3:alias})
     * @param pushLogInfo
     * @throws
     * @return
     */
    public int sendPush(PushLogInfo pushLogInfo) throws Exception;
}
