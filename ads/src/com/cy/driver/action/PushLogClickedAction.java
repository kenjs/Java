package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.PushLogInfo;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.dao.PushLogInfoDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by haoy on 2014/12/24.
 */
@Scope("prototype")
@Controller("pushLogClickedAction")
public class PushLogClickedAction extends WebBaseAction {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private PushLogInfoDao pushLogInfoDao;

    @RequestMapping(value = "/pushLogClicked")
    @ResponseBody
    @Log(type = 82)
    public JSonResponse execute(String pushLog) {
        try {
            if (log.isInfoEnabled()) {
                log.info("用户查看通知.....");
            }

            if (StringUtils.isBlank(pushLog)) {
                return JSonResponse.makeHasContentJSonRespone("-8", "参数不合法");
            }

            JSONArray jsonArray = JSONArray.fromObject(pushLog);

            int arraySize = jsonArray.size();

            if (arraySize <= 0) {
                return JSonResponse.makeHasContentJSonRespone("-8", "参数不合法");
            }

            JSONObject object;
            PushLogInfo pushLogInfo;

            for (int i = 0; i < arraySize; i++) {
                object = JSONObject.fromObject(jsonArray.getJSONObject(i));
                int id = object.getInt("id");
                String clickedTime = object.getString("clickedTime");

                pushLogInfo = new PushLogInfo();
                pushLogInfo.setId(id);
                pushLogInfo.setClickedTime(clickedTime);
                pushLogInfo.setIsClicked("1");
                pushLogInfoDao.updatePushLogInfoById(pushLogInfo);
            }
            return JSonResponse.makeHasContentJSonRespone("1", "操作成功");
        } catch (Exception e) {
            log.error("PushLogClickedAction.class" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统异常。");
        }
    }
}
