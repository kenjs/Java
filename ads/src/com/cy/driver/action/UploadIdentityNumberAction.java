package com.cy.driver.action;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.ValidateUtil;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 上传身份证
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("uploadIdentityNumberAction")
public class UploadIdentityNumberAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private DriverUserCargoInfoService driverUserCargoInfoService;

    @RequestMapping(value = "/uploadIdentityNumber")
    @ResponseBody
    @Log(type = 58)
	public JSonResponse exec(String driverId, String identityLicenseNum) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			String id = driverId,
					identityNumber = identityLicenseNum;

			if(StringUtils.isBlank(identityNumber)){
				//sendResponseToJson("-8", "用户身份证号码没有输入");
                if (log.isWarnEnabled()) {
                    log.warn("用户身份证号码没有输入");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "用户身份证号码没有输入");
			}
			if(! ValidateUtil.validateIdentityLicenseNum(identityNumber)) {
				//sendResponseToJson("-8", "身份证号码格式不正确");
                if (log.isWarnEnabled()) {
                    log.warn("身份证号码格式不正确");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "身份证号码格式不正确");
			}
			DriverUserInfoBo bo = new DriverUserInfoBo();
			bo.setId(Integer.parseInt(id));
			bo.setIdentityLicenseNum(identityNumber);
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1){
				//sendResponseToJson("1", "用户身份证修改成功.");
                if (log.isInfoEnabled()) {
                    log.info("修改信息成功");
                }
                return JSonResponse.makeHasContentJSonRespone("1", "用户身份证修改成功.");
			} else {
				//sendResponseToJson("0", "用户身份证修改失败.");
                if (log.isInfoEnabled()) {
                    log.info("修改信息失败.");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "用户身份证修改失败.");
			}
		} catch (Exception e) {
            log.error("UploadIdentityNumberAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统出错, 请稍后重试。");
		}
	}

}
