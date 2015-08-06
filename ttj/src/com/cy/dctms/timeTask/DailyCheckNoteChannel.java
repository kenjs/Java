package com.cy.dctms.timeTask;

import com.cy.dctms.common.bo.UserEnum;
import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.dao.CommonDao;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haoy on 2014/10/17.
 */
public class DailyCheckNoteChannel {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String noteUrl;

    @Resource
    private CommonDao commonDao;

    /**
     * 检查短信是否发生成功
     * @throws Exception
     */
    public void checkNoteChannel() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code","noteChannel");
        map.put("key","autoCode");
        map.put("paramType","0");
        String type = commonDao.getSystemParameter(map);

        String content = "";

        if (StringUtils.isEmpty(type.trim())) {
            log.error("短信通道未成功获取...");
            return;
        }

        if ("wd".equals(type)) {
            content = "感谢您注册快到网,验证码：000000（沃动）,如非本人操作请忽略,谢谢合作。【快到网】。";
        } else if ("zs".equals(type)) {
            content = "【快到网】感谢您注册,验证码：123456（舟山）,如非本人操作请忽略,谢谢合作。";
        } else if ("nb".equals(type)) {
            content = "【快到网】感谢您注册,验证码：456789（宁波）,如非本人操作请忽略,谢谢合作。";
        }


        String week = DateUtils.getWeekDay();

        String phone = "";
        for (UserEnum userEnum : UserEnum.values()) {
            if (userEnum.date().equalsIgnoreCase(week)) {
                phone = userEnum.phoneNum();
                break;
            }
        }

        String msg = requestNote(type,phone,content);

        if (log.isDebugEnabled()) {
            log.debug("短信测试返回结果：" + msg);
        }
    }

    private String requestNote(String channel, String phone, String content) throws Exception {
        String msg = "";

        HttpClient httpClient = new HttpClient();

        PostMethod postMethod = new PostMethod(noteUrl);

        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        postMethod.addParameter("channelType",channel);
        postMethod.addParameter("phoneNum",phone);
        postMethod.addParameter("content",content);
        postMethod.addParameter("requestIp","192.168.10.5");

        try {
            httpClient.executeMethod(postMethod);

            byte[] bytes = postMethod.getResponseBody();

            msg = new String(bytes);

            if (log.isDebugEnabled()) {
                log.debug("短信测试返回结果：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }

        return msg;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    @Value("#{propertiesReader['note.request.url']}")
    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }
}
