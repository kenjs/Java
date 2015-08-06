package com.cy.driver.action;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
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
@Controller("updateDriverUserInfoAction")
public class UpdateDriverUserInfoAction extends AuthenticationAction{

	private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;

    @RequestMapping(value = "/updateDriverUserInfoAction")
    @ResponseBody
    @Log(type = 16)
	public JSonResponse execute(String driverId, DriverUserInfoBo bo) throws Exception{
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			StringBuilder sb = new StringBuilder();

            bo.setId(Integer.parseInt(driverId));
			if(StringUtils.isBlank(bo.getDriverName())){
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入司机姓名.");
			}

			if(StringUtils.isBlank(bo.getCarNumber())){
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入车辆牌照号码.");
			}

			if(StringUtils.isBlank(bo.getCarLength())){
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入车长.");
			} else {
				sb.append(bo.getCarLength()).append(" ");
			}

			if(StringUtils.isNotBlank(bo.getCarPlateType())){
				sb.append(bo.getCarPlateType()).append(" ");
			}
			if(StringUtils.isNotBlank(bo.getCarBarType())){
				sb.append(bo.getCarBarType()).append(" ");
			}
			if(StringUtils.isNotBlank(bo.getCarWeight())){
				sb.append(bo.getCarWeight()).append(" ");
			}
			if(StringUtils.isNotBlank(bo.getCarCubage())){
				sb.append(bo.getCarCubage());
			}
			bo.setCarTypes(sb.toString());
            bo.setName(bo.getDriverName());

			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1){
                if (logger.isInfoEnabled()) {
                    logger.info("修改信息成功");
                }
                return JSonResponse.makeHasContentJSonRespone("1", "修改信息成功.");
			} else {
                if (logger.isInfoEnabled()) {
                    logger.info("修改信息失败.");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "修改信息失败.");
			}
		} catch (Exception e) {
			logger.error("修改司机信息出错-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "出错了，请稍后重试。");
		}
	}

}
