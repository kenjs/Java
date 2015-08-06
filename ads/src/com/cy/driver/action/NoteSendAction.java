package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.ValidateUtil;
import com.cy.driver.dao.CommonDao;
import com.cy.driver.service.DriverUserCargoInfoService;
import com.cy.driver.service.LoginUserInfoService;
import com.cy.driver.service.OperationLogService;
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
import java.util.Random;

/**
 * 发送短信
 * @since 2014-6-9
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("noteSendAction")
public class NoteSendAction extends AuthenticationAction{

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;
    @Resource
	private LoginUserInfoService loginUserInfoService;
    @Resource
	private OperationLogService operationLogService;
    @Resource
    private CommonDao commonDao;
	
	private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/noteSendAction")
    @ResponseBody
    @Log(type = 18)
    public JSonResponse execute(String mobilephone, String differenceFlag, String driverId,String purpose) throws Exception {
        try {
            String result;
            String message;

            int target = 0;
            if (StringUtils.isNotBlank(purpose)) {
                target = Integer.parseInt(purpose);
            }

            StringBuilder content = new StringBuilder();
            String code = getRandomStr(6);//随机生成6位验证码

            if(StringUtils.isBlank(mobilephone)){
                if (log.isInfoEnabled()) {
                    log.info("用户没有输入手机号码. ");
                }
                return JSonResponse.makeHasContentJSonRespone("-8", "请输入手机号码");
            }
            boolean match = ValidateUtil.validateTelePhone(mobilephone);//手机号码格式验证
            if(!match){
                if (log.isInfoEnabled()) {
                    log.info("手机号码格式不正确. ");
                }
                return JSonResponse.makeHasContentJSonRespone("-8", "手机号码格式不正确");
            }

            Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("code", mobilephone);
            map.put("purpose", target);
            String chkCode = commonDao.queryLatestCode(map);

            if(StringUtils.isNotBlank(differenceFlag)) {
                boolean exist;
                if("0".equals(differenceFlag)) {
                    if (StringUtils.isNotBlank(chkCode)) {
                        code = chkCode;
                    }
                    content.append("【快到网】");
                    content.append("感谢您注册，验证码：");
                    content.append(code);
                    content.append("（8小时有效），如非本人操作请忽略，谢谢合作。");
                    exist = loginUserInfoService.checkUserAccountExist(mobilephone);
                    if(exist) {
                        result = "0";
                        message = "账号已注册, 请重新输入. ";
                        return JSonResponse.makeHasContentJSonRespone(result, message);
                    }
                } else if("1".equals(differenceFlag)) {
                    exist = loginUserInfoService.checkUserAccountExist(mobilephone);
                    if(!exist) {
                        result = "0";
                        message = "该号码不存在, 请重新输入. ";
                        return JSonResponse.makeHasContentJSonRespone(result, message);
                    }
                }
            } else {
                authentication(driverId);
                if (!isOk) {
                    return jSonResponse;
                }

                if (StringUtils.isNotBlank(chkCode)) {
                    code = chkCode;
                }
                content.append("【快到网】");
                content.append("感谢您进行手机验证，验证码：");
                content.append(code);
                content.append("（8小时有效），如非本人操作请忽略，谢谢合作。");
            }

            if (log.isInfoEnabled()) {
                log.info("验证手机号码: " + mobilephone + "; 验证码: " + code);
            }
            int sdk = driverUserCargoInfoService.noteSend(request.getRemoteAddr() ,mobilephone, content.toString(),code,target);
            if(0 == sdk) {
                result = "1";
                message = "用户注册发送验证码成功";
            }else {
                result = "0";
                message = "用户注册发送验证码失败";
            }

            return JSonResponse.makeHasContentJSonRespone(result, message, code);
        } catch (Exception e) {
            log.error("验证码获取出错 - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "验证码获取出错，请稍后重试。");
        }
	}

	private String getRandomStr(int length) {
		if(length <= 0)
			return null;
		
		StringBuilder sb = new StringBuilder(length);
		int num ;
		Random random = new Random();
		for(int i = 0;i < length;i ++)
		{
			num = random.nextInt(10);
			sb.append(num);
		}
		return sb.toString();
	}

}
