package com.cy.dctms.service.impl;

import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.common.util.HttpUtil;
import com.cy.dctms.dao.CommonDao;
import com.cy.dctms.service.NoteCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haoy on 2014/11/3.
 */
@Service("noteCheckService")
public class NoteCheckServiceImpl implements NoteCheckService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String noteUrl;

    @Resource
    private CommonDao commonDao;

    @Override
    public void checkNote() throws Exception {
        Object obj = commonDao.checkNotePerTenMinus();
        if (log.isDebugEnabled()) {
            log.debug("返回值："+ obj);
        }

        if (obj != null) {//发送短信

            log.warn("截止到当前时间的十分钟内, 有" + obj + "个手机号码请求发送注册验证码但没有注册成功!");

            String content = "";

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code","noteChannel");
            map.put("key","autoCode");
            map.put("paramType","0");
            String type = commonDao.getSystemParameter(map);

            if (log.isDebugEnabled()) {
                log.debug("当前使用的短信通道为" + type);
            }

            //换通道发送短信提醒当前使用的短信通道可能存在问题
            if ("nb".equalsIgnoreCase(type)) {
                content = "宁波";
                type = "zs";
            } else if ("zs".equalsIgnoreCase(type)) {
                content = "舟山";
                type = "nb";
            } else if ("wd".equalsIgnoreCase(type)) {
                content = "沃动";
                type = "nb";
            }

            content += "通道，在" + DateUtils.getCurrentDateTime() + "检验中发现10分钟有"
                    + obj + "条注册码发出未注册，请知晓！";
            content = content.replaceAll("\\s*", "");

            if (log.isDebugEnabled()) {
                log.debug(content);
            }

            map.put("code","noteChannel");
            map.put("key","phoneList");
            map.put("paramType","0");

            String phone = commonDao.getSystemParameter(map);

            Map<String, String > para = new HashMap<String, String>();
            para.put("channelType",type);
            para.put("phoneNum",phone);
            para.put("content",content);
            para.put("requestIp","localhost");

            String msg = HttpUtil.doPostRequest(noteUrl,para);
            if (log.isDebugEnabled()) {
                log.debug("发送短信返回结果：" + msg);
            }
        }
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    @Value("#{propertiesReader['note.request.url']}")
    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }
}
