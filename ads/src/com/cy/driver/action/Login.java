package com.cy.driver.action;

import com.cy.driver.bo.DriverTelephoneInfo;
import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.dao.CommonDao;
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
 * Created by haoy on 2014/10/31.
 */
@Scope("prototype")
@Controller("login")
public class Login extends WebBaseAction{

    private Logger log  = LoggerFactory.getLogger(getClass());

    @Resource
    private LoginUserInfoService loginUserInfoService;
    @Resource
    private DriverTelephoneInfoService driverTelephoneInfoService;
    @Resource
    private CommonDao commonDao;

    @RequestMapping(value = "/login")
    @ResponseBody
    public JSonResponse login(String code, String verificationCode,String mobileBrand,
                              String operatingSystemVersionNumber,String mobilePhoneModel, 
                              String noImei,String info,String resolution, String currentVersion) {
        try {
            if (StringUtils.isBlank(code)) {
                return JSonResponse.makeHasContentJSonRespone("-8","请输入手机号码");
            }

            if (StringUtils.isBlank(verificationCode)) {
                return JSonResponse.makeHasContentJSonRespone("-8","请输入验证码");
            }

            //验证验证码是否正确
            String chkCode = commonDao.queryCodeForVerify(code);
            if (StringUtils.isBlank(chkCode)) {
                return JSonResponse.makeHasContentJSonRespone("0","验证码失效，请重新获取。");
            }

            if (! verificationCode.equals(chkCode)) {
                return JSonResponse.makeHasContentJSonRespone("0","验证码输入错误");
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

            if(StringUtils.isNotBlank(mobileBrand) || StringUtils.isNotBlank(mobilePhoneModel)
                    || StringUtils.isNotBlank(operatingSystemVersionNumber) || StringUtils.isNotBlank(noImei)
                    || StringUtils.isNotBlank(info) || StringUtils.isNotBlank(resolution)) {
                DriverTelephoneInfo driverTele = new DriverTelephoneInfo();
                driverTele.setDriverId(domain.getId());
                driverTele.setNoImei(noImei);
                driverTele.setMobilePhoneModel(mobilePhoneModel);
                driverTele.setOperatingSystemVersionNumber(operatingSystemVersionNumber);
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
