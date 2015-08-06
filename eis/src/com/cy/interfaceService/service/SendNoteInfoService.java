package com.cy.interfaceService.service;

import com.cy.interfaceService.bo.NoteLogInfo;
import com.cy.interfaceService.domain.NoteInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by nixianjing on 15/1/28.
 */
public interface SendNoteInfoService {

    /**
     * 短信发送
     * @param noteLogInfo
     * @return
     * @throws Exception
     */
    public Map<String,Object> sendNote(NoteLogInfo noteLogInfo) throws Exception;

}
