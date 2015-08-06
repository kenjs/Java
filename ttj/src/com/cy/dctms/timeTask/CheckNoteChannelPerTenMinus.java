package com.cy.dctms.timeTask;

import com.cy.dctms.service.NoteCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 每十分钟检查发送验证码的用户是否
 * 注册成为用户
 * Created by haoy on 2014/11/3.
 */
public class CheckNoteChannelPerTenMinus {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private NoteCheckService noteCheckService;

    public void execute() throws Exception {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始执行.....");
            }
            noteCheckService.checkNote();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
