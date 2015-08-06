package com.cy.driver.action;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.dao.CommonDao;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Scope("prototype")
@Controller("updateOldUserInfoAction")
public class UpdateOldUserInfoAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private DriverUserCargoInfoService driverUserCargoInfoService;
    @Resource
    private CommonDao commonDao;


	@RequestMapping(value = "/updateOldUserInfo")
    @ResponseBody
    @Log(type = 61)
	public JSonResponse execute(String driverId, String newCode,String verifyCode) throws Exception{
        if (StringUtils.isBlank(driverId)) {
            return JSonResponse.makeHasContentJSonRespone("-8", "司机ID不能为空");
        }
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			DriverUserInfoBo bo = new DriverUserInfoBo();

            if (StringUtils.isBlank(verifyCode)) {
                return JSonResponse.makeHasContentJSonRespone("-8", "请输入验证码");
            }

            String chkCode = commonDao.queryCodeForVerify(newCode);
            if (StringUtils.isBlank(chkCode)) {
                return JSonResponse.makeHasContentJSonRespone("0","验证码失效，请重新获取。");
            }

            if (! chkCode.equals(verifyCode)) {
                return JSonResponse.makeHasContentJSonRespone("0","验证码输入错误");
            }

            bo.setId(Integer.parseInt(driverId));
			bo.setCode(newCode);
			bo.setNewOrOldAppUser("0");
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1){
                if (log.isInfoEnabled()) {
                    log.info("修改信息成功");
                }

                return JSonResponse.makeHasContentJSonRespone("1", "修改信息成功.");
			} else {
                if (log.isInfoEnabled()) {
                    log.info("修改信息失败.");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "修改信息失败.");
			}
		} catch (Exception e) {
			log.error("老用户验证出错" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "出错了，请重试。");
		}

	}

}
