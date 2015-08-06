package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.dao.CommonDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by haoy on 2014/10/31.
 */
@Scope("prototype")
@Controller("nextStepAction")
public class NextStepAction extends AuthenticationAction{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private CommonDao commonDao;

    @RequestMapping(value = "/nextStepAction")
    @ResponseBody
    public JSonResponse next(String driverId,String verifyCode, String code) {
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            if (StringUtils.isBlank(verifyCode)) {
                return JSonResponse.makeHasContentJSonRespone("-8", "请输入验证码");
            }

            String chkCode = commonDao.queryCodeForVerify(code);
            if (StringUtils.isBlank(chkCode)) {
                return JSonResponse.makeHasContentJSonRespone("0","验证码失效，请重新获取。");
            }

            if (! chkCode.equals(verifyCode)) {
                return JSonResponse.makeHasContentJSonRespone("0","验证码输入错误");
            }

            return JSonResponse.makeHasContentJSonRespone("1","验证同过");
        } catch (Exception e) {
            log.error("验证失败-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "验证失败");
        }
    }
}
