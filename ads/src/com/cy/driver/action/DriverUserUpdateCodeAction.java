package com.cy.driver.action;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.ValidateUtil;
import com.cy.driver.dao.CommonDao;
import com.cy.driver.service.DriverUserCargoInfoService;
import com.cy.driver.service.LoginUserInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 修改手机号
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("driverUserUpdateCodeAction")
public class DriverUserUpdateCodeAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;
    @Resource
	private LoginUserInfoService loginUserInfoService;
    @Resource
    private CommonDao commonDao;

    @RequestMapping(value = "/driverUserUpdateCode")
    @ResponseBody
    @Log(type = 6)
	public JSonResponse execute(String driverId, String code,String verifyCode) throws Exception {
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

			if(StringUtils.isBlank(code)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入新号码");
			}
			if(! ValidateUtil.validateTelePhone(code)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "手机号码格式错误");
			}
			boolean resBool = loginUserInfoService.checkUserAccountExist(code);
			if(resBool) {
				return JSonResponse.makeHasContentJSonRespone("0", "此号码已注册过");
			}
			DriverUserInfoBo bo = new DriverUserInfoBo();
			bo.setId(Integer.parseInt(driverId));
			bo.setNewCode(code);
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 0) {
				return JSonResponse.makeHasContentJSonRespone("0", "修改手机号失败");
			}
			return JSonResponse.makeHasContentJSonRespone("1", "修改手机号成功");
		} catch (Exception e) {
			log.error("修改手机号码发生错误-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "修改手机号码发生错误, 请重试。");
		}

	}

}
