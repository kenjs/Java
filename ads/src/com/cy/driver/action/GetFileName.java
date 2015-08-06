package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.DriverUserInfoDomain;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;

@Scope("prototype")
@Controller("getFileName")
public class GetFileName extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;

	@RequestMapping(value = "/getFileName")
    @ResponseBody
    @Log(type = 50)
	public JSonResponse execute(String driverId, String fileType) throws Exception{
		String filePath;
		String fileName;
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			if(StringUtils.isBlank(fileType)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "请确认要下载的图片类型");
			}
			DriverUserInfoDomain domain = driverUserCargoInfoService.selectUserBasicInfo(driverId);
			if(domain != null) {
				if("1".equals(fileType)) {
					if(domain.getIdentityLicenseNumFront() != null) {
						filePath = domain.getIdentityLicenseNumFront();
						fileName = getFileName(filePath);
						if(StringUtils.isNotBlank(fileName)) {
							return JSonResponse.makeHasContentJSonRespone("1", "司机身份证正面照片图片名称为" + fileName, fileName);
						} else {
							return JSonResponse.makeHasContentJSonRespone("0", "没有找到图片");
						}
					} else {
						return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("2".equals(fileType)) {
					if(domain.getIdentityLicenseNumContrary() != null) {
						filePath = domain.getIdentityLicenseNumContrary();
						fileName = getFileName(filePath);
						if(StringUtils.isNotBlank(fileName)) {
							return JSonResponse.makeHasContentJSonRespone("1", "司机身份证反面照片图片名称为" + fileName, fileName);
						} else {
							return JSonResponse.makeHasContentJSonRespone("0", "没有找到图片");
						}
					} else {
						return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("3".equals(fileType)) {
					if(domain.getDriversLicense() != null) {
						filePath = domain.getDriversLicense();
						fileName = getFileName(filePath);
						if(StringUtils.isNotBlank(fileName)) {
							return JSonResponse.makeHasContentJSonRespone("1", "驾驶证路径图片名称为" + fileName, fileName);
						} else {
							return JSonResponse.makeHasContentJSonRespone("0", "没有找到图片");
						}
					} else {
						return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("4".equals(fileType)) {
					if(domain.getDrivingLicense() != null) {
						filePath = domain.getDrivingLicense();
						fileName = getFileName(filePath);
						if(StringUtils.isNotBlank(fileName)) {
							return JSonResponse.makeHasContentJSonRespone("1", "行驶证路径图片名称为" + fileName, fileName);
						} else {
							return JSonResponse.makeHasContentJSonRespone("0", "没有找到图片");
						}
					} else {
						return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("5".equals(fileType)) {
					if(domain.getOperatingLicense() != null) {
						filePath = domain.getOperatingLicense();
						fileName = getFileName(filePath);
						if(StringUtils.isNotBlank(fileName)) {
							return JSonResponse.makeHasContentJSonRespone("1", "营运证路径图片名称为" + fileName, fileName);
						} else {
							return JSonResponse.makeHasContentJSonRespone("0", "没有找到图片");
						}
					} else {
						return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("6".equals(fileType)) {
					if(domain.getHeadPortrait() != null) {
						filePath = domain.getHeadPortrait();
						fileName = getFileName(filePath);
						if(StringUtils.isNotBlank(fileName)) {
							return JSonResponse.makeHasContentJSonRespone("1", "用户头像图片名称为" + fileName, fileName);
						} else {
							return JSonResponse.makeHasContentJSonRespone("0", "没有找到图片");
						}
					} else {
						return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				}
			} else {
				return JSonResponse.makeHasContentJSonRespone("0", "未找到该用户有关的信息");
			}
		} catch (Exception e) {
			log.error("GetFileName.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统异常，请重试。");
		}
		return JSonResponse.makeHasContentJSonRespone("-8", "");
	}

	private String getFileName(String filePath) {
		File file = new File(filePath);
		if(file.exists() && file.isFile()) {
			return file.getName();
		}
		return null;
	}

}
