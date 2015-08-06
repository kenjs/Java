package com.cy.interfaceService.service;

import com.cy.interfaceService.bo.NoteLogInfo;
import com.cy.interfaceService.domain.NoteInfo;

import java.util.List;

/**
 * Created by haoy on 2014/10/16.
 */
public interface NoteSendService {

    /**
     * 短信发送
     * @param noteLogInfo
     * @return
     * @throws Exception
     */
    public NoteInfo sendNote(NoteLogInfo noteLogInfo) throws Exception;

    /**
     * 自定义发送
     * JSON格式如下 ' [{"channelType":"zs","phone":"2","content":"t1","companyId":"1"},
     *                  {"channelType":"zs","phone":"1","content":"t2","companyId":"1"}] '
     * @param requestIp     请求ip地址
     * @param json
     * @return
     * @throws Exception
     */
    public List sendNote(String requestIp, String json) throws Exception;
}
