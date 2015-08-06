package com.cy.driver.action;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.common.countevent.Count;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.ValidateUtil;
import com.cy.driver.dao.CommonDao;
import com.cy.driver.service.LoginUserInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("register")
public class Register extends WebBaseAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private LoginUserInfoService loginUserInfoService;

    @RequestMapping(value = "/register")
    @ResponseBody
    @Log(type = 1)
    @Count(tableNames = "t_count_system_busi", columns = "total_reg_drivers")
	public JSonResponse execute(DriverUserInfoBo bo, String checkCode) throws Exception{
		String code = "0", msg = "注册失败";
		try {
			int i = loginUserInfoService.register(bo, checkCode);

			switch (i) {
                case -1:
                    code = "-8";
                    msg = "请输入验证码";
                    break;
                case -2:
                    msg = "验证码已失效，请重新获取。";
                    break;
                case -3:
                    msg = "验证码输入错误";
                    break;
                case -8:
                    code = "-8";
                    msg = "请输入正确的手机号码";
                    break;
                case 0:
                    msg = "该手机号码已注册";
                    break;
                default: if (i > 0) {
                    code = "1";
                    msg = "注册成功";
                    break;
                }
            }
            return JSonResponse.makeHasContentJSonRespone(code, msg, i);
		} catch (Exception e) {
			log.error("注册时发生错误-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "注册时发生错误，请稍后重试。");
		}
	}

}
