package com.cy.driver.action;

import com.cy.driver.bo.DriverTelephoneInfo;
import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.DriverUserInfoDomain;
import com.cy.driver.service.DriverTelephoneInfoService;
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
 * APP用户登陆
 * @date 2014-5-23
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("loginUserInfoAction")
public class LoginUserInfoAction extends WebBaseAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private LoginUserInfoService loginUserInfoService;
    @Resource
    private DriverTelephoneInfoService driverTelephoneInfoService;

    @RequestMapping(value = "/loginUserInfoAction")
    @ResponseBody
    @Log(type = 2)
	public JSonResponse execute(String code, String mobileBrand, String operatingSystemVersionNnumber,
                               String mobilePhoneModel, String noIimei,String info,String resolution,
							   String currentVersion) throws Exception {
		try {
			//登陆参数{code：用户名,pwd: 密码}

			if(StringUtils.isBlank(code)){
                if (log.isInfoEnabled()) {
                    log.info("登陆没有输入登陆账号.");
                }
                return JSonResponse.makeHasContentJSonRespone("-8", "登陆账户为空");
			}
			
			DriverUserInfoDomain domain = loginUserInfoService.checkLogin(code);
			if(domain == null){
                if (log.isInfoEnabled()) {
                    log.info("登陆失败, 请检查账号");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "请检查账号");
			}

			if ("1".equals(domain.getDeleteFlag())) {
				return JSonResponse.makeHasContentJSonRespone("-9", "该用户不存在或已被删除");
			}
			if ("1".equals(domain.getFreezeFlag())) {
				return JSonResponse.makeHasContentJSonRespone("-7", "您已被冻结，请先联系管理员解冻！");
			}

            if (! domain.getNoIimei().equals(noIimei)) {
                if (log.isDebugEnabled()) {
                    log.debug("用户换手机登陆。");
                }
                domain.setMobileChanged("y");
                return JSonResponse.makeHasContentJSonRespone("1","用户换手机登陆",domain);
            }


			if(StringUtils.isNotBlank(mobileBrand) || StringUtils.isNotBlank(mobilePhoneModel)
					|| StringUtils.isNotBlank(operatingSystemVersionNnumber) || StringUtils.isNotBlank(noIimei)
					|| StringUtils.isNotBlank(info) || StringUtils.isNotBlank(resolution)) {
				
				DriverTelephoneInfo driverTele = new DriverTelephoneInfo();
				driverTele.setDriverId(domain.getId());
				driverTele.setNoImei(noIimei);
				driverTele.setMobilePhoneModel(mobilePhoneModel);
				driverTele.setOperatingSystemVersionNumber(operatingSystemVersionNnumber);
				driverTele.setMobileBrand(mobileBrand);
				driverTele.setSoftwareList(info);
				driverTele.setResolution(resolution);
				
				driverTelephoneInfoService.saveUserMobilePhoneInfo(driverTele);
			}

			if (StringUtils.isNotBlank(currentVersion)) {
				DriverUserInfoBo bo = new DriverUserInfoBo();
				bo.setId(domain.getId());
				bo.setAppVersion(currentVersion);
				loginUserInfoService.updateDriverAppVersion(bo);
			}


			if (log.isInfoEnabled()) {
                log.info("登陆成功.");
            }

            return JSonResponse.makeHasContentJSonRespone("1", "登录成功.", domain);
		} catch (Exception e) {
			log.error("登陆出错-" + e.getCause().getMessage());
			return JSonResponse.makeHasContentJSonRespone("-8", "登陆出错, 请重试。");
		}

	}

}
